# 📌 Topic 2: Advanced Java Concepts

## 🎯 Learning Objectives
Master advanced Java features that separate junior developers from senior ones - essential for modern Java development.

---

## 🔷 Part 1: Multithreading & Concurrency

### Concepts to Master:

- [ ] **Thread Basics**
  ```java
  // Method 1: Extend Thread class
  class MyThread extends Thread {
      @Override
      public void run() {
          for (int i = 0; i < 5; i++) {
              System.out.println(Thread.currentThread().getName() + ": " + i);
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      }
  }
  
  // Method 2: Implement Runnable (Better)
  class MyRunnable implements Runnable {
      @Override
      public void run() {
          System.out.println("Running in: " + Thread.currentThread().getName());
      }
  }
  
  // Usage
  Thread t1 = new MyThread();
  t1.start();  // NOT t1.run()!
  
  Thread t2 = new Thread(new MyRunnable());
  t2.start();
  
  // Method 3: Lambda (Modern)
  Thread t3 = new Thread(() -> {
      System.out.println("Lambda thread");
  });
  t3.start();
  ```
  
  **Real-life Example:** 🍳 **Cooking Multiple Dishes**
  - Main thread = You cooking
  - Child threads = Helper cooks
  - Each works on different dish simultaneously
  
  **Interview Tip:** "Threads allow concurrent execution, improving performance for I/O-bound and CPU-bound tasks"

- [ ] **Thread Lifecycle**
  ```
  NEW → RUNNABLE → RUNNING → BLOCKED/WAITING → TERMINATED
  
  NEW: Thread created but not started
  RUNNABLE: start() called, ready to run
  RUNNING: Thread executing
  BLOCKED: Waiting for lock
  WAITING: Waiting for another thread
  TERMINATED: Execution completed
  ```

- [ ] **Synchronization**
  ```java
  class Counter {
      private int count = 0;
      
      // Without synchronization (PROBLEM!)
      public void increment() {
          count++;  // Not atomic! Race condition
      }
      
      // With synchronization (SOLUTION)
      public synchronized void incrementSafe() {
          count++;  // Thread-safe
      }
      
      // Synchronized block (Better performance)
      public void incrementBlock() {
          synchronized(this) {
              count++;
          }
      }
      
      public int getCount() {
          return count;
      }
  }
  
  // Problem demonstration
  Counter counter = new Counter();
  
  // 1000 threads incrementing
  for (int i = 0; i < 1000; i++) {
      new Thread(() -> counter.increment()).start();
  }
  
  Thread.sleep(2000);
  System.out.println(counter.getCount());  // May not be 1000!
  ```
  
  **Real-life Example:** 🚻 **Public Restroom**
  - synchronized = Lock on door
  - Only one person (thread) at a time
  - Others wait outside

- [ ] **Executor Framework**
  ```java
  // Thread Pool - Reuse threads
  ExecutorService executor = Executors.newFixedThreadPool(5);
  
  // Submit tasks
  for (int i = 0; i < 10; i++) {
      final int taskId = i;
      executor.submit(() -> {
          System.out.println("Task " + taskId + " by " + 
                           Thread.currentThread().getName());
          Thread.sleep(1000);
      });
  }
  
  executor.shutdown();  // Important!
  
  // Different types
  Executors.newSingleThreadExecutor();     // 1 thread
  Executors.newFixedThreadPool(10);        // Fixed size
  Executors.newCachedThreadPool();         // Dynamic size
  Executors.newScheduledThreadPool(5);     // Scheduled tasks
  ```
  
  **Real-life Example:** 🏊 **Swimming Pool Lanes**
  - Fixed pool = Limited lanes
  - Tasks = Swimmers
  - Reuse lanes instead of building new ones

- [ ] **Concurrent Collections**
  ```java
  // Thread-safe collections
  ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
  CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
  ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
  
  // Example: Multiple threads updating map
  ExecutorService executor = Executors.newFixedThreadPool(10);
  
  for (int i = 0; i < 100; i++) {
      final int num = i;
      executor.submit(() -> {
          map.put("key" + num, num);
      });
  }
  
  executor.shutdown();
  executor.awaitTermination(1, TimeUnit.MINUTES);
  
  System.out.println("Map size: " + map.size());  // Always 100
  ```

---

## 🔷 Part 2: Java 8+ Features

### Concepts to Master:

- [ ] **Lambda Expressions**
  ```java
  // Old way (Anonymous class)
  Runnable r1 = new Runnable() {
      @Override
      public void run() {
          System.out.println("Hello");
      }
  };
  
  // Lambda way (Concise)
  Runnable r2 = () -> System.out.println("Hello");
  
  // With parameters
  Comparator<Integer> comp = (a, b) -> a.compareTo(b);
  
  // Multiple statements
  Comparator<String> comp2 = (s1, s2) -> {
      System.out.println("Comparing: " + s1 + " and " + s2);
      return s1.compareTo(s2);
  };
  
  // Practical example
  List<String> names = Arrays.asList("John", "Alice", "Bob");
  
  // Old way
  Collections.sort(names, new Comparator<String>() {
      public int compare(String a, String b) {
          return a.compareTo(b);
      }
  });
  
  // Lambda way
  Collections.sort(names, (a, b) -> a.compareTo(b));
  
  // Even shorter
  Collections.sort(names, String::compareTo);
  ```
  
  **Real-life Example:** 📝 **Quick Notes**
  - Lambda = Shorthand notation
  - Less code, same functionality

- [ ] **Stream API**
  ```java
  List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  
  // Filter even numbers
  List<Integer> evens = numbers.stream()
                               .filter(n -> n % 2 == 0)
                               .collect(Collectors.toList());
  // [2, 4, 6, 8, 10]
  
  // Map (transform)
  List<Integer> squares = numbers.stream()
                                 .map(n -> n * n)
                                 .collect(Collectors.toList());
  // [1, 4, 9, 16, 25, ...]
  
  // Filter + Map + Reduce
  int sumOfEvenSquares = numbers.stream()
                                .filter(n -> n % 2 == 0)
                                .map(n -> n * n)
                                .reduce(0, Integer::sum);
  // 2² + 4² + 6² + 8² + 10² = 220
  
  // Practical: Process list of users
  class User {
      String name;
      int age;
      String city;
  }
  
  List<User> users = getUsers();
  
  // Get names of users above 25 from Dhaka
  List<String> names = users.stream()
                            .filter(u -> u.age > 25)
                            .filter(u -> u.city.equals("Dhaka"))
                            .map(u -> u.name)
                            .collect(Collectors.toList());
  
  // Count users by city
  Map<String, Long> usersByCity = users.stream()
      .collect(Collectors.groupingBy(u -> u.city, Collectors.counting()));
  ```
  
  **Stream Operations:**
  - **Intermediate:** filter, map, sorted, distinct, limit, skip
  - **Terminal:** collect, forEach, reduce, count, anyMatch, allMatch
  
  **Real-life Example:** 🏭 **Assembly Line**
  - Stream = Conveyor belt
  - Operations = Processing stations
  - Terminal = Final product

- [ ] **Optional**
  ```java
  // Avoid NullPointerException
  Optional<String> optional = Optional.ofNullable(getName());
  
  // Check if present
  if (optional.isPresent()) {
      System.out.println(optional.get());
  }
  
  // Better way
  optional.ifPresent(name -> System.out.println(name));
  
  // Provide default
  String name = optional.orElse("Unknown");
  String name2 = optional.orElseGet(() -> getDefaultName());
  
  // Throw exception if absent
  String name3 = optional.orElseThrow(() -> new RuntimeException("Name not found"));
  
  // Practical example
  public Optional<User> findUserById(Long id) {
      User user = userRepository.findById(id);
      return Optional.ofNullable(user);
  }
  
  // Usage
  findUserById(1L)
      .map(User::getName)
      .ifPresent(System.out::println);
  ```
  
  **Real-life Example:** 📦 **Package Delivery**
  - Optional = May or may not contain item
  - Check before opening

- [ ] **Method References**
  ```java
  // Lambda
  list.forEach(s -> System.out.println(s));
  
  // Method reference
  list.forEach(System.out::println);
  
  // Types of method references:
  
  // 1. Static method
  Function<String, Integer> parseInt = Integer::parseInt;
  
  // 2. Instance method of particular object
  String str = "Hello";
  Supplier<Integer> length = str::length;
  
  // 3. Instance method of arbitrary object
  Function<String, String> toUpper = String::toUpperCase;
  
  // 4. Constructor
  Supplier<List<String>> listSupplier = ArrayList::new;
  ```

---

## 🔷 Part 3: Generics

### Concepts to Master:

- [ ] **Generic Classes**
  ```java
  // Generic class
  class Box<T> {
      private T item;
      
      public void set(T item) {
          this.item = item;
      }
      
      public T get() {
          return item;
      }
  }
  
  // Usage
  Box<Integer> intBox = new Box<>();
  intBox.set(10);
  int value = intBox.get();  // No casting needed!
  
  Box<String> strBox = new Box<>();
  strBox.set("Hello");
  String str = strBox.get();
  
  // Multiple type parameters
  class Pair<K, V> {
      private K key;
      private V value;
      
      public Pair(K key, V value) {
          this.key = key;
          this.value = value;
      }
  }
  
  Pair<String, Integer> pair = new Pair<>("Age", 25);
  ```
  
  **Real-life Example:** 📦 **Storage Box**
  - Generic = Box can hold any type
  - Type safety = Label on box tells what's inside

- [ ] **Generic Methods**
  ```java
  public class Util {
      // Generic method
      public static <T> void printArray(T[] array) {
          for (T element : array) {
              System.out.println(element);
          }
      }
      
      // Bounded type parameter
      public static <T extends Comparable<T>> T findMax(T[] array) {
          T max = array[0];
          for (T element : array) {
              if (element.compareTo(max) > 0) {
                  max = element;
              }
          }
          return max;
      }
  }
  
  // Usage
  Integer[] intArray = {1, 5, 3, 9, 2};
  Util.printArray(intArray);
  
  Integer max = Util.findMax(intArray);  // 9
  ```

- [ ] **Wildcards**
  ```java
  // Unbounded wildcard
  public void printList(List<?> list) {
      for (Object obj : list) {
          System.out.println(obj);
      }
  }
  
  // Upper bounded wildcard (extends)
  public double sumOfList(List<? extends Number> list) {
      double sum = 0.0;
      for (Number num : list) {
          sum += num.doubleValue();
      }
      return sum;
  }
  
  // Lower bounded wildcard (super)
  public void addNumbers(List<? super Integer> list) {
      list.add(1);
      list.add(2);
      list.add(3);
  }
  ```

---

## 🔷 Part 4: Functional Programming

### Concepts to Master:

- [ ] **Functional Interfaces**
  ```java
  // Built-in functional interfaces
  
  // 1. Predicate<T> - Test condition
  Predicate<Integer> isEven = n -> n % 2 == 0;
  System.out.println(isEven.test(4));  // true
  
  // 2. Function<T, R> - Transform input to output
  Function<String, Integer> stringLength = s -> s.length();
  System.out.println(stringLength.apply("Hello"));  // 5
  
  // 3. Consumer<T> - Accept input, no return
  Consumer<String> printer = s -> System.out.println(s);
  printer.accept("Hello");
  
  // 4. Supplier<T> - No input, return value
  Supplier<Double> randomValue = () -> Math.random();
  System.out.println(randomValue.get());
  
  // 5. BiFunction<T, U, R> - Two inputs, one output
  BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
  System.out.println(add.apply(5, 3));  // 8
  
  // Custom functional interface
  @FunctionalInterface
  interface Calculator {
      int calculate(int a, int b);
  }
  
  Calculator add = (a, b) -> a + b;
  Calculator multiply = (a, b) -> a * b;
  ```

- [ ] **Collectors**
  ```java
  List<String> names = Arrays.asList("John", "Alice", "Bob", "Alice");
  
  // To List
  List<String> list = names.stream().collect(Collectors.toList());
  
  // To Set (removes duplicates)
  Set<String> set = names.stream().collect(Collectors.toSet());
  
  // Joining strings
  String joined = names.stream().collect(Collectors.joining(", "));
  // "John, Alice, Bob, Alice"
  
  // Grouping
  Map<Integer, List<String>> byLength = names.stream()
      .collect(Collectors.groupingBy(String::length));
  // {3=[Bob], 4=[John], 5=[Alice, Alice]}
  
  // Counting
  Map<String, Long> frequency = names.stream()
      .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
  // {John=1, Alice=2, Bob=1}
  
  // Partitioning (true/false)
  Map<Boolean, List<String>> partition = names.stream()
      .collect(Collectors.partitioningBy(s -> s.length() > 3));
  // {false=[Bob], true=[John, Alice, Alice]}
  ```

---

## 💻 Practice Projects (2)

- [ ] **Project 1: Multithreaded File Processor**
  - Read multiple files concurrently
  - Process data using thread pool
  - Aggregate results
  - **Skills:** Multithreading, Executor, Synchronization

- [ ] **Project 2: Stream-based Data Analyzer**
  - Load CSV data
  - Filter, transform using streams
  - Generate statistics
  - **Skills:** Streams, Collectors, Functional programming

---

## ✅ Move-On Criteria

- [ ] ✔ **Multithreading concepts explain করতে পারবেন**
- [ ] ✔ **Thread-safe code লিখতে পারবেন**
- [ ] ✔ **Stream API fluently use করতে পারবেন**
- [ ] ✔ **Lambda expressions comfortably লিখতে পারবেন**
- [ ] ✔ **Generics বুঝবেন এবং use করতে পারবেন**
- [ ] ✔ **2টা projects complete করবেন**

---

**Real-World Applications:**
- 🚀 High-performance applications
- 📊 Data processing pipelines
- 🌐 Concurrent web servers
- 💾 Parallel file processing
- 🎮 Game engines

**Next:** [Operating Systems & Linux →](Java-Topic-03-Operating-Systems.md)

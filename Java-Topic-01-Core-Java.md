# 📌 Topic 1: Core Java Fundamentals Revisited

## 🎯 Learning Objectives

Master Core Java concepts that form the foundation of every Java application and are essential for interviews.

---

## 🔷 Part 1: Object-Oriented Programming (OOP)

### Concepts to Master:

- [X] **Classes and Objects**

  ```java
  // Class = Blueprint
  class Car {
      // Properties (State)
      String brand;
      String color;
      int speed;

      // Constructor
      Car(String brand, String color) {
          this.brand = brand;
          this.color = color;
          this.speed = 0;
      }

      // Methods (Behavior)
      void accelerate() {
          speed += 10;
          System.out.println(brand + " speed: " + speed);
      }

      void brake() {
          speed = Math.max(0, speed - 10);
      }
  }

  // Object = Instance
  Car myCar = new Car("Toyota", "Red");
  myCar.accelerate();  // Toyota speed: 10
  ```

  **Real-life Example:** 🚗 **Car Manufacturing**

  - Class = Car design/blueprint
  - Object = Actual car produced
  - Properties = Color, model, engine
  - Methods = Start, stop, accelerate
- [ ] **Encapsulation (Data Hiding)**

  ```java
  class BankAccount {
      // Private fields (hidden)
      private String accountNumber;
      private double balance;

      // Public constructor
      public BankAccount(String accountNumber, double initialBalance) {
          this.accountNumber = accountNumber;
          this.balance = initialBalance;
      }

      // Public getter
      public double getBalance() {
          return balance;
      }

      // Public methods with validation
      public void deposit(double amount) {
          if (amount > 0) {
              balance += amount;
              System.out.println("Deposited: " + amount);
          }
      }

      public boolean withdraw(double amount) {
          if (amount > 0 && amount <= balance) {
              balance -= amount;
              return true;
          }
          return false;
      }
  }
  ```

  **Real-life Example:** 🏦 **Bank Account**

  - Balance is private (can't directly modify)
  - Deposit/withdraw methods control access
  - Prevents invalid operations

  **Interview Tip:** "Encapsulation protects data integrity and provides controlled access"
- [X] **Inheritance (IS-A Relationship)**

  ```java
  // Parent class
  class Animal {
      String name;

      void eat() {
          System.out.println(name + " is eating");
      }

      void sleep() {
          System.out.println(name + " is sleeping");
      }
  }

  // Child class
  class Dog extends Animal {
      void bark() {
          System.out.println(name + " is barking");
      }
  }

  class Cat extends Animal {
      void meow() {
          System.out.println(name + " is meowing");
      }
  }

  // Usage
  Dog dog = new Dog();
  dog.name = "Buddy";
  dog.eat();   // Inherited
  dog.bark();  // Own method
  ```

  **Real-life Example:** 👨‍👩‍👧 **Family Tree**

  - Parent = Animal (common features)
  - Children = Dog, Cat (specific features)
  - Inheritance = Genetic traits passed down

  **Types of Inheritance:**

  - Single: A → B
  - Multilevel: A → B → C
  - Hierarchical: A → B, A → C
  - ❌ Multiple (not in Java, use interfaces)
- [X] **Polymorphism (Many Forms)**

  **Method Overloading (Compile-time)**

  ```java
  class Calculator {
      // Same method name, different parameters
      int add(int a, int b) {
          return a + b;
      }

      double add(double a, double b) {
          return a + b;
      }

      int add(int a, int b, int c) {
          return a + b + c;
      }
  }
  ```

  **Method Overriding (Runtime)**

  ```java
  class Animal {
      void makeSound() {
          System.out.println("Animal sound");
      }
  }

  class Dog extends Animal {
      @Override
      void makeSound() {
          System.out.println("Woof!");
      }
  }

  class Cat extends Animal {
      @Override
      void makeSound() {
          System.out.println("Meow!");
      }
  }

  // Polymorphism in action
  Animal myAnimal;
  myAnimal = new Dog();
  myAnimal.makeSound();  // Woof!

  myAnimal = new Cat();
  myAnimal.makeSound();  // Meow!
  ```

  **Real-life Example:** 🎭 **Actor Playing Roles**

  - Same actor (reference type)
  - Different roles (actual object)
  - Different behavior based on role
- [x] **Abstraction (Hiding Implementation)**

  **Abstract Class**

  ```java
  abstract class Shape {
      String color;

      // Abstract method (no implementation)
      abstract double calculateArea();

      // Concrete method
      void displayColor() {
          System.out.println("Color: " + color);
      }
  }

  class Circle extends Shape {
      double radius;

      @Override
      double calculateArea() {
          return Math.PI * radius * radius;
      }
  }

  class Rectangle extends Shape {
      double length, width;

      @Override
      double calculateArea() {
          return length * width;
      }
  }
  ```

  **Interface**

  ```java
  interface Drawable {
      void draw();  // Abstract by default
  }

  interface Resizable {
      void resize(int width, int height);
  }

  class Button implements Drawable, Resizable {
      @Override
      public void draw() {
          System.out.println("Drawing button");
      }

      @Override
      public void resize(int width, int height) {
          System.out.println("Resizing to " + width + "x" + height);
      }
  }
  ```

  **Real-life Example:** 🚗 **Car Dashboard**

  - You see: Speedometer, fuel gauge
  - Hidden: Engine internals, transmission
  - Abstraction: Simple interface, complex implementation

  **Abstract Class vs Interface:**

  | Feature     | Abstract Class      | Interface                              |
  | ----------- | ------------------- | -------------------------------------- |
  | Methods     | Abstract + Concrete | Abstract (default concrete in Java 8+) |
  | Variables   | Any type            | public static final                    |
  | Inheritance | Single              | Multiple                               |
  | Use case    | IS-A                | CAN-DO                                 |

---

## 🔷 Part 2: Collections Framework

### Concepts to Master:

- [x] **List Interface**

  ```java
  // ArrayList - Dynamic array
  List<String> arrayList = new ArrayList<>();
  arrayList.add("Apple");
  arrayList.add("Banana");
  arrayList.add("Cherry");
  arrayList.get(1);  // "Banana" - O(1)
  arrayList.remove("Banana");  // O(n)

  // LinkedList - Doubly linked list
  List<String> linkedList = new LinkedList<>();
  linkedList.add("First");
  linkedList.add("Second");
  ((LinkedList<String>) linkedList).addFirst("Zero");  // O(1)

  // Vector - Thread-safe ArrayList
  List<String> vector = new Vector<>();
  ```

  **Real-life Example:** 📝 **Shopping List**

  - ArrayList = Written list (random access)
  - LinkedList = Chain of items (sequential)

  **When to use:**

  - ArrayList: Frequent reads, rare modifications
  - LinkedList: Frequent insertions/deletions
  - Vector: Thread-safe operations (rare use)
- [x] **Set Interface**

  ```java
  // HashSet - No duplicates, no order
  Set<String> hashSet = new HashSet<>();
  hashSet.add("Apple");
  hashSet.add("Banana");
  hashSet.add("Apple");  // Ignored (duplicate)
  // Output: [Banana, Apple] (random order)

  // LinkedHashSet - Insertion order maintained
  Set<String> linkedHashSet = new LinkedHashSet<>();
  linkedHashSet.add("Apple");
  linkedHashSet.add("Banana");
  // Output: [Apple, Banana] (insertion order)

  // TreeSet - Sorted order
  Set<Integer> treeSet = new TreeSet<>();
  treeSet.add(5);
  treeSet.add(2);
  treeSet.add(8);
  // Output: [2, 5, 8] (sorted)
  ```

  **Real-life Example:** 🎓 **Student Roll Numbers**

  - HashSet = Unique IDs (no order needed)
  - TreeSet = Sorted roll numbers
- [x] **Map Interface**

  ```java
  // HashMap - Key-value pairs, no order
  Map<String, Integer> hashMap = new HashMap<>();
  hashMap.put("Alice", 25);
  hashMap.put("Bob", 30);
  hashMap.put("Charlie", 28);
  int age = hashMap.get("Alice");  // 25 - O(1)

  // LinkedHashMap - Insertion order
  Map<String, Integer> linkedHashMap = new LinkedHashMap<>();

  // TreeMap - Sorted by keys
  Map<String, Integer> treeMap = new TreeMap<>();
  treeMap.put("Charlie", 28);
  treeMap.put("Alice", 25);
  treeMap.put("Bob", 30);
  // Keys sorted: Alice, Bob, Charlie

  // Hashtable - Thread-safe (legacy)
  Map<String, Integer> hashtable = new Hashtable<>();
  ```

  **Real-life Example:** 📞 **Phone Book**

  - Key = Name
  - Value = Phone number
  - Quick lookup by name

  **HashMap vs TreeMap vs LinkedHashMap:**

  | Feature     | HashMap     | TreeMap     | LinkedHashMap  |
  | ----------- | ----------- | ----------- | -------------- |
  | Order       | No          | Sorted      | Insertion      |
  | Performance | O(1)        | O(log n)    | O(1)           |
  | Null keys   | 1 allowed   | Not allowed | 1 allowed      |
  | Use case    | Fast lookup | Sorted data | Maintain order |
- [x] **Queue & Deque**

  ```java
  // Queue - FIFO
  Queue<String> queue = new LinkedList<>();
  queue.offer("First");
  queue.offer("Second");
  queue.poll();  // "First"

  // PriorityQueue - Min heap
  PriorityQueue<Integer> pq = new PriorityQueue<>();
  pq.offer(5);
  pq.offer(2);
  pq.offer(8);
  pq.poll();  // 2 (smallest)

  // Deque - Double-ended queue
  Deque<String> deque = new ArrayDeque<>();
  deque.offerFirst("Front");
  deque.offerLast("Back");
  ```

  **Real-life Example:** 🎫 **Ticket Counter**

  - Queue = People waiting in line
  - PriorityQueue = VIP gets priority

---

## 🔷 Part 3: Exception Handling

### Concepts to Master:

- [x] **Try-Catch-Finally**

  ```java
  try {
      int result = 10 / 0;  // ArithmeticException
  } catch (ArithmeticException e) {
      System.out.println("Cannot divide by zero");
  } finally {
      System.out.println("Always executes");
  }

  // Multiple catch blocks
  try {
      String str = null;
      str.length();  // NullPointerException
  } catch (NullPointerException e) {
      System.out.println("Null reference");
  } catch (Exception e) {
      System.out.println("Generic exception");
  }
  ```

  **Real-life Example:** 🚗 **Driving Safety**

  - Try = Drive normally
  - Catch = Handle accident
  - Finally = Always wear seatbelt
- [x] **Checked vs Unchecked Exceptions**

  ```java
  // Checked Exception (must handle)
  try {
      FileReader file = new FileReader("file.txt");
  } catch (FileNotFoundException e) {
      e.printStackTrace();
  }

  // Unchecked Exception (runtime)
  int[] arr = new int[5];
  arr[10] = 5;  // ArrayIndexOutOfBoundsException
  ```

  **Checked:** IOException, SQLException
  **Unchecked:** NullPointerException, ArithmeticException
- [x] **Custom Exceptions**

  ```java
  class InsufficientFundsException extends Exception {
      public InsufficientFundsException(String message) {
          super(message);
      }
  }

  class BankAccount {
      private double balance;

      void withdraw(double amount) throws InsufficientFundsException {
          if (amount > balance) {
              throw new InsufficientFundsException("Balance too low");
          }
          balance -= amount;
      }
  }
  ```

---

## 🔷 Part 4: String Handling

### Concepts to Master:

- [x] **String Immutability**

  ```java
  String str1 = "Hello";
  String str2 = str1;
  str1 = str1 + " World";  // New object created

  System.out.println(str1);  // "Hello World"
  System.out.println(str2);  // "Hello" (unchanged)
  ```

  **Real-life:** 🔒 **Security** - Passwords stored as strings can't be modified
- [x] **StringBuilder vs StringBuffer**

  ```java
  // StringBuilder (not thread-safe, faster)
  StringBuilder sb = new StringBuilder("Hello");
  sb.append(" World");  // Same object modified

  // StringBuffer (thread-safe, slower)
  StringBuffer sbf = new StringBuffer("Hello");
  sbf.append(" World");
  ```

  **Use case:** Building large strings in loops
- [x] **Common String Methods**

  ```java
  String str = "Hello World";
  str.length();           // 11
  str.charAt(0);          // 'H'
  str.substring(0, 5);    // "Hello"
  str.toLowerCase();      // "hello world"
  str.toUpperCase();      // "HELLO WORLD"
  str.contains("World");  // true
  str.replace("World", "Java");  // "Hello Java"
  str.split(" ");         // ["Hello", "World"]
  str.trim();             // Remove whitespace
  ```

---

## 💻 Practice Projects (3)

- [ ] **Project 1: Library Management System**

  - Classes: Book, Member, Library
  - Collections: ArrayList for books, HashMap for members
  - Exception handling for invalid operations
  - **Skills:** OOP, Collections, File I/O
- [ ] **Project 2: Student Grade Calculator**

  - Inheritance: Person → Student
  - Collections: TreeMap for sorted grades
  - Custom exceptions for invalid grades
  - **Skills:** Inheritance, Collections, Exceptions
- [ ] **Project 3: Banking Application**

  - Encapsulation: BankAccount class
  - Collections: HashMap for accounts
  - Exception handling for transactions
  - **Skills:** All OOP concepts, Collections

---

## ✅ Move-On Criteria

- [ ] ✔ **OOP concepts explain করতে পারবেন real examples সহ**
- [ ] ✔ **Collections framework কখন কোনটা use করবেন জানবেন**
- [ ] ✔ **Exception handling properly করতে পারবেন**
- [ ] ✔ **3টা projects complete করবেন**
- [ ] ✔ **Interview questions confidently answer করতে পারবেন**

---

**Real-World Applications:**

- 🏢 Enterprise applications
- 🛒 E-commerce systems
- 🏦 Banking software
- 📱 Android apps
- 🎮 Game development

**Next:** [Advanced Java Concepts →](Java-Topic-02-Advanced-Java.md)

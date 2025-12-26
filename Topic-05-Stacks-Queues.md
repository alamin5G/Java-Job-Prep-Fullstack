# 📌 TOPIC 5: Stacks & Queues

## 🎯 Learning Objectives
Stacks এবং Queues হলো fundamental data structures যা real-world এ সবচেয়ে বেশি use হয়।

---

## 🎯 1 Stack Pattern - Quick Reference

> **Master this pattern → Solve 50+ stack problems!**

### Pattern Recognition Checklist

```
┌─────────────────────────────────────────────────────────────┐
│  PROBLEM KEYWORDS → PATTERN                                  │
├─────────────────────────────────────────────────────────────┤
│  ✅ "next greater" + "next smaller" → MONOTONIC STACK       │
└─────────────────────────────────────────────────────────────┘
```

### Visual Pattern Map

```
STACK PATTERN (1)
│
└─ ⚪ PATTERN 8: Monotonic Stack
    └─ 🧠 Memory Trick: "Stack stays increasing/decreasing - like stairs"
    └─ ⏰ Time: O(n) | Space: O(n)
    └─ 🎯 Use: Next greater/smaller element, histogram problems
```

---

## 🔷 Part 1: Stack Fundamentals

### Concepts to Master:

- [ ] **Stack কী - LIFO Principle**
  ```java
  class Stack {
      private int[] arr;
      private int top;
      private int capacity;
      
      Stack(int size) {
          arr = new int[size];
          capacity = size;
          top = -1;
      }
      
      void push(int data) {
          if (top == capacity - 1) {
              throw new StackOverflowError("Stack is full");
          }
          arr[++top] = data;
      }
      
      int pop() {
          if (top == -1) {
              throw new EmptyStackException();
          }
          return arr[top--];
      }
      
      int peek() {
          if (top == -1) {
              throw new EmptyStackException();
          }
          return arr[top];
      }
      
      boolean isEmpty() {
          return top == -1;
      }
  }
  ```
  
  **Real-life Example:** 📚 **Stack of Books**
  - Last In, First Out (LIFO)
  - সবার উপরের book টাই প্রথমে নিতে পারবেন
  - নিচের book নিতে হলে উপরেরগুলো সরাতে হবে

- [ ] **Stack Applications**
  
  **1. Function Call Stack**
  ```java
  void functionA() {
      System.out.println("A start");
      functionB();
      System.out.println("A end");
  }
  
  void functionB() {
      System.out.println("B start");
      functionC();
      System.out.println("B end");
  }
  
  void functionC() {
      System.out.println("C");
  }
  
  // Call Stack:
  // [functionA] → [functionB] → [functionC]
  // Output: A start → B start → C → B end → A end
  ```
  **Real-life:** 💻 **Program Execution** - Java/C++ function calls

  **2. Undo/Redo Operations**
  ```java
  Stack<String> undoStack = new Stack<>();
  Stack<String> redoStack = new Stack<>();
  
  void type(String text) {
      undoStack.push(text);
      redoStack.clear();  // Clear redo on new action
  }
  
  void undo() {
      if (!undoStack.isEmpty()) {
          String text = undoStack.pop();
          redoStack.push(text);
      }
  }
  
  void redo() {
      if (!redoStack.isEmpty()) {
          String text = redoStack.pop();
          undoStack.push(text);
      }
  }
  ```
  **Real-life:** 📝 **Text Editor** - MS Word, VS Code

  **3. Browser History**
  ```java
  Stack<String> backStack = new Stack<>();
  Stack<String> forwardStack = new Stack<>();
  String currentPage;
  
  void visit(String url) {
      if (currentPage != null) {
          backStack.push(currentPage);
      }
      currentPage = url;
      forwardStack.clear();
  }
  
  void back() {
      if (!backStack.isEmpty()) {
          forwardStack.push(currentPage);
          currentPage = backStack.pop();
      }
  }
  
  void forward() {
      if (!forwardStack.isEmpty()) {
          backStack.push(currentPage);
          currentPage = forwardStack.pop();
      }
  }
  ```
  **Real-life:** 🌐 **Chrome/Firefox Browser**

---

## 🔷 Part 2: Stack Problems

### 1. Balanced Parentheses

- [ ] **Valid Parentheses**
  ```java
  boolean isValid(String s) {
      Stack<Character> stack = new Stack<>();
      
      for (char c : s.toCharArray()) {
          if (c == '(' || c == '{' || c == '[') {
              stack.push(c);
          } else {
              if (stack.isEmpty()) return false;
              
              char top = stack.pop();
              if (c == ')' && top != '(') return false;
              if (c == '}' && top != '{') return false;
              if (c == ']' && top != '[') return false;
          }
      }
      
      return stack.isEmpty();
  }
  ```
  
  **Real-life Use Case:** 💻 **Code Syntax Validation**
  - IDE তে code লিখলে bracket matching check করে
  - Compiler এ syntax error detect করে

### 2. Expression Evaluation

- [ ] **Infix to Postfix Conversion**
  ```java
  int precedence(char op) {
      if (op == '+' || op == '-') return 1;
      if (op == '*' || op == '/') return 2;
      return 0;
  }
  
  String infixToPostfix(String infix) {
      StringBuilder result = new StringBuilder();
      Stack<Character> stack = new Stack<>();
      
      for (char c : infix.toCharArray()) {
          if (Character.isLetterOrDigit(c)) {
              result.append(c);
          } else if (c == '(') {
              stack.push(c);
          } else if (c == ')') {
              while (!stack.isEmpty() && stack.peek() != '(') {
                  result.append(stack.pop());
              }
              stack.pop();  // Remove '('
          } else {  // Operator
              while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                  result.append(stack.pop());
              }
              stack.push(c);
          }
      }
      
      while (!stack.isEmpty()) {
          result.append(stack.pop());
      }
      
      return result.toString();
  }
  ```
  
  **Real-life Use Case:** 🧮 **Calculator Apps**
  - "2 + 3 * 4" → "2 3 4 * +" (easier to evaluate)

### 3. Next Greater Element

- [ ] **Next Greater Element**
  ```java
  int[] nextGreaterElement(int[] arr) {
      int n = arr.length;
      int[] result = new int[n];
      Stack<Integer> stack = new Stack<>();
      
      for (int i = n - 1; i >= 0; i--) {
          while (!stack.isEmpty() && stack.peek() <= arr[i]) {
              stack.pop();
          }
          
          result[i] = stack.isEmpty() ? -1 : stack.peek();
          stack.push(arr[i]);
      }
      
      return result;
  }
  ```
  
  **Real-life Use Case:** 📈 **Stock Price Analysis**
  - Find next day when stock price will be higher
  - Weather forecast - next warmer day

---

## 🔷 Part 3: Queue Fundamentals

### Concepts to Master:

- [ ] **Queue কী - FIFO Principle**
  ```java
  class Queue {
      private int[] arr;
      private int front, rear, size, capacity;
      
      Queue(int capacity) {
          this.capacity = capacity;
          arr = new int[capacity];
          front = 0;
          rear = -1;
          size = 0;
      }
      
      void enqueue(int data) {
          if (size == capacity) {
              throw new IllegalStateException("Queue is full");
          }
          rear = (rear + 1) % capacity;
          arr[rear] = data;
          size++;
      }
      
      int dequeue() {
          if (size == 0) {
              throw new NoSuchElementException("Queue is empty");
          }
          int data = arr[front];
          front = (front + 1) % capacity;
          size--;
          return data;
      }
      
      int peek() {
          if (size == 0) {
              throw new NoSuchElementException("Queue is empty");
          }
          return arr[front];
      }
  }
  ```
  
  **Real-life Example:** 🎫 **Ticket Counter Queue**
  - First In, First Out (FIFO)
  - যে আগে আসবে সে আগে সার্ভ পাবে
  - Fair ordering

- [ ] **Queue Applications**
  
  **1. Print Queue**
  ```java
  Queue<String> printQueue = new LinkedList<>();
  
  void addPrintJob(String document) {
      printQueue.offer(document);
      System.out.println("Added to queue: " + document);
  }
  
  void processPrintJobs() {
      while (!printQueue.isEmpty()) {
          String doc = printQueue.poll();
          System.out.println("Printing: " + doc);
          // Simulate printing
      }
  }
  ```
  **Real-life:** 🖨️ **Office Printer** - Documents print in order

  **2. CPU Task Scheduling**
  ```java
  class Task {
      String name;
      int priority;
  }
  
  Queue<Task> taskQueue = new LinkedList<>();
  
  void scheduleTask(Task task) {
      taskQueue.offer(task);
  }
  
  void executeTasks() {
      while (!taskQueue.isEmpty()) {
          Task task = taskQueue.poll();
          System.out.println("Executing: " + task.name);
      }
  }
  ```
  **Real-life:** 💻 **Operating System** - Process scheduling

  **3. BFS in Graphs**
  ```java
  void BFS(int start) {
      Queue<Integer> queue = new LinkedList<>();
      boolean[] visited = new boolean[V];
      
      queue.offer(start);
      visited[start] = true;
      
      while (!queue.isEmpty()) {
          int node = queue.poll();
          System.out.print(node + " ");
          
          for (int neighbor : adj[node]) {
              if (!visited[neighbor]) {
                  visited[neighbor] = true;
                  queue.offer(neighbor);
              }
          }
      }
  }
  ```
  **Real-life:** 🗺️ **Google Maps** - Shortest path finding

---

## 🔷 Part 4: Advanced Queue Types

### 1. Circular Queue

- [ ] **Circular Queue Implementation**
  ```java
  class CircularQueue {
      private int[] arr;
      private int front, rear, size, capacity;
      
      CircularQueue(int k) {
          capacity = k;
          arr = new int[k];
          front = 0;
          rear = -1;
          size = 0;
      }
      
      boolean enQueue(int value) {
          if (isFull()) return false;
          rear = (rear + 1) % capacity;
          arr[rear] = value;
          size++;
          return true;
      }
      
      boolean deQueue() {
          if (isEmpty()) return false;
          front = (front + 1) % capacity;
          size--;
          return true;
      }
      
      boolean isFull() {
          return size == capacity;
      }
      
      boolean isEmpty() {
          return size == 0;
      }
  }
  ```
  
  **Real-life Use Case:** 🎮 **Multiplayer Game Turns**
  - Players take turns in circular fashion
  - After last player, back to first

### 2. Priority Queue

- [ ] **Priority Queue (Min Heap)**
  ```java
  PriorityQueue<Integer> pq = new PriorityQueue<>();
  
  pq.offer(5);
  pq.offer(2);
  pq.offer(8);
  pq.offer(1);
  
  while (!pq.isEmpty()) {
      System.out.println(pq.poll());  // 1, 2, 5, 8
  }
  ```
  
  **Real-life Use Cases:**
  - 🏥 **Hospital Emergency Room** - Critical patients first
  - 📧 **Email Priority** - Important emails on top
  - 🚦 **Traffic Management** - Ambulance gets priority

### 3. Deque (Double-Ended Queue)

- [ ] **Deque Operations**
  ```java
  Deque<Integer> deque = new ArrayDeque<>();
  
  deque.offerFirst(1);   // Add at front
  deque.offerLast(2);    // Add at rear
  deque.pollFirst();     // Remove from front
  deque.pollLast();      // Remove from rear
  ```
  
  **Real-life Use Case:** 🎵 **Music Player**
  - Add song at beginning or end
  - Remove from either side

---

## 💻 Coding Practice (20 Problems)

### 🟢 Stack Problems (10)

- [ ] **1. Valid Parentheses**
  - Use case: Code syntax validation

- [ ] **2. Implement Stack using Arrays**
  - Use case: Basic stack operations

- [ ] **3. Implement Stack using Queues**
  - Use case: Understanding conversions

- [ ] **4. Min Stack**
  - Use case: Track minimum in O(1)

- [ ] **5. Next Greater Element**
  - Use case: Stock price analysis

- [ ] **6. Largest Rectangle in Histogram**
  - Use case: Building height analysis

- [ ] **7. Evaluate Reverse Polish Notation**
  - Use case: Calculator implementation

- [ ] **8. Simplify Path**
  - Use case: File system navigation

- [ ] **9. Decode String**
  - Use case: String decompression

- [ ] **10. Daily Temperatures**
  - Use case: Weather forecast

### 🔵 Queue Problems (10)

- [ ] **11. Implement Queue using Stacks**
  - Use case: Data structure conversion

- [ ] **12. Circular Queue**
  - Use case: Round-robin scheduling

- [ ] **13. Sliding Window Maximum**
  - Use case: Real-time data analysis

- [ ] **14. First Unique Character in Stream**
  - Use case: Stream processing

- [ ] **15. Generate Binary Numbers**
  - Use case: Number system conversion

- [ ] **16. Rotten Oranges (BFS)**
  - Use case: Infection spread simulation

- [ ] **17. Level Order Traversal**
  - Use case: Tree traversal

- [ ] **18. Time Needed to Buy Tickets**
  - Use case: Queue simulation

- [ ] **19. Design Circular Deque**
  - Use case: Bidirectional operations

- [ ] **20. LRU Cache**
  - Use case: Browser/DB caching

---

## ✅ Move-On Criteria

- [ ] ✔ **Stack vs Queue difference real examples দিয়ে explain করতে পারবেন**
- [ ] ✔ **LIFO এবং FIFO real-world scenarios বলতে পারবেন**
- [ ] ✔ **Balanced parentheses problem instantly solve করতে পারবেন**
- [ ] ✔ **Priority Queue use cases explain করতে পারবেন**
- [ ] ✔ **Stack/Queue implementation from scratch করতে পারবেন**
- [ ] ✔ **15+ problems confidently solve করতে পারবেন**

---

**Real-World Applications:**
- 📝 Undo/Redo (Stack)
- 🌐 Browser history (Stack)
- 🖨️ Print queue (Queue)
- 🏥 Emergency room (Priority Queue)
- 💻 CPU scheduling (Queue)
- 🎮 Game turns (Circular Queue)

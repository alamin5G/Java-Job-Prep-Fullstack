# 📌 TOPIC 4: Linked Lists Mastery

## 🎯 Learning Objectives
Linked Lists হলো dynamic data structure যা interview তে frequently আসে।

---

## 🎯 3 Linked List Patterns - Quick Reference

> **Master these 3 patterns → Solve 100+ linked list problems!**

### Pattern Recognition Checklist

```
┌─────────────────────────────────────────────────────────────┐
│  PROBLEM KEYWORDS → PATTERN                                  │
├─────────────────────────────────────────────────────────────┤
│  ✅ "cycle detection" + "middle element" → FAST & SLOW      │
│  ✅ "reverse list" + "in-place" → IN-PLACE REVERSAL         │
│  ✅ "merge sorted lists" → MERGE LISTS                       │
└─────────────────────────────────────────────────────────────┘
```

### Visual Pattern Map

```
LINKED LIST PATTERNS (3)
│
├─ 🟡 PATTERN 3: Fast & Slow Pointers (Tortoise & Hare)
│   └─ 🧠 Memory Trick: "Tortoise and Hare race - they meet if there's a loop"
│   └─ ⏰ Time: O(n) | Space: O(1)
│   └─ 🎯 Use: Cycle detection, middle element, palindrome check
│   └─ 📝 Template:
│       slow = head
│       fast = head
│       while fast and fast.next:
│           slow = slow.next
│           fast = fast.next.next
│           if slow == fast: cycle found!
│
├─ 🔴 PATTERN 9: In-place Reversal
│   └─ 🧠 Memory Trick: "Three pointers dance - prev, current, next"
│   └─ ⏰ Time: O(n) | Space: O(1)
│   └─ 🎯 Use: Reverse list, reverse in groups, palindrome
│   └─ 📝 Template:
│       prev = null
│       current = head
│       while current:
│           next = current.next
│           current.next = prev
│           prev = current
│           current = next
│
└─ 🟢 PATTERN 10: Merge Lists
    └─ 🧠 Memory Trick: "Two sorted queues merging into one"
    └─ ⏰ Time: O(n + m) | Space: O(1)
    └─ 🎯 Use: Merge sorted lists, merge K lists, sort list
    └─ 📝 Template:
        dummy = Node(0)
        current = dummy
        while l1 and l2:
            if l1.val < l2.val:
                current.next = l1
                l1 = l1.next
            else:
                current.next = l2
                l2 = l2.next
            current = current.next
```

### Quick Decision Tree

```
START: Linked List Problem
    │
    ├─ Need to find MIDDLE or detect CYCLE?
    │   └─ YES → ✅ FAST & SLOW POINTERS
    │
    ├─ Need to REVERSE list (whole or parts)?
    │   └─ YES → ✅ IN-PLACE REVERSAL
    │
    └─ Need to MERGE sorted lists?
        └─ YES → ✅ MERGE LISTS
```

### Memorization Mnemonics

**Remember: "FSM" (Fast-Slow, Reversal, Merge)**

```
F - Fast & Slow    → "Fable of tortoise & hare"
R - Reversal       → "Reverse the arrow direction"
M - Merge          → "Merge two sorted queues"
```

### Common Problem Patterns

| Problem Type | Pattern | Example |
|-------------|---------|---------|
| Find middle | Fast & Slow | Median in stream |
| Detect cycle | Fast & Slow | Loop detection |
| Reverse entire list | In-place Reversal | Undo stack |
| Reverse in groups | In-place Reversal | Batch processing |
| Palindrome check | Fast & Slow + Reversal | Symmetric validation |
| Merge 2 sorted | Merge Lists | Combine logs |
| Merge K sorted | Merge Lists + Heap | Multi-source merge |

---

## 🔷 Part 1: Linked List Fundamentals

### Concepts to Master:

- [ ] **Linked List কী এবং কেন দরকার**
  ```java
  class Node {
      int data;
      Node next;
      
      Node(int data) {
          this.data = data;
          this.next = null;
      }
  }
  
  class LinkedList {
      Node head;
      
      void insert(int data) {
          Node newNode = new Node(data);
          if (head == null) {
              head = newNode;
          } else {
              Node temp = head;
              while (temp.next != null) {
                  temp = temp.next;
              }
              temp.next = newNode;
          }
      }
  }
  ```
  
  **Real-life Example:** 🚂 **Train Coaches**
  - প্রতিটা coach = node
  - Next pointer = coupling যা পরের coach এ point করে
  - Head = Engine (first coach)
  - নতুন coach যোগ করা সহজ (dynamic)

- [ ] **Array vs Linked List**
  ```
  Array:
  ✅ Random access O(1)
  ✅ Cache friendly
  ❌ Fixed size
  ❌ Insert/Delete expensive O(n)
  
  Linked List:
  ✅ Dynamic size
  ✅ Insert/Delete O(1) if position known
  ❌ No random access O(n)
  ❌ Extra memory for pointers
  ```
  
  **Real-life Use Cases:**
  - **Array:** Student roll numbers (fixed, need index access)
  - **Linked List:** Music playlist (add/remove songs frequently)

- [ ] **Types of Linked Lists**
  
  **1. Singly Linked List**
  ```java
  // Node: [data | next] → [data | next] → null
  ```
  **Use Case:** 📜 **Browser History (Forward only)**
  
  **2. Doubly Linked List**
  ```java
  class DNode {
      int data;
      DNode prev;
      DNode next;
  }
  // null ← [prev|data|next] ↔ [prev|data|next] → null
  ```
  **Use Case:** 🎵 **Music Player (Previous/Next song)**
  
  **3. Circular Linked List**
  ```java
  // Last node points back to head
  // [data|next] → [data|next] → [data|next] ↺
  ```
  **Use Case:** 🎮 **Multiplayer Game (Round-robin turns)**

---

## 🔷 Part 2: Basic Operations

### 1. Insertion

- [ ] **Insert at Beginning - O(1)**
  ```java
  void insertAtBeginning(int data) {
      Node newNode = new Node(data);
      newNode.next = head;
      head = newNode;
  }
  ```
  
  **Real-life:** 📰 **News Feed** - Latest post সবার উপরে

- [ ] **Insert at End - O(n)**
  ```java
  void insertAtEnd(int data) {
      Node newNode = new Node(data);
      if (head == null) {
          head = newNode;
          return;
      }
      Node temp = head;
      while (temp.next != null) {
          temp = temp.next;
      }
      temp.next = newNode;
  }
  ```
  
  **Real-life:** 📝 **Todo List** - নতুন task শেষে add

- [ ] **Insert at Position - O(n)**
  ```java
  void insertAtPosition(int data, int position) {
      Node newNode = new Node(data);
      if (position == 0) {
          newNode.next = head;
          head = newNode;
          return;
      }
      
      Node temp = head;
      for (int i = 0; i < position - 1 && temp != null; i++) {
          temp = temp.next;
      }
      
      if (temp != null) {
          newNode.next = temp.next;
          temp.next = newNode;
      }
  }
  ```

### 2. Deletion

- [ ] **Delete from Beginning - O(1)**
  ```java
  void deleteFromBeginning() {
      if (head != null) {
          head = head.next;
      }
  }
  ```
  
  **Real-life:** 🎫 **Queue System** - First person served

- [ ] **Delete from End - O(n)**
  ```java
  void deleteFromEnd() {
      if (head == null || head.next == null) {
          head = null;
          return;
      }
      Node temp = head;
      while (temp.next.next != null) {
          temp = temp.next;
      }
      temp.next = null;
  }
  ```

- [ ] **Delete by Value - O(n)**
  ```java
  void deleteByValue(int value) {
      if (head == null) return;
      
      if (head.data == value) {
          head = head.next;
          return;
      }
      
      Node temp = head;
      while (temp.next != null && temp.next.data != value) {
          temp = temp.next;
      }
      
      if (temp.next != null) {
          temp.next = temp.next.next;
      }
  }
  ```
  
  **Real-life:** 🛒 **Shopping Cart** - Remove specific item

---

## 🔷 Part 3: Advanced Techniques

### 1. Two Pointers (Slow & Fast)

- [ ] **Find Middle Element**
  ```java
  Node findMiddle() {
      if (head == null) return null;
      
      Node slow = head;
      Node fast = head;
      
      while (fast != null && fast.next != null) {
          slow = slow.next;
          fast = fast.next.next;
      }
      
      return slow;  // Middle node
  }
  ```
  
  **Real-life Use Case:** 📊 **Median Calculation in Stream**
  - Data continuously আসছে, median খুঁজতে হবে

- [ ] **Detect Cycle (Floyd's Algorithm)**
  ```java
  boolean hasCycle() {
      if (head == null) return false;
      
      Node slow = head;
      Node fast = head;
      
      while (fast != null && fast.next != null) {
          slow = slow.next;
          fast = fast.next.next;
          
          if (slow == fast) {
              return true;  // Cycle detected
          }
      }
      
      return false;
  }
  ```
  
  **Real-life Use Case:** 🔄 **Infinite Loop Detection**
  - Software testing
  - Network routing loops

- [ ] **Find Cycle Start Point**
  ```java
  Node detectCycleStart() {
      if (head == null) return null;
      
      Node slow = head;
      Node fast = head;
      
      // Detect cycle
      while (fast != null && fast.next != null) {
          slow = slow.next;
          fast = fast.next.next;
          if (slow == fast) break;
      }
      
      if (fast == null || fast.next == null) return null;
      
      // Find start
      slow = head;
      while (slow != fast) {
          slow = slow.next;
          fast = fast.next;
      }
      
      return slow;
  }
  ```

### 2. Reversal Techniques

- [ ] **Reverse Linked List (Iterative)**
  ```java
  Node reverse() {
      Node prev = null;
      Node current = head;
      Node next = null;
      
      while (current != null) {
          next = current.next;  // Save next
          current.next = prev;  // Reverse link
          prev = current;       // Move prev
          current = next;       // Move current
      }
      
      head = prev;
      return head;
  }
  ```
  
  **Real-life Use Case:** ⏮️ **Undo Functionality**
  - Text editor undo stack
  - Browser back button

- [ ] **Reverse in Groups of K**
  ```java
  Node reverseKGroup(Node head, int k) {
      Node current = head;
      Node next = null;
      Node prev = null;
      int count = 0;
      
      // Reverse first k nodes
      while (current != null && count < k) {
          next = current.next;
          current.next = prev;
          prev = current;
          current = next;
          count++;
      }
      
      // Recursively reverse remaining
      if (next != null) {
          head.next = reverseKGroup(next, k);
      }
      
      return prev;
  }
  ```
  
  **Real-life Use Case:** 📦 **Batch Processing**
  - Process data in chunks of k items

### 3. Merge Operations

- [ ] **Merge Two Sorted Lists**
  ```java
  Node mergeTwoLists(Node l1, Node l2) {
      Node dummy = new Node(0);
      Node current = dummy;
      
      while (l1 != null && l2 != null) {
          if (l1.data < l2.data) {
              current.next = l1;
              l1 = l1.next;
          } else {
              current.next = l2;
              l2 = l2.next;
          }
          current = current.next;
      }
      
      current.next = (l1 != null) ? l1 : l2;
      return dummy.next;
  }
  ```
  
  **Real-life Use Case:** 🔀 **Merge Sorted Logs**
  - Combine logs from multiple servers (sorted by timestamp)

---

## 💻 Coding Practice (20 Problems)

### 🟢 Basic (6)

- [ ] **1. Insert at Beginning/End**
  - Use case: Add new notification

- [ ] **2. Delete Node**
  - Use case: Remove item from cart

- [ ] **3. Search Element**
  - Use case: Find user in list

- [ ] **4. Get Length**
  - Use case: Count total items

- [ ] **5. Print Reverse (Recursive)**
  - Use case: Display history backwards

- [ ] **6. Nth Node from End**
  - Use case: Get last N transactions

### 🔵 Intermediate (7)

- [ ] **7. Reverse Linked List**
  - Use case: Undo operations

- [ ] **8. Find Middle Element**
  - Use case: Median in stream

- [ ] **9. Detect Cycle**
  - Use case: Infinite loop detection

- [ ] **10. Remove Duplicates (Sorted)**
  - Use case: Clean sorted data

- [ ] **11. Merge Two Sorted Lists**
  - Use case: Merge sorted logs

- [ ] **12. Palindrome Check**
  - Use case: Validate symmetric data

- [ ] **13. Intersection of Two Lists**
  - Use case: Find common connections

### 🟡 Advanced (7)

- [ ] **14. Reverse in Groups of K**
  - Use case: Batch processing

- [ ] **15. Add Two Numbers (as Lists)**
  - Use case: Big number arithmetic

- [ ] **16. Clone List with Random Pointer**
  - Use case: Deep copy complex structures

- [ ] **17. Flatten Multilevel List**
  - Use case: File system traversal

- [ ] **18. LRU Cache Implementation**
  - Use case: Browser cache, DB cache

- [ ] **19. Sort Linked List**
  - Use case: Order unsorted data

- [ ] **20. Rotate List**
  - Use case: Circular shift operations

---

## ✅ Move-On Criteria

- [ ] ✔ **Linked List vs Array trade-offs explain করতে পারবেন**
- [ ] ✔ **Slow-fast pointer technique master করবেন**
- [ ] ✔ **Reversal (iterative + recursive) দুটোই পারবেন**
- [ ] ✔ **Cycle detection real example দিয়ে explain করতে পারবেন**
- [ ] ✔ **15+ problems confidently solve করতে পারবেন**

---

**Real-World Applications:**
- 🎵 Music playlists
- 🌐 Browser history
- 📝 Undo/Redo systems
- 💾 Memory management
- 🔄 Circular buffers

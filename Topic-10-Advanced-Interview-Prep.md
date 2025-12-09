# 📌 TOPIC 10: Advanced Topics & Interview Prep

## 🎯 Learning Objectives
Advanced concepts যা top companies এর interviews এ আসে।

---

## 🔷 Part 1: Bit Manipulation

### Concepts to Master:

- [ ] **Bitwise Operators**
  ```java
  // AND (&): Both bits 1 → 1
  5 & 3 = 0101 & 0011 = 0001 = 1
  
  // OR (|): Any bit 1 → 1
  5 | 3 = 0101 | 0011 = 0111 = 7
  
  // XOR (^): Different bits → 1
  5 ^ 3 = 0101 ^ 0011 = 0110 = 6
  
  // NOT (~): Flip all bits
  ~5 = ~0101 = 1010 (in 4-bit)
  
  // Left Shift (<<): Multiply by 2
  5 << 1 = 0101 << 1 = 1010 = 10
  
  // Right Shift (>>): Divide by 2
  5 >> 1 = 0101 >> 1 = 0010 = 2
  ```
  
  **Real-life Use Cases:**
  - 🔐 **Encryption** - XOR cipher
  - 🎮 **Game Flags** - Store multiple boolean states
  - 🖼️ **Image Processing** - Color manipulation
  - 💾 **Memory Optimization** - Compress data

- [ ] **Common Bit Tricks**
  ```java
  // Check if power of 2
  boolean isPowerOfTwo(int n) {
      return n > 0 && (n & (n - 1)) == 0;
  }
  
  // Count set bits
  int countSetBits(int n) {
      int count = 0;
      while (n > 0) {
          count += n & 1;
          n >>= 1;
      }
      return count;
  }
  
  // Find single number (all others appear twice)
  int singleNumber(int[] nums) {
      int result = 0;
      for (int num : nums) {
          result ^= num;  // XOR cancels duplicates
      }
      return result;
  }
  ```

---

## 🔷 Part 2: Greedy Algorithms

### Concepts to Master:

- [ ] **Greedy Approach**
  ```
  Make locally optimal choice at each step
  Hope it leads to global optimum
  
  When to use:
  ✅ Optimal substructure
  ✅ Greedy choice property
  ❌ Not always correct! (unlike DP)
  ```
  
  **Real-life Example:** 💵 **Making Change (Greedy)**
  - Always pick largest coin first
  - Works for standard denominations (1, 5, 10, 25)
  - Doesn't always work! (e.g., coins: 1, 3, 4 for amount 6)

- [ ] **Activity Selection**
  ```java
  class Activity {
      int start, end;
  }
  
  int maxActivities(Activity[] activities) {
      // Sort by end time
      Arrays.sort(activities, (a, b) -> a.end - b.end);
      
      int count = 1;
      int lastEnd = activities[0].end;
      
      for (int i = 1; i < activities.length; i++) {
          if (activities[i].start >= lastEnd) {
              count++;
              lastEnd = activities[i].end;
          }
      }
      
      return count;
  }
  ```
  
  **Real-life Use Cases:**
  - 📅 **Meeting Room Scheduling** - Maximum meetings
  - 🎬 **Movie Theater** - Maximum shows
  - 🏋️ **Gym Class Booking** - Optimal schedule

- [ ] **Huffman Coding**
  ```java
  // Compress data based on frequency
  // More frequent characters → shorter codes
  ```
  
  **Real-life Use Case:** 📦 **File Compression**
  - ZIP files
  - JPEG images
  - MP3 audio

---

## 🔷 Part 3: Advanced Data Structures

### 1. Segment Tree

- [ ] **Segment Tree (Range Queries)**
  ```java
  class SegmentTree {
      int[] tree;
      int n;
      
      SegmentTree(int[] arr) {
          n = arr.length;
          tree = new int[4 * n];
          build(arr, 0, 0, n - 1);
      }
      
      void build(int[] arr, int node, int start, int end) {
          if (start == end) {
              tree[node] = arr[start];
          } else {
              int mid = (start + end) / 2;
              build(arr, 2*node+1, start, mid);
              build(arr, 2*node+2, mid+1, end);
              tree[node] = tree[2*node+1] + tree[2*node+2];
          }
      }
      
      int query(int node, int start, int end, int l, int r) {
          if (r < start || end < l) return 0;
          if (l <= start && end <= r) return tree[node];
          
          int mid = (start + end) / 2;
          return query(2*node+1, start, mid, l, r) +
                 query(2*node+2, mid+1, end, l, r);
      }
  }
  ```
  
  **Real-life Use Case:** 📊 **Stock Market Analysis**
  - Range sum queries in O(log n)
  - Find max/min in time range

### 2. Disjoint Set Union (Union-Find)

- [ ] **Union-Find Implementation**
  ```java
  class UnionFind {
      int[] parent, rank;
      
      UnionFind(int n) {
          parent = new int[n];
          rank = new int[n];
          for (int i = 0; i < n; i++) {
              parent[i] = i;
          }
      }
      
      int find(int x) {
          if (parent[x] != x) {
              parent[x] = find(parent[x]);  // Path compression
          }
          return parent[x];
      }
      
      void union(int x, int y) {
          int rootX = find(x);
          int rootY = find(y);
          
          if (rootX != rootY) {
              if (rank[rootX] < rank[rootY]) {
                  parent[rootX] = rootY;
              } else if (rank[rootX] > rank[rootY]) {
                  parent[rootY] = rootX;
              } else {
                  parent[rootY] = rootX;
                  rank[rootX]++;
              }
          }
      }
  }
  ```
  
  **Real-life Use Cases:**
  - 👥 **Social Networks** - Find connected groups
  - 🌐 **Network Connectivity** - Check if nodes connected
  - 🗺️ **Kruskal's MST** - Detect cycles

---

## 🔷 Part 4: System Design Concepts

### 1. LRU Cache

- [ ] **LRU Cache Implementation**
  ```java
  class LRUCache {
      class Node {
          int key, value;
          Node prev, next;
      }
      
      HashMap<Integer, Node> map;
      Node head, tail;
      int capacity;
      
      LRUCache(int capacity) {
          this.capacity = capacity;
          map = new HashMap<>();
          head = new Node();
          tail = new Node();
          head.next = tail;
          tail.prev = head;
      }
      
      int get(int key) {
          if (!map.containsKey(key)) return -1;
          Node node = map.get(key);
          remove(node);
          addToHead(node);
          return node.value;
      }
      
      void put(int key, int value) {
          if (map.containsKey(key)) {
              Node node = map.get(key);
              node.value = value;
              remove(node);
              addToHead(node);
          } else {
              if (map.size() == capacity) {
                  map.remove(tail.prev.key);
                  remove(tail.prev);
              }
              Node newNode = new Node();
              newNode.key = key;
              newNode.value = value;
              map.put(key, newNode);
              addToHead(newNode);
          }
      }
      
      void remove(Node node) {
          node.prev.next = node.next;
          node.next.prev = node.prev;
      }
      
      void addToHead(Node node) {
          node.next = head.next;
          node.prev = head;
          head.next.prev = node;
          head.next = node;
      }
  }
  ```
  
  **Real-life Use Cases:**
  - 🌐 **Browser Cache** - Recently visited pages
  - 💾 **Database Cache** - Frequently accessed data
  - 📱 **App Memory** - Recent app states

---

## 💻 Interview Preparation Checklist

### 🟢 Problem-Solving Strategy

- [ ] **1. Understand the Problem**
  - Read carefully
  - Ask clarifying questions
  - Identify inputs/outputs
  - Check constraints

- [ ] **2. Think of Approach**
  - Brute force first
  - Identify pattern
  - Choose data structure
  - Optimize

- [ ] **3. Code**
  - Start with pseudocode
  - Handle edge cases
  - Write clean code
  - Use meaningful names

- [ ] **4. Test**
  - Normal cases
  - Edge cases (empty, single element)
  - Large inputs
  - Invalid inputs

- [ ] **5. Analyze**
  - Time complexity
  - Space complexity
  - Can it be optimized?

---

### 🔵 Common Interview Patterns

- [ ] **Two Pointers**
  - Sorted array problems
  - Palindrome checks
  - Pair finding

- [ ] **Sliding Window**
  - Subarray/substring problems
  - Fixed/variable size windows

- [ ] **Fast & Slow Pointers**
  - Cycle detection
  - Middle element
  - Linked list problems

- [ ] **Merge Intervals**
  - Overlapping intervals
  - Meeting rooms
  - Calendar scheduling

- [ ] **Cyclic Sort**
  - Missing numbers
  - Duplicate finding
  - Range [1, n] problems

- [ ] **Binary Search**
  - Sorted arrays
  - Search space reduction
  - First/last occurrence

- [ ] **Top K Elements**
  - Heap/Priority Queue
  - Kth largest/smallest
  - Frequency problems

- [ ] **Modified Binary Search**
  - Rotated arrays
  - Search in 2D matrix
  - Peak element

---

### 🟡 Company-Specific Prep

- [ ] **FAANG Focus Areas**
  - Arrays & Strings (30%)
  - Trees & Graphs (25%)
  - Dynamic Programming (20%)
  - System Design (15%)
  - Others (10%)

- [ ] **Common Questions**
  - Two Sum variations
  - Reverse Linked List
  - Valid Parentheses
  - Binary Tree Traversals
  - LRU Cache
  - Word Search
  - Merge Intervals
  - Longest Substring
  - Coin Change
  - Course Schedule

---

## 💻 Coding Practice (20 Problems)

### 🟢 Bit Manipulation (5)

- [ ] **1. Single Number**
- [ ] **2. Number of 1 Bits**
- [ ] **3. Power of Two**
- [ ] **4. Reverse Bits**
- [ ] **5. Missing Number**

### 🔵 Greedy (5)

- [ ] **6. Jump Game**
- [ ] **7. Gas Station**
- [ ] **8. Task Scheduler**
- [ ] **9. Partition Labels**
- [ ] **10. Minimum Arrows**

### 🟡 Advanced DS (5)

- [ ] **11. LRU Cache**
- [ ] **12. Range Sum Query (Segment Tree)**
- [ ] **13. Number of Islands (Union-Find)**
- [ ] **14. Implement Trie**
- [ ] **15. Design HashMap**

### 🔴 Mixed (5)

- [ ] **16. Median of Two Sorted Arrays**
- [ ] **17. Trapping Rain Water**
- [ ] **18. Serialize/Deserialize Binary Tree**
- [ ] **19. Word Ladder**
- [ ] **20. Alien Dictionary**

---

## ✅ Final Readiness Checklist

- [ ] ✔ **All 10 topics completed**
- [ ] ✔ **150+ problems solved**
- [ ] ✔ **Can explain concepts in Bengali & English**
- [ ] ✔ **Time/Space complexity analysis automatic**
- [ ] ✔ **Mock interviews practiced**
- [ ] ✔ **System design basics understood**
- [ ] ✔ **Behavioral questions prepared**
- [ ] ✔ **Resume updated with projects**

---

## 🎯 Interview Day Tips

1. **Before Interview:**
   - Good sleep
   - Review key concepts
   - Practice 2-3 easy problems

2. **During Interview:**
   - Think aloud
   - Ask questions
   - Start with brute force
   - Optimize step by step
   - Test your code

3. **After Interview:**
   - Note down questions
   - Review mistakes
   - Learn from feedback

---

**Congratulations! 🎉**
আপনি এখন DSA তে interview-ready!

**Remember:**
- Practice > Theory
- Consistency > Intensity
- Understanding > Memorization

**All the best for your interviews! 💪**

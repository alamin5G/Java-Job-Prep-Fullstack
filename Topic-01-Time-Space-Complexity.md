# 📌 TOPIC 1: Time & Space Complexity

## 🎯 Learning Objectives
Master complexity analysis to understand algorithm efficiency - this is the foundation of DSA.

---

## 🔷 Part 1: Big-O Notation Basics

### Concepts to Master:

- [ ] **Big-O কী এবং কেন দরকার**
  ```
  Big-O = Algorithm এর performance measure করার standard way
  
  কেন দরকার?
  - Different algorithms compare করতে
  - Scalability বুঝতে
  - Performance predict করতে
  ```
  
  **Real-life Example:** 🚗 **Car Speed vs Distance**
  - Big-O = Speed (how fast algorithm grows)
  - Input size = Distance
  - Time = How long it takes
  
  **Interview Scenario:**
  ```
  Interviewer: "Your solution works, but what if we have 1 million users?"
  You: "My algorithm is O(n log n), so it will scale well!"
  ```

- [ ] **Worst-case কেন measure করা হয়**
  ```
  Best case: সবসময় হয় না (too optimistic)
  Average case: Calculate করা কঠিন
  Worst case: Guarantee দেয় - "এর চেয়ে খারাপ হবে না"
  ```
  
  **Real-life Example:** 🚦 **Traffic Planning**
  - Best case: No traffic (rare)
  - Average case: Normal traffic (varies)
  - Worst case: Rush hour (plan for this!)
  
  **Interview Tip:** Always analyze worst case unless asked otherwise

- [ ] **Constant factors কীভাবে remove করা হয়**
  ```java
  // Example 1:
  for (int i = 0; i < n; i++) {
      System.out.println(i);
  }
  // Time: 5n operations → O(n)
  
  // Example 2:
  for (int i = 0; i < n; i++) {
      for (int j = 0; j < 100; j++) {
          System.out.println(i + j);
      }
  }
  // Time: 100n operations → O(n)
  
  // Why drop constants?
  // For large n: 5n ≈ 100n ≈ n (growth rate same)
  ```
  
  **Rule:** Drop constant multipliers
  - 5n → O(n)
  - 100n → O(n)
  - n/2 → O(n)
  - 3n² → O(n²)

- [ ] **Highest growing term pick করা**
  ```java
  // Example: n² + 5n + 10
  
  For n = 10:    100 + 50 + 10 = 160
  For n = 100:   10,000 + 500 + 10 = 10,510
  For n = 1000:  1,000,000 + 5,000 + 10 = 1,005,010
  
  // n² dominates! → O(n²)
  ```
  
  **Growth Rate (Slowest to Fastest):**
  ```
  O(1) < O(log n) < O(n) < O(n log n) < O(n²) < O(n³) < O(2ⁿ) < O(n!)
  ```
  
  **Rule:** Keep only the fastest growing term

---

## 🔷 Part 2: Common Complexities (with Intuition)

### Must Memorize with Examples:

- [ ] **O(1) - Constant Time**
  ```java
  // Example 1: Array access
  int getElement(int[] arr, int index) {
      return arr[index];  // Always same time
  }
  
  // Example 2: HashMap get (average)
  HashMap<String, Integer> map = new HashMap<>();
  int value = map.get("key");  // O(1)
  
  // Example 3: Math operation
  int sum = a + b;  // O(1)
  ```
  
  **Real-life Examples:**
  - 📖 **Book Index** - Direct page number lookup
  - 🏦 **ATM PIN Check** - Fixed 4 digits
  - 🎯 **Array Index** - Direct memory access
  
  **Intuition:** Input size বাড়লেও একই সময় লাগে

- [ ] **O(log n) - Logarithmic**
  ```java
  // Example: Binary Search
  int binarySearch(int[] arr, int target) {
      int left = 0, right = arr.length - 1;
      
      while (left <= right) {
          int mid = left + (right - left) / 2;
          
          if (arr[mid] == target) return mid;
          if (arr[mid] < target) left = mid + 1;
          else right = mid - 1;
      }
      return -1;
  }
  
  // Why O(log n)?
  // Each step cuts problem in HALF
  // 1000 → 500 → 250 → 125 → 62 → 31 → 15 → 7 → 3 → 1
  // Only ~10 steps for 1000 elements!
  ```
  
  **Real-life Examples:**
  - 📚 **Dictionary Search** - Open middle, eliminate half
  - 🎯 **Guessing Game** - "Higher/Lower" game
  - 📞 **Phone Book** - Binary search by name
  
  **Intuition:** প্রতিবার problem half হয়ে যায়
  
  **Interview Insight:**
  ```
  n = 1,000 → ~10 steps
  n = 1,000,000 → ~20 steps
  n = 1,000,000,000 → ~30 steps
  VERY FAST! 🚀
  ```

- [ ] **O(n) - Linear**
  ```java
  // Example 1: Find max element
  int findMax(int[] arr) {
      int max = arr[0];
      for (int num : arr) {  // Visit each element once
          if (num > max) max = num;
      }
      return max;
  }
  
  // Example 2: Sum of array
  int sum(int[] arr) {
      int total = 0;
      for (int num : arr) {
          total += num;
      }
      return total;
  }
  ```
  
  **Real-life Examples:**
  - 👥 **Attendance Check** - Call each student's name
  - 🛒 **Shopping Bill** - Add each item's price
  - 📧 **Email Scan** - Check each email for spam
  
  **Intuition:** প্রতিটা element একবার দেখতে হয়
  
  **Scaling:**
  ```
  n = 100 → 100 operations
  n = 1,000 → 1,000 operations
  n = 10,000 → 10,000 operations
  ```

- [ ] **O(n log n) - Linearithmic**
  ```java
  // Example: Merge Sort
  void mergeSort(int[] arr, int left, int right) {
      if (left < right) {
          int mid = (left + right) / 2;
          
          mergeSort(arr, left, mid);      // Divide (log n levels)
          mergeSort(arr, mid + 1, right);
          merge(arr, left, mid, right);   // Merge (n work per level)
      }
  }
  
  // Why O(n log n)?
  // log n levels × n work per level = n log n
  ```
  
  **Real-life Examples:**
  - 📊 **Efficient Sorting** - Merge sort, Quick sort
  - 🎓 **Student Ranking** - Sort by marks
  - 📈 **Data Analysis** - Sort large datasets
  
  **Intuition:** Divide করো (log n) + প্রতি level এ n work
  
  **Best sorting complexity!** (comparison-based)

- [ ] **O(n²) - Quadratic**
  ```java
  // Example 1: Bubble Sort
  void bubbleSort(int[] arr) {
      for (int i = 0; i < arr.length; i++) {        // n times
          for (int j = 0; j < arr.length - 1; j++) { // n times
              if (arr[j] > arr[j + 1]) {
                  swap(arr, j, j + 1);
              }
          }
      }
  }
  
  // Example 2: Print all pairs
  void printPairs(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
          for (int j = 0; j < arr.length; j++) {
              System.out.println(arr[i] + "," + arr[j]);
          }
      }
  }
  ```
  
  **Real-life Examples:**
  - 🤝 **Handshakes** - Everyone shakes hands with everyone
  - 📊 **Compare All Pairs** - Find duplicates (naive)
  - 🎮 **Collision Detection** - Check all object pairs
  
  **Intuition:** Nested loop - প্রতিটা element এর জন্য সব element check
  
  **Scaling:**
  ```
  n = 100 → 10,000 operations
  n = 1,000 → 1,000,000 operations
  n = 10,000 → 100,000,000 operations (SLOW!)
  ```

- [ ] **O(2ⁿ) - Exponential**
  ```java
  // Example: Fibonacci (naive recursion)
  int fibonacci(int n) {
      if (n <= 1) return n;
      return fibonacci(n - 1) + fibonacci(n - 2);  // 2 calls each time
  }
  
  // Tree of calls:
  //           fib(5)
  //          /      \
  //      fib(4)    fib(3)
  //      /   \      /   \
  //   fib(3) fib(2) ...
  
  // Total calls = 2^n (approximately)
  ```
  
  **Real-life Examples:**
  - 🔐 **Password Cracking** - Try all combinations
  - ♟️ **Chess Moves** - Explore all possibilities
  - 🧬 **DNA Sequences** - All possible mutations
  
  **Intuition:** প্রতিটা call থেকে 2টা নতুন call
  
  **Scaling:**
  ```
  n = 10 → 1,024 operations
  n = 20 → 1,048,576 operations
  n = 30 → 1,073,741,824 operations (VERY SLOW!)
  ```
  
  **⚠️ Warning:** Avoid if possible! Use DP instead.

- [ ] **O(n!) - Factorial**
  ```java
  // Example: Generate all permutations
  void permute(String str, String prefix) {
      if (str.length() == 0) {
          System.out.println(prefix);
      } else {
          for (int i = 0; i < str.length(); i++) {
              permute(str.substring(0, i) + str.substring(i + 1),
                      prefix + str.charAt(i));
          }
      }
  }
  
  // For "ABC": 3! = 6 permutations
  // ABC, ACB, BAC, BCA, CAB, CBA
  ```
  
  **Real-life Examples:**
  - 🎭 **Seating Arrangements** - n people in n chairs
  - 🚚 **Traveling Salesman** - Visit all cities
  - 🔢 **Lock Combinations** - All possible orders
  
  **Intuition:** সব possible arrangements
  
  **Scaling:**
  ```
  n = 5 → 120 operations
  n = 10 → 3,628,800 operations
  n = 15 → 1,307,674,368,000 operations (IMPOSSIBLE!)
  ```
  
  **⚠️ Warning:** Only works for very small n!

---

## 🔷 Part 3: Space Complexity

### Concepts to Master:

- [ ] **Auxiliary Space vs Input Space**
  ```java
  // Example 1: O(1) auxiliary space
  void reverseArray(int[] arr) {
      int left = 0, right = arr.length - 1;
      while (left < right) {
          // Only 2 variables (left, right)
          int temp = arr[left];
          arr[left] = arr[right];
          arr[right] = temp;
          left++;
          right--;
      }
  }
  // Input space: O(n) - array itself
  // Auxiliary space: O(1) - only left, right, temp
  
  // Example 2: O(n) auxiliary space
  int[] copyArray(int[] arr) {
      int[] copy = new int[arr.length];  // New array!
      for (int i = 0; i < arr.length; i++) {
          copy[i] = arr[i];
      }
      return copy;
  }
  // Auxiliary space: O(n) - new array
  ```
  
  **Real-life Example:** 📝 **Copying Notes**
  - In-place: Edit same notebook (O(1) space)
  - Copy: Create new notebook (O(n) space)
  
  **Interview Tip:** 
  - "Space complexity" usually means auxiliary space
  - Always clarify with interviewer!

- [ ] **Recursion Stack Space**
  ```java
  // Example 1: Factorial
  int factorial(int n) {
      if (n <= 1) return 1;
      return n * factorial(n - 1);
  }
  
  // Call stack:
  // factorial(5)
  //   factorial(4)
  //     factorial(3)
  //       factorial(2)
  //         factorial(1) → return 1
  
  // Stack depth = n
  // Space Complexity: O(n)
  
  // Example 2: Binary Search (recursive)
  int binarySearch(int[] arr, int left, int right, int target) {
      if (left > right) return -1;
      
      int mid = left + (right - left) / 2;
      if (arr[mid] == target) return mid;
      
      if (arr[mid] > target)
          return binarySearch(arr, left, mid - 1, target);
      return binarySearch(arr, mid + 1, right, target);
  }
  
  // Stack depth = log n (halving each time)
  // Space Complexity: O(log n)
  ```
  
  **Real-life Example:** 📚 **Book Stack**
  - Each function call = একটা book stack এ রাখা
  - Return করলে book সরানো
  - Maximum stack height = space complexity
  
  **Interview Insight:**
  ```
  Iterative solution: Usually O(1) space
  Recursive solution: O(depth) space
  
  Choose iterative if space is critical!
  ```

---

## 🔷 Part 4: How to Analyze Code

### Pattern Recognition:

- [ ] **Single Loop → O(n)**
  ```java
  for (int i = 0; i < n; i++) {
      // constant work
  }
  // Time: O(n)
  ```

- [ ] **Nested Loop → O(n²)**
  ```java
  for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
          // constant work
      }
  }
  // Time: O(n²)
  ```

- [ ] **Loop that Halves/Doubles → O(log n)**
  ```java
  // Halving
  while (n > 0) {
      n = n / 2;
  }
  
  // Doubling
  for (int i = 1; i < n; i *= 2) {
      // work
  }
  // Both: O(log n)
  ```

- [ ] **Combining Components - Add or Multiply**
  ```java
  // Sequential (Add)
  for (int i = 0; i < n; i++) { }  // O(n)
  for (int i = 0; i < m; i++) { }  // O(m)
  // Total: O(n + m)
  
  // Nested (Multiply)
  for (int i = 0; i < n; i++) {      // O(n)
      for (int j = 0; j < m; j++) {  // O(m)
      }
  }
  // Total: O(n × m)
  ```

---

## 🔷 Part 5: Best / Average / Worst Case

### Understanding Different Cases:

- [ ] **কখন কোনটা Relevant**
  ```
  Best Case: Rarely useful (too optimistic)
  Average Case: Realistic but hard to calculate
  Worst Case: Industry standard (safe guarantee)
  ```

- [ ] **Binary Search Cases**
  ```java
  int binarySearch(int[] arr, int target) {
      // Best case: O(1) - target মাঝখানে পাওয়া গেল
      // Average case: O(log n)
      // Worst case: O(log n) - target নেই বা শেষে আছে
  }
  ```

- [ ] **HashMap Cases**
  ```java
  HashMap<Integer, String> map = new HashMap<>();
  
  // Best/Average case: O(1)
  // - Good hash function
  // - No collisions
  
  // Worst case: O(n)
  // - All keys same bucket (collision)
  // - Poor hash function
  // - Need to traverse linked list/tree
  ```

---

## 💻 Coding Practice (10 Mini Tasks)

Complete these to sharpen your analysis skills:

- [ ] **Task 1: Single Loop Complexity**
  ```java
  void printArray(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
          System.out.println(arr[i]);
      }
  }
  // Your answer: Time = O(n), Space = O(1)
  ```

- [ ] **Task 2: Nested Loop O(n²)**
  ```java
  void printPairs(int[] arr) {
      for (int i = 0; i < arr.length; i++) {
          for (int j = 0; j < arr.length; j++) {
              System.out.println(arr[i] + "," + arr[j]);
          }
      }
  }
  // Your answer: Time = O(n²), Space = O(1)
  ```

- [ ] **Task 3: Recursive Code Analysis**
  ```java
  int sum(int n) {
      if (n <= 0) return 0;
      return n + sum(n - 1);
  }
  // Your answer: Time = O(n), Space = O(n) - recursion stack
  ```

- [ ] **Task 4: Binary Search Complexity**
  ```
  Explain why binary search is O(log n):
  - Each step eliminates half the search space
  - n → n/2 → n/4 → n/8 → ... → 1
  - Number of steps = log₂(n)
  ```

- [ ] **Task 5: Independent Loops O(n + m)**
  ```java
  void process(int[] arr1, int[] arr2) {
      for (int i = 0; i < arr1.length; i++) {
          System.out.println(arr1[i]);
      }
      for (int j = 0; j < arr2.length; j++) {
          System.out.println(arr2[j]);
      }
  }
  // Your answer: Time = O(n + m), Space = O(1)
  ```

- [ ] **Task 6: Triple Nested Loop O(n³)**
  ```java
  void threeLevels(int n) {
      for (int i = 0; i < n; i++) {
          for (int j = 0; j < n; j++) {
              for (int k = 0; k < n; k++) {
                  System.out.println(i + j + k);
              }
          }
      }
  }
  // Your answer: Time = O(n³), Space = O(1)
  ```

- [ ] **Task 7: Loop Inside Recursion**
  ```java
  void recursiveLoop(int n) {
      if (n <= 0) return;
      for (int i = 0; i < n; i++) {
          System.out.println(i);
      }
      recursiveLoop(n - 1);
  }
  // Your answer: Time = O(n²), Space = O(n)
  // Why? n + (n-1) + (n-2) + ... + 1 = n(n+1)/2 = O(n²)
  ```

- [ ] **Task 8: HashMap Average vs Worst**
  ```
  1. কখন HashMap O(1) হয়?
     - Good hash function
     - No collisions
     - Normal case
  
  2. কখন HashMap O(n) হয়?
     - All keys hash to same bucket
     - Poor hash function
     - Need to traverse chain
  
  3. Real scenario example:
     - O(1): Normal user lookup by ID
     - O(n): All IDs = 0 (bad data)
  ```

- [ ] **Task 9: Merge Sort O(n log n)**
  ```
  Explain:
  1. কেন Merge Sort O(n log n)?
     - Divide: log n levels (halving)
     - Conquer: n work per level (merging)
     - Total: n × log n
  
  2. log n কোথা থেকে আসে?
     - Tree height = log n
  
  3. n কোথা থেকে আসে?
     - Each level processes all n elements
  
  Diagram:
         [8 elements]           ← n work
        /            \
    [4 elem]      [4 elem]      ← n work
    /    \        /    \
  [2]   [2]    [2]   [2]        ← n work
  / \   / \    / \   / \
 [1][1][1][1][1][1][1][1]       ← n work
  
  Height = log₂(8) = 3 levels
  Total = 3 × 8 = 24 = O(n log n)
  ```

- [ ] **Task 10: Space Complexity Examples**
  ```java
  // Example 1:
  int findMax(int[] arr) {
      int max = arr[0];
      for (int num : arr) {
          if (num > max) max = num;
      }
      return max;
  }
  // Space = O(1) - only 'max' variable
  
  // Example 2:
  int[] createCopy(int[] arr) {
      int[] copy = new int[arr.length];
      for (int i = 0; i < arr.length; i++) {
          copy[i] = arr[i];
      }
      return copy;
  }
  // Space = O(n) - new array of size n
  
  // Example 3:
  void printFibonacci(int n) {
      if (n <= 1) {
          System.out.println(n);
          return;
      }
      printFibonacci(n - 1);
      printFibonacci(n - 2);
  }
  // Space = O(n) - maximum recursion depth
  ```

---

## ✅ Move-On Criteria (Topic 1 Complete)

**আপনি Topic 2 তে যেতে পারবেন যখন:**

- [ ] ✔ **যেকোনো কোড দেখে 30 সেকেন্ডে time complexity বলতে পারবেন**
  - Test yourself: 5টা random code snippet analyze করো
  - log n, n log n, n² confidently identify করতে পারবে

- [ ] ✔ **Space complexity নিজে থেকে explain করতে পারবেন**
  - Recursion এ stack space কেন বাড়ে → বলতে পারবেন
  - Auxiliary vs Input space difference বুঝবেন

- [ ] ✔ **Common complexities example দিয়ে explain করতে পারবেন**
  - O(1), O(log n), O(n), O(n log n), O(n²), O(2ⁿ), O(n!)
  - নিজের ভাষায়, bookish নয়
  - প্রতিটার জন্য 1টা করে real code example দিতে পারবেন

- [ ] ✔ **Loop → Nested → Log n patterns instantly চিনে ফেলবেন**
  - Code দেখামাত্র pattern recognize করতে পারবেন
  - এটা DSA এর অর্ধেক কাজ easy করে দেয়

- [ ] ✔ **HashMap average vs worst case verbally explain করতে পারবেন**
  - কখন O(1), কখন O(n)
  - Collision কি, কেন হয়
  - Real-world scenario example

---

**Real-World Applications:**
- 🚀 Algorithm selection
- 📊 Performance optimization
- 💾 Memory management
- 🎯 Scalability planning
- 💼 Technical interviews

**Next:** [Topic 2: Arrays Mastery →](Topic-02-Arrays.md)

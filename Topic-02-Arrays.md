# 📌 TOPIC 2: Arrays Mastery

## 🎯 Learning Objectives
Arrays হলো DSA এর সবচেয়ে গুরুত্বপূর্ণ ভিত্তি। এটা master করলে পরের সব topics সহজ হবে।

---

## 🎯 8 Array Patterns - Quick Reference

> **Master these 8 patterns → Solve 200+ array problems!**

### Pattern Recognition Checklist

```
┌─────────────────────────────────────────────────────────────┐
│  PROBLEM KEYWORDS → PATTERN                                  │
├─────────────────────────────────────────────────────────────┤
│  ✅ "sorted array" + "pair/triplet" → TWO POINTERS          │
│  ✅ "subarray" + "consecutive" → SLIDING WINDOW              │
│  ✅ "cycle detection" + "linked list" → FAST & SLOW          │
│  ✅ "overlapping intervals" → MERGE INTERVALS                │
│  ✅ "array 1 to n" + "missing" → CYCLIC SORT                 │
│  ✅ "range sum" + "multiple queries" → PREFIX SUM            │
│  ✅ "count frequency" + "anagram" → FREQUENCY COUNTER        │
│  ✅ "next greater/smaller" → MONOTONIC STACK                 │
└─────────────────────────────────────────────────────────────┘
```

### Visual Pattern Map

```
ARRAY PATTERNS (8)
│
├─ 🔵 PATTERN 1: Two Pointers
│   └─ 🧠 Memory Trick: "Two friends walking towards each other"
│   └─ ⏰ Time: O(n) | Space: O(1)
│   └─ 🎯 Use: Sorted arrays, pairs, palindromes
│
├─ 🟢 PATTERN 2: Sliding Window
│   └─ 🧠 Memory Trick: "Train window - add new, remove old"
│   └─ ⏰ Time: O(n) | Space: O(1) or O(k)
│   └─ 🎯 Use: Subarrays, consecutive elements, max/min
│
├─ 🟡 PATTERN 3: Fast & Slow Pointers
│   └─ 🧠 Memory Trick: "Tortoise and Hare race"
│   └─ ⏰ Time: O(n) | Space: O(1)
│   └─ 🎯 Use: Cycle detection, middle element
│
├─ 🟣 PATTERN 4: Merge Intervals
│   └─ 🧠 Memory Trick: "Overlapping meetings - combine them"
│   └─ ⏰ Time: O(n log n) | Space: O(n)
│   └─ 🎯 Use: Intervals, scheduling, time ranges
│
├─ 🔴 PATTERN 5: Cyclic Sort
│   └─ 🧠 Memory Trick: "Put each number in its home (index)"
│   └─ ⏰ Time: O(n) | Space: O(1)
│   └─ 🎯 Use: Arrays with 1 to n, missing numbers
│
├─ 🟠 PATTERN 6: Prefix Sum
│   └─ 🧠 Memory Trick: "Running total - like bank balance"
│   └─ ⏰ Time: O(1) query | Space: O(n)
│   └─ 🎯 Use: Range queries, subarray sums
│
├─ ⚫ PATTERN 7: Frequency Counter
│   └─ 🧠 Memory Trick: "HashMap = Attendance register"
│   └─ ⏰ Time: O(n) | Space: O(n)
│   └─ 🎯 Use: Counting, anagrams, duplicates
│
└─ ⚪ PATTERN 8: Monotonic Stack
    └─ 🧠 Memory Trick: "Stack of plates - always increasing/decreasing"
    └─ ⏰ Time: O(n) | Space: O(n)
    └─ 🎯 Use: Next greater/smaller, histogram
```

### Quick Decision Tree

```
START: Array Problem
    │
    ├─ Is array SORTED?
    │   ├─ YES → Finding pairs/triplets?
    │   │   └─ YES → ✅ TWO POINTERS
    │   └─ NO → Continue...
    │
    ├─ Need CONSECUTIVE elements?
    │   ├─ YES → Max/min in window?
    │   │   └─ YES → ✅ SLIDING WINDOW
    │   └─ NO → Continue...
    │
    ├─ Multiple RANGE SUM queries?
    │   └─ YES → ✅ PREFIX SUM
    │
    ├─ Need to COUNT occurrences?
    │   └─ YES → ✅ FREQUENCY COUNTER
    │
    ├─ Array contains 1 to n?
    │   └─ YES → ✅ CYCLIC SORT
    │
    ├─ Find NEXT greater/smaller?
    │   └─ YES → ✅ MONOTONIC STACK
    │
    ├─ OVERLAPPING intervals?
    │   └─ YES → ✅ MERGE INTERVALS
    │
    └─ CYCLE detection?
        └─ YES → ✅ FAST & SLOW POINTERS
```

### Memorization Mnemonics

**Remember: "TSFMCPFM" (Two Sliding Fast Merge Cyclic Prefix Frequency Monotonic)**

```
T - Two Pointers        → "Two friends meet"
S - Sliding Window      → "Sliding train window"
F - Fast & Slow         → "Fable of tortoise & hare"
M - Merge Intervals     → "Merge overlapping meetings"
C - Cyclic Sort         → "Circle back to home"
P - Prefix Sum          → "Prefix = Previous + current"
F - Frequency Counter   → "Frequency = How many times?"
M - Monotonic Stack     → "Monotonic = Always increasing/decreasing"
```

---

## 🔷 Part 1: Array Fundamentals

### Concepts to Master:

- [ ] **Array কীভাবে Memory-তে Stored হয়**
  ```java
  int[] arr = new int[5];  // Contiguous memory allocation
  // Memory: [0][1][2][3][4] - পাশাপাশি
  // Address: 1000, 1004, 1008, 1012, 1016 (4 bytes each for int)
  
  arr[0] = 10;  // Address 1000
  arr[1] = 20;  // Address 1004
  arr[2] = 30;  // Address 1008
  ```
  
  **Real-life Example:** 🏘️ **Row Houses**
  - Houses পাশাপাশি (contiguous)
  - House number = index
  - Direct access to any house
  
  **Key Points:**
  - ✅ Contiguous memory (একটানা)
  - ✅ Fixed size (size পরে change করা যায় না)
  - ✅ Index দিয়ে direct access
  - ✅ Same data type

- [ ] **Static vs Dynamic Array**
  ```java
  // Static Array (Java)
  int[] staticArr = new int[5];  // Size fixed = 5
  // Cannot add 6th element!
  
  // Dynamic Array (ArrayList)
  ArrayList<Integer> dynamicArr = new ArrayList<>();
  dynamicArr.add(10);  // Size = 1
  dynamicArr.add(20);  // Size = 2
  dynamicArr.add(30);  // Size = 3
  // Size automatically বাড়ে!
  
  // How ArrayList grows:
  // 1. Create new array (2x size)
  // 2. Copy all elements
  // 3. Add new element
  ```
  
  **Comparison:**
  
  | Feature | Static Array | Dynamic Array (ArrayList) |
  |---------|-------------|---------------------------|
  | Size | Fixed | Grows automatically |
  | Memory | Efficient | Extra overhead |
  | Access | O(1) | O(1) |
  | Insert (end) | O(1) | O(1) amortized |
  | Resize | Not possible | Automatic |
  
  **Real-life Examples:**
  - **Static:** Movie theater seats (fixed)
  - **Dynamic:** WhatsApp group (members can join)

- [ ] **কেন Indexing O(1)**
  ```java
  int[] arr = new int[100];
  int value = arr[50];  // O(1) - কেন?
  
  // Formula: address = base_address + (index × element_size)
  // Example: 1000 + (50 × 4) = 1200
  // Direct calculation - no loop needed!
  
  // Compare with Linked List:
  // Linked List: Start from head, traverse 50 nodes → O(n)
  // Array: Direct calculation → O(1)
  ```
  
  **Real-life Example:** 🏢 **Apartment Building**
  - Floor number = index
  - Elevator goes directly to floor
  - No need to visit each floor

- [ ] **Insert/Delete - End vs Middle Cost**
  ```java
  // Insert at End: O(1)
  int[] arr = {1, 2, 3, 4, 0};
  arr[4] = 5;  // Direct placement
  
  // Insert at Middle: O(n)
  // {1, 2, 3, 4, 5} → insert 10 at index 2
  // Need to shift: 3, 4, 5 → right
  // Result: {1, 2, 10, 3, 4, 5}
  
  void insertAtMiddle(int[] arr, int index, int value, int size) {
      // Shift elements right
      for (int i = size - 1; i > index; i--) {
          arr[i] = arr[i - 1];
      }
      arr[index] = value;
  }
  // Time: O(n) - worst case shift all elements
  
  // Delete from Middle: O(n)
  void deleteAtMiddle(int[] arr, int index, int size) {
      // Shift elements left
      for (int i = index; i < size - 1; i++) {
          arr[i] = arr[i + 1];
      }
  }
  ```
  
  **Real-life Example:** 🚂 **Train Coaches**
  - Add coach at end: Easy (O(1))
  - Add coach in middle: Disconnect, insert, reconnect (O(n))

---

## 🔷 Part 2: 🔵 PATTERN 1 - Two Pointers Technique

> **🧠 Memory Trick:** "Two friends walking towards each other from opposite ends"
> 
> **⏰ Complexity:** Time O(n) | Space O(1)
> 
> **🎯 When to Use:** Sorted arrays, finding pairs, palindromes, partitioning

### Core Philosophy:
দুইটা pointer use করে array traverse করা - usually opposite ends থেকে বা same direction এ।

### Pattern Template:
```java
// Template 1: Opposite Direction (Most Common)
int left = 0;
int right = arr.length - 1;

while (left < right) {
    if (condition_met) {
        // Process and move both
        left++;
        right--;
    } else if (need_larger_value) {
        left++;  // Move left pointer right
    } else {
        right--; // Move right pointer left
    }
}

// Template 2: Same Direction
int slow = 0;
for (int fast = 0; fast < arr.length; fast++) {
    if (condition) {
        arr[slow] = arr[fast];
        slow++;
    }
}
```

### Concepts to Master:

- [ ] **Left-Right Pointer (Opposite Direction)**
  ```java
  // Example: Reverse array
  void reverseArray(int[] arr) {
      int left = 0;
      int right = arr.length - 1;
      
      while (left < right) {
          // Swap
          int temp = arr[left];
          arr[left] = arr[right];
          arr[right] = temp;
          
          left++;   // Move towards center
          right--;  // Move towards center
      }
  }
  // Time: O(n), Space: O(1)
  ```
  
  **Real-life Example:** 🤝 **Two People Meeting**
  - Start from opposite ends of a line
  - Walk towards each other
  - Meet in the middle

- [ ] **কখন Two Pointers ব্যবহার করতে হয়**
  
  **Use Cases:**
  - ✅ Sorted array তে search/manipulation
  - ✅ Pair finding problems
  - ✅ Array reversal
  - ✅ Removing duplicates
  - ✅ Partitioning problems
  
  **Pattern Recognition:**
  ```
  If problem involves:
  - Sorted array
  - Finding pairs
  - Comparing elements from both ends
  → Think TWO POINTERS!
  ```

- [ ] **Collision Approach**
  ```java
  // Example: Two Sum (sorted array)
  int[] twoSum(int[] arr, int target) {
      int left = 0;
      int right = arr.length - 1;
      
      while (left < right) {
          int sum = arr[left] + arr[right];
          
          if (sum == target) {
              return new int[]{left, right};
          } else if (sum < target) {
              left++;  // Need bigger sum
          } else {
              right--;  // Need smaller sum
          }
      }
      return new int[]{-1, -1};
  }
  // Time: O(n), Space: O(1)
  ```
  
  **Real-life Example:** 🎯 **Budget Shopping**
  - Target budget = 100 tk
  - Cheapest item (left) + Most expensive item (right)
  - Too cheap? Pick more expensive
  - Too expensive? Pick cheaper

- [ ] **Expand-Shrink Logic**
  ```java
  // Example: Container with most water
  int maxArea(int[] height) {
      int left = 0, right = height.length - 1;
      int maxArea = 0;
      
      while (left < right) {
          int width = right - left;
          int h = Math.min(height[left], height[right]);
          maxArea = Math.max(maxArea, width * h);
          
          // Move pointer with smaller height
          // (taller one might give better result with other partner)
          if (height[left] < height[right]) {
              left++;
          } else {
              right--;
          }
      }
      return maxArea;
  }
  ```
  
  **Real-life Example:** 💧 **Water Tank**
  - Two walls, water between them
  - Height limited by shorter wall
  - Move shorter wall to find better option

---

## 🔷 Part 3: Sliding Window

### Core Philosophy:
একটা window maintain করো যা expand বা shrink হয় conditions অনুযায়ী।

### Concepts to Master:

- [ ] **Fixed-Size Window**
  ```java
  // Example: Max sum of k consecutive elements
  int maxSumFixedWindow(int[] arr, int k) {
      // Step 1: Calculate first window sum
      int windowSum = 0;
      for (int i = 0; i < k; i++) {
          windowSum += arr[i];
      }
      
      int maxSum = windowSum;
      
      // Step 2: Slide window
      for (int i = k; i < arr.length; i++) {
          windowSum += arr[i];        // Add new element (right)
          windowSum -= arr[i - k];    // Remove old element (left)
          maxSum = Math.max(maxSum, windowSum);
      }
      
      return maxSum;
  }
  // Time: O(n), Space: O(1)
  
  // Without sliding window: O(n*k)
  // With sliding window: O(n) - MUCH BETTER!
  ```
  
  **Real-life Example:** 🪟 **Moving Window View**
  - Train window shows k houses at a time
  - Train moves: new house appears, old disappears
  - Don't recount all houses, just update!

- [ ] **Variable-Size Window**
  ```java
  // Example: Longest substring without repeating characters
  int lengthOfLongestSubstring(String s) {
      HashSet<Character> set = new HashSet<>();
      int left = 0, maxLen = 0;
      
      for (int right = 0; right < s.length(); right++) {
          // Shrink window if duplicate found
          while (set.contains(s.charAt(right))) {
              set.remove(s.charAt(left));
              left++;  // Shrink from left
          }
          
          // Expand window
          set.add(s.charAt(right));
          maxLen = Math.max(maxLen, right - left + 1);
      }
      
      return maxLen;
  }
  ```
  
  **Real-life Example:** 🎒 **Backpack Packing**
  - Add items (expand)
  - Too heavy? Remove items (shrink)
  - Find maximum items within weight limit

- [ ] **Window Expand/Shrink Rules**
  ```
  Template:
  
  left = 0
  for right in range:
      add arr[right] to window
      
      while (window invalid):
          remove arr[left] from window
          left++
      
      update result
  ```
  
  **Rules:**
  - **Expand:** right pointer সবসময় এগোয়
  - **Shrink:** condition violate হলে left এগোয়
  - **Update:** valid window পেলে result update

- [ ] **Sliding Window কখন Applicable**
  
  **✅ Use Sliding Window When:**
  - Subarray/substring problems
  - Consecutive elements
  - Optimization problems (max/min)
  - "Longest/shortest/maximum" keywords
  
  **❌ Don't Use When:**
  - Non-consecutive elements needed
  - Sorting required
  - Need to modify array structure

---

## 🔷 Part 4: Prefix Sum

### Core Philosophy:
Pre-calculate cumulative sum to answer range queries in O(1).

### Concepts to Master:

- [ ] **Prefix Sum কীভাবে কাজ করে**
  ```java
  // Original array: [2, 4, 6, 8, 10]
  // Prefix sum:     [2, 6, 12, 20, 30]
  
  int[] buildPrefixSum(int[] arr) {
      int[] prefix = new int[arr.length];
      prefix[0] = arr[0];
      
      for (int i = 1; i < arr.length; i++) {
          prefix[i] = prefix[i - 1] + arr[i];
      }
      
      return prefix;
  }
  
  // prefix[i] = sum of elements from 0 to i
  ```
  
  **Real-life Example:** 📊 **Running Total**
  - Day 1: Earned 100 tk (Total: 100)
  - Day 2: Earned 200 tk (Total: 300)
  - Day 3: Earned 150 tk (Total: 450)
  - Prefix sum = Running total

- [ ] **Range Sum Queries**
  ```java
  // Query: sum from index L to R
  int rangeSum(int[] prefix, int L, int R) {
      if (L == 0) {
          return prefix[R];
      }
      return prefix[R] - prefix[L - 1];
  }
  
  // Example: sum from index 2 to 4
  // Array: [2, 4, 6, 8, 10]
  // Prefix: [2, 6, 12, 20, 30]
  // Sum(2 to 4) = prefix[4] - prefix[1] = 30 - 6 = 24
  // Verify: 6 + 8 + 10 = 24 ✓
  
  // Time: O(1) instead of O(n)!
  ```
  
  **Real-life Example:** 💰 **Salary Calculation**
  - Total earned till December - Total earned till March
  - = Earnings from April to December

- [ ] **Negative Number থাকলে Prefix Sum Handle**
  ```java
  // Works same way!
  int[] arr = {-2, 3, -1, 4, -5};
  int[] prefix = buildPrefixSum(arr);
  // prefix = [-2, 1, 0, 4, -1]
  
  // Range sum still works
  int sum = rangeSum(prefix, 1, 3);  
  // = prefix[3] - prefix[0]
  // = 4 - (-2) = 6
  // Verify: 3 + (-1) + 4 = 6 ✓
  ```

---

## 💻 Coding Practice (20 Problems)

### 🟢 Basic Problems (5)

- [ ] **1. Reverse Array**
  ```java
  // Input: [1, 2, 3, 4, 5]
  // Output: [5, 4, 3, 2, 1]
  // Technique: Two pointers
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Reverse chronological order

- [ ] **2. Find Max/Min Element**
  ```java
  // Input: [3, 7, 1, 9, 2]
  // Output: Max = 9, Min = 1
  // Technique: Single loop
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Find highest/lowest price

- [ ] **3. Move Zeros to End**
  ```java
  // Input: [0, 1, 0, 3, 12]
  // Output: [1, 3, 12, 0, 0]
  // Technique: Two pointers
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Filter out empty values

- [ ] **4. Remove Duplicates (Sorted Array)**
  ```java
  // Input: [1, 1, 2, 2, 3, 4, 4]
  // Output: [1, 2, 3, 4] (length = 4)
  // Technique: Two pointers
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Clean sorted data

- [ ] **5. Rotate Array**
  ```java
  // Input: [1,2,3,4,5,6,7], k=3
  // Output: [5,6,7,1,2,3,4]
  // Technique: Reverse subarrays
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Circular shift

---

### 🔵 Two Pointers Problems (5)

- [ ] **6. Two Sum (Sorted Array)**
  ```java
  // Input: arr = [2, 7, 11, 15], target = 9
  // Output: [0, 1] (2 + 7 = 9)
  // Technique: Left-right pointers
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Budget allocation

- [ ] **7. Merge Two Sorted Arrays**
  ```java
  // Input: arr1 = [1, 3, 5], arr2 = [2, 4, 6]
  // Output: [1, 2, 3, 4, 5, 6]
  // Technique: Two pointers (one per array)
  // Time: O(n + m), Space: O(n + m)
  ```
  **Use case:** Merge sorted logs

- [ ] **8. Container With Most Water**
  ```java
  // Input: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
  // Output: 49
  // Technique: Left-right pointers with area calculation
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Water storage optimization

- [ ] **9. Valid Palindrome**
  ```java
  // Input: "A man, a plan, a canal: Panama"
  // Output: true
  // Technique: Two pointers (ignore non-alphanumeric)
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Symmetric pattern detection

- [ ] **10. Sort Colors (Dutch Flag)**
  ```java
  // Input: [2,0,2,1,1,0]
  // Output: [0,0,1,1,2,2]
  // Technique: Three pointers
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Categorize items

---

### 🟡 Sliding Window Problems (5)

- [ ] **11. Max Sum Subarray (Size K)**
  ```java
  // Input: arr = [2, 1, 5, 1, 3, 2], k = 3
  // Output: 9 (subarray [5, 1, 3])
  // Technique: Fixed-size sliding window
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Moving average

- [ ] **12. Longest Substring Without Repeating**
  ```java
  // Input: "abcabcbb"
  // Output: 3 ("abc")
  // Technique: Variable-size window + HashSet
  // Time: O(n), Space: O(min(n, charset))
  ```
  **Use case:** Password strength

- [ ] **13. Longest Substring with K Distinct Characters**
  ```java
  // Input: s = "eceba", k = 2
  // Output: 3 ("ece")
  // Technique: Variable window + HashMap
  // Time: O(n), Space: O(k)
  ```
  **Use case:** Text analysis

- [ ] **14. Minimum Window Substring**
  ```java
  // Input: s = "ADOBECODEBANC", t = "ABC"
  // Output: "BANC"
  // Technique: Variable window + frequency map
  // Time: O(n), Space: O(1) - fixed charset
  ```
  **Use case:** Search optimization

- [ ] **15. Sliding Window Maximum**
  ```java
  // Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
  // Output: [3,3,5,5,6,7]
  // Technique: Deque + sliding window
  // Time: O(n), Space: O(k)
  ```
  **Use case:** Real-time data analysis

---

### 🟣 Prefix Sum + Misc (5)

- [ ] **16. Build Prefix Sum Array**
  ```java
  // Input: [1, 2, 3, 4, 5]
  // Output: [1, 3, 6, 10, 15]
  // Technique: Cumulative sum
  // Time: O(n), Space: O(n)
  ```
  **Use case:** Running totals

- [ ] **17. Subarray Sum Equals K**
  ```java
  // Input: nums = [1, 1, 1], k = 2
  // Output: 2 (subarrays [1,1] at two positions)
  // Technique: Prefix sum + HashMap
  // Time: O(n), Space: O(n)
  ```
  **Use case:** Budget tracking

- [ ] **18. Equilibrium Index**
  ```java
  // Input: [-7, 1, 5, 2, -4, 3, 0]
  // Output: 3 (sum left = sum right)
  // Technique: Prefix sum
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Balance point

- [ ] **19. Product of Array Except Self**
  ```java
  // Input: [1,2,3,4]
  // Output: [24,12,8,6]
  // Technique: Prefix/suffix product
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Contribution analysis

- [ ] **20. Kadane's Algorithm (Max Subarray Sum)**
  ```java
  // Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
  // Output: 6 (subarray [4, -1, 2, 1])
  // Technique: Dynamic programming / greedy
  // Time: O(n), Space: O(1)
  ```
  **Use case:** Maximum profit period

---

## ✅ Move-On Criteria (Topic 2 Complete)

**আপনি Topic 3 তে যেতে পারবেন যখন:**

- [ ] ✔ **Two Pointers কখন ব্যবহার করতে হয় - Examples ছাড়াই explain করতে পারবেন**
  - Sorted array problems
  - Pair finding
  - নিজের ভাষায় বলতে পারবেন

- [ ] ✔ **Sliding Window 5 problems solve within 20 mins**
  - Fixed + variable window দুটোই
  - Pattern instantly recognize করতে পারবেন

- [ ] ✔ **Prefix Sum diagram সহ ব্যাখ্যা করতে পারবেন**
  - কেন O(1) range query possible
  - Whiteboard এ draw করে explain করতে পারবেন

- [ ] ✔ **Two Pointers vs Sliding Window - Perfect Differentiation**
  - কখন কোনটা use করবেন
  - Key differences বলতে পারবেন

- [ ] ✔ **Kadane + Two Pointers + Sliding Window - Without Google solve করতে পারবেন**
  - Problem দেখামাত্র technique identify
  - Code from scratch লিখতে পারবেন

---

**Real-World Applications:**
- 📊 Data analytics
- 💹 Stock market analysis
- 🎯 Budget optimization
- 📈 Performance monitoring
- 🔍 Pattern detection

**Next:** [Topic 3: Strings →](Topic-03-Strings.md)

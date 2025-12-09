# 📌 DSA Quick Reference Guide

> **Quick lookup for all topics - Interview cheat sheet**

---

## Topic 1: Time & Space Complexity ⏱️

### Big-O Complexities (Best to Worst)
```
O(1)      - Constant      - Array access
O(log n)  - Logarithmic   - Binary search
O(n)      - Linear        - Single loop
O(n log n)- Linearithmic  - Merge sort
O(n²)     - Quadratic     - Nested loops
O(2ⁿ)     - Exponential   - Fibonacci (naive)
O(n!)     - Factorial     - Permutations
```

**Real-life:** Understanding algorithm efficiency

---

## Topic 2: Arrays 📊

### Key Techniques
- **Two Pointers:** Sorted arrays, pair finding
- **Sliding Window:** Subarray/substring problems
- **Prefix Sum:** Range queries in O(1)

**Real-life:** Data analytics, stock prices

---

## Topic 3: Strings 📝

### Key Patterns
- **Anagrams:** Frequency counting
- **Palindrome:** Two pointers
- **Substring:** Sliding window
- **Pattern Matching:** KMP algorithm

**Real-life:** Search engines, text editors, spell checkers

---

## Topic 4: Linked Lists 🔗

### Key Operations
- **Reverse:** Iterative (3 pointers)
- **Cycle Detection:** Fast-slow pointers
- **Middle Element:** Fast-slow pointers
- **Merge:** Two pointers

**Real-life:** Music playlists, browser history

---

## Topic 5: Stacks & Queues 📚🎫

### Applications
- **Stack (LIFO):** Function calls, undo/redo, parentheses
- **Queue (FIFO):** BFS, task scheduling, print queue
- **Priority Queue:** Top K, scheduling

**Real-life:** Browser back button, printer queue

---

## Topic 6: Recursion & Backtracking 🔄

### Template
```java
void backtrack(params) {
    if (base case) return;
    
    for (each choice) {
        make choice;
        backtrack(updated params);
        undo choice;  // BACKTRACK
    }
}
```

**Real-life:** Maze solving, Sudoku, N-Queens

---

## Topic 7: Trees 🌳

### Traversals
- **Inorder:** Left → Root → Right (BST = sorted)
- **Preorder:** Root → Left → Right (copy tree)
- **Postorder:** Left → Right → Root (delete tree)
- **Level Order:** BFS (level by level)

**Real-life:** File systems, autocomplete, org charts

---

## Topic 8: Graphs 🗺️

### Algorithms
- **BFS:** Shortest path (unweighted), level-wise
- **DFS:** Cycle detection, topological sort
- **Dijkstra:** Shortest path (weighted, positive)
- **Topological Sort:** Task dependencies

**Real-life:** GPS navigation, social networks

---

## Topic 9: Dynamic Programming 💡

### Approach
```
DP = Recursion + Memoization
1. Identify overlapping subproblems
2. Define state
3. Write recurrence relation
4. Memoize or tabulate
```

**Common Problems:** Knapsack, LCS, Coin Change

**Real-life:** Investment planning, spell checker

---

## Topic 10: Advanced Topics 🎯

### Key Concepts
- **Bit Manipulation:** XOR tricks, power of 2
- **Greedy:** Local optimum → global optimum
- **Union-Find:** Connected components
- **LRU Cache:** Doubly linked list + HashMap

**Real-life:** Encryption, file compression, browser cache

---

## 🎯 Interview Problem-Solving Framework

### Step 1: Understand
- Read problem carefully
- Identify inputs/outputs
- Ask clarifying questions
- Check constraints

### Step 2: Plan
- Think of brute force
- Identify pattern/technique
- Choose data structure
- Estimate complexity

### Step 3: Code
- Start with pseudocode
- Write clean code
- Handle edge cases
- Use meaningful names

### Step 4: Test
- Normal cases
- Edge cases (empty, single, large)
- Walk through code
- Fix bugs

### Step 5: Optimize
- Analyze complexity
- Discuss trade-offs
- Suggest improvements

---

## 🔑 Common Patterns

| Pattern | When to Use | Example |
|---------|-------------|---------|
| Two Pointers | Sorted array, pairs | Two Sum |
| Sliding Window | Subarray/substring | Longest Substring |
| Fast-Slow | Cycle, middle | Linked List Cycle |
| Binary Search | Sorted, search space | Search Insert |
| BFS | Shortest path, levels | Word Ladder |
| DFS | All paths, backtrack | N-Queens |
| DP | Optimization, counting | Coin Change |
| Greedy | Local optimum | Activity Selection |
| Heap | Top K, priority | Kth Largest |
| Union-Find | Connected components | Number of Islands |

---

## 💡 Quick Tips

### Before Interview
- ✅ Sleep well
- ✅ Review key concepts
- ✅ Practice 2-3 easy problems
- ✅ Prepare questions for interviewer

### During Interview
- ✅ Think aloud
- ✅ Ask questions
- ✅ Start simple, then optimize
- ✅ Test your code
- ✅ Discuss trade-offs

### After Interview
- ✅ Note down questions
- ✅ Review mistakes
- ✅ Learn and improve

---

## 📊 Complexity Cheat Sheet

### Common Operations

| Data Structure | Access | Search | Insert | Delete |
|----------------|--------|--------|--------|--------|
| Array | O(1) | O(n) | O(n) | O(n) |
| Linked List | O(n) | O(n) | O(1)* | O(1)* |
| Stack | O(n) | O(n) | O(1) | O(1) |
| Queue | O(n) | O(n) | O(1) | O(1) |
| Hash Table | - | O(1)† | O(1)† | O(1)† |
| BST | O(log n)‡ | O(log n)‡ | O(log n)‡ | O(log n)‡ |
| Heap | - | O(n) | O(log n) | O(log n) |

*if position known | †average case | ‡balanced tree

---

## 🎯 Top 20 Must-Know Problems

1. Two Sum
2. Reverse Linked List
3. Valid Parentheses
4. Maximum Subarray (Kadane)
5. Merge Two Sorted Lists
6. Binary Tree Inorder Traversal
7. Climbing Stairs
8. Best Time to Buy/Sell Stock
9. Longest Substring Without Repeating
10. 3Sum
11. Container With Most Water
12. Number of Islands
13. Coin Change
14. LRU Cache
15. Word Search
16. Course Schedule
17. Merge Intervals
18. Longest Palindromic Substring
19. Serialize/Deserialize Binary Tree
20. Trapping Rain Water

---

**Keep this handy during practice and interviews! 📖**

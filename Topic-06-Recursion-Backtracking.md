# 📌 TOPIC 6: Recursion & Backtracking

## 🎯 Learning Objectives
Recursion হলো problem-solving এর powerful technique। Backtracking দিয়ে complex problems solve করা যায়।

---

## 🎯 2 Recursion Patterns - Quick Reference

> **Master these 2 patterns → Solve 100+ recursion problems!**

### Pattern Recognition Checklist

```
┌─────────────────────────────────────────────────────────────┐
│  PROBLEM KEYWORDS → PATTERN                                  │
├─────────────────────────────────────────────────────────────┤
│  ✅ "all combinations" + "permutations" → BACKTRACKING      │
│  ✅ "split problem" + "merge results" → DIVIDE & CONQUER    │
└─────────────────────────────────────────────────────────────┘
```

### Visual Pattern Map

```
RECURSION PATTERNS (2)
│
├─ 🔵 PATTERN 24: Backtracking
│   └─ 🧠 Memory Trick: "Try all paths, backtrack if dead end"
│   └─ ⏰ Time: O(2^n) or O(n!) | Space: O(n)
│   └─ 🎯 Use: Combinations, permutations, subsets, N-Queens
│
└─ 🟢 PATTERN 25: Divide & Conquer
    └─ 🧠 Memory Trick: "Break problem, solve parts, combine results"
    └─ ⏰ Time: O(n log n) typically | Space: O(log n)
    └─ 🎯 Use: Merge sort, quick sort, binary search
```

---

## 🔷 Part 1: Recursion Fundamentals

### Concepts to Master:

- [ ] **Recursion কী**
  ```java
  // Function যা নিজেকে call করে
  int factorial(int n) {
      // Base case
      if (n == 0 || n == 1) {
          return 1;
      }
      // Recursive case
      return n * factorial(n - 1);
  }
  
  // factorial(5) = 5 * factorial(4)
  //              = 5 * 4 * factorial(3)
  //              = 5 * 4 * 3 * factorial(2)
  //              = 5 * 4 * 3 * 2 * factorial(1)
  //              = 5 * 4 * 3 * 2 * 1 = 120
  ```
  
  **Real-life Example:** 🪆 **Russian Matryoshka Dolls**
  - বড় doll এর ভিতরে ছোট doll
  - ছোট doll এর ভিতরে আরও ছোট doll
  - Base case: সবচেয়ে ছোট doll (আর খোলা যায় না)

- [ ] **Recursion এর 3টি Rules**
  
  **1. Base Case (Stopping Condition)**
  ```java
  if (n == 0) return 1;  // Must have!
  ```
  - Base case না থাকলে infinite loop
  
  **2. Recursive Case (Problem Breakdown)**
  ```java
  return n * factorial(n - 1);
  ```
  - Problem কে ছোট করে same problem solve করা
  
  **3. Progress Towards Base Case**
  ```java
  factorial(n - 1)  // n কমছে, base case এর দিকে যাচ্ছে
  ```

- [ ] **Recursion vs Iteration**
  ```java
  // Recursive
  int sumRecursive(int n) {
      if (n == 0) return 0;
      return n + sumRecursive(n - 1);
  }
  
  // Iterative
  int sumIterative(int n) {
      int sum = 0;
      for (int i = 1; i <= n; i++) {
          sum += i;
      }
      return sum;
  }
  ```
  
  **When to use Recursion:**
  - ✅ Tree/Graph traversal
  - ✅ Divide and conquer
  - ✅ Backtracking problems
  - ❌ Simple loops (overhead বেশি)

---

## 🔷 Part 2: Classic Recursion Problems

### 1. Mathematical Recursion

- [ ] **Fibonacci Sequence**
  ```java
  int fibonacci(int n) {
      if (n <= 1) return n;
      return fibonacci(n - 1) + fibonacci(n - 2);
  }
  
  // fibonacci(5) = fibonacci(4) + fibonacci(3)
  //              = (fib(3) + fib(2)) + (fib(2) + fib(1))
  //              = ... = 5
  ```
  
  **Real-life Use Case:** 🌻 **Nature Patterns**
  - Sunflower petals arrangement
  - Rabbit population growth
  - Stock market analysis (Fibonacci retracement)

- [ ] **Power Calculation**
  ```java
  double power(double x, int n) {
      if (n == 0) return 1;
      if (n < 0) return 1 / power(x, -n);
      
      double half = power(x, n / 2);
      if (n % 2 == 0) {
          return half * half;
      } else {
          return half * half * x;
      }
  }
  // Time: O(log n) instead of O(n)
  ```
  
  **Real-life Use Case:** 🔢 **Scientific Calculations**
  - Compound interest: A = P(1 + r)^n
  - Exponential growth models

### 2. Array/String Recursion

- [ ] **Reverse String**
  ```java
  String reverse(String s) {
      if (s.isEmpty()) return s;
      return reverse(s.substring(1)) + s.charAt(0);
  }
  
  // reverse("hello")
  // = reverse("ello") + 'h'
  // = (reverse("llo") + 'e') + 'h'
  // = ((reverse("lo") + 'l') + 'e') + 'h'
  // = (((reverse("o") + 'l') + 'l') + 'e') + 'h'
  // = (((("" + 'o') + 'l') + 'l') + 'e') + 'h'
  // = "olleh"
  ```

- [ ] **Check Palindrome**
  ```java
  boolean isPalindrome(String s, int left, int right) {
      if (left >= right) return true;
      if (s.charAt(left) != s.charAt(right)) return false;
      return isPalindrome(s, left + 1, right - 1);
  }
  ```

### 3. Tree Recursion

- [ ] **Binary Tree Traversal**
  ```java
  class TreeNode {
      int val;
      TreeNode left, right;
  }
  
  // Inorder: Left → Root → Right
  void inorder(TreeNode root) {
      if (root == null) return;
      inorder(root.left);
      System.out.print(root.val + " ");
      inorder(root.right);
  }
  
  // Preorder: Root → Left → Right
  void preorder(TreeNode root) {
      if (root == null) return;
      System.out.print(root.val + " ");
      preorder(root.left);
      preorder(root.right);
  }
  
  // Postorder: Left → Right → Root
  void postorder(TreeNode root) {
      if (root == null) return;
      postorder(root.left);
      postorder(root.right);
      System.out.print(root.val + " ");
  }
  ```
  
  **Real-life Use Case:** 📁 **File System**
  - Folder structure traverse করা
  - Delete folder (postorder - files first, then folder)

---

## 🔷 Part 3: Backtracking Fundamentals

### Concepts to Master:

- [ ] **Backtracking কী**
  ```
  Backtracking = Recursion + Trial and Error
  
  1. Try a solution
  2. If works → continue
  3. If doesn't work → BACKTRACK (undo) and try another
  ```
  
  **Real-life Example:** 🧩 **Maze Solving**
  - একটা path try করো
  - Dead end পেলে back করো
  - অন্য path try করো
  - Exit পাওয়া পর্যন্ত continue

- [ ] **Backtracking Template**
  ```java
  void backtrack(parameters) {
      // Base case
      if (found solution) {
          add to result;
          return;
      }
      
      // Try all possibilities
      for (each choice) {
          // Make choice
          add choice to current solution;
          
          // Recurse
          backtrack(updated parameters);
          
          // Undo choice (BACKTRACK)
          remove choice from current solution;
      }
  }
  ```

---

## 🔷 Part 4: Classic Backtracking Problems

### 1. Permutations

- [ ] **Generate All Permutations**
  ```java
  void permute(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
      // Base case
      if (current.size() == nums.length) {
          result.add(new ArrayList<>(current));
          return;
      }
      
      // Try each number
      for (int i = 0; i < nums.length; i++) {
          if (used[i]) continue;
          
          // Choose
          current.add(nums[i]);
          used[i] = true;
          
          // Explore
          permute(nums, current, used, result);
          
          // Unchoose (Backtrack)
          current.remove(current.size() - 1);
          used[i] = false;
      }
  }
  ```
  
  **Real-life Use Case:** 🎭 **Seating Arrangement**
  - N people কে N chairs এ কতভাবে বসানো যায়
  - Team formation - different combinations

### 2. Combinations

- [ ] **Generate Combinations**
  ```java
  void combine(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
      // Base case
      if (current.size() == k) {
          result.add(new ArrayList<>(current));
          return;
      }
      
      // Try numbers from start to n
      for (int i = start; i <= n; i++) {
          // Choose
          current.add(i);
          
          // Explore
          combine(n, k, i + 1, current, result);
          
          // Unchoose
          current.remove(current.size() - 1);
      }
  }
  ```
  
  **Real-life Use Case:** 🎲 **Lottery Numbers**
  - 49 numbers থেকে 6 select করার সব combinations

### 3. Subsets

- [ ] **Generate All Subsets**
  ```java
  void subsets(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
      // Add current subset
      result.add(new ArrayList<>(current));
      
      // Try adding each remaining element
      for (int i = start; i < nums.length; i++) {
          // Choose
          current.add(nums[i]);
          
          // Explore
          subsets(nums, i + 1, current, result);
          
          // Unchoose
          current.remove(current.size() - 1);
      }
  }
  ```
  
  **Real-life Use Case:** 🍕 **Pizza Toppings**
  - Available toppings থেকে যেকোনো combination choose করা

### 4. N-Queens Problem

- [ ] **N-Queens Solution**
  ```java
  boolean isSafe(char[][] board, int row, int col, int n) {
      // Check column
      for (int i = 0; i < row; i++) {
          if (board[i][col] == 'Q') return false;
      }
      
      // Check diagonal (top-left)
      for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
          if (board[i][j] == 'Q') return false;
      }
      
      // Check diagonal (top-right)
      for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
          if (board[i][j] == 'Q') return false;
      }
      
      return true;
  }
  
  void solveNQueens(char[][] board, int row, int n, List<List<String>> result) {
      // Base case
      if (row == n) {
          result.add(construct(board));
          return;
      }
      
      // Try placing queen in each column
      for (int col = 0; col < n; col++) {
          if (isSafe(board, row, col, n)) {
              // Place queen
              board[row][col] = 'Q';
              
              // Recurse
              solveNQueens(board, row + 1, n, result);
              
              // Remove queen (Backtrack)
              board[row][col] = '.';
          }
      }
  }
  ```
  
  **Real-life Use Case:** ♟️ **Chess Strategy**
  - Optimal piece placement
  - Conflict-free scheduling

### 5. Sudoku Solver

- [ ] **Solve Sudoku**
  ```java
  boolean isValid(char[][] board, int row, int col, char num) {
      // Check row
      for (int i = 0; i < 9; i++) {
          if (board[row][i] == num) return false;
      }
      
      // Check column
      for (int i = 0; i < 9; i++) {
          if (board[i][col] == num) return false;
      }
      
      // Check 3x3 box
      int startRow = (row / 3) * 3;
      int startCol = (col / 3) * 3;
      for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
              if (board[startRow + i][startCol + j] == num) return false;
          }
      }
      
      return true;
  }
  
  boolean solveSudoku(char[][] board) {
      for (int row = 0; row < 9; row++) {
          for (int col = 0; col < 9; col++) {
              if (board[row][col] == '.') {
                  // Try digits 1-9
                  for (char num = '1'; num <= '9'; num++) {
                      if (isValid(board, row, col, num)) {
                          // Place number
                          board[row][col] = num;
                          
                          // Recurse
                          if (solveSudoku(board)) {
                              return true;
                          }
                          
                          // Backtrack
                          board[row][col] = '.';
                      }
                  }
                  return false;  // No valid number found
              }
          }
      }
      return true;  // All cells filled
  }
  ```
  
  **Real-life Use Case:** 🎮 **Puzzle Games**
  - Sudoku solver apps
  - Constraint satisfaction problems

---

## 💻 Coding Practice (20 Problems)

### 🟢 Basic Recursion (7)

- [ ] **1. Factorial**
  - Use case: Permutation calculations

- [ ] **2. Fibonacci**
  - Use case: Growth patterns

- [ ] **3. Sum of N Numbers**
  - Use case: Series summation

- [ ] **4. Power (x^n)**
  - Use case: Exponential calculations

- [ ] **5. Reverse String**
  - Use case: String manipulation

- [ ] **6. Palindrome Check**
  - Use case: Validation

- [ ] **7. Binary Search (Recursive)**
  - Use case: Efficient searching

### 🔵 Advanced Recursion (6)

- [ ] **8. Tower of Hanoi**
  - Use case: Puzzle solving

- [ ] **9. Print All Subsets**
  - Use case: Combination generation

- [ ] **10. Generate Parentheses**
  - Use case: Valid expression generation

- [ ] **11. Letter Combinations of Phone**
  - Use case: T9 keyboard

- [ ] **12. Word Search in Grid**
  - Use case: Crossword puzzles

- [ ] **13. Flood Fill**
  - Use case: Paint bucket tool

### 🟡 Backtracking (7)

- [ ] **14. Permutations**
  - Use case: Arrangement problems

- [ ] **15. Combinations**
  - Use case: Selection problems

- [ ] **16. Combination Sum**
  - Use case: Target sum problems

- [ ] **17. N-Queens**
  - Use case: Constraint satisfaction

- [ ] **18. Sudoku Solver**
  - Use case: Puzzle solving

- [ ] **19. Rat in Maze**
  - Use case: Path finding

- [ ] **20. Knight's Tour**
  - Use case: Chess problems

---

## ✅ Move-On Criteria

- [ ] ✔ **Recursion এর 3 rules explain করতে পারবেন**
- [ ] ✔ **Base case importance real example দিয়ে বলতে পারবেন**
- [ ] ✔ **Backtracking template মুখস্থ থাকবে**
- [ ] ✔ **Recursion tree draw করতে পারবেন**
- [ ] ✔ **N-Queens problem solve করতে পারবেন**
- [ ] ✔ **15+ problems confidently solve করতে পারবেন**

---

**Real-World Applications:**
- 🧩 Puzzle solving
- 🎮 Game AI
- 📁 File system traversal
- 🗺️ Maze solving
- ♟️ Chess algorithms

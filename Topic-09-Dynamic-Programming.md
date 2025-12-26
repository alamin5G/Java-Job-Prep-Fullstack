# 📌 TOPIC 9: Dynamic Programming

## 🎯 Learning Objectives
Dynamic Programming (DP) হলো optimization technique - overlapping subproblems solve করার জন্য।

---

## 🎯 4 DP Patterns - Quick Reference

> **Master these 4 patterns → Solve 200+ DP problems!**

### Pattern Recognition Checklist

```
┌─────────────────────────────────────────────────────────────┐
│  PROBLEM KEYWORDS → PATTERN                                  │
├─────────────────────────────────────────────────────────────┤
│  ✅ "subset" + "target" + "0/1 choice" → 0/1 KNAPSACK       │
│  ✅ "unlimited" + "ways" + "coins" → UNBOUNDED KNAPSACK     │
│  ✅ "common" + "subsequence" + "two strings" → LCS          │
│  ✅ "increasing" + "subsequence" → LIS                       │
└─────────────────────────────────────────────────────────────┘
```

### Visual Pattern Map

```
DP PATTERNS (4)
│
├─ 🔵 PATTERN 20: 0/1 Knapsack
│   └─ 🧠 Memory Trick: "Each item: take it or leave it (0 or 1)"
│   └─ ⏰ Time: O(n * W) | Space: O(n * W) or O(W)
│   └─ 🎯 Use: Subset selection, partition, target sum
│   └─ 📝 Template:
│       dp[i][w] = max value with first i items, capacity w
│       dp[i][w] = max(
│           dp[i-1][w],                    # Don't take
│           dp[i-1][w-weight[i]] + value[i]  # Take
│       )
│
├─ 🟢 PATTERN 21: Unbounded Knapsack
│   └─ 🧠 Memory Trick: "Unlimited items - can use same item multiple times"
│   └─ ⏰ Time: O(n * W) | Space: O(W)
│   └─ 🎯 Use: Coin change, rod cutting, unlimited supply
│   └─ 📝 Template:
│       dp[w] = max value with capacity w
│       for item in items:
│           for w in range(weight[item], W+1):
│               dp[w] = max(dp[w], dp[w-weight[item]] + value[item])
│
├─ 🟡 PATTERN 22: LCS (Longest Common Subsequence)
│   └─ 🧠 Memory Trick: "Match characters from both strings"
│   └─ ⏰ Time: O(m * n) | Space: O(m * n)
│   └─ 🎯 Use: String matching, diff tools, DNA alignment
│   └─ 📝 Template:
│       dp[i][j] = LCS of s1[0..i] and s2[0..j]
│       if s1[i] == s2[j]:
│           dp[i][j] = dp[i-1][j-1] + 1
│       else:
│           dp[i][j] = max(dp[i-1][j], dp[i][j-1])
│
└─ 🟣 PATTERN 23: LIS (Longest Increasing Subsequence)
    └─ 🧠 Memory Trick: "Build increasing sequence - remember best so far"
    └─ ⏰ Time: O(n²) or O(n log n) | Space: O(n)
    └─ 🎯 Use: Increasing patterns, stock prices, scheduling
    └─ 📝 Template:
        dp[i] = length of LIS ending at index i
        for i in range(n):
            for j in range(i):
                if arr[j] < arr[i]:
                    dp[i] = max(dp[i], dp[j] + 1)
```

### Quick Decision Tree

```
START: DP Problem
    │
    ├─ Can use each item ONCE only?
    │   └─ YES → ✅ 0/1 KNAPSACK (Pattern 20)
    │
    ├─ Can use items UNLIMITED times?
    │   └─ YES → ✅ UNBOUNDED KNAPSACK (Pattern 21)
    │
    ├─ Comparing TWO STRINGS for common parts?
    │   └─ YES → ✅ LCS (Pattern 22)
    │
    └─ Finding INCREASING pattern in array?
        └─ YES → ✅ LIS (Pattern 23)
```

### Memorization Mnemonics

**Remember: "KULL" (Knapsack-0/1, Unbounded, LCS, LIS)**

```
K - 0/1 Knapsack       → "Keep or skip each item"
U - Unbounded Knapsack → "Unlimited use"
L - LCS                → "Longest common"
L - LIS                → "Longest increasing"
```

---

## 🔷 Part 1: DP Fundamentals

### Concepts to Master:

- [ ] **Dynamic Programming কী**
  ```
  DP = Recursion + Memoization (or Tabulation)
  
  Key Idea: Don't solve same subproblem twice!
  Store results and reuse them.
  ```
  
  **Real-life Example:** 📝 **Homework Solutions**
  - Without DP: প্রতিবার same problem solve করো
  - With DP: একবার solve করে note রাখো, পরে দেখে নাও

- [ ] **When to Use DP**
  ```
  ✅ Overlapping Subproblems
  ✅ Optimal Substructure
  ✅ Optimization problems (min/max)
  ✅ Counting problems
  ```
  
  **Real-life Scenarios:**
  - 💰 **Investment Planning** - Maximum profit
  - 🎒 **Packing Luggage** - Maximum value in limited weight
  - 📊 **Resource Allocation** - Optimal distribution

- [ ] **DP Approaches**
  
  **1. Memoization (Top-Down)**
  ```java
  // Start from main problem, break down
  // Store results in cache
  int fib(int n, int[] memo) {
      if (n <= 1) return n;
      if (memo[n] != 0) return memo[n];  // Already computed
      memo[n] = fib(n-1, memo) + fib(n-2, memo);
      return memo[n];
  }
  ```
  
  **2. Tabulation (Bottom-Up)**
  ```java
  // Start from base case, build up
  // Store results in table
  int fib(int n) {
      if (n <= 1) return n;
      int[] dp = new int[n + 1];
      dp[0] = 0;
      dp[1] = 1;
      for (int i = 2; i <= n; i++) {
          dp[i] = dp[i-1] + dp[i-2];
      }
      return dp[n];
  }
  ```

---

## 🔷 Part 2: Classic DP Problems

### 1. Fibonacci (Introduction)

- [ ] **Fibonacci with DP**
  ```java
  // Without DP: O(2^n) - VERY SLOW!
  int fibRecursive(int n) {
      if (n <= 1) return n;
      return fibRecursive(n-1) + fibRecursive(n-2);
  }
  
  // With DP: O(n) - FAST!
  int fibDP(int n) {
      if (n <= 1) return n;
      int[] dp = new int[n + 1];
      dp[0] = 0;
      dp[1] = 1;
      for (int i = 2; i <= n; i++) {
          dp[i] = dp[i-1] + dp[i-2];
      }
      return dp[n];
  }
  ```
  
  **Real-life Use Case:** 📈 **Population Growth Modeling**

### 2. Climbing Stairs

- [ ] **Climbing Stairs Problem**
  ```java
  // You can climb 1 or 2 steps at a time
  // How many ways to reach top?
  int climbStairs(int n) {
      if (n <= 2) return n;
      int[] dp = new int[n + 1];
      dp[1] = 1;
      dp[2] = 2;
      for (int i = 3; i <= n; i++) {
          dp[i] = dp[i-1] + dp[i-2];
      }
      return dp[n];
  }
  ```
  
  **Real-life Use Case:** 🏃 **Path Counting**
  - কতভাবে destination এ পৌঁছানো যায়

### 3. Coin Change

- [ ] **Minimum Coins**
  ```java
  int coinChange(int[] coins, int amount) {
      int[] dp = new int[amount + 1];
      Arrays.fill(dp, amount + 1);
      dp[0] = 0;
      
      for (int i = 1; i <= amount; i++) {
          for (int coin : coins) {
              if (i >= coin) {
                  dp[i] = Math.min(dp[i], dp[i - coin] + 1);
              }
          }
      }
      
      return dp[amount] > amount ? -1 : dp[amount];
  }
  ```
  
  **Real-life Use Case:** 💵 **Making Change**
  - Cashier minimum coins দিয়ে change দেয়
  - Vending machine optimization

### 4. Knapsack Problem

- [ ] **0/1 Knapsack**
  ```java
  int knapsack(int[] weights, int[] values, int capacity) {
      int n = weights.length;
      int[][] dp = new int[n + 1][capacity + 1];
      
      for (int i = 1; i <= n; i++) {
          for (int w = 1; w <= capacity; w++) {
              if (weights[i-1] <= w) {
                  dp[i][w] = Math.max(
                      dp[i-1][w],  // Don't take
                      values[i-1] + dp[i-1][w - weights[i-1]]  // Take
                  );
              } else {
                  dp[i][w] = dp[i-1][w];
              }
          }
      }
      
      return dp[n][capacity];
  }
  ```
  
  **Real-life Use Cases:**
  - 🎒 **Packing Luggage** - Maximum value in weight limit
  - 📦 **Cargo Loading** - Optimize truck capacity
  - 💼 **Portfolio Selection** - Maximum return in budget

---

## 🔷 Part 3: String DP

### 1. Longest Common Subsequence

- [ ] **LCS Problem**
  ```java
  int longestCommonSubsequence(String s1, String s2) {
      int m = s1.length(), n = s2.length();
      int[][] dp = new int[m + 1][n + 1];
      
      for (int i = 1; i <= m; i++) {
          for (int j = 1; j <= n; j++) {
              if (s1.charAt(i-1) == s2.charAt(j-1)) {
                  dp[i][j] = dp[i-1][j-1] + 1;
              } else {
                  dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
              }
          }
      }
      
      return dp[m][n];
  }
  ```
  
  **Real-life Use Cases:**
  - 🧬 **DNA Sequence Alignment** - Find similarities
  - 📝 **Diff Tools** - Git diff, file comparison
  - 🔍 **Plagiarism Detection** - Find common text

### 2. Edit Distance

- [ ] **Minimum Edit Distance**
  ```java
  int minDistance(String word1, String word2) {
      int m = word1.length(), n = word2.length();
      int[][] dp = new int[m + 1][n + 1];
      
      for (int i = 0; i <= m; i++) dp[i][0] = i;
      for (int j = 0; j <= n; j++) dp[0][j] = j;
      
      for (int i = 1; i <= m; i++) {
          for (int j = 1; j <= n; j++) {
              if (word1.charAt(i-1) == word2.charAt(j-1)) {
                  dp[i][j] = dp[i-1][j-1];
              } else {
                  dp[i][j] = 1 + Math.min(
                      dp[i-1][j],    // Delete
                      Math.min(
                          dp[i][j-1],    // Insert
                          dp[i-1][j-1]   // Replace
                      )
                  );
              }
          }
      }
      
      return dp[m][n];
  }
  ```
  
  **Real-life Use Cases:**
  - ✍️ **Spell Checker** - Suggest corrections
  - 🔍 **Search Engines** - "Did you mean...?"
  - 🗣️ **Speech Recognition** - Match similar words

---

## 🔷 Part 4: Advanced DP Patterns

### 1. Longest Increasing Subsequence

- [ ] **LIS Problem**
  ```java
  int lengthOfLIS(int[] nums) {
      int n = nums.length;
      int[] dp = new int[n];
      Arrays.fill(dp, 1);
      
      for (int i = 1; i < n; i++) {
          for (int j = 0; j < i; j++) {
              if (nums[i] > nums[j]) {
                  dp[i] = Math.max(dp[i], dp[j] + 1);
              }
          }
      }
      
      int maxLen = 0;
      for (int len : dp) {
          maxLen = Math.max(maxLen, len);
      }
      return maxLen;
  }
  ```
  
  **Real-life Use Case:** 📈 **Stock Market Analysis**
  - Longest period of increasing prices

### 2. Matrix Chain Multiplication

- [ ] **MCM Problem**
  ```java
  int matrixChainOrder(int[] dims) {
      int n = dims.length - 1;
      int[][] dp = new int[n][n];
      
      for (int len = 2; len <= n; len++) {
          for (int i = 0; i < n - len + 1; i++) {
              int j = i + len - 1;
              dp[i][j] = Integer.MAX_VALUE;
              
              for (int k = i; k < j; k++) {
                  int cost = dp[i][k] + dp[k+1][j] + 
                             dims[i] * dims[k+1] * dims[j+1];
                  dp[i][j] = Math.min(dp[i][j], cost);
              }
          }
      }
      
      return dp[0][n-1];
  }
  ```
  
  **Real-life Use Case:** 🖥️ **Query Optimization**
  - Database query execution order

---

## 💻 Coding Practice (20 Problems)

### 🟢 Basic DP (7)

- [ ] **1. Fibonacci**
  - Use case: Growth patterns

- [ ] **2. Climbing Stairs**
  - Use case: Path counting

- [ ] **3. House Robber**
  - Use case: Non-adjacent selection

- [ ] **4. Min Cost Climbing Stairs**
  - Use case: Minimum cost path

- [ ] **5. Maximum Subarray (Kadane)**
  - Use case: Best profit period

- [ ] **6. Coin Change (Min Coins)**
  - Use case: Making change

- [ ] **7. Coin Change (Ways)**
  - Use case: Count combinations

### 🔵 Intermediate (7)

- [ ] **8. 0/1 Knapsack**
  - Use case: Resource optimization

- [ ] **9. Longest Common Subsequence**
  - Use case: DNA alignment

- [ ] **10. Longest Increasing Subsequence**
  - Use case: Trend analysis

- [ ] **11. Edit Distance**
  - Use case: Spell checker

- [ ] **12. Unique Paths**
  - Use case: Grid navigation

- [ ] **13. Partition Equal Subset Sum**
  - Use case: Fair division

- [ ] **14. Target Sum**
  - Use case: Expression evaluation

### 🟡 Advanced (6)

- [ ] **15. Longest Palindromic Substring**
  - Use case: Pattern detection

- [ ] **16. Word Break**
  - Use case: Dictionary validation

- [ ] **17. Decode Ways**
  - Use case: Message decoding

- [ ] **18. Matrix Chain Multiplication**
  - Use case: Query optimization

- [ ] **19. Burst Balloons**
  - Use case: Game strategy

- [ ] **20. Regular Expression Matching**
  - Use case: Pattern matching

---

## ✅ Move-On Criteria

- [ ] ✔ **DP vs Recursion difference explain করতে পারবেন**
- [ ] ✔ **Memoization vs Tabulation কখন use করবেন বলতে পারবেন**
- [ ] ✔ **Knapsack problem real example দিয়ে explain করতে পারবেন**
- [ ] ✔ **LCS use cases বলতে পারবেন**
- [ ] ✔ **DP state transition define করতে পারবেন**
- [ ] ✔ **15+ problems solve করতে পারবেন**

---

**Real-World Applications:**
- 💰 Investment optimization
- 🎒 Resource allocation
- 🧬 Bioinformatics
- ✍️ Text processing
- 🎮 Game AI
- 📊 Data analysis

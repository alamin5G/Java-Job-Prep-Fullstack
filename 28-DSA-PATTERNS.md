# 🎯 28 DSA Patterns - Complete Pattern-Based Learning Guide

## 📚 Overview

> **Pattern-Based Learning:** Instead of memorizing solutions for each data structure, learn **patterns** that can be applied to solve hundreds of problems!

**Source:** [YouTube Playlist - DSA Patterns](https://www.youtube.com/watch?v=qH2VQY48mg4&list=PLbJhGqY-mq47k_WLUtzVjmarUm1EuXPj2)

---

## 🔑 Why Pattern-Based Learning?

```
Traditional Approach:
- Learn Arrays → Solve 50 array problems
- Learn Linked Lists → Solve 50 linked list problems
- Memorize 500+ solutions ❌

Pattern-Based Approach:
- Learn 28 patterns
- Apply to ANY data structure
- Solve 1000+ problems with same patterns ✅
```

**Real-life Example:** 🔧 **Toolbox**
- Traditional = Different tool for each screw
- Pattern-based = Universal screwdriver (one tool, many uses)

---

## 📊 Complete 28 Patterns List

### 🟢 Category 1: Array & String Patterns (8 patterns)

#### 1. **Two Pointers** ⭐⭐⭐⭐⭐
```
When to Use:
- Sorted array problems
- Finding pairs/triplets
- Palindrome checking
- Container with most water

Template:
left = 0, right = n-1
while left < right:
    if condition:
        left++
    else:
        right--

Example Problems:
- Two Sum (sorted array)
- 3Sum
- Container With Most Water
- Valid Palindrome
```

#### 2. **Sliding Window** ⭐⭐⭐⭐⭐
```
When to Use:
- Subarray/substring problems
- Maximum/minimum in window
- Longest/shortest substring
- Fixed or variable window size

Template:
left = 0
for right in range(n):
    add arr[right] to window
    while window invalid:
        remove arr[left]
        left++
    update result

Example Problems:
- Maximum Sum Subarray of Size K
- Longest Substring Without Repeating Characters
- Minimum Window Substring
- Fruits Into Baskets
```

#### 3. **Fast & Slow Pointers (Tortoise & Hare)** ⭐⭐⭐⭐
```
When to Use:
- Cycle detection
- Finding middle element
- Linked list problems
- Array cycle detection

Template:
slow = head
fast = head
while fast and fast.next:
    slow = slow.next
    fast = fast.next.next
    if slow == fast:
        cycle detected

Example Problems:
- Linked List Cycle
- Find Middle of Linked List
- Happy Number
- Circular Array Loop
```

#### 4. **Merge Intervals** ⭐⭐⭐⭐
```
When to Use:
- Overlapping intervals
- Meeting rooms
- Insert interval
- Interval scheduling

Template:
sort intervals by start time
for each interval:
    if overlaps with previous:
        merge
    else:
        add to result

Example Problems:
- Merge Intervals
- Insert Interval
- Meeting Rooms II
- Employee Free Time
```

#### 5. **Cyclic Sort** ⭐⭐⭐
```
When to Use:
- Array with numbers 1 to n
- Find missing/duplicate numbers
- In-place sorting

Template:
i = 0
while i < n:
    correct_index = arr[i] - 1
    if arr[i] != arr[correct_index]:
        swap(arr[i], arr[correct_index])
    else:
        i++

Example Problems:
- Find Missing Number
- Find All Duplicates
- Find Corrupt Pair
- First Missing Positive
```

#### 6. **Prefix Sum / Difference Array** ⭐⭐⭐⭐
```
When to Use:
- Range sum queries
- Subarray sum problems
- Cumulative operations

Template:
prefix[0] = arr[0]
for i in 1 to n:
    prefix[i] = prefix[i-1] + arr[i]

range_sum(L, R) = prefix[R] - prefix[L-1]

Example Problems:
- Subarray Sum Equals K
- Range Sum Query
- Product of Array Except Self
- Continuous Subarray Sum
```

#### 7. **Frequency Counter / HashMap** ⭐⭐⭐⭐⭐
```
When to Use:
- Counting occurrences
- Anagram problems
- Character frequency
- Two arrays comparison

Template:
map = {}
for element in array:
    map[element] = map.get(element, 0) + 1

Example Problems:
- Two Sum
- Valid Anagram
- Group Anagrams
- Top K Frequent Elements
```

#### 8. **Monotonic Stack/Queue** ⭐⭐⭐⭐
```
When to Use:
- Next greater/smaller element
- Stock span problem
- Histogram problems

Template:
stack = []
for i in range(n):
    while stack and arr[stack[-1]] < arr[i]:
        index = stack.pop()
        result[index] = arr[i]
    stack.append(i)

Example Problems:
- Next Greater Element
- Daily Temperatures
- Largest Rectangle in Histogram
- Trapping Rain Water
```

---

### 🟡 Category 2: Linked List Patterns (2 patterns)

#### 9. **In-place Reversal** ⭐⭐⭐⭐
```
When to Use:
- Reverse linked list
- Reverse in groups
- Palindrome linked list

Template:
prev = None
curr = head
while curr:
    next = curr.next
    curr.next = prev
    prev = curr
    curr = next

Example Problems:
- Reverse Linked List
- Reverse Nodes in k-Group
- Swap Nodes in Pairs
- Palindrome Linked List
```

#### 10. **Merge Lists** ⭐⭐⭐
```
When to Use:
- Merge sorted lists
- K-way merge
- Flatten multilevel list

Template:
while list1 and list2:
    if list1.val < list2.val:
        add list1 to result
        list1 = list1.next
    else:
        add list2 to result
        list2 = list2.next

Example Problems:
- Merge Two Sorted Lists
- Merge K Sorted Lists
- Flatten Multilevel Doubly Linked List
```

---

### 🔵 Category 3: Tree Patterns (5 patterns)

#### 11. **Tree DFS (Depth First Search)** ⭐⭐⭐⭐⭐
```
When to Use:
- Tree traversal
- Path problems
- Tree depth/height

Template (Recursive):
def dfs(node):
    if not node:
        return
    # Process node
    dfs(node.left)
    dfs(node.right)

Example Problems:
- Binary Tree Paths
- Path Sum
- Maximum Depth
- Diameter of Binary Tree
```

#### 12. **Tree BFS (Breadth First Search)** ⭐⭐⭐⭐⭐
```
When to Use:
- Level order traversal
- Minimum depth
- Level-wise operations

Template:
queue = [root]
while queue:
    level_size = len(queue)
    for i in range(level_size):
        node = queue.pop(0)
        # Process node
        if node.left: queue.append(node.left)
        if node.right: queue.append(node.right)

Example Problems:
- Level Order Traversal
- Zigzag Level Order
- Minimum Depth
- Right Side View
```

#### 13. **Binary Search Tree (BST)** ⭐⭐⭐⭐
```
When to Use:
- BST validation
- Search in BST
- Insert/Delete in BST

Template:
def search(root, target):
    if not root:
        return None
    if target < root.val:
        return search(root.left, target)
    elif target > root.val:
        return search(root.right, target)
    else:
        return root

Example Problems:
- Validate BST
- Kth Smallest in BST
- Lowest Common Ancestor in BST
- Convert Sorted Array to BST
```

#### 14. **Trie (Prefix Tree)** ⭐⭐⭐⭐
```
When to Use:
- Prefix matching
- Autocomplete
- Word search
- Dictionary problems

Template:
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end = False

def insert(word):
    node = root
    for char in word:
        if char not in node.children:
            node.children[char] = TrieNode()
        node = node.children[char]
    node.is_end = True

Example Problems:
- Implement Trie
- Word Search II
- Design Add and Search Words
- Longest Word in Dictionary
```

#### 15. **Heap (Priority Queue)** ⭐⭐⭐⭐⭐
```
When to Use:
- Top K elements
- Kth largest/smallest
- Median finding
- Merge K sorted

Template:
import heapq
heap = []
heapq.heappush(heap, value)
min_val = heapq.heappop(heap)

Example Problems:
- Kth Largest Element
- Top K Frequent Elements
- Find Median from Data Stream
- Merge K Sorted Lists
```

---

### 🟣 Category 4: Graph Patterns (4 patterns)

#### 16. **Graph DFS** ⭐⭐⭐⭐⭐
```
When to Use:
- Connected components
- Path finding
- Cycle detection
- Topological sort

Template:
visited = set()
def dfs(node):
    if node in visited:
        return
    visited.add(node)
    for neighbor in graph[node]:
        dfs(neighbor)

Example Problems:
- Number of Islands
- Clone Graph
- Course Schedule
- All Paths From Source to Target
```

#### 17. **Graph BFS** ⭐⭐⭐⭐⭐
```
When to Use:
- Shortest path (unweighted)
- Level-wise traversal
- Minimum steps

Template:
queue = [start]
visited = {start}
while queue:
    node = queue.pop(0)
    for neighbor in graph[node]:
        if neighbor not in visited:
            visited.add(neighbor)
            queue.append(neighbor)

Example Problems:
- Shortest Path in Binary Matrix
- Word Ladder
- Rotting Oranges
- Minimum Knight Moves
```

#### 18. **Union Find (Disjoint Set)** ⭐⭐⭐⭐
```
When to Use:
- Connected components
- Cycle detection
- Network connectivity

Template:
class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.rank = [0] * n
    
    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]
    
    def union(self, x, y):
        px, py = self.find(x), self.find(y)
        if px == py:
            return False
        if self.rank[px] < self.rank[py]:
            self.parent[px] = py
        elif self.rank[px] > self.rank[py]:
            self.parent[py] = px
        else:
            self.parent[py] = px
            self.rank[px] += 1
        return True

Example Problems:
- Number of Connected Components
- Redundant Connection
- Accounts Merge
- Most Stones Removed
```

#### 19. **Topological Sort** ⭐⭐⭐⭐
```
When to Use:
- Task scheduling
- Course prerequisites
- Build dependencies

Template (Kahn's Algorithm):
in_degree = [0] * n
for u, v in edges:
    in_degree[v] += 1

queue = [i for i in range(n) if in_degree[i] == 0]
result = []

while queue:
    node = queue.pop(0)
    result.append(node)
    for neighbor in graph[node]:
        in_degree[neighbor] -= 1
        if in_degree[neighbor] == 0:
            queue.append(neighbor)

Example Problems:
- Course Schedule
- Course Schedule II
- Alien Dictionary
- Minimum Height Trees
```

---

### 🔴 Category 5: Dynamic Programming Patterns (4 patterns)

#### 20. **0/1 Knapsack** ⭐⭐⭐⭐⭐
```
When to Use:
- Subset selection
- Partition problems
- Target sum

Template:
dp[i][j] = max value with first i items and capacity j
dp[i][j] = max(
    dp[i-1][j],              # Don't take item i
    dp[i-1][j-weight[i]] + value[i]  # Take item i
)

Example Problems:
- 0/1 Knapsack
- Partition Equal Subset Sum
- Target Sum
- Last Stone Weight II
```

#### 21. **Unbounded Knapsack** ⭐⭐⭐⭐
```
When to Use:
- Unlimited items
- Coin change
- Rod cutting

Template:
dp[i][j] = max value with capacity j using items 0 to i
dp[i][j] = max(
    dp[i-1][j],              # Don't use item i
    dp[i][j-weight[i]] + value[i]  # Use item i (can reuse)
)

Example Problems:
- Coin Change
- Coin Change 2
- Minimum Cost For Tickets
- Rod Cutting
```

#### 22. **Longest Common Subsequence (LCS)** ⭐⭐⭐⭐⭐
```
When to Use:
- String matching
- Edit distance
- Diff algorithms

Template:
dp[i][j] = LCS length of s1[0..i] and s2[0..j]
if s1[i] == s2[j]:
    dp[i][j] = dp[i-1][j-1] + 1
else:
    dp[i][j] = max(dp[i-1][j], dp[i][j-1])

Example Problems:
- Longest Common Subsequence
- Edit Distance
- Minimum ASCII Delete Sum
- Shortest Common Supersequence
```

#### 23. **Longest Increasing Subsequence (LIS)** ⭐⭐⭐⭐
```
When to Use:
- Increasing subsequence
- Box stacking
- Building bridges

Template:
dp[i] = length of LIS ending at index i
for i in range(n):
    for j in range(i):
        if arr[j] < arr[i]:
            dp[i] = max(dp[i], dp[j] + 1)

Example Problems:
- Longest Increasing Subsequence
- Number of LIS
- Russian Doll Envelopes
- Maximum Length of Pair Chain
```

---

### 🟠 Category 6: Backtracking & Recursion Patterns (2 patterns)

#### 24. **Backtracking** ⭐⭐⭐⭐⭐
```
When to Use:
- Generate all combinations
- Permutations
- Subset problems
- N-Queens

Template:
def backtrack(path, choices):
    if is_solution(path):
        result.append(path.copy())
        return
    
    for choice in choices:
        # Make choice
        path.append(choice)
        
        # Recurse
        backtrack(path, remaining_choices)
        
        # Undo choice
        path.pop()

Example Problems:
- Subsets
- Permutations
- Combination Sum
- N-Queens
- Sudoku Solver
```

#### 25. **Divide and Conquer** ⭐⭐⭐⭐
```
When to Use:
- Merge sort
- Quick sort
- Binary search variations

Template:
def divide_conquer(arr, left, right):
    if left >= right:
        return base_case
    
    mid = (left + right) // 2
    left_result = divide_conquer(arr, left, mid)
    right_result = divide_conquer(arr, mid+1, right)
    
    return combine(left_result, right_result)

Example Problems:
- Merge Sort
- Quick Sort
- Maximum Subarray
- Kth Largest Element
```

---

### ⚫ Category 7: Advanced Patterns (3 patterns)

#### 26. **Binary Search (Modified)** ⭐⭐⭐⭐⭐
```
When to Use:
- Search in rotated array
- Find peak element
- Search in 2D matrix
- Minimize/maximize problems

Template:
left, right = 0, n-1
while left <= right:
    mid = (left + right) // 2
    if condition(mid):
        right = mid - 1
    else:
        left = mid + 1

Example Problems:
- Search in Rotated Sorted Array
- Find Peak Element
- Koko Eating Bananas
- Capacity To Ship Packages
```

#### 27. **Bitwise XOR** ⭐⭐⭐
```
When to Use:
- Find unique number
- Missing number
- Duplicate detection

Properties:
a ^ a = 0
a ^ 0 = a
a ^ b ^ a = b

Example Problems:
- Single Number
- Single Number II
- Missing Number
- Find the Duplicate Number
```

#### 28. **Two Heaps (Median Finding)** ⭐⭐⭐⭐
```
When to Use:
- Find median
- Sliding window median
- Stream of numbers

Template:
max_heap = []  # Left half (smaller numbers)
min_heap = []  # Right half (larger numbers)

# Keep heaps balanced
# Median = max_heap[0] or (max_heap[0] + min_heap[0]) / 2

Example Problems:
- Find Median from Data Stream
- Sliding Window Median
- IPO
```

---

## 📊 Pattern Coverage in Our Roadmap

| Pattern | Topic File | Status |
|---------|-----------|--------|
| 1. Two Pointers | Topic-02-Arrays.md | ✅ |
| 2. Sliding Window | Topic-02-Arrays.md | ✅ |
| 3. Fast & Slow Pointers | Topic-04-LinkedLists.md | ✅ |
| 4. Merge Intervals | Topic-02-Arrays.md | ✅ |
| 5. Cyclic Sort | Topic-02-Arrays.md | ✅ |
| 6. Prefix Sum | Topic-02-Arrays.md | ✅ |
| 7. Frequency Counter | Topic-02-Arrays.md | ✅ |
| 8. Monotonic Stack | Topic-05-Stacks-Queues.md | ✅ |
| 9. In-place Reversal | Topic-04-LinkedLists.md | ✅ |
| 10. Merge Lists | Topic-04-LinkedLists.md | ✅ |
| 11. Tree DFS | Topic-07-Trees.md | ✅ |
| 12. Tree BFS | Topic-07-Trees.md | ✅ |
| 13. BST | Topic-07-Trees.md | ✅ |
| 14. Trie | Topic-07-Trees.md | ✅ |
| 15. Heap | Topic-07-Trees.md | ✅ |
| 16. Graph DFS | Topic-08-Graphs.md | ✅ |
| 17. Graph BFS | Topic-08-Graphs.md | ✅ |
| 18. Union Find | Topic-08-Graphs.md | ✅ |
| 19. Topological Sort | Topic-08-Graphs.md | ✅ |
| 20. 0/1 Knapsack | Topic-09-Dynamic-Programming.md | ✅ |
| 21. Unbounded Knapsack | Topic-09-Dynamic-Programming.md | ✅ |
| 22. LCS | Topic-09-Dynamic-Programming.md | ✅ |
| 23. LIS | Topic-09-Dynamic-Programming.md | ✅ |
| 24. Backtracking | Topic-06-Recursion-Backtracking.md | ✅ |
| 25. Divide & Conquer | Topic-06-Recursion-Backtracking.md | ✅ |
| 26. Binary Search | Topic-02-Arrays.md | ✅ |
| 27. Bitwise XOR | Topic-10-Advanced-Interview-Prep.md | ✅ |
| 28. Two Heaps | Topic-07-Trees.md | ✅ |

**Coverage: 28/28 ✅ (100%)**

---

## 🎯 How to Use This Guide

### Step 1: Learn Pattern
```
1. Understand the pattern concept
2. Study the template
3. Understand when to use it
```

### Step 2: Practice Problems
```
1. Start with easy problems
2. Identify the pattern
3. Apply the template
4. Solve 5-10 problems per pattern
```

### Step 3: Master Pattern
```
1. Can identify pattern in new problems
2. Can modify template for variations
3. Can explain pattern to others
```

---

## 📚 Resources

### YouTube Playlist
[DSA Patterns Playlist](https://www.youtube.com/watch?v=qH2VQY48mg4&list=PLbJhGqY-mq47k_WLUtzVjmarUm1EuXPj2)

### Practice Platforms
- **LeetCode:** Filter by pattern tags
- **NeetCode:** Pattern-based problem lists
- **AlgoExpert:** Pattern-based courses

---

## 🏆 Success Strategy

```
Week 1-2: Array patterns (1-8)
Week 3: Linked List patterns (9-10)
Week 4-5: Tree patterns (11-15)
Week 6-7: Graph patterns (16-19)
Week 8-9: DP patterns (20-23)
Week 10: Backtracking patterns (24-25)
Week 11: Advanced patterns (26-28)
Week 12: Mixed practice
```

**Total:** 12 weeks to master all 28 patterns! 🚀

---

**Remember:** 
- ✅ Learn pattern once, solve 100+ problems
- ✅ Focus on understanding, not memorizing
- ✅ Practice identifying patterns
- ✅ Master templates

**You've got this! 💪**

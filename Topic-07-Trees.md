# 📌 TOPIC 7: Trees

## 🎯 Learning Objectives
Trees হলো hierarchical data structure যা real-world এ সবচেয়ে বেশি use হয়।

---

## 🔷 Part 1: Tree Fundamentals

### Concepts to Master:

- [ ] **Tree কী**
  ```java
  class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      
      TreeNode(int val) {
          this.val = val;
          this.left = null;
          this.right = null;
      }
  }
  ```
  
  **Real-life Example:** 🌳 **Family Tree**
  - Root = Great-grandparent
  - Nodes = Family members
  - Children = Direct descendants
  - Leaves = Current generation (no children)

- [ ] **Tree Terminology**
  ```
  Root: Top node (no parent)
  Parent: Node with children
  Child: Node with parent
  Leaf: Node with no children
  Height: Longest path from root to leaf
  Depth: Distance from root to node
  Level: Depth + 1
  ```
  
  **Real-life Use Cases:**
  - 📁 **File System** - Folders and files
  - 🏢 **Organization Chart** - CEO → Managers → Employees
  - 🌐 **DOM Tree** - HTML elements hierarchy

- [ ] **Binary Tree vs Binary Search Tree**
  
  **Binary Tree:**
  ```java
  // Each node has at most 2 children
  // No ordering rule
  ```
  
  **Binary Search Tree (BST):**
  ```java
  // Left subtree < Root < Right subtree
  // Enables efficient search O(log n)
  ```
  
  **Real-life Example:** 📚 **Library Organization**
  - BST = Books sorted by ID
  - Binary Tree = Random book arrangement

---

## 🔷 Part 2: Tree Traversals

### 1. Depth-First Search (DFS)

- [ ] **Inorder (Left → Root → Right)**
  ```java
  void inorder(TreeNode root) {
      if (root == null) return;
      inorder(root.left);
      System.out.print(root.val + " ");
      inorder(root.right);
  }
  // BST তে inorder = Sorted order
  ```
  
  **Real-life Use Case:** 📖 **Dictionary**
  - Words alphabetically sorted
  - BST এ inorder traversal = A to Z

- [ ] **Preorder (Root → Left → Right)**
  ```java
  void preorder(TreeNode root) {
      if (root == null) return;
      System.out.print(root.val + " ");
      preorder(root.left);
      preorder(root.right);
  }
  ```
  
  **Real-life Use Case:** 📁 **Copy Folder**
  - First create folder (root)
  - Then copy contents (children)

- [ ] **Postorder (Left → Right → Root)**
  ```java
  void postorder(TreeNode root) {
      if (root == null) return;
      postorder(root.left);
      postorder(root.right);
      System.out.print(root.val + " ");
  }
  ```
  
  **Real-life Use Case:** 🗑️ **Delete Folder**
  - First delete files (children)
  - Then delete folder (root)

### 2. Breadth-First Search (BFS)

- [ ] **Level Order Traversal**
  ```java
  void levelOrder(TreeNode root) {
      if (root == null) return;
      
      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
      
      while (!queue.isEmpty()) {
          TreeNode node = queue.poll();
          System.out.print(node.val + " ");
          
          if (node.left != null) queue.offer(node.left);
          if (node.right != null) queue.offer(node.right);
      }
  }
  ```
  
  **Real-life Use Case:** 🏢 **Company Hierarchy**
  - Level 1: CEO
  - Level 2: VPs
  - Level 3: Managers
  - Print level by level

---

## 🔷 Part 3: Binary Search Tree Operations

### 1. Search

- [ ] **Search in BST - O(log n)**
  ```java
  TreeNode search(TreeNode root, int val) {
      if (root == null || root.val == val) {
          return root;
      }
      
      if (val < root.val) {
          return search(root.left, val);
      } else {
          return search(root.right, val);
      }
  }
  ```
  
  **Real-life Use Case:** 📞 **Phone Directory**
  - Binary search by name
  - Efficient lookup

### 2. Insert

- [ ] **Insert in BST - O(log n)**
  ```java
  TreeNode insert(TreeNode root, int val) {
      if (root == null) {
          return new TreeNode(val);
      }
      
      if (val < root.val) {
          root.left = insert(root.left, val);
      } else {
          root.right = insert(root.right, val);
      }
      
      return root;
  }
  ```
  
  **Real-life Use Case:** 📝 **Maintain Sorted Data**
  - Add new employee to org chart
  - Keep hierarchy intact

### 3. Delete

- [ ] **Delete from BST - O(log n)**
  ```java
  TreeNode delete(TreeNode root, int val) {
      if (root == null) return null;
      
      if (val < root.val) {
          root.left = delete(root.left, val);
      } else if (val > root.val) {
          root.right = delete(root.right, val);
      } else {
          // Node found
          // Case 1: Leaf node
          if (root.left == null && root.right == null) {
              return null;
          }
          // Case 2: One child
          if (root.left == null) return root.right;
          if (root.right == null) return root.left;
          
          // Case 3: Two children
          TreeNode minNode = findMin(root.right);
          root.val = minNode.val;
          root.right = delete(root.right, minNode.val);
      }
      
      return root;
  }
  
  TreeNode findMin(TreeNode root) {
      while (root.left != null) {
          root = root.left;
      }
      return root;
  }
  ```

---

## 🔷 Part 4: Advanced Tree Concepts

### 1. Balanced Trees

- [ ] **AVL Tree**
  ```
  Height difference between left and right subtree ≤ 1
  Self-balancing after insert/delete
  Ensures O(log n) operations
  ```
  
  **Real-life Use Case:** 🗄️ **Database Indexing**
  - Fast search, insert, delete
  - MySQL uses B+ trees (similar concept)

### 2. Heap

- [ ] **Min Heap / Max Heap**
  ```java
  // Min Heap: Parent ≤ Children
  // Max Heap: Parent ≥ Children
  
  PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
  ```
  
  **Real-life Use Cases:**
  - 🏥 **Hospital Emergency** - Max heap (priority)
  - 📊 **Top K Elements** - Min heap
  - 🎮 **Game Leaderboard** - Max heap

### 3. Trie (Prefix Tree)

- [ ] **Trie Implementation**
  ```java
  class TrieNode {
      TrieNode[] children = new TrieNode[26];
      boolean isEndOfWord;
  }
  
  class Trie {
      TrieNode root = new TrieNode();
      
      void insert(String word) {
          TrieNode node = root;
          for (char c : word.toCharArray()) {
              int index = c - 'a';
              if (node.children[index] == null) {
                  node.children[index] = new TrieNode();
              }
              node = node.children[index];
          }
          node.isEndOfWord = true;
      }
      
      boolean search(String word) {
          TrieNode node = root;
          for (char c : word.toCharArray()) {
              int index = c - 'a';
              if (node.children[index] == null) {
                  return false;
              }
              node = node.children[index];
          }
          return node.isEndOfWord;
      }
      
      boolean startsWith(String prefix) {
          TrieNode node = root;
          for (char c : prefix.toCharArray()) {
              int index = c - 'a';
              if (node.children[index] == null) {
                  return false;
              }
              node = node.children[index];
          }
          return true;
      }
  }
  ```
  
  **Real-life Use Cases:**
  - 🔍 **Google Search Autocomplete**
  - 📱 **T9 Keyboard Prediction**
  - 🎮 **Spell Checker**

---

## 💻 Coding Practice (20 Problems)

### 🟢 Basic Tree (7)

- [ ] **1. Inorder Traversal**
  - Use case: Get sorted data from BST

- [ ] **2. Preorder Traversal**
  - Use case: Copy tree structure

- [ ] **3. Postorder Traversal**
  - Use case: Delete tree

- [ ] **4. Level Order Traversal**
  - Use case: Print by levels

- [ ] **5. Maximum Depth**
  - Use case: Tree height calculation

- [ ] **6. Same Tree**
  - Use case: Compare structures

- [ ] **7. Invert Binary Tree**
  - Use case: Mirror image

### 🔵 BST Operations (6)

- [ ] **8. Search in BST**
  - Use case: Find element

- [ ] **9. Insert in BST**
  - Use case: Add element

- [ ] **10. Delete in BST**
  - Use case: Remove element

- [ ] **11. Validate BST**
  - Use case: Check correctness

- [ ] **12. Kth Smallest in BST**
  - Use case: Find rank

- [ ] **13. Lowest Common Ancestor**
  - Use case: Find common parent

### 🟡 Advanced (7)

- [ ] **14. Serialize/Deserialize Tree**
  - Use case: Save/load tree

- [ ] **15. Binary Tree from Inorder/Preorder**
  - Use case: Reconstruct tree

- [ ] **16. Path Sum**
  - Use case: Find target path

- [ ] **17. Diameter of Tree**
  - Use case: Longest path

- [ ] **18. Implement Trie**
  - Use case: Autocomplete

- [ ] **19. Top K Frequent (Heap)**
  - Use case: Find popular items

- [ ] **20. Merge K Sorted Lists (Heap)**
  - Use case: Combine sorted data

---

## ✅ Move-On Criteria

- [ ] ✔ **Tree vs Graph difference explain করতে পারবেন**
- [ ] ✔ **All traversals (4 types) code করতে পারবেন**
- [ ] ✔ **BST operations (search, insert, delete) master করবেন**
- [ ] ✔ **Heap use cases real examples দিয়ে বলতে পারবেন**
- [ ] ✔ **Trie application explain করতে পারবেন**
- [ ] ✔ **15+ problems solve করতে পারবেন**

---

**Real-World Applications:**
- 📁 File systems
- 🗄️ Database indexing
- 🔍 Search autocomplete
- 🏢 Organization charts
- 🌐 DOM manipulation
- 🎮 Game trees (AI)

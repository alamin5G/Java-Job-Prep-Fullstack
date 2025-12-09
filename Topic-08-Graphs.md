# 📌 TOPIC 8: Graphs

## 🎯 Learning Objectives
Graphs হলো সবচেয়ে versatile data structure - real-world relationships model করার জন্য।

---

## 🔷 Part 1: Graph Fundamentals

### Concepts to Master:

- [ ] **Graph কী**
  ```
  Graph = Vertices (Nodes) + Edges (Connections)
  
  Example:
  A --- B
  |     |
  C --- D
  
  Vertices: A, B, C, D
  Edges: (A,B), (A,C), (B,D), (C,D)
  ```
  
  **Real-life Examples:**
  - 🗺️ **Road Network** - Cities (vertices), Roads (edges)
  - 👥 **Social Network** - People (vertices), Friendships (edges)
  - 🌐 **Internet** - Computers (vertices), Connections (edges)
  - ✈️ **Flight Routes** - Cities (vertices), Flights (edges)

- [ ] **Graph Representation**
  
  **1. Adjacency Matrix**
  ```java
  int[][] adjMatrix = new int[V][V];
  // adjMatrix[i][j] = 1 if edge exists
  // Space: O(V²)
  // Edge check: O(1)
  ```
  
  **Use Case:** Dense graphs (many edges)
  
  **2. Adjacency List**
  ```java
  ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
  // adjList.get(i) = list of neighbors of vertex i
  // Space: O(V + E)
  // Edge check: O(degree)
  ```
  
  **Use Case:** Sparse graphs (few edges) - Most common!

- [ ] **Graph Types**
  
  **1. Directed vs Undirected**
  ```
  Undirected: A --- B (both ways)
  Directed:   A --> B (one way)
  ```
  **Examples:**
  - Undirected: Facebook friends (mutual)
  - Directed: Twitter follows (one-way)
  
  **2. Weighted vs Unweighted**
  ```
  Unweighted: A --- B
  Weighted:   A --5-- B (distance/cost)
  ```
  **Examples:**
  - Unweighted: Friend connections
  - Weighted: Road distances, flight costs
  
  **3. Cyclic vs Acyclic**
  ```
  Cyclic: A → B → C → A (cycle exists)
  Acyclic: A → B → C (no cycles)
  ```
  **Examples:**
  - Cyclic: Road network
  - Acyclic (DAG): Task dependencies

---

## 🔷 Part 2: Graph Traversals

### 1. Breadth-First Search (BFS)

- [ ] **BFS Implementation**
  ```java
  void BFS(int start, ArrayList<ArrayList<Integer>> adj) {
      boolean[] visited = new boolean[adj.size()];
      Queue<Integer> queue = new LinkedList<>();
      
      visited[start] = true;
      queue.offer(start);
      
      while (!queue.isEmpty()) {
          int node = queue.poll();
          System.out.print(node + " ");
          
          for (int neighbor : adj.get(node)) {
              if (!visited[neighbor]) {
                  visited[neighbor] = true;
                  queue.offer(neighbor);
              }
          }
      }
  }
  // Time: O(V + E)
  // Space: O(V)
  ```
  
  **Real-life Use Cases:**
  - 🗺️ **Google Maps** - Shortest path (unweighted)
  - 👥 **LinkedIn** - Degrees of connection
  - 🎮 **Game AI** - Shortest move sequence
  - 🌐 **Web Crawler** - Level-by-level crawling

### 2. Depth-First Search (DFS)

- [ ] **DFS Implementation**
  ```java
  void DFS(int node, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
      visited[node] = true;
      System.out.print(node + " ");
      
      for (int neighbor : adj.get(node)) {
          if (!visited[neighbor]) {
              DFS(neighbor, visited, adj);
          }
      }
  }
  // Time: O(V + E)
  // Space: O(V) - recursion stack
  ```
  
  **Real-life Use Cases:**
  - 🧩 **Maze Solving** - Explore all paths
  - 🔍 **Topological Sort** - Task scheduling
  - 🌲 **Detect Cycles** - Dependency loops
  - 📁 **File System** - Deep directory traversal

---

## 🔷 Part 3: Shortest Path Algorithms

### 1. Dijkstra's Algorithm

- [ ] **Dijkstra Implementation**
  ```java
  class Pair {
      int node, distance;
      Pair(int node, int distance) {
          this.node = node;
          this.distance = distance;
      }
  }
  
  int[] dijkstra(int src, ArrayList<ArrayList<Pair>> adj, int V) {
      int[] dist = new int[V];
      Arrays.fill(dist, Integer.MAX_VALUE);
      dist[src] = 0;
      
      PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
      pq.offer(new Pair(src, 0));
      
      while (!pq.isEmpty()) {
          Pair current = pq.poll();
          int node = current.node;
          
          for (Pair neighbor : adj.get(node)) {
              int newDist = dist[node] + neighbor.distance;
              if (newDist < dist[neighbor.node]) {
                  dist[neighbor.node] = newDist;
                  pq.offer(new Pair(neighbor.node, newDist));
              }
          }
      }
      
      return dist;
  }
  // Time: O((V + E) log V)
  ```
  
  **Real-life Use Cases:**
  - 🗺️ **GPS Navigation** - Shortest route
  - 📦 **Delivery Optimization** - Fastest delivery
  - 🌐 **Network Routing** - Packet routing
  - 🎮 **Game Pathfinding** - NPC movement

### 2. Bellman-Ford Algorithm

- [ ] **Bellman-Ford (Handles Negative Weights)**
  ```java
  class Edge {
      int src, dest, weight;
  }
  
  int[] bellmanFord(int V, ArrayList<Edge> edges, int src) {
      int[] dist = new int[V];
      Arrays.fill(dist, Integer.MAX_VALUE);
      dist[src] = 0;
      
      // Relax all edges V-1 times
      for (int i = 0; i < V - 1; i++) {
          for (Edge edge : edges) {
              if (dist[edge.src] != Integer.MAX_VALUE &&
                  dist[edge.src] + edge.weight < dist[edge.dest]) {
                  dist[edge.dest] = dist[edge.src] + edge.weight;
              }
          }
      }
      
      // Check for negative cycles
      for (Edge edge : edges) {
          if (dist[edge.src] != Integer.MAX_VALUE &&
              dist[edge.src] + edge.weight < dist[edge.dest]) {
              System.out.println("Negative cycle detected");
          }
      }
      
      return dist;
  }
  // Time: O(V * E)
  ```
  
  **Real-life Use Case:** 💱 **Currency Arbitrage Detection**
  - Find profitable currency exchange cycles

---

## 🔷 Part 4: Advanced Graph Algorithms

### 1. Topological Sort

- [ ] **Topological Sort (DFS)**
  ```java
  void topologicalSortUtil(int node, boolean[] visited, Stack<Integer> stack,
                           ArrayList<ArrayList<Integer>> adj) {
      visited[node] = true;
      
      for (int neighbor : adj.get(node)) {
          if (!visited[neighbor]) {
              topologicalSortUtil(neighbor, visited, stack, adj);
          }
      }
      
      stack.push(node);
  }
  
  void topologicalSort(ArrayList<ArrayList<Integer>> adj, int V) {
      Stack<Integer> stack = new Stack<>();
      boolean[] visited = new boolean[V];
      
      for (int i = 0; i < V; i++) {
          if (!visited[i]) {
              topologicalSortUtil(i, visited, stack, adj);
          }
      }
      
      while (!stack.isEmpty()) {
          System.out.print(stack.pop() + " ");
      }
  }
  ```
  
  **Real-life Use Cases:**
  - 📚 **Course Prerequisites** - Which course to take first
  - 🏗️ **Build Systems** - Compile order (Makefile)
  - 📦 **Package Dependencies** - npm, Maven
  - 👔 **Getting Dressed** - Socks before shoes!

### 2. Detect Cycle

- [ ] **Detect Cycle in Directed Graph**
  ```java
  boolean isCyclicUtil(int node, boolean[] visited, boolean[] recStack,
                       ArrayList<ArrayList<Integer>> adj) {
      visited[node] = true;
      recStack[node] = true;
      
      for (int neighbor : adj.get(node)) {
          if (!visited[neighbor]) {
              if (isCyclicUtil(neighbor, visited, recStack, adj)) {
                  return true;
              }
          } else if (recStack[neighbor]) {
              return true;  // Cycle found
          }
      }
      
      recStack[node] = false;
      return false;
  }
  ```
  
  **Real-life Use Cases:**
  - 🔄 **Deadlock Detection** - Circular wait in OS
  - 📦 **Dependency Resolution** - Circular dependencies
  - 🎓 **Course Planning** - Impossible prerequisites

### 3. Minimum Spanning Tree

- [ ] **Prim's Algorithm**
  ```java
  int primMST(ArrayList<ArrayList<Pair>> adj, int V) {
      boolean[] inMST = new boolean[V];
      PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
      
      pq.offer(new Pair(0, 0));  // Start from node 0
      int totalCost = 0;
      
      while (!pq.isEmpty()) {
          Pair current = pq.poll();
          int node = current.node;
          int weight = current.distance;
          
          if (inMST[node]) continue;
          
          inMST[node] = true;
          totalCost += weight;
          
          for (Pair neighbor : adj.get(node)) {
              if (!inMST[neighbor.node]) {
                  pq.offer(neighbor);
              }
          }
      }
      
      return totalCost;
  }
  ```
  
  **Real-life Use Cases:**
  - 🌐 **Network Design** - Minimum cable length
  - 🚰 **Water Pipeline** - Connect all cities cheaply
  - ⚡ **Power Grid** - Minimum wire cost
  - 📡 **Telecom Networks** - Optimal tower placement

---

## 💻 Coding Practice (20 Problems)

### 🟢 Basic Graph (6)

- [ ] **1. Create Graph (Adjacency List)**
  - Use case: Graph representation

- [ ] **2. BFS Traversal**
  - Use case: Level-wise exploration

- [ ] **3. DFS Traversal**
  - Use case: Deep exploration

- [ ] **4. Number of Connected Components**
  - Use case: Find isolated groups

- [ ] **5. Detect Cycle (Undirected)**
  - Use case: Check connectivity issues

- [ ] **6. Bipartite Graph Check**
  - Use case: Two-coloring problem

### 🔵 Shortest Path (5)

- [ ] **7. BFS Shortest Path (Unweighted)**
  - Use case: Minimum hops

- [ ] **8. Dijkstra's Algorithm**
  - Use case: GPS navigation

- [ ] **9. Bellman-Ford**
  - Use case: Negative weights

- [ ] **10. Floyd-Warshall (All Pairs)**
  - Use case: Distance matrix

- [ ] **11. Network Delay Time**
  - Use case: Signal propagation

### 🟡 Advanced (9)

- [ ] **12. Topological Sort**
  - Use case: Task scheduling

- [ ] **13. Detect Cycle (Directed)**
  - Use case: Deadlock detection

- [ ] **14. Course Schedule**
  - Use case: Prerequisite checking

- [ ] **15. Minimum Spanning Tree (Prim)**
  - Use case: Network design

- [ ] **16. Minimum Spanning Tree (Kruskal)**
  - Use case: Alternative MST

- [ ] **17. Clone Graph**
  - Use case: Deep copy

- [ ] **18. Word Ladder**
  - Use case: Transformation sequence

- [ ] **19. Alien Dictionary**
  - Use case: Custom ordering

- [ ] **20. Strongly Connected Components**
  - Use case: Web page ranking

---

## ✅ Move-On Criteria

- [ ] ✔ **Graph vs Tree difference explain করতে পারবেন**
- [ ] ✔ **BFS vs DFS কখন use করবেন বলতে পারবেন**
- [ ] ✔ **Dijkstra's algorithm real example দিয়ে explain করতে পারবেন**
- [ ] ✔ **Topological sort use cases বলতে পারবেন**
- [ ] ✔ **MST applications explain করতে পারবেন**
- [ ] ✔ **15+ problems solve করতে পারবেন**

---

**Real-World Applications:**
- 🗺️ GPS & Maps
- 👥 Social networks
- 🌐 Internet routing
- 📦 Package dependencies
- 🚰 Infrastructure design
- 🎮 Game AI pathfinding

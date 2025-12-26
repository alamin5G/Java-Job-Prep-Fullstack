# 📌 Topic 16: Performance Tuning & Optimization

## 🎯 Learning Objectives

Master performance optimization techniques to build fast, scalable applications - a critical skill that separates senior developers from juniors and is impossible for AI to replace without deep understanding.

---

## 🔷 Part 1: JVM Performance Tuning

### Concepts to Master:

- [ ] **Understanding JVM Memory**
  ```
  JVM Memory Structure:
  
  Heap Memory (Objects):
  ├── Young Generation
  │   ├── Eden Space (new objects)
  │   ├── Survivor 0
  │   └── Survivor 1
  └── Old Generation (long-lived objects)
  
  Non-Heap Memory:
  ├── Metaspace (class metadata)
  ├── Code Cache (compiled code)
  └── Thread Stacks
  ```
  
  **Real-life Example:** 🏢 **Office Building**
  - Eden = Reception (new employees)
  - Survivor = Training rooms (short-term)
  - Old Gen = Permanent offices (long-term)
  - Metaspace = Building blueprints

- [ ] **Garbage Collection Tuning**
  ```bash
  # View GC logs
  java -Xlog:gc* -jar myapp.jar
  
  # Common GC options
  java -Xms512m \              # Initial heap size
       -Xmx2g \                # Maximum heap size
       -XX:+UseG1GC \          # Use G1 Garbage Collector
       -XX:MaxGCPauseMillis=200 \  # Target pause time
       -jar myapp.jar
  
  # For low-latency apps
  java -XX:+UseZGC \           # Z Garbage Collector (Java 15+)
       -Xmx4g \
       -jar myapp.jar
  ```
  
  **GC Algorithms:**
  ```
  Serial GC:
  - Single thread
  - Small apps
  - Low memory
  
  Parallel GC:
  - Multiple threads
  - High throughput
  - Batch processing
  
  G1 GC (Default Java 9+):
  - Balanced
  - Predictable pauses
  - Most apps
  
  ZGC / Shenandoah:
  - Ultra-low latency
  - Large heaps (100GB+)
  - Real-time apps
  ```

- [ ] **Monitor JVM Metrics**
  ```java
  @RestController
  @RequestMapping("/api/metrics")
  public class MetricsController {
      
      @GetMapping("/memory")
      public Map<String, Object> getMemoryMetrics() {
          Runtime runtime = Runtime.getRuntime();
          
          long maxMemory = runtime.maxMemory();
          long totalMemory = runtime.totalMemory();
          long freeMemory = runtime.freeMemory();
          long usedMemory = totalMemory - freeMemory;
          
          return Map.of(
              "maxMemoryMB", maxMemory / (1024 * 1024),
              "totalMemoryMB", totalMemory / (1024 * 1024),
              "usedMemoryMB", usedMemory / (1024 * 1024),
              "freeMemoryMB", freeMemory / (1024 * 1024),
              "usagePercent", (usedMemory * 100) / totalMemory
          );
      }
      
      @GetMapping("/threads")
      public Map<String, Object> getThreadMetrics() {
          ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
          
          return Map.of(
              "threadCount", threadBean.getThreadCount(),
              "peakThreadCount", threadBean.getPeakThreadCount(),
              "daemonThreadCount", threadBean.getDaemonThreadCount()
          );
      }
  }
  ```

---

## 🔷 Part 2: Database Performance Optimization

### Concepts to Master:

- [ ] **Query Optimization**
  ```java
  // ❌ BAD: N+1 Query Problem
  @Service
  public class OrderService {
      
      public List<OrderDTO> getAllOrders() {
          List<Order> orders = orderRepository.findAll();
          
          return orders.stream()
              .map(order -> {
                  // Each iteration = 1 query!
                  User user = userRepository.findById(order.getUserId()).get();
                  return new OrderDTO(order, user);
              })
              .collect(Collectors.toList());
          // Total queries: 1 + N (where N = number of orders)
      }
  }
  
  // ✅ GOOD: Join Fetch
  @Repository
  public interface OrderRepository extends JpaRepository<Order, Long> {
      
      @Query("SELECT o FROM Order o JOIN FETCH o.user")
      List<Order> findAllWithUser();
      // Total queries: 1 (single join query)
  }
  
  @Service
  public class OrderService {
      
      public List<OrderDTO> getAllOrders() {
          List<Order> orders = orderRepository.findAllWithUser();
          return orders.stream()
              .map(OrderDTO::new)
              .collect(Collectors.toList());
      }
  }
  ```

- [ ] **Indexing Strategy**
  ```java
  @Entity
  @Table(name = "users", indexes = {
      @Index(name = "idx_email", columnList = "email"),
      @Index(name = "idx_created_at", columnList = "created_at"),
      @Index(name = "idx_status_created", columnList = "status, created_at")
  })
  public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      
      @Column(unique = true, nullable = false)
      private String email;  // Indexed for fast lookup
      
      private String status;
      
      @Column(name = "created_at")
      private LocalDateTime createdAt;
  }
  ```
  
  **When to Index:**
  ```
  ✅ Index:
  - Columns in WHERE clause
  - Columns in JOIN conditions
  - Columns in ORDER BY
  - Foreign keys
  
  ❌ Don't Index:
  - Small tables (< 1000 rows)
  - Frequently updated columns
  - Columns with low cardinality (e.g., boolean)
  ```

- [ ] **Connection Pooling**
  ```properties
  # application.properties
  
  # HikariCP (Default in Spring Boot)
  spring.datasource.hikari.maximum-pool-size=10
  spring.datasource.hikari.minimum-idle=5
  spring.datasource.hikari.connection-timeout=30000
  spring.datasource.hikari.idle-timeout=600000
  spring.datasource.hikari.max-lifetime=1800000
  
  # Monitor pool
  spring.datasource.hikari.register-mbeans=true
  ```
  
  **Real-life Example:** 🏊 **Swimming Pool**
  - Connections = Swimmers
  - Pool = Connection pool
  - Max size = Pool capacity
  - Reuse connections instead of creating new

- [ ] **Pagination for Large Datasets**
  ```java
  // ❌ BAD: Load all data
  @GetMapping("/users")
  public List<User> getAllUsers() {
      return userRepository.findAll();  // Loads 1 million users!
  }
  
  // ✅ GOOD: Pagination
  @GetMapping("/users")
  public Page<User> getUsers(
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "20") int size) {
      
      Pageable pageable = PageRequest.of(page, size, 
          Sort.by("createdAt").descending());
      
      return userRepository.findAll(pageable);
      // Loads only 20 users per page
  }
  ```

---

## 🔷 Part 3: Caching Strategies

### Concepts to Master:

- [ ] **Spring Cache Abstraction**
  ```java
  @Configuration
  @EnableCaching
  public class CacheConfig {
      
      @Bean
      public CacheManager cacheManager() {
          SimpleCacheManager cacheManager = new SimpleCacheManager();
          cacheManager.setCaches(Arrays.asList(
              new ConcurrentMapCache("users"),
              new ConcurrentMapCache("products")
          ));
          return cacheManager;
      }
  }
  
  @Service
  public class UserService {
      
      @Cacheable(value = "users", key = "#id")
      public User getUserById(Long id) {
          log.info("Fetching user from database: {}", id);
          return userRepository.findById(id)
              .orElseThrow(() -> new UserNotFoundException("User not found"));
          // First call: DB query
          // Subsequent calls: From cache
      }
      
      @CachePut(value = "users", key = "#user.id")
      public User updateUser(User user) {
          return userRepository.save(user);
          // Updates cache after saving
      }
      
      @CacheEvict(value = "users", key = "#id")
      public void deleteUser(Long id) {
          userRepository.deleteById(id);
          // Removes from cache
      }
      
      @CacheEvict(value = "users", allEntries = true)
      public void clearCache() {
          // Clears entire cache
      }
  }
  ```

- [ ] **Redis Caching**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
  </dependency>
  ```
  
  ```properties
  # application.properties
  spring.redis.host=localhost
  spring.redis.port=6379
  spring.cache.type=redis
  spring.cache.redis.time-to-live=600000  # 10 minutes
  ```
  
  ```java
  @Configuration
  @EnableCaching
  public class RedisCacheConfig {
      
      @Bean
      public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
          RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
              .entryTtl(Duration.ofMinutes(10))
              .serializeKeysWith(
                  RedisSerializationContext.SerializationPair.fromSerializer(
                      new StringRedisSerializer()))
              .serializeValuesWith(
                  RedisSerializationContext.SerializationPair.fromSerializer(
                      new GenericJackson2JsonRedisSerializer()));
          
          return RedisCacheManager.builder(connectionFactory)
              .cacheDefaults(config)
              .build();
      }
  }
  ```

- [ ] **Cache Strategies**
  ```
  Cache-Aside (Lazy Loading):
  1. Check cache
  2. If miss, load from DB
  3. Store in cache
  4. Return data
  
  Write-Through:
  1. Write to cache
  2. Write to DB
  3. Both updated together
  
  Write-Behind:
  1. Write to cache
  2. Async write to DB
  3. Faster writes
  
  Refresh-Ahead:
  1. Proactively refresh cache
  2. Before expiration
  3. Always fresh data
  ```

---

## 🔷 Part 4: API Performance

### Concepts to Master:

- [ ] **Async Processing**
  ```java
  @Configuration
  @EnableAsync
  public class AsyncConfig {
      
      @Bean
      public Executor taskExecutor() {
          ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
          executor.setCorePoolSize(5);
          executor.setMaxPoolSize(10);
          executor.setQueueCapacity(100);
          executor.setThreadNamePrefix("async-");
          executor.initialize();
          return executor;
      }
  }
  
  @Service
  public class EmailService {
      
      @Async
      public CompletableFuture<Void> sendEmail(String to, String subject, String body) {
          log.info("Sending email to: {}", to);
          
          // Simulate email sending
          try {
              Thread.sleep(2000);  // 2 seconds
              log.info("Email sent successfully");
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
          }
          
          return CompletableFuture.completedFuture(null);
      }
  }
  
  @RestController
  public class UserController {
      
      @Autowired
      private EmailService emailService;
      
      @PostMapping("/users")
      public ResponseEntity<User> createUser(@RequestBody User user) {
          // Save user (fast)
          User savedUser = userService.createUser(user);
          
          // Send welcome email (async - doesn't block)
          emailService.sendEmail(
              user.getEmail(),
              "Welcome!",
              "Welcome to our platform"
          );
          
          // Return immediately (user doesn't wait for email)
          return ResponseEntity.ok(savedUser);
      }
  }
  ```

- [ ] **Response Compression**
  ```properties
  # application.properties
  
  # Enable GZIP compression
  server.compression.enabled=true
  server.compression.mime-types=application/json,application/xml,text/html,text/xml,text/plain
  server.compression.min-response-size=1024
  
  # Reduces response size by 70-90%!
  ```

- [ ] **Lazy Loading**
  ```java
  @Entity
  public class User {
      @Id
      private Long id;
      
      private String name;
      
      // Lazy: Only loaded when accessed
      @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
      private List<Order> orders;
      
      // Eager: Always loaded (avoid for large collections)
      @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
      private List<Address> addresses;
  }
  
  // Use DTOs to control what's returned
  public class UserDTO {
      private Long id;
      private String name;
      // No orders - lighter response
      
      public UserDTO(User user) {
          this.id = user.getId();
          this.name = user.getName();
      }
  }
  ```

---

## 🔷 Part 5: Load Testing & Monitoring

### Concepts to Master:

- [ ] **Load Testing with JMeter**
  ```
  Test Scenario:
  - 1000 concurrent users
  - 10,000 requests
  - Measure response time
  
  Metrics to Track:
  - Average response time
  - 95th percentile
  - 99th percentile
  - Error rate
  - Throughput (requests/sec)
  
  Acceptable:
  - p95 < 500ms
  - p99 < 1000ms
  - Error rate < 1%
  ```

- [ ] **Performance Monitoring**
  ```java
  @Aspect
  @Component
  public class PerformanceMonitorAspect {
      
      private static final Logger log = LoggerFactory.getLogger(PerformanceMonitorAspect.class);
      
      @Around("@annotation(com.myapp.annotation.MonitorPerformance)")
      public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
          long startTime = System.currentTimeMillis();
          
          try {
              Object result = joinPoint.proceed();
              return result;
          } finally {
              long duration = System.currentTimeMillis() - startTime;
              
              if (duration > 1000) {
                  log.warn("Slow method: {} took {}ms", 
                      joinPoint.getSignature().getName(), duration);
              } else {
                  log.debug("Method: {} took {}ms", 
                      joinPoint.getSignature().getName(), duration);
              }
          }
      }
  }
  
  // Usage
  @Service
  public class OrderService {
      
      @MonitorPerformance
      public Order createOrder(Order order) {
          // Method execution time will be logged
          return orderRepository.save(order);
      }
  }
  ```

---

## 💻 Practice: Optimize Slow API

### Scenario: E-Commerce Product API

**Problem:**
```
GET /api/products
- Response time: 5 seconds
- 10,000 products
- Loading all data
- No caching
- N+1 queries
```

**Optimization Steps:**

- [ ] **Step 1: Add Pagination**
  ```java
  @GetMapping("/products")
  public Page<Product> getProducts(Pageable pageable) {
      return productRepository.findAll(pageable);
  }
  // Response time: 500ms (10x faster!)
  ```

- [ ] **Step 2: Add Caching**
  ```java
  @Cacheable("products")
  public Page<Product> getProducts(Pageable pageable) {
      return productRepository.findAll(pageable);
  }
  // Response time: 50ms (100x faster!)
  ```

- [ ] **Step 3: Optimize Queries**
  ```java
  @Query("SELECT p FROM Product p JOIN FETCH p.category")
  Page<Product> findAllWithCategory(Pageable pageable);
  // Eliminates N+1 queries
  ```

- [ ] **Step 4: Use DTOs**
  ```java
  public class ProductDTO {
      private Long id;
      private String name;
      private BigDecimal price;
      // Only essential fields
  }
  // Smaller response size
  ```

**Result:**
- Before: 5000ms
- After: 50ms
- **100x faster!** 🚀

---

## 🎯 Interview Questions & Answers

### Q1: How do you identify performance bottlenecks?

**Answer:**
```
Tools & Techniques:

1. Application Metrics:
   - Response time (p50, p95, p99)
   - Error rate
   - Throughput
   - Resource usage (CPU, memory)

2. Database Monitoring:
   - Slow query log
   - Query execution time
   - Connection pool usage
   - Index usage

3. Profiling:
   - JProfiler, YourKit
   - Identify slow methods
   - Memory leaks
   - Thread contention

4. Load Testing:
   - JMeter, Gatling
   - Simulate production load
   - Find breaking point

Real Example:
- API slow: 2 seconds
- Check metrics: DB queries taking 1.8s
- Enable slow query log
- Find N+1 query problem
- Add JOIN FETCH
- Response time: 200ms
```

### Q2: Explain caching strategies.

**Answer:**
```
1. Cache-Aside (Most Common):
   - App checks cache first
   - If miss, load from DB
   - Store in cache
   - Example: User profile

2. Write-Through:
   - Write to cache and DB together
   - Always consistent
   - Example: Session data

3. Write-Behind:
   - Write to cache immediately
   - Async write to DB
   - Faster writes
   - Example: Analytics data

4. Refresh-Ahead:
   - Proactively refresh cache
   - Before expiration
   - Example: Popular products

When to Use:
- Read-heavy: Cache-Aside
- Write-heavy: Write-Behind
- Consistency critical: Write-Through
```

---

## ✅ Move-On Criteria

- [ ] ✔ **JVM memory structure বুঝবেন**
- [ ] ✔ **GC tuning করতে পারবেন**
- [ ] ✔ **Database queries optimize করতে পারবেন**
- [ ] ✔ **Caching implement করতে পারবেন**
- [ ] ✔ **Async processing করতে পারবেন**
- [ ] ✔ **Performance monitoring setup করতে পারবেন**
- [ ] ✔ **Load testing করতে পারবেন**
- [ ] ✔ **Bottlenecks identify করতে পারবেন**

---

**Real-World Impact:**
- 🚀 10x faster APIs
- 💰 50% cost reduction
- 😊 Better user experience
- 📈 Higher scalability
- 🏆 Senior developer skill

**Next:** [Advanced Security →](Java-Topic-17-Advanced-Security.md)

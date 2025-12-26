# 📌 Topic 15: Monitoring & Logging - Production Readiness

## 🎯 Learning Objectives

Master monitoring and logging to debug production issues and ensure application health - skills that AI cannot replace and are critical for senior developer roles.

---

## 🔷 Part 1: Understanding Observability

### Concepts to Master:

- [ ] **What is Observability?**
  ```
  Observability = Ability to understand system's internal state
  
  Three Pillars:
  1. Logs: What happened?
  2. Metrics: How much/how many?
  3. Traces: Where did request go?
  ```
  
  **Real-life Example:** 🏥 **Medical Diagnosis**
  - Logs = Patient symptoms (what happened)
  - Metrics = Vital signs (heart rate, temperature)
  - Traces = Blood flow through body
  - Doctor = You debugging the system

- [ ] **Why Monitoring?**
  ```
  Without Monitoring:
  - App crashes, you don't know
  - Users complain, you investigate
  - Reactive approach
  - Downtime costs money
  
  With Monitoring:
  - Alerts before crash
  - Proactive fixes
  - Better user experience
  - Prevent issues
  ```
  
  **Real-life Example:** 🚗 **Car Dashboard**
  - Speedometer = Metrics
  - Warning lights = Alerts
  - Trip computer = Logs
  - You fix issues before breakdown

---

## 🔷 Part 2: Logging with SLF4J & Logback

### Concepts to Master:

- [ ] **Logging Levels**
  ```
  TRACE: Very detailed (rarely used)
  DEBUG: Detailed for debugging
  INFO: General information
  WARN: Warning, but app continues
  ERROR: Error occurred
  FATAL: Critical error, app might crash
  
  Production: INFO and above
  Development: DEBUG and above
  ```
  
  **Real-life Example:** 📢 **Announcement Volume**
  - TRACE = Whisper
  - DEBUG = Normal voice
  - INFO = Announcement
  - WARN = Loud warning
  - ERROR = Alarm
  - FATAL = Emergency siren

- [ ] **Setup Logback**
  ```xml
  <!-- pom.xml (Spring Boot includes this by default) -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-logging</artifactId>
  </dependency>
  ```
  
  ```xml
  <!-- src/main/resources/logback-spring.xml -->
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration>
      
      <!-- Console Appender -->
      <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
          <encoder>
              <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
          </encoder>
      </appender>
      
      <!-- File Appender -->
      <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
          <file>logs/application.log</file>
          <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
              <fileNamePattern>logs/application-%d{yyyy-MM-dd}.log</fileNamePattern>
              <maxHistory>30</maxHistory>
          </rollingPolicy>
          <encoder>
              <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
          </encoder>
      </appender>
      
      <!-- Root Logger -->
      <root level="INFO">
          <appender-ref ref="CONSOLE" />
          <appender-ref ref="FILE" />
      </root>
      
      <!-- Package-specific logging -->
      <logger name="com.myapp" level="DEBUG" />
      <logger name="org.springframework" level="WARN" />
      <logger name="org.hibernate" level="WARN" />
      
  </configuration>
  ```

- [ ] **Logging in Code**
  ```java
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  
  @Service
  public class UserService {
      
      private static final Logger log = LoggerFactory.getLogger(UserService.class);
      
      public User createUser(User user) {
          log.info("Creating user: {}", user.getEmail());
          
          try {
              // Validate
              validateUser(user);
              
              // Save
              User savedUser = userRepository.save(user);
              
              log.info("User created successfully: id={}", savedUser.getId());
              return savedUser;
              
          } catch (ValidationException e) {
              log.warn("User validation failed: {}", e.getMessage());
              throw e;
          } catch (Exception e) {
              log.error("Error creating user: {}", user.getEmail(), e);
              throw new RuntimeException("Failed to create user", e);
          }
      }
      
      public void deleteUser(Long userId) {
          log.debug("Attempting to delete user: {}", userId);
          
          User user = userRepository.findById(userId)
              .orElseThrow(() -> {
                  log.error("User not found for deletion: {}", userId);
                  return new UserNotFoundException("User not found");
              });
          
          userRepository.delete(user);
          log.info("User deleted: id={}", userId);
      }
  }
  ```

- [ ] **Structured Logging (JSON)**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>net.logstash.logback</groupId>
      <artifactId>logstash-logback-encoder</artifactId>
      <version>7.3</version>
  </dependency>
  ```
  
  ```xml
  <!-- logback-spring.xml -->
  <appender name="JSON_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
      <file>logs/application.json</file>
      <encoder class="net.logstash.logback.encoder.LogstashEncoder">
          <includeMdcKeyName>userId</includeMdcKeyName>
          <includeMdcKeyName>requestId</includeMdcKeyName>
      </encoder>
  </appender>
  ```
  
  **Output:**
  ```json
  {
    "timestamp": "2024-01-15T10:30:45.123Z",
    "level": "INFO",
    "logger": "com.myapp.UserService",
    "message": "User created successfully",
    "userId": "123",
    "requestId": "abc-def-ghi"
  }
  ```

---

## 🔷 Part 3: Application Metrics with Micrometer

### Concepts to Master:

- [ ] **What are Metrics?**
  ```
  Metrics = Numerical measurements over time
  
  Types:
  - Counter: Increments only (requests count)
  - Gauge: Up/down value (memory usage)
  - Timer: Duration (request latency)
  - Distribution Summary: Distribution (response sizes)
  ```
  
  **Real-life Example:** 📊 **Store Analytics**
  - Counter = Customer count
  - Gauge = Current customers in store
  - Timer = Average checkout time
  - Summary = Purchase amounts distribution

- [ ] **Setup Micrometer (Spring Boot Actuator)**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-actuator</artifactId>
  </dependency>
  <dependency>
      <groupId>io.micrometer</groupId>
      <artifactId>micrometer-registry-prometheus</artifactId>
  </dependency>
  ```
  
  ```properties
  # application.properties
  management.endpoints.web.exposure.include=health,info,metrics,prometheus
  management.endpoint.health.show-details=always
  management.metrics.export.prometheus.enabled=true
  ```

- [ ] **Custom Metrics**
  ```java
  @Service
  public class OrderService {
      
      private final MeterRegistry meterRegistry;
      private final Counter orderCounter;
      private final Timer orderProcessingTimer;
      
      public OrderService(MeterRegistry meterRegistry) {
          this.meterRegistry = meterRegistry;
          
          // Counter
          this.orderCounter = Counter.builder("orders.created")
              .description("Total orders created")
              .tag("type", "online")
              .register(meterRegistry);
          
          // Timer
          this.orderProcessingTimer = Timer.builder("order.processing.time")
              .description("Order processing duration")
              .register(meterRegistry);
      }
      
      public Order createOrder(Order order) {
          return orderProcessingTimer.record(() -> {
              // Process order
              Order savedOrder = orderRepository.save(order);
              
              // Increment counter
              orderCounter.increment();
              
              // Gauge (current value)
              meterRegistry.gauge("orders.pending", orderRepository.countPending());
              
              return savedOrder;
          });
      }
  }
  ```

- [ ] **Access Metrics**
  ```
  Health: http://localhost:8080/actuator/health
  Metrics: http://localhost:8080/actuator/metrics
  Prometheus: http://localhost:8080/actuator/prometheus
  ```

---

## 🔷 Part 4: Prometheus & Grafana

### Concepts to Master:

- [ ] **What is Prometheus?**
  ```
  Prometheus = Metrics collection and storage
  
  Features:
  - Time-series database
  - Pulls metrics from apps
  - Powerful query language (PromQL)
  - Alerting
  ```
  
  **Real-life Example:** 📈 **Stock Market Tracker**
  - Prometheus = Stock price tracker
  - Collects prices every minute
  - Stores historical data
  - Query: "Show me prices last 7 days"

- [ ] **Setup Prometheus**
  ```yaml
  # prometheus.yml
  global:
    scrape_interval: 15s
  
  scrape_configs:
    - job_name: 'spring-boot-app'
      metrics_path: '/actuator/prometheus'
      static_configs:
        - targets: ['localhost:8080']
  ```
  
  ```bash
  # Run Prometheus
  docker run -d -p 9090:9090 \
    -v $(pwd)/prometheus.yml:/etc/prometheus/prometheus.yml \
    prom/prometheus
  
  # Access: http://localhost:9090
  ```

- [ ] **What is Grafana?**
  ```
  Grafana = Visualization tool
  
  Features:
  - Beautiful dashboards
  - Connects to Prometheus
  - Alerts
  - Templating
  ```
  
  **Real-life Example:** 📺 **TV News Graphics**
  - Grafana = Graphics department
  - Takes data (Prometheus)
  - Creates visual charts
  - Easy to understand

- [ ] **Setup Grafana**
  ```bash
  # Run Grafana
  docker run -d -p 3000:3000 grafana/grafana
  
  # Access: http://localhost:3000
  # Default: admin/admin
  
  # Steps:
  # 1. Add Data Source → Prometheus
  # 2. URL: http://prometheus:9090
  # 3. Import Dashboard → ID: 4701 (JVM Micrometer)
  ```

- [ ] **Common Dashboards**
  ```
  JVM Metrics:
  - Heap memory usage
  - GC activity
  - Thread count
  - CPU usage
  
  Application Metrics:
  - Request rate
  - Error rate
  - Response time (p50, p95, p99)
  - Active users
  
  Database Metrics:
  - Connection pool
  - Query duration
  - Slow queries
  ```

---

## 🔷 Part 5: Distributed Tracing (Already covered in Spring Cloud)

### Quick Recap:

- [ ] **Sleuth + Zipkin**
  ```
  - Trace requests across microservices
  - See timing for each service
  - Identify bottlenecks
  - Correlate logs
  ```

---

## 🔷 Part 6: Alerting

### Concepts to Master:

- [ ] **Alert Rules in Prometheus**
  ```yaml
  # alert.rules.yml
  groups:
    - name: application_alerts
      rules:
        # High error rate
        - alert: HighErrorRate
          expr: rate(http_requests_total{status="500"}[5m]) > 0.05
          for: 5m
          labels:
            severity: critical
          annotations:
            summary: "High error rate detected"
            description: "Error rate is {{ $value }} errors/sec"
        
        # High memory usage
        - alert: HighMemoryUsage
          expr: jvm_memory_used_bytes / jvm_memory_max_bytes > 0.9
          for: 10m
          labels:
            severity: warning
          annotations:
            summary: "High memory usage"
            description: "Memory usage is {{ $value }}%"
        
        # Service down
        - alert: ServiceDown
          expr: up == 0
          for: 1m
          labels:
            severity: critical
          annotations:
            summary: "Service is down"
  ```

- [ ] **Slack Notifications**
  ```java
  @Service
  public class AlertService {
      
      @Value("${slack.webhook.url}")
      private String slackWebhookUrl;
      
      public void sendAlert(String message, String severity) {
          String color = severity.equals("critical") ? "danger" : "warning";
          
          String payload = String.format("""
              {
                "attachments": [{
                  "color": "%s",
                  "title": "Application Alert",
                  "text": "%s",
                  "footer": "Monitoring System",
                  "ts": %d
                }]
              }
              """, color, message, System.currentTimeMillis() / 1000);
          
          // Send to Slack
          restTemplate.postForEntity(slackWebhookUrl, payload, String.class);
      }
  }
  ```

---

## 💻 Practice Project: Complete Monitoring Setup

### Project: Production-Ready Monitoring

**Stack:**
```
Spring Boot App
    ↓
Logback (Logs)
    ↓
Micrometer (Metrics)
    ↓
Prometheus (Collection)
    ↓
Grafana (Visualization)
    ↓
Alerts → Slack
```

### Implementation Steps:

- [ ] **Step 1: Setup Logging**
  - Configure Logback
  - Structured logging (JSON)
  - Log rotation

- [ ] **Step 2: Add Metrics**
  - Spring Boot Actuator
  - Custom metrics
  - Prometheus endpoint

- [ ] **Step 3: Setup Prometheus**
  - Docker container
  - Configure scraping
  - Define alert rules

- [ ] **Step 4: Setup Grafana**
  - Docker container
  - Connect to Prometheus
  - Import dashboards
  - Customize panels

- [ ] **Step 5: Configure Alerts**
  - High error rate
  - High memory usage
  - Service down
  - Slack notifications

**Duration:** 3-4 days
**Skills:** Logging, Metrics, Monitoring, Alerting

---

## 🎯 Interview Questions & Answers

### Q1: What are the three pillars of observability?

**Answer:**
```
1. Logs:
   - What happened?
   - Detailed events
   - Example: "User 123 logged in"
   
2. Metrics:
   - How much/many?
   - Numerical measurements
   - Example: "500 requests/sec"
   
3. Traces:
   - Where did request go?
   - Request flow across services
   - Example: "API → User Service → DB"

Real Example: E-commerce checkout
- Logs: "Payment processed for order 456"
- Metrics: "Average checkout time: 2.5s"
- Traces: "Checkout → Payment → Inventory → Email"
```

### Q2: Explain logging levels and when to use each.

**Answer:**
```
DEBUG:
- Development only
- Detailed debugging info
- Example: "SQL query: SELECT * FROM users"

INFO:
- Production default
- General information
- Example: "User registered: john@email.com"

WARN:
- Potential issues
- App continues running
- Example: "Slow query detected: 5s"

ERROR:
- Actual errors
- Needs attention
- Example: "Failed to send email"

FATAL:
- Critical errors
- App might crash
- Example: "Database connection lost"

Production: INFO and above
Development: DEBUG and above
```

### Q3: How do you monitor application performance?

**Answer:**
```
Key Metrics:

1. Request Rate:
   - Requests per second
   - Track traffic patterns
   
2. Error Rate:
   - Percentage of failed requests
   - Alert if > 1%
   
3. Response Time:
   - p50 (median)
   - p95 (95th percentile)
   - p99 (99th percentile)
   
4. Resource Usage:
   - CPU usage
   - Memory usage
   - Database connections
   
5. Business Metrics:
   - Orders per hour
   - Active users
   - Revenue

Tools:
- Prometheus (collect)
- Grafana (visualize)
- Alerts (notify)

Real Example:
- Normal: 1000 req/s, 0.1% errors, 200ms p95
- Alert: 500 req/s, 5% errors, 2s p95
- Action: Investigate and fix
```

---

## ✅ Move-On Criteria

- [ ] ✔ **Observability pillars explain করতে পারবেন**
- [ ] ✔ **Logback configure করতে পারবেন**
- [ ] ✔ **Proper logging করতে পারবেন code এ**
- [ ] ✔ **Custom metrics create করতে পারবেন**
- [ ] ✔ **Prometheus setup করতে পারবেন**
- [ ] ✔ **Grafana dashboard তৈরি করতে পারবেন**
- [ ] ✔ **Alert rules configure করতে পারবেন**
- [ ] ✔ **Production monitoring setup করতে পারবেন**
- [ ] ✔ **Interview questions confidently answer করতে পারবেন**

---

**Real-World Applications:**
- 🛒 E-commerce (Track sales, errors)
- 🏦 Banking (Monitor transactions)
- 📱 Social Media (User activity)
- 🎬 Streaming (Video quality)
- 🚗 Ride-sharing (Driver locations)

**Congratulations! You've completed all critical topics! 🎉**

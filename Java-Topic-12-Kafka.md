# 📌 Topic 12: Apache Kafka - Event-Driven Architecture

## 🎯 Learning Objectives

Master Apache Kafka to build real-time, event-driven applications - a critical skill for modern distributed systems that AI cannot easily replace.

---

## 🔷 Part 1: Understanding Event-Driven Architecture

### Concepts to Master:

- [ ] **What is Event-Driven Architecture?**
  ```
  Traditional (Request-Response):
  - Service A calls Service B
  - Waits for response
  - Tight coupling
  - Synchronous
  
  Event-Driven:
  - Service A publishes event
  - Service B subscribes to event
  - No direct dependency
  - Asynchronous
  ```
  
  **Real-life Example:** 📰 **Newspaper Subscription**
  - Publisher = Newspaper company
  - Event = Daily newspaper
  - Subscribers = Readers
  - Publisher doesn't know who reads
  - Readers don't know who else reads
  - Loose coupling!

- [ ] **Why Kafka?**
  ```
  Benefits:
  ✅ High throughput (millions of messages/sec)
  ✅ Scalable (horizontal scaling)
  ✅ Fault-tolerant (replication)
  ✅ Durable (persistent storage)
  ✅ Real-time processing
  ✅ Decoupling services
  
  Use Cases:
  - Real-time analytics
  - Log aggregation
  - Event sourcing
  - Stream processing
  - Microservices communication
  ```
  
  **Real-life Example:** 📺 **TV Broadcasting**
  - TV Station = Kafka
  - TV Channels = Topics
  - Viewers = Consumers
  - Broadcast once, many watch
  - Viewers can rewind (replay events)

- [ ] **Kafka vs Traditional Messaging**
  ```
  RabbitMQ/ActiveMQ:
  - Message deleted after consumption
  - Lower throughput
  - Better for task queues
  
  Kafka:
  - Messages retained (configurable)
  - Very high throughput
  - Better for event streaming
  - Can replay events
  ```

---

## 🔷 Part 2: Kafka Core Concepts

### Concepts to Master:

- [ ] **Topics & Partitions**
  ```
  Topic = Category of messages
  - Like a folder for events
  - Example: "user-registrations", "order-placed"
  
  Partition = Subdivision of topic
  - For parallel processing
  - Each partition is ordered
  - Messages distributed across partitions
  ```
  
  **Diagram:**
  ```
  Topic: "orders"
  ┌─────────────────────────────────┐
  │ Partition 0: [msg1, msg2, msg3] │
  │ Partition 1: [msg4, msg5, msg6] │
  │ Partition 2: [msg7, msg8, msg9] │
  └─────────────────────────────────┘
  ```
  
  **Real-life Example:** 🏪 **Supermarket Checkout**
  - Topic = Checkout area
  - Partitions = Individual checkout counters
  - Customers distributed across counters
  - Each counter processes in order

- [ ] **Producers & Consumers**
  ```
  Producer:
  - Sends messages to topics
  - Decides which partition (or Kafka decides)
  - Fire and forget OR wait for acknowledgment
  
  Consumer:
  - Reads messages from topics
  - Can be part of consumer group
  - Tracks offset (position in partition)
  ```
  
  **Real-life Example:** 📬 **Post Office**
  - Producer = Person sending letters
  - Topic = Mailbox
  - Consumer = Mail carrier
  - Offset = Which letters already delivered

- [ ] **Consumer Groups**
  ```
  Consumer Group = Multiple consumers working together
  - Each partition consumed by ONE consumer in group
  - Load balancing automatic
  - If consumer fails, partition reassigned
  ```
  
  **Diagram:**
  ```
  Topic with 3 partitions:
  P0, P1, P2
  
  Consumer Group A:
  - Consumer 1 → P0
  - Consumer 2 → P1
  - Consumer 3 → P2
  
  Consumer Group B:
  - Consumer 4 → P0, P1, P2 (reads all)
  ```
  
  **Real-life Example:** 👨‍🍳 **Restaurant Kitchen**
  - Consumer Group = Kitchen staff
  - Partitions = Order tickets
  - Each chef takes different orders
  - If chef leaves, others take over

- [ ] **Offsets**
  ```
  Offset = Position in partition
  - Like a bookmark
  - Consumer tracks where it left off
  - Can replay from any offset
  - Committed offset = Last processed message
  ```
  
  **Real-life Example:** 📖 **Reading a Book**
  - Offset = Page number
  - Bookmark = Committed offset
  - Can go back and re-read (replay)
  - Multiple readers can have different bookmarks

---

## 🔷 Part 3: Kafka with Spring Boot

### Concepts to Master:

- [ ] **Setup Kafka**
  ```bash
  # Download Kafka
  wget https://downloads.apache.org/kafka/3.6.0/kafka_2.13-3.6.0.tgz
  tar -xzf kafka_2.13-3.6.0.tgz
  cd kafka_2.13-3.6.0
  
  # Start Zookeeper
  bin/zookeeper-server-start.sh config/zookeeper.properties
  
  # Start Kafka Server (new terminal)
  bin/kafka-server-start.sh config/server.properties
  
  # Create Topic
  bin/kafka-topics.sh --create --topic user-events \
    --bootstrap-server localhost:9092 \
    --partitions 3 \
    --replication-factor 1
  ```

- [ ] **Spring Boot Kafka Dependencies**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.kafka</groupId>
      <artifactId>spring-kafka</artifactId>
  </dependency>
  ```
  
  ```properties
  # application.properties
  spring.kafka.bootstrap-servers=localhost:9092
  
  # Producer config
  spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
  spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
  
  # Consumer config
  spring.kafka.consumer.group-id=my-consumer-group
  spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
  spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
  spring.kafka.consumer.properties.spring.json.trusted.packages=*
  spring.kafka.consumer.auto-offset-reset=earliest
  ```

- [ ] **Kafka Producer (Sending Events)**
  ```java
  // Event Model
  public class UserRegisteredEvent {
      private Long userId;
      private String email;
      private String name;
      private LocalDateTime timestamp;
      
      // Getters, Setters, Constructor
  }
  
  // Producer Service
  @Service
  public class UserEventProducer {
      
      private static final String TOPIC = "user-events";
      
      @Autowired
      private KafkaTemplate<String, UserRegisteredEvent> kafkaTemplate;
      
      public void publishUserRegistered(UserRegisteredEvent event) {
          // Send event to Kafka
          kafkaTemplate.send(TOPIC, event.getUserId().toString(), event);
          
          log.info("Published user registered event: {}", event.getUserId());
      }
      
      // With callback
      public void publishWithCallback(UserRegisteredEvent event) {
          CompletableFuture<SendResult<String, UserRegisteredEvent>> future = 
              kafkaTemplate.send(TOPIC, event.getUserId().toString(), event);
          
          future.whenComplete((result, ex) -> {
              if (ex == null) {
                  log.info("Sent message=[{}] with offset=[{}]", 
                      event, result.getRecordMetadata().offset());
              } else {
                  log.error("Unable to send message=[{}] due to: {}", 
                      event, ex.getMessage());
              }
          });
      }
  }
  
  // Usage in Controller
  @RestController
  @RequestMapping("/api/users")
  public class UserController {
      
      @Autowired
      private UserService userService;
      
      @Autowired
      private UserEventProducer eventProducer;
      
      @PostMapping("/register")
      public ResponseEntity<User> registerUser(@RequestBody User user) {
          // Save user to database
          User savedUser = userService.createUser(user);
          
          // Publish event
          UserRegisteredEvent event = new UserRegisteredEvent(
              savedUser.getId(),
              savedUser.getEmail(),
              savedUser.getName(),
              LocalDateTime.now()
          );
          eventProducer.publishUserRegistered(event);
          
          return ResponseEntity.ok(savedUser);
      }
  }
  ```
  
  **Real-life Example:** 📢 **School Announcement**
  - Teacher makes announcement (producer)
  - Announcement broadcast to all classes (topic)
  - Students hear it (consumers)
  - Teacher doesn't wait for confirmation

- [ ] **Kafka Consumer (Receiving Events)**
  ```java
  @Service
  public class EmailNotificationConsumer {
      
      @KafkaListener(
          topics = "user-events",
          groupId = "email-notification-group"
      )
      public void consumeUserEvent(UserRegisteredEvent event) {
          log.info("Received user registered event: {}", event.getUserId());
          
          // Send welcome email
          sendWelcomeEmail(event.getEmail(), event.getName());
      }
      
      private void sendWelcomeEmail(String email, String name) {
          // Email sending logic
          log.info("Sending welcome email to: {}", email);
      }
  }
  
  @Service
  public class AnalyticsConsumer {
      
      @KafkaListener(
          topics = "user-events",
          groupId = "analytics-group"
      )
      public void consumeUserEvent(UserRegisteredEvent event) {
          log.info("Recording analytics for user: {}", event.getUserId());
          
          // Update analytics dashboard
          updateUserMetrics(event);
      }
      
      private void updateUserMetrics(UserRegisteredEvent event) {
          // Analytics logic
          log.info("User count increased");
      }
  }
  ```
  
  **Key Points:**
  - Different consumer groups process same event
  - Email service and Analytics service independent
  - If one fails, other continues
  - Loose coupling!

- [ ] **Error Handling**
  ```java
  @Service
  public class OrderEventConsumer {
      
      @KafkaListener(topics = "order-events", groupId = "order-processing-group")
      public void consumeOrderEvent(OrderEvent event) {
          try {
              processOrder(event);
          } catch (Exception ex) {
              log.error("Error processing order: {}", event.getOrderId(), ex);
              
              // Send to Dead Letter Queue (DLQ)
              sendToDeadLetterQueue(event, ex);
          }
      }
      
      private void sendToDeadLetterQueue(OrderEvent event, Exception ex) {
          // Publish to DLQ topic for manual review
          kafkaTemplate.send("order-events-dlq", event);
      }
  }
  
  // DLQ Consumer (for manual review)
  @Service
  public class DeadLetterQueueConsumer {
      
      @KafkaListener(topics = "order-events-dlq", groupId = "dlq-group")
      public void consumeFailedEvents(OrderEvent event) {
          log.warn("Failed event in DLQ: {}", event.getOrderId());
          // Alert admin, log to database, etc.
      }
  }
  ```

---

## 🔷 Part 4: Real-World Use Cases

### Concepts to Master:

- [ ] **Use Case 1: E-Commerce Order Processing**
  ```
  Flow:
  1. User places order → Publish "OrderPlaced" event
  2. Multiple consumers:
     - Inventory Service → Reduce stock
     - Payment Service → Process payment
     - Email Service → Send confirmation
     - Analytics Service → Update metrics
  
  Benefits:
  - Services don't wait for each other
  - If email fails, order still processed
  - Easy to add new consumers (e.g., SMS service)
  ```
  
  ```java
  // Order Service (Producer)
  @PostMapping("/orders")
  public ResponseEntity<Order> createOrder(@RequestBody Order order) {
      Order savedOrder = orderService.createOrder(order);
      
      // Publish event
      OrderPlacedEvent event = new OrderPlacedEvent(
          savedOrder.getId(),
          savedOrder.getUserId(),
          savedOrder.getItems(),
          savedOrder.getTotalAmount()
      );
      kafkaTemplate.send("order-events", event);
      
      return ResponseEntity.ok(savedOrder);
  }
  
  // Inventory Service (Consumer)
  @KafkaListener(topics = "order-events", groupId = "inventory-group")
  public void handleOrderPlaced(OrderPlacedEvent event) {
      for (OrderItem item : event.getItems()) {
          inventoryService.reduceStock(item.getProductId(), item.getQuantity());
      }
  }
  
  // Email Service (Consumer)
  @KafkaListener(topics = "order-events", groupId = "email-group")
  public void handleOrderPlaced(OrderPlacedEvent event) {
      emailService.sendOrderConfirmation(event.getUserId(), event.getOrderId());
  }
  ```

- [ ] **Use Case 2: Real-Time Analytics**
  ```
  Scenario: Track user activity in real-time
  
  Flow:
  1. User clicks button → Publish "UserClickEvent"
  2. Analytics service consumes events
  3. Aggregate data in real-time
  4. Update dashboard
  ```
  
  ```java
  // Frontend sends click events
  @PostMapping("/analytics/click")
  public void trackClick(@RequestBody ClickEvent event) {
      kafkaTemplate.send("user-activity", event);
  }
  
  // Analytics Consumer
  @KafkaListener(topics = "user-activity", groupId = "analytics-group")
  public void processUserActivity(ClickEvent event) {
      // Update real-time dashboard
      metricsService.incrementClickCount(event.getButtonId());
      metricsService.updateUserActivity(event.getUserId());
  }
  ```

- [ ] **Use Case 3: Log Aggregation**
  ```
  Scenario: Collect logs from multiple services
  
  Flow:
  1. Each service publishes logs to Kafka
  2. Log aggregation service consumes
  3. Store in Elasticsearch
  4. Visualize in Kibana
  ```
  
  ```java
  // Any service can publish logs
  @Service
  public class LogPublisher {
      
      public void logError(String service, String message, Exception ex) {
          LogEvent event = new LogEvent(
              service,
              "ERROR",
              message,
              ex.getMessage(),
              LocalDateTime.now()
          );
          kafkaTemplate.send("application-logs", event);
      }
  }
  
  // Log Aggregation Service
  @KafkaListener(topics = "application-logs", groupId = "log-aggregator")
  public void aggregateLogs(LogEvent event) {
      // Store in Elasticsearch
      elasticsearchService.indexLog(event);
  }
  ```

---

## 💻 Practice Project: Real-Time Notification System

### Project Overview:
Build a notification system that sends notifications via multiple channels (Email, SMS, Push) using Kafka.

### Architecture:
```
User Action → Notification Service (Producer)
                    ↓
              Kafka Topic: "notifications"
                    ↓
        ┌───────────┼───────────┐
        ↓           ↓           ↓
   Email       SMS         Push
  Consumer   Consumer    Consumer
```

### Implementation Steps:

- [ ] **Step 1: Setup Kafka**
  - Install Kafka locally
  - Create topic: "notifications"
  - Configure Spring Boot

- [ ] **Step 2: Create Notification Event**
  ```java
  public class NotificationEvent {
      private String userId;
      private String message;
      private NotificationType type; // EMAIL, SMS, PUSH
      private Map<String, String> metadata;
      private LocalDateTime timestamp;
  }
  ```

- [ ] **Step 3: Producer Service**
  ```java
  @Service
  public class NotificationProducer {
      
      @Autowired
      private KafkaTemplate<String, NotificationEvent> kafkaTemplate;
      
      public void sendNotification(NotificationEvent event) {
          kafkaTemplate.send("notifications", event.getUserId(), event);
      }
  }
  ```

- [ ] **Step 4: Consumer Services**
  - Email Consumer (sends emails)
  - SMS Consumer (sends SMS)
  - Push Consumer (sends push notifications)

- [ ] **Step 5: Add Features**
  - Retry mechanism for failed notifications
  - Dead Letter Queue for permanent failures
  - Notification history tracking
  - User preferences (opt-out)

**Duration:** 1-2 weeks
**Skills:** Kafka, Event-Driven Architecture, Async Processing

---

## 🎯 Interview Questions & Answers

### Q1: What is Apache Kafka and when would you use it?

**Answer:**
```
Kafka = Distributed event streaming platform

Use When:
✅ High throughput needed (millions of events/sec)
✅ Real-time data processing
✅ Event sourcing architecture
✅ Microservices communication (async)
✅ Log aggregation
✅ Activity tracking

Don't Use When:
❌ Simple request-response needed
❌ Low message volume
❌ Immediate response required
❌ Small team, simple app

Real Example: E-commerce order processing
- Order placed → Multiple services react
- Inventory, Payment, Email, Analytics
- All process asynchronously
```

### Q2: Explain Kafka Topics and Partitions.

**Answer:**
```
Topic = Category of messages
- Like a database table
- Example: "user-events", "order-events"

Partition = Subdivision of topic
- For parallel processing
- Each partition is ordered sequence
- Messages distributed across partitions

Example:
Topic: "orders" with 3 partitions
- Order 1 → Partition 0
- Order 2 → Partition 1
- Order 3 → Partition 2
- Order 4 → Partition 0 (round-robin)

Benefits:
- Parallel consumption (3 consumers)
- Scalability (add more partitions)
- Fault tolerance (replication)
```

### Q3: What is a Consumer Group?

**Answer:**
```
Consumer Group = Multiple consumers working together

Rules:
- Each partition consumed by ONE consumer in group
- Load balancing automatic
- If consumer fails, partition reassigned

Example:
Topic with 3 partitions: P0, P1, P2

Group A (3 consumers):
- Consumer 1 → P0
- Consumer 2 → P1
- Consumer 3 → P2
(Parallel processing)

Group B (1 consumer):
- Consumer 4 → P0, P1, P2
(Sequential processing)

Real Use: Order processing
- Group A = Fast processing (3 workers)
- Group B = Audit logging (1 worker)
```

### Q4: How does Kafka ensure message delivery?

**Answer:**
```
Kafka Guarantees:

1. At-most-once:
   - Producer sends, doesn't wait
   - Message might be lost
   - Fast but risky

2. At-least-once (Default):
   - Producer waits for acknowledgment
   - Message might be duplicated
   - Safe, need idempotent consumers

3. Exactly-once:
   - Kafka transactions
   - No loss, no duplicates
   - Slower but safest

Configuration:
acks=0 → At-most-once
acks=1 → At-least-once
acks=all → Exactly-once

Real Example: Payment processing
- Use exactly-once
- Can't charge customer twice!
```

### Q5: Kafka vs RabbitMQ - When to use which?

**Answer:**
```
Kafka:
✅ High throughput (millions/sec)
✅ Event streaming
✅ Message replay (retained)
✅ Real-time analytics
Example: User activity tracking

RabbitMQ:
✅ Complex routing
✅ Task queues
✅ Message deleted after consumption
✅ Lower latency
Example: Background job processing

Decision:
- Need replay? → Kafka
- Need routing? → RabbitMQ
- High volume? → Kafka
- Low latency? → RabbitMQ
- Event-driven? → Kafka
- Task queue? → RabbitMQ
```

---

## ✅ Move-On Criteria

- [ ] ✔ **Event-driven architecture explain করতে পারবেন**
- [ ] ✔ **Kafka topics, partitions, consumer groups বুঝবেন**
- [ ] ✔ **Producer implement করতে পারবেন**
- [ ] ✔ **Consumer implement করতে পারবেন**
- [ ] ✔ **Error handling এবং DLQ setup করতে পারবেন**
- [ ] ✔ **Real-world use cases explain করতে পারবেন**
- [ ] ✔ **Complete Kafka project build করতে পারবেন**
- [ ] ✔ **Interview questions confidently answer করতে পারবেন**

---

**Real-World Applications:**
- 🛒 E-commerce (Order processing)
- 📊 Analytics (Real-time dashboards)
- 📱 Social Media (Activity feeds)
- 🏦 Banking (Transaction processing)
- 🚗 Uber (Location tracking)

**Next:** [AWS Cloud Basics →](Java-Topic-13-AWS-Cloud.md)

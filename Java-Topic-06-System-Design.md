# 📌 Topic 6: System Design Fundamentals

## 🎯 Learning Objectives
Master system design concepts and design patterns - crucial for senior roles and scaling applications.

---

## 🔷 Part 1: Design Patterns

### Creational Patterns:

- [ ] **Singleton Pattern**
  ```java
  // Thread-safe Singleton
  public class Database {
      private static volatile Database instance;
      
      private Database() {
          // Private constructor
      }
      
      public static Database getInstance() {
          if (instance == null) {
              synchronized (Database.class) {
                  if (instance == null) {
                      instance = new Database();
                  }
              }
          }
          return instance;
      }
  }
  ```
  **Use Case:** Database connection, Logger, Configuration

- [ ] **Factory Pattern**
  ```java
  interface Vehicle {
      void drive();
  }
  
  class Car implements Vehicle {
      public void drive() { System.out.println("Driving car"); }
  }
  
  class Bike implements Vehicle {
      public void drive() { System.out.println("Riding bike"); }
  }
  
  class VehicleFactory {
      public static Vehicle createVehicle(String type) {
          if (type.equals("car")) return new Car();
          if (type.equals("bike")) return new Bike();
          return null;
      }
  }
  ```
  **Use Case:** Object creation based on input

- [ ] **Builder Pattern**
  ```java
  public class User {
      private String name;
      private String email;
      private int age;
      
      private User(Builder builder) {
          this.name = builder.name;
          this.email = builder.email;
          this.age = builder.age;
      }
      
      public static class Builder {
          private String name;
          private String email;
          private int age;
          
          public Builder name(String name) {
              this.name = name;
              return this;
          }
          
          public Builder email(String email) {
              this.email = email;
              return this;
          }
          
          public Builder age(int age) {
              this.age = age;
              return this;
          }
          
          public User build() {
              return new User(this);
          }
      }
  }
  
  // Usage
  User user = new User.Builder()
                  .name("John")
                  .email("john@test.com")
                  .age(25)
                  .build();
  ```
  **Use Case:** Complex object construction

### Behavioral Patterns:

- [ ] **Observer Pattern**
  ```java
  interface Observer {
      void update(String message);
  }
  
  class Subject {
      private List<Observer> observers = new ArrayList<>();
      
      public void attach(Observer observer) {
          observers.add(observer);
      }
      
      public void notifyObservers(String message) {
          for (Observer observer : observers) {
              observer.update(message);
          }
      }
  }
  ```
  **Use Case:** Event handling, Notifications

- [ ] **Strategy Pattern**
  ```java
  interface PaymentStrategy {
      void pay(int amount);
  }
  
  class CreditCardPayment implements PaymentStrategy {
      public void pay(int amount) {
          System.out.println("Paid " + amount + " using Credit Card");
      }
  }
  
  class PayPalPayment implements PaymentStrategy {
      public void pay(int amount) {
          System.out.println("Paid " + amount + " using PayPal");
      }
  }
  
  class ShoppingCart {
      private PaymentStrategy paymentStrategy;
      
      public void setPaymentStrategy(PaymentStrategy strategy) {
          this.paymentStrategy = strategy;
      }
      
      public void checkout(int amount) {
          paymentStrategy.pay(amount);
      }
  }
  ```
  **Use Case:** Multiple algorithms for same task

---

## 🔷 Part 2: SOLID Principles

- [ ] **S - Single Responsibility**
  ```java
  // Bad: Multiple responsibilities
  class User {
      void saveToDatabase() { }
      void sendEmail() { }
      void generateReport() { }
  }
  
  // Good: Single responsibility
  class User { }
  class UserRepository {
      void save(User user) { }
  }
  class EmailService {
      void send(User user) { }
  }
  class ReportGenerator {
      void generate(User user) { }
  }
  ```

- [ ] **O - Open/Closed**
  ```java
  // Open for extension, closed for modification
  interface Shape {
      double area();
  }
  
  class Circle implements Shape {
      private double radius;
      public double area() {
          return Math.PI * radius * radius;
      }
  }
  
  class Rectangle implements Shape {
      private double width, height;
      public double area() {
          return width * height;
      }
  }
  ```

- [ ] **L - Liskov Substitution**
  ```java
  // Subtypes must be substitutable for base types
  class Bird {
      void fly() { }
  }
  
  class Sparrow extends Bird {
      void fly() { /* can fly */ }
  }
  
  // Bad: Penguin can't fly!
  class Penguin extends Bird {
      void fly() { throw new UnsupportedOperationException(); }
  }
  ```

- [ ] **I - Interface Segregation**
  ```java
  // Many specific interfaces > One general interface
  interface Workable {
      void work();
  }
  
  interface Eatable {
      void eat();
  }
  
  class Human implements Workable, Eatable {
      public void work() { }
      public void eat() { }
  }
  
  class Robot implements Workable {
      public void work() { }
      // No eat() needed
  }
  ```

- [ ] **D - Dependency Inversion**
  ```java
  // Depend on abstractions, not concretions
  interface Database {
      void save(String data);
  }
  
  class MySQL implements Database {
      public void save(String data) { }
  }
  
  class UserService {
      private Database database;  // Depends on interface
      
      public UserService(Database database) {
          this.database = database;
      }
  }
  ```

---

## 🔷 Part 3: Microservices Architecture

### Concepts:

- [ ] **Monolith vs Microservices**
  ```
  Monolith:
  ✅ Simple deployment
  ✅ Easy development initially
  ❌ Scaling entire app
  ❌ Single point of failure
  
  Microservices:
  ✅ Independent scaling
  ✅ Technology flexibility
  ✅ Fault isolation
  ❌ Complex deployment
  ❌ Network overhead
  ```

- [ ] **Service Communication**
  ```
  1. REST APIs (Synchronous)
  2. Message Queues (Asynchronous)
     - RabbitMQ, Kafka
  3. gRPC (High performance)
  ```

- [ ] **API Gateway Pattern**
  ```
  Client → API Gateway → [Service 1, Service 2, Service 3]
  
  Benefits:
  - Single entry point
  - Authentication/Authorization
  - Rate limiting
  - Load balancing
  ```

---

## 🔷 Part 4: Scalability Concepts

- [ ] **Horizontal vs Vertical Scaling**
  ```
  Vertical (Scale Up):
  - Add more CPU/RAM to server
  - Limited by hardware
  - Downtime for upgrade
  
  Horizontal (Scale Out):
  - Add more servers
  - Load balancer distributes traffic
  - Better fault tolerance
  ```

- [ ] **Caching Strategies**
  ```
  1. Cache-Aside (Lazy Loading)
     - Check cache first
     - If miss, load from DB and cache
  
  2. Write-Through
     - Write to cache and DB simultaneously
  
  3. Write-Behind
     - Write to cache first
     - Async write to DB
  ```

- [ ] **Database Sharding**
  ```
  Horizontal Partitioning:
  - Split data across multiple databases
  - User 1-1000 → DB1
  - User 1001-2000 → DB2
  
  Sharding Key: user_id % num_shards
  ```

- [ ] **Load Balancing**
  ```
  Algorithms:
  1. Round Robin
  2. Least Connections
  3. IP Hash
  4. Weighted Round Robin
  ```

---

## 🔷 Part 5: System Design Problems

- [ ] **URL Shortener (like bit.ly)**
  ```
  Requirements:
  - Shorten long URLs
  - Redirect to original URL
  - Track clicks
  
  Design:
  1. Generate unique short code (base62 encoding)
  2. Store mapping in database
  3. Cache popular URLs (Redis)
  4. Handle high read traffic
  ```

- [ ] **Rate Limiter**
  ```
  Requirements:
  - Limit API requests per user
  - 100 requests per minute
  
  Algorithms:
  1. Token Bucket
  2. Leaky Bucket
  3. Fixed Window
  4. Sliding Window
  ```

- [ ] **Notification System**
  ```
  Requirements:
  - Send email, SMS, push notifications
  - Handle millions of users
  
  Design:
  1. Message Queue (Kafka)
  2. Worker services
  3. Third-party APIs (Twilio, SendGrid)
  4. Retry mechanism
  ```

---

## ✅ Move-On Criteria

- [ ] ✔ **Design patterns explain করতে পারবেন with use cases**
- [ ] ✔ **SOLID principles বুঝবেন**
- [ ] ✔ **Microservices vs Monolith trade-offs বলতে পারবেন**
- [ ] ✔ **Caching strategies explain করতে পারবেন**
- [ ] ✔ **Basic system design problems solve করতে পারবেন**

**Next:** [Networking Fundamentals →](Java-Topic-07-Networking.md)

# 📌 Topic 11: Spring Cloud & Microservices Ecosystem

## 🎯 Learning Objectives

Master Spring Cloud to build production-grade microservices architecture - a critical skill that AI cannot replace and is in extremely high demand.

---

## 🔷 Part 1: Understanding Microservices

### Concepts to Master:

- [ ] **What are Microservices?**
  ```
  Monolithic Application:
  - Single large application
  - All features in one codebase
  - Deploy entire app for small change
  - Scaling = Scale everything
  
  Microservices:
  - Multiple small services
  - Each service = one business capability
  - Independent deployment
  - Scaling = Scale specific services
  ```
  
  **Real-life Example:** 🏢 **Company Departments**
  - Monolith = One person doing everything
  - Microservices = HR, Finance, IT departments
  - Each department works independently
  - Communicate when needed
  
  **Benefits:**
  - ✅ Independent deployment
  - ✅ Technology flexibility
  - ✅ Better scalability
  - ✅ Fault isolation
  - ✅ Team autonomy
  
  **Challenges:**
  - ❌ Increased complexity
  - ❌ Network overhead
  - ❌ Distributed debugging
  - ❌ Data consistency

- [ ] **When to Use Microservices?**
  ```
  ✅ Use Microservices When:
  - Large team (10+ developers)
  - Complex domain
  - Need independent scaling
  - Different tech stacks needed
  - Frequent deployments
  
  ❌ DON'T Use Microservices When:
  - Small team (< 5 developers)
  - Simple application
  - Tight deadline
  - Limited DevOps expertise
  ```
  
  **Interview Tip:** "Microservices are not always the answer. For small apps, monolith is better. Choose based on team size, complexity, and scaling needs."

---

## 🔷 Part 2: Service Discovery (Eureka)

### Concepts to Master:

- [ ] **Why Service Discovery?**
  ```
  Problem Without Service Discovery:
  - Service A needs to call Service B
  - Hard-coded URL: http://localhost:8081
  - What if Service B moves to another server?
  - What if multiple instances of Service B?
  - Manual configuration nightmare!
  
  Solution With Eureka:
  - Services register themselves
  - Dynamic service lookup
  - Automatic load balancing
  - Health monitoring
  ```
  
  **Real-life Example:** 📞 **Phone Directory**
  - Eureka Server = Phone book
  - Services = People
  - Register = Add your number
  - Discover = Look up someone's number
  - No need to remember everyone's number!

- [ ] **Eureka Server Setup**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
  </dependency>
  ```
  
  ```java
  // EurekaServerApplication.java
  @SpringBootApplication
  @EnableEurekaServer
  public class EurekaServerApplication {
      public static void main(String[] args) {
          SpringApplication.run(EurekaServerApplication.class, args);
      }
  }
  ```
  
  ```properties
  # application.properties
  server.port=8761
  spring.application.name=eureka-server
  
  # Don't register itself as a client
  eureka.client.register-with-eureka=false
  eureka.client.fetch-registry=false
  ```
  
  **Access Dashboard:** http://localhost:8761

- [ ] **Eureka Client (Your Service)**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
  ```
  
  ```java
  @SpringBootApplication
  @EnableDiscoveryClient
  public class UserServiceApplication {
      public static void main(String[] args) {
          SpringApplication.run(UserServiceApplication.class, args);
      }
  }
  ```
  
  ```properties
  # application.properties
  spring.application.name=user-service
  server.port=8081
  
  # Eureka server location
  eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
  
  # Health check
  eureka.instance.prefer-ip-address=true
  ```

- [ ] **Service-to-Service Communication**
  ```java
  @Configuration
  public class RestTemplateConfig {
      
      @Bean
      @LoadBalanced  // Magic! Enables service discovery
      public RestTemplate restTemplate() {
          return new RestTemplate();
      }
  }
  
  @Service
  public class OrderService {
      
      @Autowired
      private RestTemplate restTemplate;
      
      public User getUserDetails(Long userId) {
          // Use service name instead of URL!
          String url = "http://USER-SERVICE/api/users/" + userId;
          return restTemplate.getForObject(url, User.class);
      }
  }
  ```
  
  **Real-life Example:** 🏪 **Shopping Mall**
  - You want to buy shoes
  - Ask mall directory (Eureka)
  - Directory tells you: "Shoe store on 2nd floor"
  - You go there (service call)
  - No need to remember exact location!

---

## 🔷 Part 3: API Gateway (Spring Cloud Gateway)

### Concepts to Master:

- [ ] **Why API Gateway?**
  ```
  Without Gateway:
  - Frontend calls 10 different services
  - 10 different URLs to manage
  - Authentication in each service (duplicate code)
  - CORS configuration everywhere
  - Rate limiting per service
  
  With Gateway:
  - Single entry point
  - Centralized authentication
  - Request routing
  - Load balancing
  - Rate limiting
  - Monitoring
  ```
  
  **Real-life Example:** 🏢 **Hotel Reception**
  - Gateway = Reception desk
  - Services = Hotel departments (restaurant, spa, gym)
  - All requests go through reception
  - Reception routes to correct department
  - Security check at reception (not each department)

- [ ] **API Gateway Setup**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-gateway</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
  </dependency>
  ```
  
  ```java
  @SpringBootApplication
  @EnableDiscoveryClient
  public class ApiGatewayApplication {
      public static void main(String[] args) {
          SpringApplication.run(ApiGatewayApplication.class, args);
      }
  }
  ```

- [ ] **Route Configuration**
  ```yaml
  # application.yml
  spring:
    application:
      name: api-gateway
    cloud:
      gateway:
        routes:
          # User Service Routes
          - id: user-service
            uri: lb://USER-SERVICE
            predicates:
              - Path=/api/users/**
            filters:
              - RewritePath=/api/users/(?<segment>.*), /${segment}
          
          # Order Service Routes
          - id: order-service
            uri: lb://ORDER-SERVICE
            predicates:
              - Path=/api/orders/**
          
          # Product Service Routes
          - id: product-service
            uri: lb://PRODUCT-SERVICE
            predicates:
              - Path=/api/products/**
            filters:
              - AddRequestHeader=X-Request-Source, API-Gateway
  
  server:
    port: 8080
  
  eureka:
    client:
      service-url:
        defaultZone: http://localhost:8761/eureka/
  ```
  
  **Explanation:**
  - `lb://USER-SERVICE` = Load balanced call to USER-SERVICE
  - `predicates` = Routing rules
  - `filters` = Modify request/response

- [ ] **Custom Filters**
  ```java
  @Component
  public class AuthenticationFilter implements GlobalFilter {
      
      @Override
      public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
          ServerHttpRequest request = exchange.getRequest();
          
          // Check if public endpoint
          if (isPublicEndpoint(request.getPath().toString())) {
              return chain.filter(exchange);
          }
          
          // Validate JWT token
          String token = request.getHeaders().getFirst("Authorization");
          if (token == null || !validateToken(token)) {
              exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
              return exchange.getResponse().setComplete();
          }
          
          return chain.filter(exchange);
      }
      
      private boolean isPublicEndpoint(String path) {
          return path.contains("/public/") || path.contains("/login");
      }
      
      private boolean validateToken(String token) {
          // JWT validation logic
          return true;
      }
  }
  ```
  
  **Real-life Example:** 🎫 **Airport Security**
  - All passengers go through security (gateway)
  - Check boarding pass (authentication)
  - Route to correct gate (service)
  - No security check at each gate

---

## 🔷 Part 4: Config Server (Centralized Configuration)

### Concepts to Master:

- [ ] **Why Config Server?**
  ```
  Problem Without Config Server:
  - Each service has own config file
  - Change DB password? Update 10 services!
  - Different configs for dev/staging/prod
  - Restart all services for config change
  - Config scattered everywhere
  
  Solution With Config Server:
  - Single source of truth (Git repository)
  - Change once, all services updated
  - Environment-specific configs
  - Refresh without restart
  - Version control for configs
  ```
  
  **Real-life Example:** 📚 **Library Catalog System**
  - Config Server = Central catalog
  - Services = Branch libraries
  - Update catalog once
  - All branches see updated info
  - No need to update each branch manually

- [ ] **Config Server Setup**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-config-server</artifactId>
  </dependency>
  ```
  
  ```java
  @SpringBootApplication
  @EnableConfigServer
  public class ConfigServerApplication {
      public static void main(String[] args) {
          SpringApplication.run(ConfigServerApplication.class, args);
      }
  }
  ```
  
  ```properties
  # application.properties
  server.port=8888
  spring.application.name=config-server
  
  # Git repository for configs
  spring.cloud.config.server.git.uri=https://github.com/your-username/config-repo
  spring.cloud.config.server.git.default-label=main
  spring.cloud.config.server.git.clone-on-start=true
  ```

- [ ] **Config Repository Structure**
  ```
  config-repo/
  ├── application.yml          # Common config for all services
  ├── user-service.yml         # User service specific
  ├── order-service.yml        # Order service specific
  ├── user-service-dev.yml     # User service dev environment
  ├── user-service-prod.yml    # User service prod environment
  └── application-prod.yml     # Common prod config
  ```
  
  ```yaml
  # application.yml (Common)
  management:
    endpoints:
      web:
        exposure:
          include: "*"
  
  # user-service.yml
  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/userdb
      username: root
      password: password
  
  app:
    jwt:
      secret: mySecretKey
      expiration: 86400000
  
  # user-service-prod.yml
  spring:
    datasource:
      url: jdbc:mysql://prod-db-server:3306/userdb
      username: ${DB_USERNAME}
      password: ${DB_PASSWORD}
  ```

- [ ] **Config Client (Your Service)**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-config</artifactId>
  </dependency>
  ```
  
  ```properties
  # application.properties
  spring.application.name=user-service
  spring.profiles.active=dev
  
  # Config server location
  spring.config.import=optional:configserver:http://localhost:8888
  
  # Fail fast if config server is down
  spring.cloud.config.fail-fast=true
  ```
  
  **Access Config:** http://localhost:8888/user-service/dev

- [ ] **Refresh Config Without Restart**
  ```java
  @RestController
  @RefreshScope  // Enable dynamic refresh
  public class UserController {
      
      @Value("${app.welcome.message}")
      private String welcomeMessage;
      
      @GetMapping("/welcome")
      public String welcome() {
          return welcomeMessage;
      }
  }
  ```
  
  **Refresh Command:**
  ```bash
  curl -X POST http://localhost:8081/actuator/refresh
  ```
  
  **Real-life Example:** 📱 **App Settings**
  - Config Server = Cloud settings
  - Services = Your phone apps
  - Change setting in cloud
  - Apps sync automatically
  - No need to reinstall apps

---

## 🔷 Part 5: Circuit Breaker (Resilience4j)

### Concepts to Master:

- [ ] **Why Circuit Breaker?**
  ```
  Problem Without Circuit Breaker:
  - Service A calls Service B
  - Service B is down
  - Service A keeps trying (wasting resources)
  - Requests pile up
  - Service A also crashes (cascading failure)
  - Entire system down!
  
  Solution With Circuit Breaker:
  - Detects failures
  - Stops calling failed service
  - Returns fallback response
  - Periodically checks if service is back
  - Auto-recovery
  ```
  
  **Real-life Example:** ⚡ **Electrical Circuit Breaker**
  - Detects power overload
  - Cuts power to prevent fire
  - Protects the system
  - Can be reset when safe
  - Prevents damage to appliances

- [ ] **Circuit Breaker States**
  ```
  CLOSED (Normal):
  - All requests go through
  - Counting failures
  
  OPEN (Service Down):
  - All requests rejected immediately
  - Return fallback response
  - Wait for timeout
  
  HALF-OPEN (Testing):
  - Allow few requests
  - If success → CLOSED
  - If failure → OPEN again
  ```
  
  **Diagram:**
  ```
  CLOSED ──(failures > threshold)──> OPEN
    ↑                                  |
    |                                  |
    └──(success)── HALF-OPEN ←─(timeout)
  ```

- [ ] **Resilience4j Setup**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-circuitbreaker-resilience4j</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-aop</artifactId>
  </dependency>
  ```

- [ ] **Circuit Breaker Implementation**
  ```java
  @Service
  public class OrderService {
      
      @Autowired
      private RestTemplate restTemplate;
      
      @CircuitBreaker(name = "userService", fallbackMethod = "getUserFallback")
      @Retry(name = "userService")
      public User getUser(Long userId) {
          String url = "http://USER-SERVICE/api/users/" + userId;
          return restTemplate.getForObject(url, User.class);
      }
      
      // Fallback method (same signature + Exception parameter)
      public User getUserFallback(Long userId, Exception ex) {
          log.error("User service is down: " + ex.getMessage());
          
          // Return default user or cached data
          User defaultUser = new User();
          defaultUser.setId(userId);
          defaultUser.setName("Guest User");
          return defaultUser;
      }
  }
  ```

- [ ] **Configuration**
  ```yaml
  # application.yml
  resilience4j:
    circuitbreaker:
      instances:
        userService:
          sliding-window-size: 10           # Track last 10 calls
          failure-rate-threshold: 50        # Open if 50% fail
          wait-duration-in-open-state: 10s  # Wait 10s before HALF-OPEN
          permitted-number-of-calls-in-half-open-state: 3
          automatic-transition-from-open-to-half-open-enabled: true
    
    retry:
      instances:
        userService:
          max-attempts: 3
          wait-duration: 1s
  ```
  
  **Real-life Example:** 🚗 **Car Safety Features**
  - Circuit Breaker = Airbag
  - Detects crash (service failure)
  - Deploys airbag (fallback)
  - Protects driver (your service)
  - Prevents further damage

---

## 🔷 Part 6: Distributed Tracing (Sleuth + Zipkin)

### Concepts to Master:

- [ ] **Why Distributed Tracing?**
  ```
  Problem in Microservices:
  - Request goes through 5 services
  - Something is slow (2 seconds)
  - Which service is the bottleneck?
  - Logs scattered across services
  - Debugging nightmare!
  
  Solution With Tracing:
  - Trace request across all services
  - See timing for each service
  - Identify bottlenecks
  - Correlate logs
  ```
  
  **Real-life Example:** 📦 **Package Tracking**
  - Trace ID = Tracking number
  - Each service = Warehouse/Transit point
  - Track package journey
  - See where delay happened
  - Find exact location

- [ ] **Sleuth + Zipkin Setup**
  ```xml
  <!-- pom.xml (Add to ALL services) -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-sleuth</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-sleuth-zipkin</artifactId>
  </dependency>
  ```
  
  ```properties
  # application.properties
  spring.sleuth.sampler.probability=1.0  # Sample 100% (dev only)
  spring.zipkin.base-url=http://localhost:9411
  ```

- [ ] **Run Zipkin Server**
  ```bash
  # Download and run Zipkin
  curl -sSL https://zipkin.io/quickstart.sh | bash -s
  java -jar zipkin.jar
  
  # Access UI: http://localhost:9411
  ```

- [ ] **Trace IDs in Logs**
  ```
  Without Sleuth:
  2024-01-15 10:30:45 INFO  Processing user request
  2024-01-15 10:30:46 INFO  Fetching orders
  2024-01-15 10:30:47 INFO  Processing payment
  
  With Sleuth:
  [user-service,abc123,xyz789] Processing user request
  [order-service,abc123,def456] Fetching orders
  [payment-service,abc123,ghi789] Processing payment
  
  abc123 = Trace ID (same across all services)
  xyz789 = Span ID (unique per service)
  ```
  
  **Real-life Example:** 🏥 **Hospital Patient Journey**
  - Trace ID = Patient ID
  - Each service = Department (ER, X-Ray, Lab)
  - Track patient across departments
  - See time spent in each
  - Identify delays

---

## 💻 Practice Project: E-Commerce Microservices

### Project Architecture:
```
                    ┌─────────────────┐
                    │   API Gateway   │ (Port 8080)
                    │   (Routing)     │
                    └────────┬────────┘
                             │
              ┌──────────────┼──────────────┐
              │              │              │
       ┌──────▼─────┐ ┌─────▼──────┐ ┌────▼──────┐
       │   User      │ │  Product   │ │   Order   │
       │  Service    │ │  Service   │ │  Service  │
       │  (8081)     │ │  (8082)    │ │  (8083)   │
       └─────────────┘ └────────────┘ └───────────┘
              │              │              │
              └──────────────┼──────────────┘
                             │
                    ┌────────▼────────┐
                    │ Eureka Server   │ (Port 8761)
                    │(Service Discovery)│
                    └─────────────────┘
                    
                    ┌─────────────────┐
                    │  Config Server  │ (Port 8888)
                    │(Centralized Config)│
                    └─────────────────┘
                    
                    ┌─────────────────┐
                    │  Zipkin Server  │ (Port 9411)
                    │(Distributed Tracing)│
                    └─────────────────┘
```

### Implementation Steps:

- [ ] **Step 1: Setup Infrastructure Services**
  - Create Eureka Server
  - Create Config Server
  - Create API Gateway
  - Run Zipkin Server

- [ ] **Step 2: Create User Service**
  - User registration/login
  - JWT authentication
  - User profile management
  - Register with Eureka

- [ ] **Step 3: Create Product Service**
  - Product CRUD operations
  - Category management
  - Search functionality
  - Register with Eureka

- [ ] **Step 4: Create Order Service**
  - Create orders
  - Call User Service (get user details)
  - Call Product Service (check availability)
  - Circuit breaker for external calls

- [ ] **Step 5: Implement Communication**
  - Service-to-service calls via RestTemplate
  - Load balancing
  - Circuit breaker with fallbacks

- [ ] **Step 6: Add Monitoring**
  - Distributed tracing
  - Centralized logging
  - Health checks

### Features to Implement:
- [ ] Service discovery
- [ ] API Gateway routing
- [ ] Centralized configuration
- [ ] Circuit breaker
- [ ] Distributed tracing
- [ ] Load balancing
- [ ] JWT authentication
- [ ] Exception handling

**Duration:** 3 weeks
**Skills:** Spring Cloud, Microservices, System Design

---

## 🎯 Interview Questions & Answers

### Q1: What is the difference between Monolithic and Microservices architecture?

**Answer:**
```
Monolithic:
- Single deployable unit
- All features in one codebase
- Tight coupling
- Scale entire application
- Technology stack locked

Microservices:
- Multiple independent services
- Each service = one business capability
- Loose coupling
- Scale specific services
- Technology flexibility

When to use:
- Monolith: Small team, simple app, tight deadline
- Microservices: Large team, complex domain, need scalability
```

### Q2: How do microservices communicate with each other?

**Answer:**
```
1. Synchronous (REST/gRPC):
   - Direct HTTP calls
   - Immediate response
   - Example: Get user details
   - Cons: Tight coupling, cascading failures

2. Asynchronous (Kafka/RabbitMQ):
   - Event-driven
   - Eventual consistency
   - Example: Order placed → Send email
   - Pros: Loose coupling, better fault tolerance

Best Practice: Use both based on use case
- Synchronous: Read operations, immediate data needed
- Asynchronous: Write operations, notifications
```

### Q3: Explain Circuit Breaker pattern with real example.

**Answer:**
```
Circuit Breaker prevents cascading failures.

Real Example: E-commerce checkout
- Order Service calls Payment Service
- Payment Service is down
- Without Circuit Breaker:
  * Order Service keeps trying
  * Requests pile up
  * Order Service crashes too
  
- With Circuit Breaker:
  * Detects Payment Service is down
  * Stops calling it (OPEN state)
  * Returns fallback: "Payment service unavailable, try later"
  * Periodically checks if service is back (HALF-OPEN)
  * Resumes normal operation when service is up (CLOSED)

States: CLOSED → OPEN → HALF-OPEN → CLOSED
```

### Q4: What is Service Discovery and why is it needed?

**Answer:**
```
Service Discovery = Dynamic service location lookup

Why needed:
- In microservices, services can be on different servers
- IP addresses change (cloud auto-scaling)
- Multiple instances of same service
- Hard-coding URLs is not feasible

Eureka Solution:
1. Services register themselves with Eureka Server
2. Services discover other services via Eureka
3. Load balancing automatic
4. Health monitoring built-in

Example:
- Order Service needs User Service
- Asks Eureka: "Where is USER-SERVICE?"
- Eureka: "3 instances at IP1, IP2, IP3"
- Order Service calls one (load balanced)
```

### Q5: How does API Gateway help in microservices?

**Answer:**
```
API Gateway = Single entry point for all clients

Benefits:
1. Centralized Authentication
   - JWT validation at gateway
   - No need in each service

2. Request Routing
   - /api/users → User Service
   - /api/orders → Order Service

3. Load Balancing
   - Distribute requests across instances

4. Rate Limiting
   - Prevent abuse
   - Protect backend services

5. Monitoring
   - Single place to log all requests
   - Track API usage

Real Example: Hotel Reception
- All guests go through reception (gateway)
- Reception routes to correct department (service)
- Security check at reception (authentication)
```

---

## ✅ Move-On Criteria

- [ ] ✔ **Microservices benefits/drawbacks explain করতে পারবেন**
- [ ] ✔ **Eureka service discovery implement করতে পারবেন**
- [ ] ✔ **API Gateway routing configure করতে পারবেন**
- [ ] ✔ **Config Server setup করতে পারবেন**
- [ ] ✔ **Circuit Breaker pattern বুঝবেন এবং implement করতে পারবেন**
- [ ] ✔ **Distributed tracing setup করতে পারবেন**
- [ ] ✔ **Complete microservices project build করতে পারবেন**
- [ ] ✔ **Interview questions confidently answer করতে পারবেন**

---

**Real-World Applications:**
- 🛒 Amazon, Flipkart (E-commerce)
- 🎬 Netflix (Streaming)
- 🚗 Uber (Ride sharing)
- 💳 PayPal (Payments)
- 📱 WhatsApp (Messaging)

**Next:** [Apache Kafka →](Java-Topic-12-Kafka.md)

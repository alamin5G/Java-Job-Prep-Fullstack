# 📌 Topic 7: Networking Fundamentals

## 🎯 Learning Objectives
Master networking concepts essential for backend development - how data travels across the internet.

---

## 🔷 Part 1: HTTP/HTTPS Basics

### Concepts to Master:

- [ ] **HTTP Protocol**
  ```
  HTTP = HyperText Transfer Protocol
  
  Request-Response Model:
  Client → HTTP Request → Server
  Client ← HTTP Response ← Server
  
  Stateless: Each request independent
  ```

- [ ] **HTTP Methods**
  ```
  GET: Retrieve data
  - Safe, Idempotent
  - Example: GET /api/users/1
  
  POST: Create new resource
  - Not idempotent
  - Example: POST /api/users
  
  PUT: Update entire resource
  - Idempotent
  - Example: PUT /api/users/1
  
  PATCH: Partial update
  - Example: PATCH /api/users/1
  
  DELETE: Remove resource
  - Idempotent
  - Example: DELETE /api/users/1
  
  HEAD: Like GET but no body
  OPTIONS: Supported methods
  ```

- [ ] **HTTP Status Codes**
  ```
  1xx: Informational
  100 Continue
  
  2xx: Success
  200 OK - Request successful
  201 Created - Resource created
  204 No Content - Success but no data
  
  3xx: Redirection
  301 Moved Permanently
  302 Found (Temporary redirect)
  304 Not Modified (Cache)
  
  4xx: Client Errors
  400 Bad Request - Invalid input
  401 Unauthorized - Authentication required
  403 Forbidden - No permission
  404 Not Found - Resource doesn't exist
  409 Conflict - Duplicate resource
  
  5xx: Server Errors
  500 Internal Server Error
  502 Bad Gateway
  503 Service Unavailable
  504 Gateway Timeout
  ```

- [ ] **HTTP Headers**
  ```
  Request Headers:
  Content-Type: application/json
  Authorization: Bearer <token>
  Accept: application/json
  User-Agent: Mozilla/5.0...
  
  Response Headers:
  Content-Type: application/json
  Content-Length: 1234
  Cache-Control: max-age=3600
  Set-Cookie: sessionId=abc123
  ```

- [ ] **HTTPS (Secure HTTP)**
  ```
  HTTPS = HTTP + TLS/SSL
  
  Benefits:
  - Encrypted communication
  - Data integrity
  - Authentication
  
  How it works:
  1. Client requests HTTPS connection
  2. Server sends SSL certificate
  3. Client verifies certificate
  4. Encrypted communication begins
  ```

---

## 🔷 Part 2: REST API Design

### Concepts to Master:

- [ ] **REST Principles**
  ```
  1. Client-Server Architecture
  2. Stateless
  3. Cacheable
  4. Uniform Interface
  5. Layered System
  
  Resource-Based:
  - Everything is a resource
  - Identified by URI
  - Manipulated using HTTP methods
  ```

- [ ] **RESTful URL Design**
  ```
  Good:
  GET    /api/users           - Get all users
  GET    /api/users/1         - Get user 1
  POST   /api/users           - Create user
  PUT    /api/users/1         - Update user 1
  DELETE /api/users/1         - Delete user 1
  
  GET    /api/users/1/posts   - Get posts of user 1
  POST   /api/users/1/posts   - Create post for user 1
  
  Bad:
  GET /api/getAllUsers
  POST /api/createUser
  GET /api/user/delete/1
  ```

- [ ] **API Versioning**
  ```
  1. URL Path:
     /api/v1/users
     /api/v2/users
  
  2. Header:
     Accept: application/vnd.myapi.v1+json
  
  3. Query Parameter:
     /api/users?version=1
  ```

- [ ] **Pagination**
  ```
  GET /api/users?page=1&size=20
  
  Response:
  {
    "data": [...],
    "page": 1,
    "size": 20,
    "totalPages": 5,
    "totalElements": 100
  }
  ```

- [ ] **Filtering & Sorting**
  ```
  Filtering:
  GET /api/users?age=25&city=Dhaka
  
  Sorting:
  GET /api/users?sort=name,asc
  GET /api/users?sort=age,desc
  
  Combined:
  GET /api/users?age=25&sort=name,asc&page=1&size=10
  ```

---

## 🔷 Part 3: WebSockets

### Concepts to Master:

- [ ] **WebSocket vs HTTP**
  ```
  HTTP:
  - Request-Response
  - Client initiates
  - New connection each time
  
  WebSocket:
  - Full-duplex communication
  - Persistent connection
  - Real-time bidirectional
  ```

- [ ] **WebSocket Use Cases**
  ```
  ✅ Chat applications
  ✅ Live notifications
  ✅ Real-time dashboards
  ✅ Multiplayer games
  ✅ Live sports scores
  
  ❌ Simple CRUD operations
  ❌ File uploads
  ❌ Static content
  ```

- [ ] **WebSocket in Spring Boot**
  ```java
  @Configuration
  @EnableWebSocketMessageBroker
  public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
      
      @Override
      public void configureMessageBroker(MessageBrokerRegistry config) {
          config.enableSimpleBroker("/topic");
          config.setApplicationDestinationPrefixes("/app");
      }
      
      @Override
      public void registerStompEndpoints(StompEndpointRegistry registry) {
          registry.addEndpoint("/ws").withSockJS();
      }
  }
  
  @Controller
  public class ChatController {
      
      @MessageMapping("/chat.send")
      @SendTo("/topic/messages")
      public ChatMessage sendMessage(ChatMessage message) {
          return message;
      }
  }
  ```

---

## 🔷 Part 4: TCP/IP Basics

### Concepts to Master:

- [ ] **OSI Model (7 Layers)**
  ```
  7. Application  - HTTP, FTP, SMTP
  6. Presentation - Encryption, Compression
  5. Session      - Session management
  4. Transport    - TCP, UDP
  3. Network      - IP, Routing
  2. Data Link    - MAC addresses
  1. Physical     - Cables, Signals
  ```

- [ ] **TCP vs UDP**
  ```
  TCP (Transmission Control Protocol):
  ✅ Reliable (guaranteed delivery)
  ✅ Ordered packets
  ✅ Error checking
  ❌ Slower
  Use: HTTP, Email, File transfer
  
  UDP (User Datagram Protocol):
  ✅ Fast
  ✅ Low overhead
  ❌ No guarantee
  ❌ No ordering
  Use: Video streaming, Gaming, DNS
  ```

- [ ] **IP Address**
  ```
  IPv4: 192.168.1.1 (32-bit)
  IPv6: 2001:0db8:85a3::8a2e:0370:7334 (128-bit)
  
  Private IP Ranges:
  10.0.0.0 - 10.255.255.255
  172.16.0.0 - 172.31.255.255
  192.168.0.0 - 192.168.255.255
  
  Localhost: 127.0.0.1
  ```

- [ ] **DNS (Domain Name System)**
  ```
  Converts domain names to IP addresses
  
  example.com → 93.184.216.34
  
  Process:
  1. Check browser cache
  2. Check OS cache
  3. Query DNS resolver
  4. Query root server
  5. Query TLD server (.com)
  6. Query authoritative server
  7. Return IP address
  ```

---

## 🔷 Part 5: API Security

### Concepts to Master:

- [ ] **Authentication vs Authorization**
  ```
  Authentication: Who are you?
  - Login with username/password
  - JWT token
  - OAuth
  
  Authorization: What can you do?
  - Role-based (ADMIN, USER)
  - Permission-based
  - Resource-based
  ```

- [ ] **JWT (JSON Web Token)**
  ```
  Structure:
  Header.Payload.Signature
  
  Header:
  {
    "alg": "HS256",
    "typ": "JWT"
  }
  
  Payload:
  {
    "sub": "user123",
    "name": "John Doe",
    "exp": 1516239022
  }
  
  Signature:
  HMACSHA256(
    base64UrlEncode(header) + "." +
    base64UrlEncode(payload),
    secret
  )
  ```

- [ ] **CORS (Cross-Origin Resource Sharing)**
  ```java
  @Configuration
  public class CorsConfig {
      @Bean
      public WebMvcConfigurer corsConfigurer() {
          return new WebMvcConfigurer() {
              @Override
              public void addCorsMappings(CorsRegistry registry) {
                  registry.addMapping("/api/**")
                          .allowedOrigins("http://localhost:3000")
                          .allowedMethods("GET", "POST", "PUT", "DELETE")
                          .allowedHeaders("*")
                          .allowCredentials(true);
              }
          };
      }
  }
  ```

- [ ] **Rate Limiting**
  ```
  Prevent API abuse
  
  Strategies:
  1. Fixed Window: 100 requests/minute
  2. Sliding Window: More accurate
  3. Token Bucket: Burst allowed
  
  Implementation:
  - Redis for distributed systems
  - Spring Boot: Bucket4j library
  ```

---

## ✅ Move-On Criteria

- [ ] ✔ **HTTP methods এবং status codes বুঝবেন**
- [ ] ✔ **RESTful API design করতে পারবেন**
- [ ] ✔ **WebSocket use cases বলতে পারবেন**
- [ ] ✔ **TCP vs UDP difference জানবেন**
- [ ] ✔ **JWT authentication implement করতে পারবেন**

**Next:** [Frontend Essentials →](Java-Topic-09-Frontend.md)

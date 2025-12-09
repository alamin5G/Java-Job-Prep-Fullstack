# 📊 Spring Boot Practice Problems

## 🎯 Hands-on Coding Challenges
Build real features to master Spring Boot - organized by difficulty and topic.

---

## 🟢 Beginner Level (20 Challenges)

### REST API Basics (10)

- [ ] **1. Hello World API**
  - Create GET endpoint returning "Hello World"
  - **Skills:** @RestController, @GetMapping
  
- [ ] **2. Echo Service**
  - POST endpoint that returns received data
  - **Skills:** @PostMapping, @RequestBody
  
- [ ] **3. Path Variable**
  - GET /greet/{name} returns "Hello, {name}"
  - **Skills:** @PathVariable
  
- [ ] **4. Query Parameters**
  - GET /search?keyword=java returns filtered results
  - **Skills:** @RequestParam
  
- [ ] **5. CRUD for Single Entity**
  - Create User entity with CRUD operations
  - **Skills:** JPA, Repository, Service, Controller
  
- [ ] **6. Custom Response**
  - Return ResponseEntity with status codes
  - **Skills:** ResponseEntity, HttpStatus
  
- [ ] **7. Request Validation**
  - Validate user input (@NotNull, @Email, etc.)
  - **Skills:** @Valid, Bean Validation
  
- [ ] **8. Exception Handling**
  - Global exception handler
  - **Skills:** @RestControllerAdvice, @ExceptionHandler
  
- [ ] **9. Pagination**
  - GET /users?page=0&size=10
  - **Skills:** Pageable, Page
  
- [ ] **10. Sorting**
  - GET /users?sort=name,asc
  - **Skills:** Sort parameter

### Database Operations (10)

- [ ] **11. One-to-Many Relationship**
  - Author → Books
  - **Skills:** @OneToMany, @ManyToOne
  
- [ ] **12. Many-to-Many Relationship**
  - Students ↔ Courses
  - **Skills:** @ManyToMany, @JoinTable
  
- [ ] **13. Custom Query**
  - Find users by name containing keyword
  - **Skills:** @Query, JPQL
  
- [ ] **14. Native Query**
  - Complex SQL query
  - **Skills:** @Query(nativeQuery = true)
  
- [ ] **15. Derived Query Methods**
  - findByEmailAndAge(email, age)
  - **Skills:** Spring Data JPA naming convention
  
- [ ] **16. Projection**
  - Return only specific fields
  - **Skills:** Interface-based projection
  
- [ ] **17. Specifications**
  - Dynamic query building
  - **Skills:** JPA Specifications
  
- [ ] **18. Auditing**
  - Track createdAt, updatedAt
  - **Skills:** @CreatedDate, @LastModifiedDate
  
- [ ] **19. Soft Delete**
  - Mark as deleted instead of removing
  - **Skills:** @Where, @SQLDelete
  
- [ ] **20. Database Seeding**
  - Load initial data on startup
  - **Skills:** CommandLineRunner, data.sql

---

## 🔵 Intermediate Level (25 Challenges)

### Authentication & Security (10)

- [ ] **21. Basic Authentication**
  - Username/password authentication
  - **Skills:** Spring Security, HttpBasic
  
- [ ] **22. JWT Token Generation**
  - Generate JWT on login
  - **Skills:** JWT library, token creation
  
- [ ] **23. JWT Token Validation**
  - Validate token on each request
  - **Skills:** JWT filter, token parsing
  
- [ ] **24. Role-Based Authorization**
  - ADMIN vs USER roles
  - **Skills:** @PreAuthorize, hasRole()
  
- [ ] **25. Password Encryption**
  - BCrypt password encoding
  - **Skills:** PasswordEncoder
  
- [ ] **26. Refresh Token**
  - Implement refresh token mechanism
  - **Skills:** Token refresh logic
  
- [ ] **27. OAuth2 Login**
  - Login with Google/GitHub
  - **Skills:** OAuth2 client
  
- [ ] **28. CORS Configuration**
  - Allow frontend access
  - **Skills:** CorsConfiguration
  
- [ ] **29. Rate Limiting**
  - Limit API requests per user
  - **Skills:** Bucket4j or custom filter
  
- [ ] **30. API Key Authentication**
  - Custom header-based auth
  - **Skills:** Custom filter

### Advanced Features (15)

- [ ] **31. File Upload**
  - Upload and store files
  - **Skills:** MultipartFile, file handling
  
- [ ] **32. File Download**
  - Download files with proper headers
  - **Skills:** Resource, ResponseEntity
  
- [ ] **33. Email Sending**
  - Send email on user registration
  - **Skills:** JavaMailSender
  
- [ ] **34. Scheduled Tasks**
  - Run task every hour
  - **Skills:** @Scheduled, @EnableScheduling
  
- [ ] **35. Async Processing**
  - Non-blocking operations
  - **Skills:** @Async, @EnableAsync
  
- [ ] **36. Caching**
  - Cache frequently accessed data
  - **Skills:** @Cacheable, @CacheEvict
  
- [ ] **37. Redis Integration**
  - Use Redis for caching
  - **Skills:** Spring Data Redis
  
- [ ] **38. WebSocket Chat**
  - Real-time messaging
  - **Skills:** WebSocket, STOMP
  
- [ ] **39. Server-Sent Events**
  - Push notifications to client
  - **Skills:** SseEmitter
  
- [ ] **40. Actuator Endpoints**
  - Health check, metrics
  - **Skills:** Spring Boot Actuator
  
- [ ] **41. Custom Actuator Endpoint**
  - Create custom health indicator
  - **Skills:** HealthIndicator
  
- [ ] **42. Logging**
  - Structured logging with SLF4J
  - **Skills:** Logger, log levels
  
- [ ] **43. Request/Response Logging**
  - Log all API calls
  - **Skills:** Filter, interceptor
  
- [ ] **44. API Versioning**
  - /api/v1/users vs /api/v2/users
  - **Skills:** Versioning strategies
  
- [ ] **45. Swagger Documentation**
  - Auto-generate API docs
  - **Skills:** Springdoc OpenAPI

---

## 🟡 Advanced Level (15 Challenges)

### Microservices (8)

- [ ] **46. Service Discovery**
  - Eureka server and client
  - **Skills:** Spring Cloud Netflix Eureka
  
- [ ] **47. API Gateway**
  - Single entry point for microservices
  - **Skills:** Spring Cloud Gateway
  
- [ ] **48. Load Balancing**
  - Distribute requests across instances
  - **Skills:** Ribbon or Spring Cloud LoadBalancer
  
- [ ] **49. Circuit Breaker**
  - Resilience4j integration
  - **Skills:** @CircuitBreaker
  
- [ ] **50. Distributed Tracing**
  - Trace requests across services
  - **Skills:** Sleuth, Zipkin
  
- [ ] **51. Config Server**
  - Centralized configuration
  - **Skills:** Spring Cloud Config
  
- [ ] **52. Message Queue**
  - RabbitMQ or Kafka integration
  - **Skills:** Spring AMQP or Spring Kafka
  
- [ ] **53. Saga Pattern**
  - Distributed transactions
  - **Skills:** Choreography or Orchestration

### Performance & Testing (7)

- [ ] **54. Database Connection Pooling**
  - HikariCP configuration
  - **Skills:** Connection pool tuning
  
- [ ] **55. Query Optimization**
  - N+1 problem solution
  - **Skills:** @EntityGraph, JOIN FETCH
  
- [ ] **56. Unit Testing**
  - Test service layer
  - **Skills:** JUnit, Mockito
  
- [ ] **57. Integration Testing**
  - Test with real database
  - **Skills:** @SpringBootTest, TestContainers
  
- [ ] **58. API Testing**
  - Test REST endpoints
  - **Skills:** MockMvc, RestAssured
  
- [ ] **59. Load Testing**
  - Performance testing
  - **Skills:** JMeter, Gatling
  
- [ ] **60. Docker Deployment**
  - Containerize application
  - **Skills:** Dockerfile, Docker Compose

---

## 🔴 Real-World Projects (10)

### Complete Applications

- [ ] **61. Todo List API**
  - CRUD, authentication, user-specific todos
  - **Duration:** 3-5 days
  
- [ ] **62. Blog Platform**
  - Posts, comments, likes, categories
  - **Duration:** 1 week
  
- [ ] **63. E-commerce Backend**
  - Products, cart, orders, payment
  - **Duration:** 2 weeks
  
- [ ] **64. Social Media API**
  - Users, posts, followers, feed
  - **Duration:** 2 weeks
  
- [ ] **65. Job Portal**
  - Job postings, applications, resume upload
  - **Duration:** 2 weeks
  
- [ ] **66. Hotel Booking System**
  - Rooms, bookings, availability
  - **Duration:** 1.5 weeks
  
- [ ] **67. Library Management**
  - Books, members, borrowing, fines
  - **Duration:** 1 week
  
- [ ] **68. Food Delivery API**
  - Restaurants, menu, orders, delivery
  - **Duration:** 2 weeks
  
- [ ] **69. Online Learning Platform**
  - Courses, enrollments, progress tracking
  - **Duration:** 2 weeks
  
- [ ] **70. Expense Tracker**
  - Transactions, categories, reports
  - **Duration:** 1 week

---

## 📊 Progress Tracking

### By Difficulty
- **Beginner:** ___ / 20
- **Intermediate:** ___ / 25
- **Advanced:** ___ / 15
- **Projects:** ___ / 10

### By Topic
- **REST API:** ___ / 10
- **Database:** ___ / 10
- **Security:** ___ / 10
- **Advanced Features:** ___ / 15
- **Microservices:** ___ / 8
- **Testing:** ___ / 7
- **Projects:** ___ / 10

---

## 🎯 Learning Path

### Month 1: Basics
- Complete all Beginner challenges
- Build 2 simple projects

### Month 2: Intermediate
- Complete Intermediate challenges
- Build 2 medium projects

### Month 3: Advanced
- Complete Advanced challenges
- Build 2 complex projects

### Month 4: Polish
- Optimize existing projects
- Add tests
- Deploy to cloud

---

## 💡 Tips

### For Each Challenge:
1. **Understand requirement** clearly
2. **Plan approach** before coding
3. **Write clean code** with comments
4. **Test thoroughly** with Postman
5. **Handle edge cases**
6. **Add proper error handling**

### Code Quality:
- Follow naming conventions
- Use proper HTTP status codes
- Validate all inputs
- Log important events
- Write meaningful commit messages

---

**Keep building! Practice makes perfect! 🚀**

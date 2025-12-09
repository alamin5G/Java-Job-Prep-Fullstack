# 📌 Topic 5: Spring Boot Framework

## 🎯 Learning Objectives
Master Spring Boot to build production-ready REST APIs and backend applications - the most in-demand skill for Java developers.

---

## 🔷 Part 1: Spring Boot Basics

### Concepts to Master:

- [ ] **What is Spring Boot?**
  ```
  Spring Boot = Spring Framework + Auto-configuration + Embedded Server
  
  Benefits:
  ✅ No XML configuration
  ✅ Embedded Tomcat (no deployment hassle)
  ✅ Production-ready features
  ✅ Microservices friendly
  ✅ Huge community support
  ```
  
  **Real-life Example:** 🏗️ **Pre-fabricated House**
  - Spring = Building materials
  - Spring Boot = Ready-to-move house
  - Just add furniture (your code) and live!

- [ ] **Spring Boot Project Structure**
  ```
  my-spring-app/
  ├── src/
  │   ├── main/
  │   │   ├── java/
  │   │   │   └── com/myapp/
  │   │   │       ├── MyApplication.java (Main class)
  │   │   │       ├── controller/
  │   │   │       ├── service/
  │   │   │       ├── repository/
  │   │   │       └── model/
  │   │   └── resources/
  │   │       ├── application.properties
  │   │       └── static/
  │   └── test/
  ├── pom.xml (Maven) or build.gradle (Gradle)
  └── target/
  ```

- [ ] **First Spring Boot Application**
  ```java
  // Main Application Class
  @SpringBootApplication
  public class MyApplication {
      public static void main(String[] args) {
          SpringApplication.run(MyApplication.class, args);
      }
  }
  
  // Simple REST Controller
  @RestController
  @RequestMapping("/api")
  public class HelloController {
      
      @GetMapping("/hello")
      public String sayHello() {
          return "Hello, Spring Boot!";
      }
      
      @GetMapping("/hello/{name}")
      public String sayHelloToName(@PathVariable String name) {
          return "Hello, " + name + "!";
      }
  }
  
  // Run: http://localhost:8080/api/hello
  // Output: Hello, Spring Boot!
  ```
  
  **Real-life Use Case:** 🌐 **Website Backend**
  - User visits URL → Controller handles request
  - Returns response → User sees data

---

## 🔷 Part 2: REST API Development

### Concepts to Master:

- [ ] **CRUD Operations**
  ```java
  @RestController
  @RequestMapping("/api/users")
  public class UserController {
      
      @Autowired
      private UserService userService;
      
      // CREATE
      @PostMapping
      public ResponseEntity<User> createUser(@RequestBody User user) {
          User created = userService.createUser(user);
          return ResponseEntity.status(HttpStatus.CREATED).body(created);
      }
      
      // READ (All)
      @GetMapping
      public List<User> getAllUsers() {
          return userService.getAllUsers();
      }
      
      // READ (One)
      @GetMapping("/{id}")
      public ResponseEntity<User> getUserById(@PathVariable Long id) {
          User user = userService.getUserById(id);
          if (user != null) {
              return ResponseEntity.ok(user);
          }
          return ResponseEntity.notFound().build();
      }
      
      // UPDATE
      @PutMapping("/{id}")
      public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
          User updated = userService.updateUser(id, user);
          return ResponseEntity.ok(updated);
      }
      
      // DELETE
      @DeleteMapping("/{id}")
      public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
          userService.deleteUser(id);
          return ResponseEntity.noContent().build();
      }
  }
  ```
  
  **Real-life Example:** 📝 **Blog Management**
  - POST /api/posts → Create new post
  - GET /api/posts → Get all posts
  - GET /api/posts/1 → Get specific post
  - PUT /api/posts/1 → Update post
  - DELETE /api/posts/1 → Delete post

- [ ] **Service Layer**
  ```java
  @Service
  public class UserService {
      
      @Autowired
      private UserRepository userRepository;
      
      public User createUser(User user) {
          // Business logic
          validateUser(user);
          return userRepository.save(user);
      }
      
      public List<User> getAllUsers() {
          return userRepository.findAll();
      }
      
      public User getUserById(Long id) {
          return userRepository.findById(id)
                  .orElseThrow(() -> new UserNotFoundException("User not found"));
      }
      
      private void validateUser(User user) {
          if (user.getEmail() == null || user.getEmail().isEmpty()) {
              throw new InvalidUserException("Email is required");
          }
      }
  }
  ```
  
  **Layered Architecture:**
  ```
  Controller → Service → Repository → Database
  (HTTP)     (Business)  (Data Access)
  ```

- [ ] **Data Persistence with JPA**
  ```java
  // Entity (Model)
  @Entity
  @Table(name = "users")
  public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      
      @Column(nullable = false)
      private String name;
      
      @Column(unique = true, nullable = false)
      private String email;
      
      private String password;
      
      @CreatedDate
      private LocalDateTime createdAt;
      
      // Getters and Setters
  }
  
  // Repository
  @Repository
  public interface UserRepository extends JpaRepository<User, Long> {
      // Custom queries
      Optional<User> findByEmail(String email);
      List<User> findByNameContaining(String name);
      
      @Query("SELECT u FROM User u WHERE u.createdAt > :date")
      List<User> findRecentUsers(@Param("date") LocalDateTime date);
  }
  ```
  
  **Real-life Example:** 🗄️ **Database Operations**
  - Entity = Table structure
  - Repository = Database queries
  - JPA = Automatic SQL generation

---

## 🔷 Part 3: Advanced Features

### Concepts to Master:

- [ ] **Dependency Injection**
  ```java
  // Without DI (Tight coupling)
  public class UserController {
      private UserService userService = new UserService();  // ❌ Bad
  }
  
  // With DI (Loose coupling)
  @RestController
  public class UserController {
      @Autowired  // Spring injects dependency
      private UserService userService;  // ✅ Good
  }
  
  // Constructor Injection (Best practice)
  @RestController
  public class UserController {
      private final UserService userService;
      
      public UserController(UserService userService) {
          this.userService = userService;
      }
  }
  ```
  
  **Real-life Example:** 🔌 **Power Socket**
  - Device doesn't create electricity
  - Plugs into socket (dependency injected)
  - Easy to replace/test

- [ ] **Exception Handling**
  ```java
  // Custom Exception
  public class UserNotFoundException extends RuntimeException {
      public UserNotFoundException(String message) {
          super(message);
      }
  }
  
  // Global Exception Handler
  @RestControllerAdvice
  public class GlobalExceptionHandler {
      
      @ExceptionHandler(UserNotFoundException.class)
      public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
          ErrorResponse error = new ErrorResponse(
              HttpStatus.NOT_FOUND.value(),
              ex.getMessage(),
              LocalDateTime.now()
          );
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
      }
      
      @ExceptionHandler(Exception.class)
      public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
          ErrorResponse error = new ErrorResponse(
              HttpStatus.INTERNAL_SERVER_ERROR.value(),
              "An error occurred",
              LocalDateTime.now()
          );
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
      }
  }
  ```

- [ ] **Validation**
  ```java
  @Entity
  public class User {
      @NotNull(message = "Name is required")
      @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
      private String name;
      
      @Email(message = "Invalid email format")
      @NotBlank(message = "Email is required")
      private String email;
      
      @Min(value = 18, message = "Age must be at least 18")
      private Integer age;
  }
  
  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
      // If validation fails, automatic 400 Bad Request
      return ResponseEntity.ok(userService.createUser(user));
  }
  ```

- [ ] **Configuration Properties**
  ```properties
  # application.properties
  server.port=8080
  spring.application.name=my-app
  
  # Database
  spring.datasource.url=jdbc:mysql://localhost:3306/mydb
  spring.datasource.username=root
  spring.datasource.password=password
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  
  # Custom properties
  app.jwt.secret=mySecretKey
  app.jwt.expiration=86400000
  ```
  
  ```java
  @Configuration
  @ConfigurationProperties(prefix = "app.jwt")
  public class JwtConfig {
      private String secret;
      private Long expiration;
      
      // Getters and Setters
  }
  ```

---

## 🔷 Part 4: Security & Authentication

### Concepts to Master:

- [ ] **Spring Security Basics**
  ```java
  @Configuration
  @EnableWebSecurity
  public class SecurityConfig {
      
      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
              .csrf().disable()
              .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/api/public/**").permitAll()
                  .requestMatchers("/api/admin/**").hasRole("ADMIN")
                  .anyRequest().authenticated()
              )
              .httpBasic();
          
          return http.build();
      }
  }
  ```

- [ ] **JWT Authentication**
  ```java
  // JWT Utility
  @Component
  public class JwtUtil {
      @Value("${jwt.secret}")
      private String secret;
      
      public String generateToken(String username) {
          return Jwts.builder()
                  .setSubject(username)
                  .setIssuedAt(new Date())
                  .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                  .signWith(SignatureAlgorithm.HS256, secret)
                  .compact();
      }
      
      public String extractUsername(String token) {
          return Jwts.parser()
                  .setSigningKey(secret)
                  .parseClaimsJws(token)
                  .getBody()
                  .getSubject();
      }
  }
  
  // Login Controller
  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
      // Authenticate user
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()
          )
      );
      
      // Generate token
      String token = jwtUtil.generateToken(request.getUsername());
      return ResponseEntity.ok(new AuthResponse(token));
  }
  ```
  
  **Real-life Example:** 🎫 **Movie Ticket**
  - Login = Buy ticket
  - JWT Token = Ticket with QR code
  - Access resources = Enter theater with ticket

---

## 💻 Practice Projects (4)

- [ ] **Project 1: Todo List API**
  - CRUD operations for todos
  - User authentication
  - **Skills:** REST API, JPA, Security

- [ ] **Project 2: Blog Platform Backend**
  - Posts, comments, likes
  - User roles (admin, author, reader)
  - **Skills:** Relationships, Authorization

- [ ] **Project 3: E-commerce Product API**
  - Products, categories, inventory
  - Search and filtering
  - **Skills:** Complex queries, Pagination

- [ ] **Project 4: Social Media API**
  - Users, posts, followers
  - Feed generation
  - **Skills:** Many-to-many relationships, Performance

---

## ✅ Move-On Criteria

- [ ] ✔ **Spring Boot architecture explain করতে পারবেন**
- [ ] ✔ **REST API CRUD operations build করতে পারবেন**
- [ ] ✔ **JPA relationships বুঝবেন**
- [ ] ✔ **JWT authentication implement করতে পারবেন**
- [ ] ✔ **4টা projects complete করবেন**

---

**Real-World Applications:**
- 🛒 E-commerce backends
- 📱 Mobile app APIs
- 🏦 Banking systems
- 📊 Analytics platforms
- 🎮 Game servers

**Next:** [System Design Fundamentals →](Java-Topic-06-System-Design.md)

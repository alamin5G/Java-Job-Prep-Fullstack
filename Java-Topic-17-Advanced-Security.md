# 📌 Topic 17: Advanced Security - Production-Grade Protection

## 🎯 Learning Objectives

Master advanced security techniques to build secure, production-ready applications - a critical skill that protects user data and company reputation, and cannot be automated by AI.

---

## 🔷 Part 1: OWASP Top 10 Security Risks

### Concepts to Master:

- [ ] **1. Injection Attacks (SQL Injection)**
  ```java
  // ❌ VULNERABLE: SQL Injection
  @GetMapping("/users/search")
  public List<User> searchUsers(@RequestParam String name) {
      String sql = "SELECT * FROM users WHERE name = '" + name + "'";
      // Attacker input: ' OR '1'='1
      // Query becomes: SELECT * FROM users WHERE name = '' OR '1'='1'
      // Returns ALL users!
      return jdbcTemplate.query(sql, new UserRowMapper());
  }
  
  // ✅ SECURE: Prepared Statements
  @GetMapping("/users/search")
  public List<User> searchUsers(@RequestParam String name) {
      String sql = "SELECT * FROM users WHERE name = ?";
      return jdbcTemplate.query(sql, new UserRowMapper(), name);
      // Input is escaped automatically
  }
  
  // ✅ BEST: JPA (automatically prevents injection)
  @GetMapping("/users/search")
  public List<User> searchUsers(@RequestParam String name) {
      return userRepository.findByName(name);
      // JPA uses prepared statements internally
  }
  ```
  
  **Real-life Example:** 🏦 **Bank Vault**
  - SQL Injection = Fake ID to access vault
  - Prepared Statement = Biometric verification
  - Can't fake your way in!

- [ ] **2. Broken Authentication**
  ```java
  // ❌ VULNERABLE: Weak password storage
  @Service
  public class UserService {
      public void createUser(String username, String password) {
          User user = new User();
          user.setUsername(username);
          user.setPassword(password);  // Plain text! ❌
          userRepository.save(user);
      }
  }
  
  // ✅ SECURE: Password hashing with BCrypt
  @Service
  public class UserService {
      
      @Autowired
      private PasswordEncoder passwordEncoder;
      
      public void createUser(String username, String password) {
          User user = new User();
          user.setUsername(username);
          user.setPassword(passwordEncoder.encode(password));  // Hashed ✅
          userRepository.save(user);
      }
      
      public boolean authenticate(String username, String password) {
          User user = userRepository.findByUsername(username)
              .orElseThrow(() -> new UserNotFoundException("User not found"));
          
          return passwordEncoder.matches(password, user.getPassword());
      }
  }
  
  @Configuration
  public class SecurityConfig {
      @Bean
      public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder(12);  // Strength: 12
      }
  }
  ```

- [ ] **3. Sensitive Data Exposure**
  ```java
  // ❌ VULNERABLE: Exposing sensitive data
  @Entity
  public class User {
      private Long id;
      private String username;
      private String password;  // Exposed in API response!
      private String ssn;       // Exposed!
  }
  
  // ✅ SECURE: Use DTOs
  public class UserDTO {
      private Long id;
      private String username;
      // No password, no SSN
      
      public UserDTO(User user) {
          this.id = user.getId();
          this.username = user.getUsername();
      }
  }
  
  @RestController
  public class UserController {
      @GetMapping("/users/{id}")
      public UserDTO getUser(@PathVariable Long id) {
          User user = userService.findById(id);
          return new UserDTO(user);  // Only safe data exposed
      }
  }
  
  // Alternative: @JsonIgnore
  @Entity
  public class User {
      private Long id;
      private String username;
      
      @JsonIgnore  // Never serialize in JSON
      private String password;
      
      @JsonIgnore
      private String ssn;
  }
  ```

- [ ] **4. XML External Entities (XXE)**
  ```java
  // ❌ VULNERABLE: XXE Attack
  public void parseXML(String xml) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new InputSource(new StringReader(xml)));
      // Attacker can read local files!
  }
  
  // ✅ SECURE: Disable external entities
  public void parseXML(String xml) throws Exception {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      
      // Disable external entities
      factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
      factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new InputSource(new StringReader(xml)));
  }
  ```

- [ ] **5. Broken Access Control**
  ```java
  // ❌ VULNERABLE: No authorization check
  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id) {
      userService.deleteUser(id);
      // Any user can delete any user!
  }
  
  // ✅ SECURE: Check authorization
  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable Long id, Authentication auth) {
      User currentUser = (User) auth.getPrincipal();
      
      // Only admin or self can delete
      if (!currentUser.isAdmin() && !currentUser.getId().equals(id)) {
          throw new AccessDeniedException("Not authorized");
      }
      
      userService.deleteUser(id);
  }
  
  // ✅ BETTER: Use @PreAuthorize
  @DeleteMapping("/users/{id}")
  @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
  public void deleteUser(@PathVariable Long id) {
      userService.deleteUser(id);
  }
  ```

- [ ] **6. Security Misconfiguration**
  ```properties
  # ❌ VULNERABLE: Default settings
  spring.h2.console.enabled=true  # H2 console exposed in production!
  server.error.include-stacktrace=always  # Stack traces exposed!
  management.endpoints.web.exposure.include=*  # All endpoints exposed!
  
  # ✅ SECURE: Production settings
  spring.h2.console.enabled=false
  server.error.include-stacktrace=never
  server.error.include-message=never
  management.endpoints.web.exposure.include=health,info
  
  # Security headers
  server.servlet.session.cookie.http-only=true
  server.servlet.session.cookie.secure=true
  server.servlet.session.cookie.same-site=strict
  ```

- [ ] **7. Cross-Site Scripting (XSS)**
  ```java
  // ❌ VULNERABLE: XSS Attack
  @GetMapping("/search")
  public String search(@RequestParam String query, Model model) {
      model.addAttribute("query", query);
      return "search";  // Directly renders user input
      // Attacker input: <script>alert('XSS')</script>
  }
  
  // ✅ SECURE: Escape output (Thymeleaf does this automatically)
  <!-- search.html -->
  <p>Search results for: <span th:text="${query}"></span></p>
  <!-- th:text escapes HTML automatically -->
  
  // For REST APIs: Validate and sanitize input
  @PostMapping("/comments")
  public Comment createComment(@RequestBody @Valid CommentDTO dto) {
      // Validation
      if (dto.getText().contains("<script>")) {
          throw new ValidationException("Invalid input");
      }
      
      // Sanitize
      String sanitized = Jsoup.clean(dto.getText(), Whitelist.basic());
      
      Comment comment = new Comment();
      comment.setText(sanitized);
      return commentRepository.save(comment);
  }
  ```

- [ ] **8. Insecure Deserialization**
  ```java
  // ❌ VULNERABLE: Deserializing untrusted data
  public Object deserialize(byte[] data) {
      ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
      return ois.readObject();  // Can execute malicious code!
  }
  
  // ✅ SECURE: Use JSON instead
  @PostMapping("/data")
  public ResponseEntity<?> processData(@RequestBody DataDTO data) {
      // JSON deserialization is safer
      return ResponseEntity.ok(service.process(data));
  }
  
  // If you must use Java serialization:
  public Object deserialize(byte[] data) throws Exception {
      ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data)) {
          @Override
          protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException {
              // Whitelist allowed classes
              if (!desc.getName().startsWith("com.myapp.")) {
                  throw new InvalidClassException("Unauthorized deserialization");
              }
              return super.resolveClass(desc);
          }
      };
      return ois.readObject();
  }
  ```

---

## 🔷 Part 2: Advanced Authentication & Authorization

### Concepts to Master:

- [ ] **OAuth2 Implementation**
  ```java
  @Configuration
  @EnableWebSecurity
  public class OAuth2SecurityConfig {
      
      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
              .authorizeHttpRequests(auth -> auth
                  .requestMatchers("/", "/login", "/oauth2/**").permitAll()
                  .anyRequest().authenticated()
              )
              .oauth2Login(oauth2 -> oauth2
                  .loginPage("/login")
                  .defaultSuccessUrl("/dashboard")
                  .userInfoEndpoint(userInfo -> userInfo
                      .userService(customOAuth2UserService)
                  )
              );
          
          return http.build();
      }
  }
  
  @Service
  public class CustomOAuth2UserService extends DefaultOAuth2UserService {
      
      @Override
      public OAuth2User loadUser(OAuth2UserRequest userRequest) {
          OAuth2User oauth2User = super.loadUser(userRequest);
          
          // Save or update user in database
          String email = oauth2User.getAttribute("email");
          User user = userRepository.findByEmail(email)
              .orElseGet(() -> {
                  User newUser = new User();
                  newUser.setEmail(email);
                  newUser.setName(oauth2User.getAttribute("name"));
                  return userRepository.save(newUser);
              });
          
          return new CustomOAuth2User(oauth2User, user);
      }
  }
  ```

- [ ] **Role-Based Access Control (RBAC)**
  ```java
  @Entity
  public class User {
      @Id
      private Long id;
      
      private String username;
      
      @ManyToMany(fetch = FetchType.EAGER)
      @JoinTable(
          name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
      )
      private Set<Role> roles = new HashSet<>();
  }
  
  @Entity
  public class Role {
      @Id
      private Long id;
      
      private String name;  // ROLE_USER, ROLE_ADMIN, ROLE_MODERATOR
      
      @ManyToMany
      private Set<Permission> permissions;
  }
  
  // Usage in controllers
  @RestController
  @RequestMapping("/api/admin")
  public class AdminController {
      
      @GetMapping("/users")
      @PreAuthorize("hasRole('ADMIN')")
      public List<User> getAllUsers() {
          return userService.findAll();
      }
      
      @DeleteMapping("/users/{id}")
      @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
      public void deleteUser(@PathVariable Long id) {
          userService.deleteUser(id);
      }
      
      @PostMapping("/settings")
      @PreAuthorize("hasAuthority('WRITE_SETTINGS')")
      public void updateSettings(@RequestBody Settings settings) {
          settingsService.update(settings);
      }
  }
  ```

- [ ] **Multi-Factor Authentication (MFA)**
  ```java
  @Service
  public class MFAService {
      
      private final GoogleAuthenticator gAuth = new GoogleAuthenticator();
      
      public String generateSecretKey() {
          GoogleAuthenticatorKey key = gAuth.createCredentials();
          return key.getKey();
      }
      
      public String generateQRCode(String username, String secretKey) {
          return GoogleAuthenticatorQRGenerator.getOtpAuthURL(
              "MyApp",
              username,
              new GoogleAuthenticatorKey.Builder(secretKey).build()
          );
      }
      
      public boolean verifyCode(String secretKey, int code) {
          return gAuth.authorize(secretKey, code);
      }
  }
  
  @RestController
  public class MFAController {
      
      @Autowired
      private MFAService mfaService;
      
      @PostMapping("/mfa/setup")
      public MFASetupResponse setupMFA(Authentication auth) {
          User user = (User) auth.getPrincipal();
          
          String secretKey = mfaService.generateSecretKey();
          String qrCodeUrl = mfaService.generateQRCode(user.getUsername(), secretKey);
          
          user.setMfaSecret(secretKey);
          user.setMfaEnabled(true);
          userRepository.save(user);
          
          return new MFASetupResponse(qrCodeUrl);
      }
      
      @PostMapping("/mfa/verify")
      public ResponseEntity<?> verifyMFA(@RequestBody MFAVerifyRequest request, Authentication auth) {
          User user = (User) auth.getPrincipal();
          
          boolean valid = mfaService.verifyCode(user.getMfaSecret(), request.getCode());
          
          if (valid) {
              // Generate JWT token
              String token = jwtService.generateToken(user);
              return ResponseEntity.ok(new AuthResponse(token));
          } else {
              return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                  .body("Invalid MFA code");
          }
      }
  }
  ```

---

## 🔷 Part 3: API Security

### Concepts to Master:

- [ ] **Rate Limiting**
  ```java
  @Component
  public class RateLimitFilter extends OncePerRequestFilter {
      
      private final Map<String, RateLimiter> limiters = new ConcurrentHashMap<>();
      
      @Override
      protected void doFilterInternal(HttpServletRequest request, 
                                     HttpServletResponse response, 
                                     FilterChain filterChain) throws ServletException, IOException {
          
          String clientId = getClientId(request);
          
          RateLimiter limiter = limiters.computeIfAbsent(clientId, 
              k -> RateLimiter.create(100.0));  // 100 requests per second
          
          if (!limiter.tryAcquire()) {
              response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
              response.getWriter().write("Rate limit exceeded");
              return;
          }
          
          filterChain.doFilter(request, response);
      }
      
      private String getClientId(HttpServletRequest request) {
          // Use IP address or API key
          return request.getRemoteAddr();
      }
  }
  ```

- [ ] **API Key Authentication**
  ```java
  @Component
  public class ApiKeyFilter extends OncePerRequestFilter {
      
      @Autowired
      private ApiKeyService apiKeyService;
      
      @Override
      protected void doFilterInternal(HttpServletRequest request, 
                                     HttpServletResponse response, 
                                     FilterChain filterChain) throws ServletException, IOException {
          
          String apiKey = request.getHeader("X-API-Key");
          
          if (apiKey == null || !apiKeyService.isValid(apiKey)) {
              response.setStatus(HttpStatus.UNAUTHORIZED.value());
              response.getWriter().write("Invalid API key");
              return;
          }
          
          // Set authentication
          ApiKeyAuthentication auth = new ApiKeyAuthentication(apiKey);
          SecurityContextHolder.getContext().setAuthentication(auth);
          
          filterChain.doFilter(request, response);
      }
  }
  
  @Service
  public class ApiKeyService {
      
      @Autowired
      private ApiKeyRepository apiKeyRepository;
      
      public boolean isValid(String key) {
          return apiKeyRepository.findByKey(key)
              .map(ApiKey::isActive)
              .orElse(false);
      }
      
      public String generateApiKey(User user) {
          String key = UUID.randomUUID().toString();
          
          ApiKey apiKey = new ApiKey();
          apiKey.setKey(key);
          apiKey.setUser(user);
          apiKey.setActive(true);
          apiKey.setCreatedAt(LocalDateTime.now());
          
          apiKeyRepository.save(apiKey);
          
          return key;
      }
  }
  ```

- [ ] **CORS Security**
  ```java
  @Configuration
  public class CorsConfig {
      
      @Bean
      public WebMvcConfigurer corsConfigurer() {
          return new WebMvcConfigurer() {
              @Override
              public void addCorsMappings(CorsRegistry registry) {
                  registry.addMapping("/api/**")
                      .allowedOrigins("https://myapp.com")  // Specific domain only
                      .allowedMethods("GET", "POST", "PUT", "DELETE")
                      .allowedHeaders("*")
                      .allowCredentials(true)
                      .maxAge(3600);
              }
          };
      }
  }
  
  // ❌ DON'T DO THIS in production:
  // .allowedOrigins("*")  // Allows any domain!
  ```

---

## 🔷 Part 4: Data Encryption

### Concepts to Master:

- [ ] **Encrypt Sensitive Data**
  ```java
  @Service
  public class EncryptionService {
      
      private static final String ALGORITHM = "AES/GCM/NoPadding";
      private static final int GCM_IV_LENGTH = 12;
      private static final int GCM_TAG_LENGTH = 16;
      
      @Value("${encryption.secret.key}")
      private String secretKey;
      
      public String encrypt(String data) throws Exception {
          byte[] iv = new byte[GCM_IV_LENGTH];
          new SecureRandom().nextBytes(iv);
          
          Cipher cipher = Cipher.getInstance(ALGORITHM);
          GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
          
          SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
          cipher.init(Cipher.ENCRYPT_MODE, keySpec, parameterSpec);
          
          byte[] encrypted = cipher.doFinal(data.getBytes());
          
          // Combine IV + encrypted data
          byte[] combined = new byte[iv.length + encrypted.length];
          System.arraycopy(iv, 0, combined, 0, iv.length);
          System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);
          
          return Base64.getEncoder().encodeToString(combined);
      }
      
      public String decrypt(String encryptedData) throws Exception {
          byte[] combined = Base64.getDecoder().decode(encryptedData);
          
          // Extract IV
          byte[] iv = new byte[GCM_IV_LENGTH];
          System.arraycopy(combined, 0, iv, 0, iv.length);
          
          // Extract encrypted data
          byte[] encrypted = new byte[combined.length - GCM_IV_LENGTH];
          System.arraycopy(combined, GCM_IV_LENGTH, encrypted, 0, encrypted.length);
          
          Cipher cipher = Cipher.getInstance(ALGORITHM);
          GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, iv);
          
          SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
          cipher.init(Cipher.DECRYPT_MODE, keySpec, parameterSpec);
          
          byte[] decrypted = cipher.doFinal(encrypted);
          return new String(decrypted);
      }
  }
  
  // Usage
  @Entity
  public class User {
      @Id
      private Long id;
      
      private String username;
      
      @Column(name = "ssn_encrypted")
      private String ssnEncrypted;  // Encrypted in database
      
      @Transient
      public String getSsn() throws Exception {
          return encryptionService.decrypt(ssnEncrypted);
      }
      
      public void setSsn(String ssn) throws Exception {
          this.ssnEncrypted = encryptionService.encrypt(ssn);
      }
  }
  ```

---

## 💻 Practice: Secure a REST API

### Scenario: Banking API

**Security Requirements:**
1. JWT authentication
2. Role-based access
3. Rate limiting
4. Input validation
5. Audit logging
6. Encryption for sensitive data

**Implementation:**

- [ ] **Step 1: JWT Authentication**
- [ ] **Step 2: Role-based endpoints**
- [ ] **Step 3: Rate limiting**
- [ ] **Step 4: Input validation**
- [ ] **Step 5: Audit all transactions**
- [ ] **Step 6: Encrypt account numbers**

---

## 🎯 Interview Questions & Answers

### Q1: Explain OWASP Top 10.

**Answer:**
```
Top 3 Most Critical:

1. Injection (SQL, NoSQL, OS):
   - Attacker inserts malicious code
   - Prevention: Prepared statements, input validation
   - Example: SQL Injection

2. Broken Authentication:
   - Weak passwords, session management
   - Prevention: Strong password policy, MFA, secure sessions
   - Example: Password stored in plain text

3. Sensitive Data Exposure:
   - Exposing passwords, credit cards, SSN
   - Prevention: Encryption, HTTPS, DTOs
   - Example: API returning password field

Real Impact:
- Equifax breach: 147 million records
- Cost: $700 million
- Cause: Unpatched vulnerability
```

### Q2: How do you secure a REST API?

**Answer:**
```
Security Layers:

1. Authentication:
   - JWT tokens
   - OAuth2
   - API keys

2. Authorization:
   - Role-based access (RBAC)
   - Permission checks
   - @PreAuthorize annotations

3. Input Validation:
   - @Valid annotations
   - Custom validators
   - Sanitize input

4. Rate Limiting:
   - Prevent abuse
   - 100 requests/minute per user

5. HTTPS Only:
   - Encrypt data in transit
   - TLS 1.2+

6. Security Headers:
   - CORS
   - Content-Security-Policy
   - X-Frame-Options

7. Logging & Monitoring:
   - Audit all access
   - Alert on suspicious activity
```

---

## ✅ Move-On Criteria

- [ ] ✔ **OWASP Top 10 বুঝবেন এবং prevent করতে পারবেন**
- [ ] ✔ **Secure authentication implement করতে পারবেন**
- [ ] ✔ **Authorization (RBAC) setup করতে পারবেন**
- [ ] ✔ **Input validation করতে পারবেন**
- [ ] ✔ **Data encryption করতে পারবেন**
- [ ] ✔ **Rate limiting implement করতে পারবেন**
- [ ] ✔ **Security best practices follow করতে পারবেন**
- [ ] ✔ **Security vulnerabilities identify করতে পারবেন**

---

**Real-World Impact:**
- 🔒 Protect user data
- 💰 Prevent financial loss
- 🏆 Build trust
- 📈 Company reputation
- ⚖️ Legal compliance

**Congratulations! You now have ALL essential topics covered! 🎉**

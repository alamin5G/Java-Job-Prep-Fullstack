# 📌 Topic 18: GraphQL - Modern API Development

## 🎯 Learning Objectives

Master GraphQL to build flexible, efficient APIs - a modern alternative to REST that's gaining massive adoption in companies like Facebook, GitHub, Shopify, and is becoming essential for mid-level developers.

---

## 🔷 Part 1: Understanding GraphQL

### Concepts to Master:

- [ ] **What is GraphQL?**
  ```
  REST API Problems:
  - Over-fetching: Get more data than needed
  - Under-fetching: Need multiple requests
  - Fixed endpoints: /users, /posts, /comments
  - Versioning nightmare: /api/v1, /api/v2
  
  GraphQL Solution:
  - Single endpoint: /graphql
  - Client specifies exact data needed
  - One request, all data
  - No versioning needed
  - Strongly typed schema
  ```
  
  **Real-life Example:** 🍕 **Pizza Ordering**
  - REST = Fixed combos (Combo 1, Combo 2)
  - GraphQL = Build your own pizza
  - Choose exactly what you want
  - No unwanted toppings

- [ ] **GraphQL vs REST**
  ```
  REST Example:
  GET /users/1           → { id, name, email, address, ... }
  GET /users/1/posts     → [ { id, title, content, ... }, ... ]
  GET /posts/1/comments  → [ { id, text, author, ... }, ... ]
  
  3 requests, lots of unnecessary data!
  
  GraphQL Example:
  POST /graphql
  {
    user(id: 1) {
      name
      email
      posts {
        title
        comments {
          text
        }
      }
    }
  }
  
  1 request, exactly what you need!
  ```

- [ ] **When to Use GraphQL?**
  ```
  ✅ Use GraphQL When:
  - Mobile apps (reduce data transfer)
  - Complex data relationships
  - Multiple clients (web, mobile, IoT)
  - Rapid frontend development
  - Real-time updates needed
  
  ❌ Use REST When:
  - Simple CRUD operations
  - File uploads/downloads
  - Caching is critical
  - Team unfamiliar with GraphQL
  - Small project
  ```

---

## 🔷 Part 2: GraphQL Basics

### Concepts to Master:

- [ ] **Schema Definition**
  ```graphql
  # schema.graphqls
  
  # Types
  type User {
    id: ID!              # ! means required
    name: String!
    email: String!
    age: Int
    posts: [Post!]!      # List of posts
    createdAt: String!
  }
  
  type Post {
    id: ID!
    title: String!
    content: String!
    author: User!        # Relationship
    comments: [Comment!]!
    published: Boolean!
  }
  
  type Comment {
    id: ID!
    text: String!
    author: User!
    post: Post!
  }
  
  # Queries (Read operations)
  type Query {
    # Get single user
    user(id: ID!): User
    
    # Get all users
    users: [User!]!
    
    # Search users
    searchUsers(name: String!): [User!]!
    
    # Get single post
    post(id: ID!): Post
    
    # Get all posts
    posts(limit: Int, offset: Int): [Post!]!
  }
  
  # Mutations (Write operations)
  type Mutation {
    # Create user
    createUser(name: String!, email: String!, age: Int): User!
    
    # Update user
    updateUser(id: ID!, name: String, email: String): User!
    
    # Delete user
    deleteUser(id: ID!): Boolean!
    
    # Create post
    createPost(title: String!, content: String!, authorId: ID!): Post!
  }
  
  # Subscriptions (Real-time updates)
  type Subscription {
    # Listen for new posts
    postCreated: Post!
    
    # Listen for new comments
    commentAdded(postId: ID!): Comment!
  }
  ```

- [ ] **Queries (Read Data)**
  ```graphql
  # Get user with specific fields
  query {
    user(id: "1") {
      name
      email
    }
  }
  
  # Response:
  {
    "data": {
      "user": {
        "name": "John Doe",
        "email": "john@example.com"
      }
    }
  }
  
  # Get user with posts
  query {
    user(id: "1") {
      name
      posts {
        title
        published
      }
    }
  }
  
  # Get multiple resources
  query {
    user(id: "1") {
      name
      email
    }
    posts(limit: 5) {
      title
      author {
        name
      }
    }
  }
  
  # Query with variables
  query GetUser($userId: ID!) {
    user(id: $userId) {
      name
      email
      posts {
        title
      }
    }
  }
  
  # Variables:
  {
    "userId": "1"
  }
  ```

- [ ] **Mutations (Write Data)**
  ```graphql
  # Create user
  mutation {
    createUser(
      name: "Jane Doe"
      email: "jane@example.com"
      age: 25
    ) {
      id
      name
      email
    }
  }
  
  # Update user
  mutation {
    updateUser(
      id: "1"
      name: "Jane Smith"
    ) {
      id
      name
      email
    }
  }
  
  # Delete user
  mutation {
    deleteUser(id: "1")
  }
  
  # Create post
  mutation {
    createPost(
      title: "My First Post"
      content: "Hello GraphQL!"
      authorId: "1"
    ) {
      id
      title
      author {
        name
      }
    }
  }
  ```

---

## 🔷 Part 3: GraphQL with Spring Boot

### Concepts to Master:

- [ ] **Setup Spring Boot GraphQL**
  ```xml
  <!-- pom.xml -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-graphql</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
  </dependency>
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
  </dependency>
  ```
  
  ```properties
  # application.properties
  spring.graphql.graphiql.enabled=true
  spring.graphql.graphiql.path=/graphiql
  
  # Access GraphiQL: http://localhost:8080/graphiql
  ```

- [ ] **Entity Classes**
  ```java
  @Entity
  public class User {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      
      private String name;
      private String email;
      private Integer age;
      
      @OneToMany(mappedBy = "author")
      private List<Post> posts = new ArrayList<>();
      
      private LocalDateTime createdAt;
      
      // Getters and Setters
  }
  
  @Entity
  public class Post {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      
      private String title;
      private String content;
      private Boolean published;
      
      @ManyToOne
      @JoinColumn(name = "author_id")
      private User author;
      
      @OneToMany(mappedBy = "post")
      private List<Comment> comments = new ArrayList<>();
      
      // Getters and Setters
  }
  
  @Entity
  public class Comment {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long id;
      
      private String text;
      
      @ManyToOne
      private User author;
      
      @ManyToOne
      private Post post;
      
      // Getters and Setters
  }
  ```

- [ ] **GraphQL Schema**
  ```graphql
  # src/main/resources/graphql/schema.graphqls
  
  type User {
      id: ID!
      name: String!
      email: String!
      age: Int
      posts: [Post!]!
      createdAt: String!
  }
  
  type Post {
      id: ID!
      title: String!
      content: String!
      published: Boolean!
      author: User!
      comments: [Comment!]!
  }
  
  type Comment {
      id: ID!
      text: String!
      author: User!
      post: Post!
  }
  
  type Query {
      user(id: ID!): User
      users: [User!]!
      post(id: ID!): Post
      posts: [Post!]!
  }
  
  type Mutation {
      createUser(name: String!, email: String!, age: Int): User!
      updateUser(id: ID!, name: String, email: String): User!
      deleteUser(id: ID!): Boolean!
      createPost(title: String!, content: String!, authorId: ID!): Post!
  }
  ```

- [ ] **Query Resolver**
  ```java
  @Controller
  public class UserQueryResolver {
      
      @Autowired
      private UserRepository userRepository;
      
      @Autowired
      private PostRepository postRepository;
      
      @QueryMapping
      public User user(@Argument Long id) {
          return userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not found"));
      }
      
      @QueryMapping
      public List<User> users() {
          return userRepository.findAll();
      }
      
      @QueryMapping
      public Post post(@Argument Long id) {
          return postRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Post not found"));
      }
      
      @QueryMapping
      public List<Post> posts() {
          return postRepository.findAll();
      }
  }
  ```

- [ ] **Mutation Resolver**
  ```java
  @Controller
  public class UserMutationResolver {
      
      @Autowired
      private UserRepository userRepository;
      
      @Autowired
      private PostRepository postRepository;
      
      @MutationMapping
      public User createUser(@Argument String name, 
                            @Argument String email, 
                            @Argument Integer age) {
          User user = new User();
          user.setName(name);
          user.setEmail(email);
          user.setAge(age);
          user.setCreatedAt(LocalDateTime.now());
          
          return userRepository.save(user);
      }
      
      @MutationMapping
      public User updateUser(@Argument Long id, 
                            @Argument String name, 
                            @Argument String email) {
          User user = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not found"));
          
          if (name != null) user.setName(name);
          if (email != null) user.setEmail(email);
          
          return userRepository.save(user);
      }
      
      @MutationMapping
      public Boolean deleteUser(@Argument Long id) {
          userRepository.deleteById(id);
          return true;
      }
      
      @MutationMapping
      public Post createPost(@Argument String title, 
                            @Argument String content, 
                            @Argument Long authorId) {
          User author = userRepository.findById(authorId)
              .orElseThrow(() -> new RuntimeException("Author not found"));
          
          Post post = new Post();
          post.setTitle(title);
          post.setContent(content);
          post.setAuthor(author);
          post.setPublished(true);
          
          return postRepository.save(post);
      }
  }
  ```

- [ ] **Field Resolver (N+1 Problem Solution)**
  ```java
  @Controller
  public class UserFieldResolver {
      
      @Autowired
      private PostRepository postRepository;
      
      // Resolve posts for user
      @SchemaMapping(typeName = "User", field = "posts")
      public List<Post> posts(User user) {
          return postRepository.findByAuthorId(user.getId());
      }
  }
  
  // Better: Use DataLoader to batch requests
  @Configuration
  public class DataLoaderConfig {
      
      @Bean
      public DataLoader<Long, List<Post>> postsDataLoader(PostRepository postRepository) {
          return DataLoader.newDataLoader(userIds -> {
              // Batch load posts for multiple users
              Map<Long, List<Post>> postsByUserId = postRepository
                  .findByAuthorIdIn(userIds)
                  .stream()
                  .collect(Collectors.groupingBy(post -> post.getAuthor().getId()));
              
              return CompletableFuture.completedFuture(
                  userIds.stream()
                      .map(id -> postsByUserId.getOrDefault(id, Collections.emptyList()))
                      .collect(Collectors.toList())
              );
          });
      }
  }
  ```

---

## 🔷 Part 4: Advanced Features

### Concepts to Master:

- [ ] **Input Types**
  ```graphql
  # Define input type for complex objects
  input CreateUserInput {
      name: String!
      email: String!
      age: Int
  }
  
  input UpdateUserInput {
      name: String
      email: String
      age: Int
  }
  
  type Mutation {
      createUser(input: CreateUserInput!): User!
      updateUser(id: ID!, input: UpdateUserInput!): User!
  }
  ```
  
  ```java
  // Java record for input
  public record CreateUserInput(String name, String email, Integer age) {}
  
  @MutationMapping
  public User createUser(@Argument CreateUserInput input) {
      User user = new User();
      user.setName(input.name());
      user.setEmail(input.email());
      user.setAge(input.age());
      user.setCreatedAt(LocalDateTime.now());
      
      return userRepository.save(user);
  }
  ```

- [ ] **Pagination**
  ```graphql
  type Query {
      posts(page: Int, size: Int): PostPage!
  }
  
  type PostPage {
      content: [Post!]!
      totalElements: Int!
      totalPages: Int!
      number: Int!
      size: Int!
  }
  ```
  
  ```java
  @QueryMapping
  public PostPage posts(@Argument Integer page, @Argument Integer size) {
      Pageable pageable = PageRequest.of(
          page != null ? page : 0,
          size != null ? size : 10
      );
      
      Page<Post> postPage = postRepository.findAll(pageable);
      
      return new PostPage(
          postPage.getContent(),
          postPage.getTotalElements(),
          postPage.getTotalPages(),
          postPage.getNumber(),
          postPage.getSize()
      );
  }
  ```

- [ ] **Authentication & Authorization**
  ```java
  @Controller
  public class SecureResolver {
      
      @QueryMapping
      @PreAuthorize("isAuthenticated()")
      public User currentUser(Authentication auth) {
          String email = auth.getName();
          return userRepository.findByEmail(email)
              .orElseThrow(() -> new RuntimeException("User not found"));
      }
      
      @MutationMapping
      @PreAuthorize("hasRole('ADMIN')")
      public Boolean deleteUser(@Argument Long id) {
          userRepository.deleteById(id);
          return true;
      }
      
      @SchemaMapping(typeName = "User", field = "email")
      public String email(User user, Authentication auth) {
          // Only show email to owner or admin
          User currentUser = (User) auth.getPrincipal();
          
          if (currentUser.getId().equals(user.getId()) || currentUser.isAdmin()) {
              return user.getEmail();
          }
          
          return null;  // Hidden
      }
  }
  ```

- [ ] **Error Handling**
  ```java
  @ControllerAdvice
  public class GraphQLExceptionHandler {
      
      @ExceptionHandler(UserNotFoundException.class)
      public GraphQLError handleUserNotFound(UserNotFoundException ex) {
          return GraphQLError.newError()
              .message(ex.getMessage())
              .errorType(ErrorType.NOT_FOUND)
              .build();
      }
      
      @ExceptionHandler(ValidationException.class)
      public GraphQLError handleValidation(ValidationException ex) {
          return GraphQLError.newError()
              .message(ex.getMessage())
              .errorType(ErrorType.ValidationError)
              .build();
      }
  }
  ```

- [ ] **Subscriptions (Real-time)**
  ```graphql
  type Subscription {
      postCreated: Post!
      commentAdded(postId: ID!): Comment!
  }
  ```
  
  ```java
  @Controller
  public class PostSubscriptionResolver {
      
      private final Sinks.Many<Post> postSink = Sinks.many().multicast().onBackpressureBuffer();
      
      @SubscriptionMapping
      public Flux<Post> postCreated() {
          return postSink.asFlux();
      }
      
      @MutationMapping
      public Post createPost(@Argument String title, @Argument String content, @Argument Long authorId) {
          // Create post
          Post post = // ... save post
          
          // Emit to subscribers
          postSink.tryEmitNext(post);
          
          return post;
      }
  }
  ```

---

## 💻 Practice Project: Social Media GraphQL API

### Project Features:
```
Users:
- Register, login, profile
- Follow/unfollow users
- Get followers/following

Posts:
- Create, update, delete posts
- Like/unlike posts
- Get feed (posts from followed users)

Comments:
- Add comments to posts
- Reply to comments
- Like comments

Real-time:
- New post notifications
- New comment notifications
- Like notifications
```

### Implementation Steps:

- [ ] **Step 1: Setup Schema**
  - Define types
  - Define queries
  - Define mutations
  - Define subscriptions

- [ ] **Step 2: Implement Resolvers**
  - Query resolvers
  - Mutation resolvers
  - Field resolvers
  - Subscription resolvers

- [ ] **Step 3: Add Features**
  - Authentication (JWT)
  - Authorization
  - Pagination
  - Real-time updates

- [ ] **Step 4: Optimize**
  - DataLoader (N+1 problem)
  - Caching
  - Query complexity limits

**Duration:** 1-2 weeks
**Skills:** GraphQL, Spring Boot, Real-time, Optimization

---

## 🎯 Interview Questions & Answers

### Q1: What is GraphQL and why use it over REST?

**Answer:**
```
GraphQL = Query language for APIs

Advantages over REST:

1. No Over-fetching:
   REST: GET /users/1 → Returns ALL fields
   GraphQL: Request only needed fields
   
2. No Under-fetching:
   REST: Multiple requests for related data
   GraphQL: One request, all related data
   
3. Single Endpoint:
   REST: /users, /posts, /comments
   GraphQL: /graphql (single endpoint)
   
4. Strongly Typed:
   Schema defines exact data structure
   Auto-generated documentation
   
5. No Versioning:
   REST: /api/v1, /api/v2
   GraphQL: Evolve schema without versions

When to Use:
- Mobile apps (reduce data transfer)
- Complex data relationships
- Multiple clients (web, mobile, IoT)
- Rapid frontend development

When NOT to Use:
- Simple CRUD
- File uploads
- Caching critical
- Team unfamiliar
```

### Q2: Explain N+1 problem in GraphQL and how to solve it.

**Answer:**
```
N+1 Problem:

Query:
{
  users {
    name
    posts {
      title
    }
  }
}

Without DataLoader:
1. SELECT * FROM users           (1 query)
2. SELECT * FROM posts WHERE user_id = 1  (N queries)
3. SELECT * FROM posts WHERE user_id = 2
...
Total: 1 + N queries (if 100 users = 101 queries!)

Solution: DataLoader

DataLoader batches requests:
1. SELECT * FROM users
2. SELECT * FROM posts WHERE user_id IN (1,2,3,...,100)
Total: 2 queries!

Implementation:
@Bean
public DataLoader<Long, List<Post>> postsLoader(PostRepository repo) {
    return DataLoader.newDataLoader(userIds -> {
        Map<Long, List<Post>> posts = repo
            .findByAuthorIdIn(userIds)
            .stream()
            .collect(Collectors.groupingBy(p -> p.getAuthor().getId()));
        
        return CompletableFuture.completedFuture(
            userIds.stream()
                .map(id -> posts.getOrDefault(id, emptyList()))
                .collect(toList())
        );
    });
}
```

### Q3: How do you handle authentication in GraphQL?

**Answer:**
```
Authentication Methods:

1. JWT in Header:
   Authorization: Bearer <token>
   
   @QueryMapping
   @PreAuthorize("isAuthenticated()")
   public User currentUser(Authentication auth) {
       return userRepository.findByEmail(auth.getName());
   }

2. Context-based:
   Pass user in GraphQL context
   Access in resolvers
   
3. Field-level Security:
   @SchemaMapping
   public String email(User user, Authentication auth) {
       if (isOwnerOrAdmin(user, auth)) {
           return user.getEmail();
       }
       return null;  // Hidden
   }

Best Practice:
- Use JWT tokens
- Validate in interceptor
- Set in SecurityContext
- Use @PreAuthorize for methods
- Field-level for sensitive data
```

---

## ✅ Move-On Criteria

- [ ] ✔ **GraphQL concepts বুঝবেন (vs REST)**
- [ ] ✔ **Schema define করতে পারবেন**
- [ ] ✔ **Queries লিখতে পারবেন**
- [ ] ✔ **Mutations implement করতে পারবেন**
- [ ] ✔ **Spring Boot GraphQL setup করতে পারবেন**
- [ ] ✔ **N+1 problem solve করতে পারবেন (DataLoader)**
- [ ] ✔ **Authentication/Authorization করতে পারবেন**
- [ ] ✔ **Subscriptions (real-time) implement করতে পারবেন**
- [ ] ✔ **Interview questions answer করতে পারবেন**

---

**Real-World Companies Using GraphQL:**
- 📘 Facebook (Creator)
- 🐙 GitHub
- 🛒 Shopify
- 📺 Netflix
- 🎵 Spotify
- 💬 Twitter
- 📱 Airbnb

**Next:** [Kubernetes Basics →](Java-Topic-19-Kubernetes.md) (Optional)

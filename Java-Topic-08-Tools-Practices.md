# 📌 Topic 8: Essential Tools & Practices

## 🎯 Learning Objectives
Master professional development tools and practices - what separates hobbyists from professional developers.

---

## 🔷 Part 1: Version Control with Git

### Concepts to Master:

- [ ] **Git Basics**
  ```bash
  # Initialize repository
  git init
  
  # Clone existing repository
  git clone https://github.com/username/repo.git
  
  # Check status
  git status
  
  # Add files to staging
  git add filename.java
  git add .  # Add all files
  
  # Commit changes
  git commit -m "Add user authentication feature"
  
  # Push to remote
  git push origin main
  
  # Pull latest changes
  git pull origin main
  ```
  
  **Real-life Example:** 💾 **Save Game Checkpoints**
  - Commit = Save checkpoint
  - Branch = Parallel universe
  - Merge = Combine timelines

- [ ] **Branching & Merging**
  ```bash
  # Create and switch to new branch
  git checkout -b feature/user-login
  
  # List branches
  git branch
  
  # Switch branch
  git checkout main
  
  # Merge branch
  git merge feature/user-login
  
  # Delete branch
  git branch -d feature/user-login
  ```
  
  **Workflow:**
  ```
  main (production)
    ↓
  develop (integration)
    ↓
  feature/login (your work)
  ```

- [ ] **Common Git Commands**
  ```bash
  # View commit history
  git log
  git log --oneline
  
  # Undo changes
  git checkout -- filename.java  # Discard local changes
  git reset HEAD filename.java   # Unstage file
  git revert commit-hash         # Undo commit
  
  # Stash changes
  git stash  # Save work temporarily
  git stash pop  # Restore stashed work
  
  # View differences
  git diff
  git diff --staged
  ```

---

## 🔷 Part 2: Build Tools

### Maven

- [ ] **Maven Basics**
  ```xml
  <!-- pom.xml -->
  <project>
      <modelVersion>4.0.0</modelVersion>
      <groupId>com.myapp</groupId>
      <artifactId>my-spring-app</artifactId>
      <version>1.0.0</version>
      
      <dependencies>
          <!-- Spring Boot -->
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
              <version>3.2.0</version>
          </dependency>
          
          <!-- MySQL -->
          <dependency>
              <groupId>mysql</groupId>
              <artifactId>mysql-connector-java</artifactId>
              <version>8.0.33</version>
          </dependency>
      </dependencies>
  </project>
  ```
  
  ```bash
  # Maven commands
  mvn clean          # Clean target directory
  mvn compile        # Compile code
  mvn test           # Run tests
  mvn package        # Create JAR/WAR
  mvn install        # Install to local repo
  mvn spring-boot:run  # Run Spring Boot app
  ```
  
  **Real-life Example:** 🏗️ **Construction Project**
  - pom.xml = Blueprint
  - Dependencies = Materials needed
  - Maven = Project manager

---

## 🔷 Part 3: Testing

### JUnit & Mockito

- [ ] **Unit Testing with JUnit**
  ```java
  @Test
  public void testAddition() {
      Calculator calc = new Calculator();
      int result = calc.add(2, 3);
      assertEquals(5, result);
  }
  
  @Test
  public void testDivisionByZero() {
      Calculator calc = new Calculator();
      assertThrows(ArithmeticException.class, () -> {
          calc.divide(10, 0);
      });
  }
  ```

- [ ] **Mocking with Mockito**
  ```java
  @ExtendWith(MockitoExtension.class)
  public class UserServiceTest {
      
      @Mock
      private UserRepository userRepository;
      
      @InjectMocks
      private UserService userService;
      
      @Test
      public void testGetUserById() {
          // Arrange
          User mockUser = new User(1L, "John", "john@test.com");
          when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
          
          // Act
          User result = userService.getUserById(1L);
          
          // Assert
          assertNotNull(result);
          assertEquals("John", result.getName());
          verify(userRepository, times(1)).findById(1L);
      }
  }
  ```
  
  **Real-life Example:** 🧪 **Lab Testing**
  - Unit test = Test individual component
  - Mock = Fake dependency for testing

---

## 🔷 Part 4: Docker Basics

### Concepts to Master:

- [ ] **Dockerfile**
  ```dockerfile
  FROM openjdk:17-jdk-slim
  WORKDIR /app
  COPY target/my-app.jar app.jar
  EXPOSE 8080
  ENTRYPOINT ["java", "-jar", "app.jar"]
  ```
  
  ```bash
  # Build image
  docker build -t my-spring-app .
  
  # Run container
  docker run -p 8080:8080 my-spring-app
  
  # List containers
  docker ps
  
  # Stop container
  docker stop container-id
  ```

- [ ] **Docker Compose**
  ```yaml
  version: '3.8'
  services:
    app:
      build: .
      ports:
        - "8080:8080"
      depends_on:
        - db
    
    db:
      image: mysql:8.0
      environment:
        MYSQL_ROOT_PASSWORD: root
        MYSQL_DATABASE: mydb
      ports:
        - "3306:3306"
  ```
  
  ```bash
  # Start all services
  docker-compose up
  
  # Stop all services
  docker-compose down
  ```
  
  **Real-life Example:** 📦 **Shipping Container**
  - App + dependencies = Sealed container
  - Runs anywhere (dev, staging, production)

---

## 💻 Practice Projects (2)

- [ ] **Project 1: CI/CD Pipeline**
  - Setup GitHub Actions
  - Automated testing
  - **Skills:** Git, Testing, Automation

- [ ] **Project 2: Dockerized Spring Boot App**
  - Dockerfile for app
  - Docker Compose with MySQL
  - **Skills:** Docker, Deployment

---

## ✅ Move-On Criteria

- [ ] ✔ **Git commands confidently use করতে পারবেন**
- [ ] ✔ **Maven project setup করতে পারবেন**
- [ ] ✔ **Unit tests লিখতে পারবেন**
- [ ] ✔ **Docker container run করতে পারবেন**
- [ ] ✔ **2টা projects complete করবেন**

---

**Real-World Applications:**
- 🔄 Version control in teams
- 🧪 Quality assurance
- 📦 Deployment automation
- 🚀 DevOps practices

**Next:** [Projects & Mock Interviews →](Java-Topic-10-Projects-Interviews.md)

# 🚀 Full Stack Java - Quick Reference

> **Quick lookup for interviews and revision**

---

## Core Java Essentials ☕

### OOP Pillars
```
1. Encapsulation: Data hiding (private fields, public methods)
2. Inheritance: Code reuse (extends)
3. Polymorphism: Many forms (overloading, overriding)
4. Abstraction: Hide implementation (abstract class, interface)
```

### Collections Framework
| Interface | Implementation | Use Case |
|-----------|---------------|----------|
| List | ArrayList | Fast access, rare modifications |
| List | LinkedList | Frequent insertions/deletions |
| Set | HashSet | Unique elements, no order |
| Set | TreeSet | Unique + sorted |
| Map | HashMap | Key-value, fast lookup |
| Map | TreeMap | Sorted by keys |
| Queue | PriorityQueue | Priority-based processing |

### String Methods
```java
length(), charAt(), substring(), toLowerCase(), toUpperCase()
contains(), replace(), split(), trim(), equals()
```

---

## Database & SQL 🗄️

### CRUD Operations
```sql
INSERT INTO table VALUES (...);
SELECT * FROM table WHERE condition;
UPDATE table SET column = value WHERE condition;
DELETE FROM table WHERE condition;
```

### JOINS
```sql
INNER JOIN: Matching records from both tables
LEFT JOIN: All from left + matching from right
RIGHT JOIN: All from right + matching from left
```

### Normalization
```
1NF: Atomic values, unique rows
2NF: 1NF + No partial dependencies
3NF: 2NF + No transitive dependencies
```

---

## Spring Boot 🍃

### Annotations
```java
@SpringBootApplication  // Main class
@RestController         // REST API controller
@Service                // Business logic
@Repository             // Data access
@Entity                 // JPA entity
@Autowired              // Dependency injection

// Request Mapping
@GetMapping("/path")
@PostMapping("/path")
@PutMapping("/path")
@DeleteMapping("/path")
@PathVariable
@RequestBody
@RequestParam
```

### Layered Architecture
```
Controller → Service → Repository → Database
(HTTP)      (Business) (Data Access)
```

### REST API Example
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }
    
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }
}
```

---

## Git Commands 🔄

```bash
# Basic
git init
git clone <url>
git status
git add .
git commit -m "message"
git push origin main
git pull origin main

# Branching
git branch feature-name
git checkout feature-name
git merge feature-name

# Undo
git reset HEAD file
git checkout -- file
git revert commit-hash
```

---

## Maven Commands 🛠️

```bash
mvn clean          # Clean target
mvn compile        # Compile code
mvn test           # Run tests
mvn package        # Create JAR
mvn spring-boot:run  # Run app
```

---

## Docker Commands 📦

```bash
# Images
docker build -t app-name .
docker images
docker rmi image-id

# Containers
docker run -p 8080:8080 app-name
docker ps
docker stop container-id
docker rm container-id

# Docker Compose
docker-compose up
docker-compose down
```

---

## Interview Quick Tips 🎯

### Technical Round
1. **Understand problem** → Ask clarifying questions
2. **Think aloud** → Explain your approach
3. **Start simple** → Brute force first
4. **Optimize** → Improve time/space complexity
5. **Test** → Walk through examples

### Behavioral Round (STAR Method)
- **S**ituation: Context
- **T**ask: Your responsibility
- **A**ction: What you did
- **R**esult: Outcome achieved

### Common Questions
**Java:**
- OOP concepts with examples
- ArrayList vs LinkedList
- HashMap internal working
- Exception handling

**Spring Boot:**
- Dependency injection
- REST API creation
- @Autowired vs @Inject
- Spring Boot advantages

**Database:**
- Normalization
- JOINS with examples
- Index usage
- ACID properties

**Projects:**
- Explain architecture
- Challenges faced
- Technologies used
- Your contribution

---

## HTTP Status Codes 🌐

```
200 OK - Success
201 Created - Resource created
400 Bad Request - Invalid input
401 Unauthorized - Authentication required
403 Forbidden - No permission
404 Not Found - Resource not found
500 Internal Server Error - Server error
```

---

## Time Complexity Cheat Sheet ⏱️

```
O(1)      - HashMap get/put
O(log n)  - Binary search, TreeMap
O(n)      - Linear search, ArrayList iteration
O(n log n)- Sorting (merge sort)
O(n²)     - Nested loops
```

---

## Project Checklist ✅

### Before Interview
- [ ] All projects on GitHub
- [ ] README for each project
- [ ] Live demo links
- [ ] Clean, commented code
- [ ] Can explain architecture

### Portfolio Must-Haves
- [ ] 5+ complete projects
- [ ] At least 1 full-stack project
- [ ] Database integration
- [ ] REST API implementation
- [ ] Authentication/Authorization

---

## Resume Keywords 📝

**Include these:**
- Spring Boot, Hibernate, JPA
- REST API, Microservices
- MySQL, PostgreSQL
- Git, Docker, Maven
- JUnit, Mockito
- AWS, CI/CD
- Agile, Scrum

---

## Salary Negotiation 💰

### Fresher Range (India)
- Service Companies: 3-5 LPA
- Product Companies: 6-12 LPA
- Startups: 4-8 LPA
- Top Product: 12-25 LPA

### Tips
1. Research market rates
2. Know your worth
3. Don't reveal current salary
4. Negotiate benefits too
5. Get offer in writing

---

## Day Before Interview 📅

- [ ] Review projects
- [ ] Practice 2-3 easy problems
- [ ] Prepare questions for interviewer
- [ ] Check dress code
- [ ] Good sleep (8 hours)
- [ ] Positive mindset

---

## Interview Day ⭐

### Do's
✅ Arrive 10 mins early
✅ Dress professionally
✅ Bring resume copies
✅ Smile and be confident
✅ Ask questions
✅ Send thank you email

### Don'ts
❌ Bad-mouth previous employer
❌ Lie about skills
❌ Check phone
❌ Interrupt interviewer
❌ Give up easily

---

**Keep this handy during preparation! 📖**

**You're ready to ace the interview! 💪🚀**

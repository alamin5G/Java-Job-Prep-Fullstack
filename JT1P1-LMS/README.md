# 📚 Library Management System - SOLID Principles Explained

## 🎯 Project Overview

A complete Library Management System demonstrating **SOLID principles** and **clean architecture**.

---

## 🏗️ Architecture

```
LibraryManagementSystem/
├── models/              # Data entities
│   ├── Book.java
│   ├── Member.java
│   └── Library.java (deprecated - not needed with services)
├── services/            # Business logic
│   ├── BookService.java
│   ├── MemberService.java
│   └── LibraryService.java
├── exception/           # Custom exceptions
│   ├── BookNotFoundException.java
│   ├── BookNotAvailableException.java
│   └── MemberNotFoundException.java
└── Main.java           # Demo application
```

---

## ✅ SOLID Principles Applied

### 1. **Single Responsibility Principle (SRP)**

**Each class has ONE job:**

```java
// ✅ GOOD - Each service has single responsibility
BookService      → Manages ONLY books
MemberService    → Manages ONLY members
LibraryService   → Coordinates book + member operations

// ❌ BAD - Would violate SRP
class Library {
    void addBook() { }
    void registerMember() { }
    void issueBook() { }
    void generateReport() { }
    void sendEmail() { }  // Too many responsibilities!
}
```

**Why it matters:**
- Easy to understand
- Easy to test
- Easy to maintain
- Changes in one area don't affect others

---

### 2. **Open/Closed Principle**

**Open for extension, closed for modification:**

```java
// ✅ Can add new features without changing existing code
public class BookService {
    // Existing methods stay unchanged
    public void addBook(Book book) { }
    
    // New feature - just add new method
    public List<Book> findBooksByGenre(String genre) {
        // New functionality
    }
}
```

---

### 3. **Liskov Substitution Principle**

**Subtypes must be substitutable for base types:**

```java
// If we extend Book class
class EBook extends Book {
    // Must work everywhere Book works
    // No breaking changes
}
```

---

### 4. **Interface Segregation Principle**

**Many specific interfaces > One general interface:**

```java
// ✅ GOOD - Focused methods
interface BookOperations {
    void addBook(Book book);
    Book findBook(long id);
}

interface MemberOperations {
    void registerMember(Member member);
    Member findMember(long id);
}

// ❌ BAD - Fat interface
interface LibraryOperations {
    void addBook();
    void addMember();
    void issueBook();
    void generateReport();
    void sendEmail();
    void manageInventory();  // Too many unrelated methods!
}
```

---

### 5. **Dependency Inversion Principle**

**Depend on abstractions, not concretions:**

```java
// ✅ GOOD - LibraryService depends on services (injected)
public class LibraryService {
    private BookService bookService;      // Dependency
    private MemberService memberService;  // Dependency
    
    // Constructor injection
    public LibraryService(BookService bookService, MemberService memberService) {
        this.bookService = bookService;
        this.memberService = memberService;
    }
}

// ❌ BAD - Creating dependencies inside
public class LibraryService {
    private BookService bookService = new BookService();  // Tight coupling!
}
```

---

## 🔄 How Services Work Together

### Example: Issue Book Flow

```
User Request: Issue book ID 1 to member ID 1

LibraryService.issueBook(1, 1)
    ↓
1. BookService.findBookById(1)        → Get book details
    ↓
2. MemberService.findMemberById(1)    → Get member details
    ↓
3. Validate: Is book available?
    ↓
4. Validate: Member already has this book?
    ↓
5. Book.decrementCopy()               → Update book state
    ↓
6. Member.addBorrowedBook(1)          → Update member state
    ↓
✅ Success!
```

**Key Point:** LibraryService **coordinates** but doesn't do everything itself!

---

## 📊 Data Structures Used

### BookService
```java
HashMap<Long, Book> books;
```
**Why HashMap?**
- O(1) lookup by book ID
- Fast search operations
- Unique book IDs guaranteed

### MemberService
```java
HashMap<Long, Member> members;
```
**Why HashMap?**
- O(1) lookup by member ID
- Fast member retrieval during issue/return
- Unique member IDs

---

## 🎓 Interview Talking Points

### Q: "Why separate BookService and MemberService?"

**Answer:**
"Following Single Responsibility Principle:
- BookService focuses ONLY on book operations
- MemberService focuses ONLY on member operations
- This makes code:
  - Easier to test (mock one service at a time)
  - Easier to maintain (changes isolated)
  - Easier to understand (clear boundaries)
  - Reusable (can use BookService in other contexts)"

---

### Q: "Why do you need LibraryService if you have BookService and MemberService?"

**Answer:**
"LibraryService is the **coordinator**:
- Issue book needs BOTH book and member data
- It orchestrates the flow:
  1. Get book from BookService
  2. Get member from MemberService
  3. Validate both
  4. Update both
- This is **Separation of Concerns**
- Each service has its own responsibility
- LibraryService brings them together for complex operations"

---

### Q: "How does this follow SOLID principles?"

**Answer:**
"1. **SRP**: Each service has one job
2. **OCP**: Can add features without changing existing code
3. **LSP**: Models can be extended safely
4. **ISP**: Focused methods, no fat interfaces
5. **DIP**: LibraryService depends on injected services, not concrete implementations"

---

## 🚀 How to Run

```bash
# Compile
javac LibrarayManagementSystem/Main.java

# Run
java LibrarayManagementSystem.Main
```

---

## ✅ Features Implemented

- ✅ Add books
- ✅ Register members
- ✅ Issue books
- ✅ Return books
- ✅ Search books (by title, author)
- ✅ Display all books
- ✅ Display available books
- ✅ Display member's borrowed books
- ✅ Library statistics
- ✅ Exception handling
- ✅ Input validation

---

## 🎯 Key Takeaways

1. **Separation of Concerns** - Each class has clear responsibility
2. **Loose Coupling** - Services are independent
3. **High Cohesion** - Related operations grouped together
4. **Dependency Injection** - Services injected, not created
5. **Clean Architecture** - Easy to understand and maintain

---

**This is production-ready code following industry best practices! 💪**

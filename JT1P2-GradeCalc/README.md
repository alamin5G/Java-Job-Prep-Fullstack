# 🎓 Student Grade Calculator

## 📋 Project Overview

A comprehensive **Student Grade Management System** built in Java that demonstrates core OOP concepts, Collections Framework, and Exception Handling.

---

## ✨ Features

### Core Functionalities:
- ✅ **Add New Students** - Register students with unique IDs
- ✅ **Grade Management** - Add subject-wise grades for each student
- ✅ **Automatic Calculations** - Calculate average and letter grades
- ✅ **View Student Details** - Complete report card with all grades
- ✅ **Display All Students** - Sorted by Student ID (TreeMap feature)
- ✅ **Top Performers** - Rank students by performance
- ✅ **Class Statistics** - Overall class metrics and analytics
- ✅ **Remove Students** - Delete student records with confirmation
- ✅ **Exception Handling** - Robust error handling for invalid grades

---

## 🎯 Learning Objectives Demonstrated

### 1. **Inheritance (IS-A Relationship)**
```java
Person (Parent)
   ↓
Student (Child)
```
- `Person` class: Base class with common attributes (name)
- `Student` class: Extends Person, adds student-specific features (ID, grades)

### 2. **Collections Framework**
- **TreeMap<Integer, Student>**: Automatically sorts students by ID
- **HashMap<String, Double>**: Stores subject-wise grades for each student
- Demonstrates different collection types and their use cases

### 3. **Exception Handling**
- **Custom Exception**: `InvalidGradeException`
- **Validation**: Grades must be between 0-100
- **Try-Catch**: Proper error handling throughout

### 4. **Encapsulation**
- Private fields with public getters/setters
- Data hiding and controlled access

### 5. **Polymorphism**
- Override `toString()` method in Student class
- Method overriding from Person to Student

---

## 🏗️ Project Structure

```
JT1P2-GradeCalc/
├── src/
│   └── StudentGradeCalculator/
│       ├── Main.java                          # Entry point & UI
│       ├── model/
│       │   ├── Person.java                    # Base class
│       │   └── Student.java                   # Student class (inherits Person)
│       ├── services/
│       │   └── GradeCalculation.java          # Business logic & TreeMap operations
│       └── exception/
│           └── InvalidGradeException.java     # Custom exception
└── README.md
```

---

## 🚀 How to Run

### Prerequisites:
- Java JDK 8 or higher
- IntelliJ IDEA (or any Java IDE)

### Steps:
1. **Open the project** in IntelliJ IDEA
2. **Navigate** to `src/StudentGradeCalculator/Main.java`
3. **Run** the Main class
4. **Interact** with the menu-driven interface

### Command Line:
```bash
# Compile
javac -d bin src/StudentGradeCalculator/*.java src/StudentGradeCalculator/model/*.java src/StudentGradeCalculator/services/*.java src/StudentGradeCalculator/exception/*.java

# Run
java -cp bin StudentGradeCalculator.Main
```

---

## 📊 Sample Output

```
╔════════════════════════════════════════════════════════════╗
║        🎓 STUDENT GRADE CALCULATOR SYSTEM 🎓              ║
╚════════════════════════════════════════════════════════════╝

📦 Loading sample data...

✅ Sample data loaded successfully!
   Total students: 4

═══════════════════════════════════════════════════════════
                    📚 MAIN MENU 📚
═══════════════════════════════════════════════════════════
  1. ➕ Add New Student
  2. 📝 Add Grade to Student
  3. 👤 View Student Details
  4. 📋 Display All Students (Sorted by ID)
  5. ➖ Remove Student
  6. 🏆 Display Top Performers
  7. 📊 View Class Statistics
  0. 🚪 Exit
═══════════════════════════════════════════════════════════
```

---

## 🎯 Grade Scale

| Average     | Letter Grade |
|-------------|--------------|
| 90 - 100    | A+           |
| 85 - 89     | A            |
| 80 - 84     | A-           |
| 75 - 79     | B+           |
| 70 - 74     | B            |
| 65 - 69     | B-           |
| 60 - 64     | C+           |
| 55 - 59     | C            |
| 50 - 54     | D            |
| Below 50    | F            |

---

## 🧪 Test Cases Covered

### ✅ Valid Operations:
- Adding students with valid IDs
- Adding grades between 0-100
- Viewing student details
- Calculating averages and letter grades
- Displaying sorted student lists
- Removing existing students

### ❌ Exception Handling:
- **InvalidGradeException**: Grade < 0 or > 100
- **Student Not Found**: Invalid student ID
- **Input Validation**: Empty names, invalid numbers
- **Duplicate ID**: Student already exists

---

## 💡 Key Concepts Demonstrated

### TreeMap Benefits:
- **Automatic Sorting**: Students always sorted by ID
- **O(log n)**: Efficient operations
- **NavigableMap**: Additional navigation methods

### Real-World Application:
- 🏫 **School Management Systems**
- 📊 **Grade Tracking**
- 📈 **Performance Analytics**
- 🎓 **Student Information Systems**

---

## 🔧 Possible Enhancements

1. **File Persistence**: Save/load data from files
2. **Search Functionality**: Search by name or grade range
3. **Grade Distribution**: Histogram of letter grades
4. **Subject Analytics**: Average per subject across all students
5. **Export Reports**: Generate PDF/CSV reports
6. **Attendance Tracking**: Add attendance management
7. **GUI Interface**: JavaFX or Swing UI
8. **Database Integration**: Connect to MySQL/PostgreSQL

---

## 📚 Learning Resources

- [Java Collections Framework](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/)
- [Exception Handling in Java](https://docs.oracle.com/javase/tutorial/essential/exceptions/)
- [Inheritance in Java](https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html)
- [TreeMap Documentation](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html)

---

## 👨‍💻 Author

**Java Full-Stack Job Preparation Course**
- Topic 1: Core Java Fundamentals
- Project 2: Student Grade Calculator

---

## 📝 License

This project is part of educational material for Java learning and interview preparation.

---

## ⭐ Key Takeaways

✅ **Inheritance**: Learned how to extend classes and reuse code  
✅ **Collections**: Mastered TreeMap for sorted data management  
✅ **Exceptions**: Implemented custom exceptions and validation  
✅ **OOP Principles**: Applied encapsulation, inheritance, and polymorphism  
✅ **Real-World Design**: Built a practical, usable application  

---

**Happy Coding! 🚀**


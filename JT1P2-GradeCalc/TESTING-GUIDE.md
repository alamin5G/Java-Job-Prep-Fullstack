# 🧪 Testing Guide - Student Grade Calculator

## Quick Test Scenarios

### Scenario 1: View Pre-loaded Data
**Steps:**
1. Run the application
2. Choose option `4` (Display All Students)
3. Observe 4 students sorted by ID (101, 102, 103, 104)

**Expected Output:**
- Students displayed in ascending order of ID
- Each student shows average and letter grade
- Subject-wise grades listed for each

---

### Scenario 2: Add a New Student
**Steps:**
1. Choose option `1` (Add New Student)
2. Enter Student ID: `105`
3. Enter Name: `Eve Wilson`
4. Success message displayed

**Expected Output:**
```
✅ Student added successfully!
```

---

### Scenario 3: Add Grades to Student
**Steps:**
1. Choose option `2` (Add Grade to Student)
2. Enter Student ID: `105`
3. Enter Subject: `Mathematics`
4. Enter Grade: `88.5`
5. Repeat for more subjects

**Expected Output:**
```
✅ Grade added successfully for Mathematics
📊 Updated Average: 88.50
📊 Letter Grade: A
```

---

### Scenario 4: Test Invalid Grade (Exception Handling)
**Steps:**
1. Choose option `2` (Add Grade to Student)
2. Enter Student ID: `101`
3. Enter Subject: `English`
4. Enter Grade: `105` (invalid - over 100)

**Expected Output:**
```
❌ Invalid grade: Grade must be between 0 and 100. Received: 105.0
```

---

### Scenario 5: Test Negative Grade (Exception Handling)
**Steps:**
1. Choose option `2` (Add Grade to Student)
2. Enter Student ID: `101`
3. Enter Subject: `English`
4. Enter Grade: `-10` (invalid - negative)

**Expected Output:**
```
❌ Invalid grade: Grade must be between 0 and 100. Received: -10.0
```

---

### Scenario 6: View Individual Student Details
**Steps:**
1. Choose option `3` (View Student Details)
2. Enter Student ID: `101`

**Expected Output:**
```
═══════════════════════════════════════════════════════════
📄 STUDENT REPORT CARD
═══════════════════════════════════════════════════════════
Student ID   : 101
Student Name : Alice Johnson
------------------------------------------------------------
SUBJECT-WISE GRADES:
------------------------------------------------------------
  Mathematics                    : 92.00
  Physics                        : 88.00
  Chemistry                      : 95.00
------------------------------------------------------------
  AVERAGE                        : 91.67
  LETTER GRADE                   : A+
═══════════════════════════════════════════════════════════
```

---

### Scenario 7: View Top Performers
**Steps:**
1. Choose option `6` (Display Top Performers)
2. Enter count: `3`

**Expected Output:**
- Top 3 students ranked by average grade
- Highest scorer first
- Students with their averages and letter grades

---

### Scenario 8: View Class Statistics
**Steps:**
1. Choose option `7` (View Class Statistics)

**Expected Output:**
```
════════════════════════════════════════════════════════════
📈 CLASS STATISTICS
════════════════════════════════════════════════════════════
Total Students: 4
Class Average: 87.75
Highest Scorer: Charlie Brown (95.00)
Lowest Scorer: Bob Smith (80.00)
════════════════════════════════════════════════════════════
```

---

### Scenario 9: Test Non-Existent Student
**Steps:**
1. Choose option `3` (View Student Details)
2. Enter Student ID: `999`

**Expected Output:**
```
❌ Student with ID 999 not found!
```

---

### Scenario 10: Remove Student with Confirmation
**Steps:**
1. Choose option `5` (Remove Student)
2. Enter Student ID: `102`
3. Confirm with: `yes`

**Expected Output:**
```
⚠️ Are you sure you want to remove Bob Smith? (yes/no): yes
✅ Student removed: Bob Smith
```

---

### Scenario 11: Cancel Student Removal
**Steps:**
1. Choose option `5` (Remove Student)
2. Enter Student ID: `101`
3. Enter: `no`

**Expected Output:**
```
❌ Removal cancelled.
```

---

### Scenario 12: Test Invalid Input Handling
**Steps:**
1. Choose option `1` (Add New Student)
2. Enter Student ID: `abc` (invalid - not a number)

**Expected Output:**
```
❌ Invalid Student ID! Please enter a number.
```

---

## Automated Test Checklist

### ✅ Inheritance Testing
- [ ] Student correctly inherits from Person
- [ ] `getName()` method accessible in Student
- [ ] Constructor chaining works properly

### ✅ Collections Testing (TreeMap)
- [ ] Students sorted by ID automatically
- [ ] New students inserted in correct order
- [ ] TreeMap operations: get, put, remove work correctly

### ✅ Exception Handling Testing
- [ ] InvalidGradeException thrown for grade < 0
- [ ] InvalidGradeException thrown for grade > 100
- [ ] Student not found handled gracefully
- [ ] Invalid input types handled (NumberFormatException)

### ✅ Business Logic Testing
- [ ] Average calculation correct
- [ ] Letter grade assignment correct
- [ ] Subject grades stored properly (HashMap)
- [ ] Top performers sorted correctly
- [ ] Class statistics calculated accurately

### ✅ Edge Cases
- [ ] Empty student list handled
- [ ] Student with no grades handled
- [ ] Duplicate student ID prevented
- [ ] Empty name validation
- [ ] Zero students in statistics

---

## Sample Data Pre-loaded

| ID  | Name           | Subjects (Grades)                           | Average | Grade |
|-----|----------------|---------------------------------------------|---------|-------|
| 101 | Alice Johnson  | Math(92), Physics(88), Chemistry(95)        | 91.67   | A+    |
| 102 | Bob Smith      | Math(78), Physics(82), Chemistry(80)        | 80.00   | A-    |
| 103 | Charlie Brown  | Math(95), Physics(93), Chemistry(97)        | 95.00   | A+    |
| 104 | Diana Prince   | Math(85), Physics(87), Chemistry(86)        | 86.00   | A     |

---

## Performance Testing

### Expected Time Complexities:
- **Add Student**: O(log n) - TreeMap insertion
- **Get Student**: O(log n) - TreeMap lookup
- **Remove Student**: O(log n) - TreeMap deletion
- **Display All**: O(n) - Iterate through all students
- **Top Performers**: O(n log n) - Sorting by average

---

## Integration Test Flow

```
Start Application
    ↓
Load Sample Data (4 students)
    ↓
Add New Student (ID: 105)
    ↓
Add Grades (Math: 90, Physics: 85)
    ↓
View Student Details (ID: 105)
    ↓
Test Invalid Grade (Grade: 150) → Exception
    ↓
View All Students (5 students, sorted)
    ↓
View Top 3 Performers
    ↓
View Class Statistics
    ↓
Remove Student (ID: 102)
    ↓
Verify Removal (4 students remaining)
    ↓
Exit Application
```

---

## Manual Testing Commands

```bash
# Compile
javac StudentGradeCalculator/Main.java StudentGradeCalculator/model/*.java StudentGradeCalculator/services/*.java StudentGradeCalculator/exception/*.java

# Run
java StudentGradeCalculator.Main

# Test sequence (enter these choices):
4   # View all students
1   # Add student: 105, "Test Student"
2   # Add grade: 105, "Math", 90
2   # Add grade: 105, "Physics", 150 (should fail)
3   # View details: 105
6   # Top performers: 3
7   # Class statistics
5   # Remove: 105, yes
0   # Exit
```

---

## Regression Testing Checklist

After any code changes, verify:
- [ ] Application starts without errors
- [ ] Sample data loads correctly
- [ ] All menu options work
- [ ] Exception handling still functional
- [ ] TreeMap sorting maintained
- [ ] Calculations remain accurate
- [ ] No memory leaks or infinite loops

---

**Testing Status: ✅ READY FOR TESTING**

Run the application and follow the scenarios above to verify all functionality!


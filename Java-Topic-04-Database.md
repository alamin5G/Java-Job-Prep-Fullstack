# 📌 Topic 4: Database & SQL Mastery

## 🎯 Learning Objectives
Master databases and SQL - essential for any backend developer. Every application needs data storage!

---

## 🔷 Part 1: Database Fundamentals

### Concepts to Master:

- [ ] **What is a Database?**
  ```
  Database = Organized collection of data
  
  Types:
  1. Relational (SQL): MySQL, PostgreSQL, Oracle
  2. NoSQL: MongoDB, Redis, Cassandra
  
  For Java Backend: Focus on SQL databases
  ```
  
  **Real-life Example:** 📚 **Library**
  - Database = Entire library
  - Table = Bookshelf (category)
  - Row = Individual book
  - Column = Book properties (title, author, ISBN)

- [ ] **RDBMS Concepts**
  ```
  Key Concepts:
  - Table: Collection of related data
  - Row (Record/Tuple): Single entry
  - Column (Field/Attribute): Data property
  - Primary Key: Unique identifier
  - Foreign Key: Reference to another table
  - Index: Fast data retrieval
  ```
  
  **Example Schema:**
  ```sql
  -- Students Table
  CREATE TABLE students (
      student_id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(100) NOT NULL,
      email VARCHAR(100) UNIQUE,
      age INT CHECK (age >= 18),
      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
  );
  
  -- Courses Table
  CREATE TABLE courses (
      course_id INT PRIMARY KEY AUTO_INCREMENT,
      course_name VARCHAR(100) NOT NULL,
      credits INT
  );
  
  -- Enrollments Table (Many-to-Many)
  CREATE TABLE enrollments (
      enrollment_id INT PRIMARY KEY AUTO_INCREMENT,
      student_id INT,
      course_id INT,
      enrollment_date DATE,
      FOREIGN KEY (student_id) REFERENCES students(student_id),
      FOREIGN KEY (course_id) REFERENCES courses(course_id)
  );
  ```

---

## 🔷 Part 2: SQL Queries

### Concepts to Master:

- [ ] **Basic CRUD Operations**
  ```sql
  -- CREATE (INSERT)
  INSERT INTO students (name, email, age) 
  VALUES ('John Doe', 'john@example.com', 20);
  
  -- READ (SELECT)
  SELECT * FROM students;
  SELECT name, email FROM students WHERE age > 18;
  
  -- UPDATE
  UPDATE students 
  SET email = 'newemail@example.com' 
  WHERE student_id = 1;
  
  -- DELETE
  DELETE FROM students WHERE student_id = 1;
  ```
  
  **Real-life Use Case:** 👤 **User Management**
  - INSERT = Register new user
  - SELECT = Login / View profile
  - UPDATE = Edit profile
  - DELETE = Delete account

- [ ] **Filtering & Sorting**
  ```sql
  -- WHERE clause
  SELECT * FROM students WHERE age >= 20;
  SELECT * FROM students WHERE name LIKE 'J%';  -- Starts with J
  SELECT * FROM students WHERE age BETWEEN 18 AND 25;
  SELECT * FROM students WHERE email IN ('a@test.com', 'b@test.com');
  
  -- ORDER BY
  SELECT * FROM students ORDER BY name ASC;
  SELECT * FROM students ORDER BY age DESC;
  
  -- LIMIT
  SELECT * FROM students LIMIT 10;  -- First 10 records
  SELECT * FROM students LIMIT 10 OFFSET 20;  -- Pagination
  ```

- [ ] **Aggregate Functions**
  ```sql
  -- COUNT
  SELECT COUNT(*) FROM students;
  SELECT COUNT(*) FROM students WHERE age > 20;
  
  -- SUM, AVG, MIN, MAX
  SELECT AVG(age) FROM students;
  SELECT MIN(age), MAX(age) FROM students;
  
  -- GROUP BY
  SELECT age, COUNT(*) as count 
  FROM students 
  GROUP BY age;
  
  -- HAVING (filter groups)
  SELECT age, COUNT(*) as count 
  FROM students 
  GROUP BY age 
  HAVING count > 5;
  ```
  
  **Real-life Use Case:** 📊 **Analytics Dashboard**
  - Total users, average age, etc.

- [ ] **JOINS**
  ```sql
  -- INNER JOIN (matching records from both tables)
  SELECT students.name, courses.course_name
  FROM students
  INNER JOIN enrollments ON students.student_id = enrollments.student_id
  INNER JOIN courses ON enrollments.course_id = courses.course_id;
  
  -- LEFT JOIN (all from left table)
  SELECT students.name, courses.course_name
  FROM students
  LEFT JOIN enrollments ON students.student_id = enrollments.student_id
  LEFT JOIN courses ON enrollments.course_id = courses.course_id;
  
  -- RIGHT JOIN (all from right table)
  -- FULL OUTER JOIN (all from both)
  ```
  
  **Real-life Example:** 🎓 **Student-Course Enrollment**
  - Find all students and their enrolled courses
  - Find students who haven't enrolled (LEFT JOIN)

---

## 🔷 Part 3: Database Design

### Concepts to Master:

- [ ] **Normalization**
  ```
  Purpose: Reduce redundancy, improve data integrity
  
  1NF (First Normal Form):
  - Atomic values (no lists in cells)
  - Each row unique
  
  2NF (Second Normal Form):
  - 1NF + No partial dependencies
  
  3NF (Third Normal Form):
  - 2NF + No transitive dependencies
  ```
  
  **Example:**
  ```sql
  -- Bad (Denormalized)
  CREATE TABLE orders (
      order_id INT,
      customer_name VARCHAR(100),
      customer_email VARCHAR(100),
      product_name VARCHAR(100),
      product_price DECIMAL
  );
  -- Problem: Customer data repeated for each order
  
  -- Good (Normalized)
  CREATE TABLE customers (
      customer_id INT PRIMARY KEY,
      name VARCHAR(100),
      email VARCHAR(100)
  );
  
  CREATE TABLE products (
      product_id INT PRIMARY KEY,
      name VARCHAR(100),
      price DECIMAL
  );
  
  CREATE TABLE orders (
      order_id INT PRIMARY KEY,
      customer_id INT,
      product_id INT,
      order_date DATE,
      FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
      FOREIGN KEY (product_id) REFERENCES products(product_id)
  );
  ```

- [ ] **Relationships**
  ```
  1. One-to-One: User ↔ Profile
  2. One-to-Many: Author → Books
  3. Many-to-Many: Students ↔ Courses
  ```
  
  **Implementation:**
  ```sql
  -- One-to-Many
  CREATE TABLE authors (
      author_id INT PRIMARY KEY,
      name VARCHAR(100)
  );
  
  CREATE TABLE books (
      book_id INT PRIMARY KEY,
      title VARCHAR(200),
      author_id INT,
      FOREIGN KEY (author_id) REFERENCES authors(author_id)
  );
  
  -- Many-to-Many (Junction Table)
  CREATE TABLE students_courses (
      student_id INT,
      course_id INT,
      PRIMARY KEY (student_id, course_id),
      FOREIGN KEY (student_id) REFERENCES students(student_id),
      FOREIGN KEY (course_id) REFERENCES courses(course_id)
  );
  ```

- [ ] **Indexes**
  ```sql
  -- Create index for faster queries
  CREATE INDEX idx_email ON students(email);
  CREATE INDEX idx_name ON students(name);
  
  -- Composite index
  CREATE INDEX idx_name_age ON students(name, age);
  
  -- Unique index
  CREATE UNIQUE INDEX idx_unique_email ON students(email);
  ```
  
  **Real-life Example:** 📖 **Book Index**
  - Without index: Read entire book to find topic
  - With index: Jump directly to page

---

## 🔷 Part 4: Advanced SQL

### Concepts to Master:

- [ ] **Subqueries**
  ```sql
  -- Find students older than average age
  SELECT name, age 
  FROM students 
  WHERE age > (SELECT AVG(age) FROM students);
  
  -- Find students enrolled in 'Java' course
  SELECT name 
  FROM students 
  WHERE student_id IN (
      SELECT student_id 
      FROM enrollments 
      WHERE course_id = (
          SELECT course_id 
          FROM courses 
          WHERE course_name = 'Java'
      )
  );
  ```

- [ ] **Transactions**
  ```sql
  -- Bank transfer example
  START TRANSACTION;
  
  UPDATE accounts SET balance = balance - 100 WHERE account_id = 1;
  UPDATE accounts SET balance = balance + 100 WHERE account_id = 2;
  
  -- If both succeed
  COMMIT;
  
  -- If any fails
  ROLLBACK;
  ```
  
  **ACID Properties:**
  - **A**tomicity: All or nothing
  - **C**onsistency: Valid state always
  - **I**solation: Concurrent transactions don't interfere
  - **D**urability: Committed data persists

- [ ] **Views**
  ```sql
  -- Create virtual table
  CREATE VIEW student_courses AS
  SELECT s.name, c.course_name
  FROM students s
  JOIN enrollments e ON s.student_id = e.student_id
  JOIN courses c ON e.course_id = c.course_id;
  
  -- Use like a table
  SELECT * FROM student_courses WHERE name = 'John';
  ```

---

## 💻 Practice Projects (2)

- [ ] **Project 1: School Management Database**
  - Tables: Students, Teachers, Courses, Enrollments
  - Complex queries with joins
  - **Skills:** Schema design, Relationships, Queries

- [ ] **Project 2: E-commerce Database**
  - Tables: Users, Products, Orders, Order_Items
  - Transactions for orders
  - **Skills:** Normalization, Transactions, Indexes

---

## ✅ Move-On Criteria

- [ ] ✔ **Database schema design করতে পারবেন**
- [ ] ✔ **Complex SQL queries লিখতে পারবেন**
- [ ] ✔ **JOINS বুঝবেন এবং use করতে পারবেন**
- [ ] ✔ **Normalization apply করতে পারবেন**
- [ ] ✔ **2টা database projects complete করবেন**

---

**Real-World Applications:**
- 🛒 E-commerce order management
- 🏦 Banking transactions
- 📊 Analytics and reporting
- 👥 User management systems
- 📝 Content management

**Next:** [Spring Boot Framework →](Java-Topic-05-Spring-Boot.md)

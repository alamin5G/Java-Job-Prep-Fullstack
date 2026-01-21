package StudentGradeCalculator;

import StudentGradeCalculator.exception.InvalidGradeException;
import StudentGradeCalculator.model.Student;
import StudentGradeCalculator.services.GradeCalculation;

import java.util.Scanner;

/**
 * Student Grade Calculator
 * Features:
 * - Inheritance: Person → Student
 * - Collections: TreeMap for sorted grades (by student ID)
 * - Custom exceptions for invalid grades
 * - Complete grade management system
 */
public class Main {
    private static GradeCalculation gradeCalculation = new GradeCalculation();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        🎓 STUDENT GRADE CALCULATOR SYSTEM 🎓              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");

        // Add sample data for demonstration
        loadSampleData();

        boolean running = true;

        while (running) {
            displayMenu();

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addNewStudent();
                        break;
                    case 2:
                        addGradeToStudent();
                        break;
                    case 3:
                        viewStudentDetails();
                        break;
                    case 4:
                        gradeCalculation.displayAllStudents();
                        break;
                    case 5:
                        removeStudent();
                        break;
                    case 6:
                        displayTopPerformers();
                        break;
                    case 7:
                        gradeCalculation.displayStatistics();
                        break;
                    case 0:
                        System.out.println("\n👋 Thank you for using Student Grade Calculator!");
                        System.out.println("   Goodbye! 🎓");
                        running = false;
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please try again.");
                }

                if (running && choice != 0) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number!");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("                    📚 MAIN MENU 📚");
        System.out.println("═".repeat(60));
        System.out.println("  1. ➕ Add New Student");
        System.out.println("  2. 📝 Add Grade to Student");
        System.out.println("  3. 👤 View Student Details");
        System.out.println("  4. 📋 Display All Students (Sorted by ID)");
        System.out.println("  5. ➖ Remove Student");
        System.out.println("  6. 🏆 Display Top Performers");
        System.out.println("  7. 📊 View Class Statistics");
        System.out.println("  0. 🚪 Exit");
        System.out.println("═".repeat(60));
        System.out.print("Enter your choice: ");
    }

    private static void addNewStudent() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("➕ ADD NEW STUDENT");
        System.out.println("─".repeat(60));

        try {
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            // Check if student already exists
            if (gradeCalculation.getStudent(studentId) != null) {
                System.out.println("❌ Student with ID " + studentId + " already exists!");
                return;
            }

            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("❌ Name cannot be empty!");
                return;
            }

            Student student = new Student(name, studentId);
            gradeCalculation.addStudent(student);

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid Student ID! Please enter a number.");
        }
    }

    private static void addGradeToStudent() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("📝 ADD GRADE TO STUDENT");
        System.out.println("─".repeat(60));

        try {
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            Student student = gradeCalculation.getStudent(studentId);
            if (student == null) {
                System.out.println("❌ Student with ID " + studentId + " not found!");
                return;
            }

            System.out.println("Student: " + student.getName());

            System.out.print("Enter Subject Name: ");
            String subject = scanner.nextLine();

            if (subject.trim().isEmpty()) {
                System.out.println("❌ Subject name cannot be empty!");
                return;
            }

            System.out.print("Enter Grade (0-100): ");
            double grade = Double.parseDouble(scanner.nextLine());

            gradeCalculation.addGradeToStudent(studentId, subject, grade);

            // Display updated average
            System.out.println("📊 Updated Average: " + String.format("%.2f", student.calculateAverage()));
            System.out.println("📊 Letter Grade: " + student.getLetterGrade());

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input! Please enter valid numbers.");
        } catch (InvalidGradeException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private static void viewStudentDetails() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("👤 VIEW STUDENT DETAILS");
        System.out.println("─".repeat(60));

        try {
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            Student student = gradeCalculation.getStudent(studentId);
            if (student == null) {
                System.out.println("❌ Student with ID " + studentId + " not found!");
                return;
            }

            System.out.println("\n" + "═".repeat(60));
            System.out.println("📄 STUDENT REPORT CARD");
            System.out.println("═".repeat(60));
            System.out.println("Student ID   : " + student.getStudentId());
            System.out.println("Student Name : " + student.getName());
            System.out.println("-".repeat(60));

            if (student.getSubjectGrades().isEmpty()) {
                System.out.println("⚠️ No grades recorded yet!");
            } else {
                System.out.println("SUBJECT-WISE GRADES:");
                System.out.println("-".repeat(60));
                for (var entry : student.getSubjectGrades().entrySet()) {
                    System.out.println(String.format("  %-30s : %.2f", entry.getKey(), entry.getValue()));
                }
                System.out.println("-".repeat(60));
                System.out.println(String.format("  %-30s : %.2f", "AVERAGE", student.calculateAverage()));
                System.out.println(String.format("  %-30s : %s", "LETTER GRADE", student.getLetterGrade()));
            }
            System.out.println("═".repeat(60));

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid Student ID! Please enter a number.");
        }
    }

    private static void removeStudent() {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("➖ REMOVE STUDENT");
        System.out.println("─".repeat(60));

        try {
            System.out.print("Enter Student ID to remove: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            Student student = gradeCalculation.getStudent(studentId);
            if (student != null) {
                System.out.print("⚠️ Are you sure you want to remove " + student.getName() + "? (yes/no): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("yes")) {
                    gradeCalculation.removeStudent(studentId);
                } else {
                    System.out.println("❌ Removal cancelled.");
                }
            } else {
                System.out.println("❌ Student with ID " + studentId + " not found!");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid Student ID! Please enter a number.");
        }
    }

    private static void displayTopPerformers() {
        System.out.println("\n" + "─".repeat(60));
        System.out.print("Enter number of top performers to display: ");

        try {
            int count = Integer.parseInt(scanner.nextLine());
            if (count <= 0) {
                System.out.println("❌ Please enter a positive number!");
                return;
            }
            gradeCalculation.displayTopPerformers(count);
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid number! Please enter a valid number.");
        }
    }

    private static void loadSampleData() {
        System.out.println("\n📦 Loading sample data...\n");

        try {
            // Create sample students
            Student s1 = new Student("Alice Johnson", 101);
            s1.addGrade("Mathematics", 92.0);
            s1.addGrade("Physics", 88.0);
            s1.addGrade("Chemistry", 95.0);
            gradeCalculation.addStudent(s1);

            Student s2 = new Student("Bob Smith", 102);
            s2.addGrade("Mathematics", 78.0);
            s2.addGrade("Physics", 82.0);
            s2.addGrade("Chemistry", 80.0);
            gradeCalculation.addStudent(s2);

            Student s3 = new Student("Charlie Brown", 103);
            s3.addGrade("Mathematics", 95.0);
            s3.addGrade("Physics", 93.0);
            s3.addGrade("Chemistry", 97.0);
            gradeCalculation.addStudent(s3);

            Student s4 = new Student("Diana Prince", 104);
            s4.addGrade("Mathematics", 85.0);
            s4.addGrade("Physics", 87.0);
            s4.addGrade("Chemistry", 86.0);
            gradeCalculation.addStudent(s4);

            System.out.println("✅ Sample data loaded successfully!");
            System.out.println("   Total students: " + gradeCalculation.getTotalStudents());

        } catch (Exception e) {
            System.out.println("⚠️ Error loading sample data: " + e.getMessage());
        }
    }
}
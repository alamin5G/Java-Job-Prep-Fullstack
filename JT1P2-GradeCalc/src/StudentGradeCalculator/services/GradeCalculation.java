package StudentGradeCalculator.services;

import StudentGradeCalculator.exception.InvalidGradeException;
import StudentGradeCalculator.model.Student;

import java.util.TreeMap;
import java.util.Map;

public class GradeCalculation {
    // TreeMap automatically sorts by studentId (key)
    private TreeMap<Integer, Student> students;

    public GradeCalculation() {
        this.students = new TreeMap<>();
    }

    // Add a new student
    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
        System.out.println("✅ Student added successfully!");
    }

    // Add grade to a student
    public void addGradeToStudent(int studentId, String subject, double grade) throws InvalidGradeException {
        // Validate grade
        if (grade < 0 || grade > 100) {
            throw new InvalidGradeException("Grade must be between 0 and 100. Received: " + grade);
        }

        Student student = students.get(studentId);
        if (student == null) {
            throw new InvalidGradeException("Student with ID " + studentId + " not found!");
        }

        student.addGrade(subject, grade);
        System.out.println("✅ Grade added successfully for " + subject);
    }

    // Get student by ID
    public Student getStudent(int studentId) {
        return students.get(studentId);
    }

    // Remove student
    public boolean removeStudent(int studentId) {
        Student removed = students.remove(studentId);
        if (removed != null) {
            System.out.println("✅ Student removed: " + removed.getName());
            return true;
        }
        System.out.println("❌ Student not found!");
        return false;
    }

    // Display all students (sorted by ID due to TreeMap)
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students in the system!");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("📊 ALL STUDENTS (Sorted by ID)");
        System.out.println("=".repeat(80));

        for (Student student : students.values()) {
            System.out.println(student);

            // Display subject-wise grades
            Map<String, Double> grades = student.getSubjectGrades();
            if (!grades.isEmpty()) {
                System.out.println("  Subjects:");
                for (Map.Entry<String, Double> entry : grades.entrySet()) {
                    System.out.println("    • " + entry.getKey() + ": " + entry.getValue());
                }
            }
            System.out.println("-".repeat(80));
        }
    }

    // Display top performers
    public void displayTopPerformers(int count) {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students in the system!");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("🏆 TOP " + count + " PERFORMERS");
        System.out.println("=".repeat(80));

        // Sort by average grade (descending)
        students.values().stream()
                .sorted((s1, s2) -> Double.compare(s2.calculateAverage(), s1.calculateAverage()))
                .limit(count)
                .forEach(student -> {
                    System.out.println(student);
                    System.out.println("-".repeat(80));
                });
    }

    // Calculate class average
    public double calculateClassAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }

        double sum = students.values().stream()
                .mapToDouble(Student::calculateAverage)
                .sum();

        return sum / students.size();
    }

    // Display statistics
    public void displayStatistics() {
        if (students.isEmpty()) {
            System.out.println("⚠️ No students in the system!");
            return;
        }

        System.out.println("\n" + "=".repeat(80));
        System.out.println("📈 CLASS STATISTICS");
        System.out.println("=".repeat(80));
        System.out.println("Total Students: " + students.size());
        System.out.println("Class Average: " + String.format("%.2f", calculateClassAverage()));

        // Find highest and lowest performers
        Student highest = students.values().stream()
                .max((s1, s2) -> Double.compare(s1.calculateAverage(), s2.calculateAverage()))
                .orElse(null);

        Student lowest = students.values().stream()
                .min((s1, s2) -> Double.compare(s1.calculateAverage(), s2.calculateAverage()))
                .orElse(null);

        if (highest != null) {
            System.out.println("Highest Scorer: " + highest.getName() +
                             " (" + String.format("%.2f", highest.calculateAverage()) + ")");
        }

        if (lowest != null) {
            System.out.println("Lowest Scorer: " + lowest.getName() +
                             " (" + String.format("%.2f", lowest.calculateAverage()) + ")");
        }

        System.out.println("=".repeat(80));
    }

    // Get total student count
    public int getTotalStudents() {
        return students.size();
    }
}

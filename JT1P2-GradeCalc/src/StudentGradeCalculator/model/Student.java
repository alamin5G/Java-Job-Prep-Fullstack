package StudentGradeCalculator.model;

import java.util.HashMap;
import java.util.Map;

public class Student extends Person {
    private int studentId;
    private Map<String, Double> subjectGrades; // Subject -> Grade

    public Student(String name, int studentId) {
        super(name);
        this.studentId = studentId;
        this.subjectGrades = new HashMap<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public void addGrade(String subject, double grade) {
        subjectGrades.put(subject, grade);
    }

    public Map<String, Double> getSubjectGrades() {
        return subjectGrades;
    }

    public double calculateAverage() {
        if (subjectGrades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;

        for (double grade : subjectGrades.values()) {
            sum += grade;
        }
        return sum / subjectGrades.size();
    }

    public String getLetterGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return "A+";
        else if (avg >= 85) return "A";
        else if (avg >= 80) return "A-";
        else if (avg >= 75) return "B+";
        else if (avg >= 70) return "B";
        else if (avg >= 65) return "B-";
        else if (avg >= 60) return "C+";
        else if (avg >= 55) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId +
               ", Name: " + getName() +
               ", Average: " + String.format("%.2f", calculateAverage()) +
               ", Grade: " + getLetterGrade();
    }
}

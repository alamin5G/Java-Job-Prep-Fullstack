package StudentGradeCalculator.exception;

public class InvalidGradeException extends  Exception {
    public InvalidGradeException(String message) {
        super("Invalid grade: " + message);
    }
}

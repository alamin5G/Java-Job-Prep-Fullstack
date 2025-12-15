package LibrarayManagementSystem.exception;

public class BookNotAvailableException extends  Exception {
    public BookNotAvailableException(String message) {
        super("Book: " + message);
    }
}

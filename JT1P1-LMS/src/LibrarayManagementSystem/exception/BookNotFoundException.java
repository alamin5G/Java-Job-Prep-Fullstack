package LibrarayManagementSystem.exception;

public class BookNotFoundException extends Exception {
    public BookNotFoundException(String message) {
        super("Book not found: " + message);
    }

}

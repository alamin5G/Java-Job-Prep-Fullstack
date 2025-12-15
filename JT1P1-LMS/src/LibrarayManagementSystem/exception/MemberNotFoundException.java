package LibrarayManagementSystem.exception;

public class MemberNotFoundException extends Exception {

    public MemberNotFoundException(String message) {
        super("Member not found: " + message);
    }
}

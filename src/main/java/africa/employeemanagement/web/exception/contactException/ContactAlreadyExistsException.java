package africa.semicolon.employeemanagement.web.exception.contactException;

public class ContactAlreadyExistsException extends RuntimeException {
    public ContactAlreadyExistsException(String message) {
        super(message);
    }
}

package africa.semicolon.employeemanagement.web.exception.contactException;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String unsuccessful) {
        super(unsuccessful);
    }
}

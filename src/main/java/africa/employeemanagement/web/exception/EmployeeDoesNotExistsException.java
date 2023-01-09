package africa.semicolon.employeemanagement.web.exception;

public class EmployeeDoesNotExistsException extends Throwable{
    public EmployeeDoesNotExistsException(String message) {
        super(message);
    }
}

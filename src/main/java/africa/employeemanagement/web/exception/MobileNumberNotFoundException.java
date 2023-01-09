package africa.semicolon.employeemanagement.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MobileNumberNotFoundException extends RuntimeException {
    public MobileNumberNotFoundException(String message) {
        super(message);
    }
}

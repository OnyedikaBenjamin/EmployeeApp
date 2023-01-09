package africa.semicolon.employeemanagement.web.exception.contactException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ContactNotFoundExceptionHandler {

    public ResponseEntity<Object> handleContactNotFoundRequestException(ContactNotFoundException exception){
        GeneralException ex = new GeneralException(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}

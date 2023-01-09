package africa.semicolon.employeemanagement.web.exception.contactException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class AlreadyExistHandler {

    public ResponseEntity<Object> handleContactAlreadyExist(ContactAlreadyExistsException ex){
        GeneralException exp = new GeneralException(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exp, HttpStatus.BAD_REQUEST);
    }
}

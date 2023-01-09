package africa.semicolon.employeemanagement.web.exception.contactException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class GeneralException {
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;
}

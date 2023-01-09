package africa.semicolon.employeemanagement.data.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MobileNumberResponse {
    private String countryCode;
    private String number;
    private Boolean isBlocked;
    private LocalDateTime dateCreated;
}

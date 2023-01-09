package africa.semicolon.employeemanagement.data.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MobileNumberRequest {
    @NotNull(message = "Field can not be null")
    private String countryCode;
    @NotNull(message = "Field can not be null")
    private String number;
}

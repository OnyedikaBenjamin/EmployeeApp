package africa.semicolon.employeemanagement.data.dto.request;

import africa.semicolon.employeemanagement.data.model.JobDepartment;
import africa.semicolon.employeemanagement.data.model.Level;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequest {
    @NotNull(message="Field can not be empty")
    private JobDepartment fieldName;
    @NotNull(message="Field can not be empty")
    private Level level;
}

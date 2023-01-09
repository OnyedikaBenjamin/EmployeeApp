package africa.semicolon.employeemanagement.data.dto.request;

import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String departmentName;

}

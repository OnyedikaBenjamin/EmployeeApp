package africa.semicolon.employeemanagement.data.dto.response;

import africa.semicolon.employeemanagement.data.model.Level;
import lombok.*;

import java.time.LocalDateTime;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String departmentName;
    private Integer employeeSalary;
    private Level employeeJobLevel;
    private Boolean isSuspended;
    private LocalDateTime creationDate;
}

package africa.semicolon.employeemanagement.data.dto.response;

import africa.semicolon.employeemanagement.data.model.Level;

import java.time.LocalDateTime;

public class DepartmentResponse {
    private Long id;
    private String fieldName;
    private String description;
    private Level employeeJobLevel;
    private LocalDateTime creationDate;
}

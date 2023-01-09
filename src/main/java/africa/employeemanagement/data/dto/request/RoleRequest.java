package africa.semicolon.employeemanagement.data.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RoleRequest {
    private Long employeeId;
    private String position;
}

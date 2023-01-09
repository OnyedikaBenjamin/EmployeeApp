package africa.semicolon.employeemanagement.data.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer salaryId;
    private Integer departmentId;
    private Integer salaryAmount;
}

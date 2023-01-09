package africa.semicolon.employeemanagement.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long QualificationId;
    private SchoolQualification qualificationName;
    @CreationTimestamp
    private LocalDateTime dateSubmitted;
}

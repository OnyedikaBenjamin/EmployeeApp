package africa.semicolon.employeemanagement.data.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long levelId;
    private JobLevel jobLevel;
}

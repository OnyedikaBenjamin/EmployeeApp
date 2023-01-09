package africa.semicolon.employeemanagement.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity(name = "phone_number")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MobileNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String countryCode;
    private String number;
    @CreationTimestamp
    private LocalDateTime dateCreated;
}

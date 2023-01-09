package africa.semicolon.employeemanagement.data.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @Column(unique=true)
    private String email;
    private int age;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Department departmentName;

    private Integer employeeSalary;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Level employeeJobLevel;

    @OneToMany
    private List<Qualification> employeeQualifications;
    private Boolean isSuspended = false;

    @CreationTimestamp
    private LocalDateTime creationDate = LocalDateTime.now();
}

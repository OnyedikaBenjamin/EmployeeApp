package africa.semicolon.employeemanagement.data.repository;

import africa.semicolon.employeemanagement.data.model.MobileNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, Integer> {
    MobileNumber findByNumber(String mobileNumber);
}

package africa.semicolon.employeemanagement.data.repository;

import africa.semicolon.employeemanagement.data.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByFieldName(String fieldName);

    @Transactional
    void deleteByFieldName(String departmentName);
}

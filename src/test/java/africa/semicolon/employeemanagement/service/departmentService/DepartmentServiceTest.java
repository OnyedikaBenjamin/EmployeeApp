package africa.semicolon.employeemanagement.service.departmentService;

import africa.semicolon.employeemanagement.data.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagement.data.dto.response.DepartmentResponse;
import africa.semicolon.employeemanagement.data.model.JobDepartment;
import africa.semicolon.employeemanagement.data.model.JobLevel;
import africa.semicolon.employeemanagement.data.model.Level;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class DepartmentServiceTest {

    private final DepartmentService departmentService;

    @Autowired
    DepartmentServiceTest(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    DepartmentRequest departmentRequest;
    Level level;
    @BeforeEach
    void setUp() {
        level = Level.builder()
                .jobLevel(JobLevel.ENTRY_LEVEL)
                .build();

        departmentRequest = DepartmentRequest.builder()
                .fieldName(JobDepartment.FINANCE)
                .level(level)
                .build();
    }

    @AfterEach
    void tearDown() {
        departmentService.deleteAllDepartments();
    }

    @Test
    void updateDepartmentByDepartmentId() {
    }

    @Test
    void deleteDepartmentByDepartmentId() {
    }

    @Test
    void deleteDepartmentByDepartmentName() {
    }

    @Test
    void findAllDepartment() {
    }

    @Test
    void findDepartmentById() {
    }

    @Test
    void findDepartmentByName() {
    }
}
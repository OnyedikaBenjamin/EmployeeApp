package africa.semicolon.employeemanagement.service;

import africa.semicolon.employeemanagement.data.dto.request.EmployeeRequest;
import africa.semicolon.employeemanagement.data.dto.response.EmployeeResponse;
import africa.semicolon.employeemanagement.data.model.*;
import africa.semicolon.employeemanagement.web.exception.EmployeeAlreadyExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeDoesNotExistsException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
class EmployeeServiceTest {

    @Autowired
    @Qualifier("initialService")
    EmployeeService employeeService;

    EmployeeResponse res;
    EmployeeRequest employeeDtoOne;

    @BeforeEach
    void setUp() {
        employeeDtoOne = EmployeeRequest.builder()
                .firstName("Jahzeal")
                .lastName("Chiemerie")
                .email("jahzeal@gmail.com")
                .age(3)
                .build();

        res = employeeService.createEmployee(employeeDtoOne);

    }

    @AfterEach
    void tearDown(){
        employeeService.deleteAllEmployees();
    }


    @Test
    @DisplayName("Employee can create an account")
    void createEmployee() throws EmployeeAlreadyExistsException {
        EmployeeRequest employeeDtoThree = EmployeeRequest.builder()
                .firstName("zeal")
                .lastName("emerie")
                .email("zeal@gmail.com")
                .age(3)
                .build();

        assertThat(employeeDtoThree).isNotNull();

        EmployeeResponse employee = employeeService.createEmployee(employeeDtoThree);
        log.info("Employee created is :: {}", employee);

        assertThat(employee.getFirstName()).isEqualTo("zeal");
        assertThat(employee.getLastName()).isEqualTo("emerie");
        assertThat(employee.getEmail()).isEqualTo("zeal@gmail.com");
        assertThat(employee.getAge()).isEqualTo(3);

    }

    @Test
    void getAllEmployees() {
        List<Employee> emp = employeeService.findAllEmployee();
        assertThat(emp.size()).isEqualTo(1);
    }

    @Test
    void updateEmployee() {

    }

    @Test
    void deleteAllEmployees() throws EmployeeAlreadyExistsException {
        EmployeeRequest employeeDtoThree = EmployeeRequest.builder()
                .firstName("zeal")
                .lastName("emerie")
                .email("zeal@gmail.com")
                .age(3)
                .build();

        assertThat(employeeDtoThree).isNotNull();

        EmployeeResponse employee = employeeService.createEmployee(employeeDtoThree);
        log.info("Employee created is :: {}", employee);

        assertThat(employee.getFirstName()).isEqualTo("zeal");
        assertThat(employee.getLastName()).isEqualTo("emerie");
        assertThat(employee.getEmail()).isEqualTo("zeal@gmail.com");
        assertThat(employee.getAge()).isEqualTo(3);

        employeeService.deleteAllEmployees();
       assertEquals(0,employeeService.findAllEmployee().size());
    }

    @Test
    void deleteEmployeeById() throws EmployeeDoesNotExistsException, EmployeeAlreadyExistsException {
        EmployeeRequest employeeDtoThree = EmployeeRequest.builder()
                .firstName("zeal")
                .lastName("emerie")
                .email("zeal@gmail.com")
                .age(3)
                .build();

        assertThat(employeeDtoThree).isNotNull();

        EmployeeResponse employee = employeeService.createEmployee(employeeDtoThree);
        log.info("Employee created is :: {}", employee);

        assertThat(employee.getFirstName()).isEqualTo("zeal");
        assertThat(employee.getEmail()).isEqualTo(employeeDtoThree.getEmail());

        employeeService.deleteEmployeeById(employee.getId());
        assertThat(employeeService.findAllEmployee().size()).isEqualTo(1);
    }

    @Test
    void findEmployeeByEmail() throws EmployeeDoesNotExistsException {
        Employee employee = employeeService.findEmployeeByEmail(res.getEmail()).orElse(null);
        assertThat(employee).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo(res.getFirstName());
        assertThat(employee.getLastName()).isEqualTo(res.getLastName());
    }
}
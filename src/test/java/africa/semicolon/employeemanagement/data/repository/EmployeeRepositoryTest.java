package africa.semicolon.employeemanagement.data.repository;

import africa.semicolon.employeemanagement.data.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts={"/db/insert.sql"})
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Employee can create account")
    void EmployeeCanCreateAccountTest(){
        Employee employee = Employee.builder()
                .firstName("Delight")
                .lastName("Adaora")
                .email("delight@gmail.com")
                .age(6)
                .build();
        log.info("Employee saved to db :: {}", employee);
        assertThat(employee).isNotNull();

        employeeRepository.save(employee);
        log.info("Employee saved to db :: {}", employee);



        assertThat(employee.getFirstName()).isNotNull();
        assertThat(employee.getFirstName()).isEqualTo("Delight");
        assertThat(employee.getLastName()).isEqualTo("Adaora");
        assertThat(employee.getEmail()).isEqualTo("delight@gmail.com");
        assertThat(employee.getAge()).isEqualTo(6);
    }

    @Test
    @DisplayName("All employee can be extracted from the db")
    void findAllEmployeeTest(){
       List<Employee> allEmployee = employeeRepository.findAll();
       assertThat(allEmployee.size() ).isNotNull();
       assertThat(allEmployee.size() ).isEqualTo(3);
    }

    @Test
    @DisplayName("Find employee by email")
    void findEmployeeByEmailTest(){
        Optional<Employee> foundEmployee = employeeRepository.findByEmail("precious@gmail.com");
        assert foundEmployee.isPresent();
        Employee employee = foundEmployee.get();
        assertThat(employee.getFirstName()).isEqualTo("Precious");
        assertThat(employee.getLastName()).isEqualTo("Lois");
        assertThat(employee.getEmail()).isEqualTo("precious@gmail.com");
        assertThat(employee.getAge()).isEqualTo(30);
    }

    @Test
    @DisplayName("Employee details can be updated")
    void employeeDetailsCanBeUpdatedTest(){
        Optional<Employee> foundEmployee = employeeRepository.findByEmail("precious@gmail.com");
        assert foundEmployee.isPresent();
        Employee employee = foundEmployee.get();
//        Employee employee = employeeRepository.findByEmail("precious@gmail.com");
        assertThat(employee.getFirstName()).isEqualTo("Precious");
        assertThat(employee.getLastName()).isEqualTo("Lois");
        assertThat(employee.getEmail()).isEqualTo("precious@gmail.com");
        assertThat(employee.getAge()).isEqualTo(30);

        employee.setFirstName("Lois");
        employee.setLastName("Precious");
        employee.setEmail("preciouslois@gmail.com");
        employee.setAge(19);

        assertThat(employee.getFirstName()).isEqualTo("Lois");
        assertThat(employee.getLastName()).isEqualTo("Precious");
        assertThat(employee.getEmail()).isEqualTo("preciouslois@gmail.com");
        assertThat(employee.getAge()).isEqualTo(19);
    }

//    @Test
//    @DisplayName("Employee can be deleted")
//    void employeeCanBeDeleted(){
//        Employee employee = new Employee();
//        employee.setId(9L);
//        employee.setFirstName("Lois");
//        employee.setLastName("Precious");
//        employee.setEmail("preciouslois@gmail.com");
//        employee.setAge(19);
//
//        employeeRepository.save(employee);
//
//        assertThat(employee.getFirstName()).isEqualTo("Lois");
//        assertThat(employee.getLastName()).isEqualTo("Precious");
//        assertThat(employee.getEmail()).isEqualTo("preciouslois@gmail.com");
//        assertThat(employee.getAge()).isEqualTo(19);
//
//        employeeRepository.deleteById(9L);

//        assertThat(employeeRepository.findAll().size()).isEqualTo(3);
//    }
}
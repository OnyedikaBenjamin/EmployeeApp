package africa.semicolon.employeemanagement.service;

import africa.semicolon.employeemanagement.data.dto.request.EmployeeRequest;
import africa.semicolon.employeemanagement.data.dto.request.QualificationRequest;
import africa.semicolon.employeemanagement.data.dto.request.RoleRequest;
import africa.semicolon.employeemanagement.data.dto.response.EmployeeResponse;
import africa.semicolon.employeemanagement.data.model.*;
import africa.semicolon.employeemanagement.data.repository.EmployeeRepository;
import africa.semicolon.employeemanagement.web.exception.EmployeeAlreadyExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeDoesNotExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("initialService")
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest employeeDto) {
        if(employeeDto == null) throw new IllegalArgumentException("Employee records can not be empty");

        Optional<Employee> foundEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if(foundEmployee.isPresent()) throw new EmployeeAlreadyExistsException("Employee with email "
                +employeeDto.getEmail()+ " already exists");

        Employee employee = mapper.map(employeeDto, Employee.class);
        Employee emp = employeeRepository.save(employee);
        return mapper.map(emp, EmployeeResponse.class);
    }

    @Override
    public Optional<Employee> findEmployeeByDepartmentName(String departmentName) throws EmployeeDoesNotExistsException {
        Optional<Employee> employee = employeeRepository.findByDepartmentNameFieldName(departmentName);
        if (employee.isEmpty()) throw new EmployeeDoesNotExistsException("Employee with this "+ departmentName+" department does not exist");
        return employee;
    }

    @Override
    public EmployeeResponse setEmployeeSalaryByJobLevel(Long employeeId) throws EmployeeDoesNotExistsException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) throw new EmployeeDoesNotExistsException("Employee with this "+ employeeId+" employee id does not exist");

        Level level = new Level();
        if (level.getJobLevel().equals(JobLevel.INTERNSHIP)) {
            employee = Employee.builder()
                    .employeeSalary(ConstantSalary.INTERNSHIP_EMPLOYEE_SALARY)
                    .build();
        } else  if (level.getJobLevel().equals(JobLevel.ENTRY_LEVEL)) {
            employee = Employee.builder()
                    .employeeSalary(ConstantSalary.ENTRY_LEVEL_EMPLOYEE_SALARY)
                    .build();
        } else  if (level.getJobLevel().equals(JobLevel.MIDDLE_LEVEL)) {
            employee = Employee.builder()
                    .employeeSalary(ConstantSalary.MIDDLE_LEVEL_EMPLOYEE_SALARY)
                    .build();
        } else {
            employee = Employee.builder()
                    .employeeSalary(ConstantSalary.SENIOR_LEVEL_EMPLOYEE_SALARY)
                    .build();
        }
        return mapper.map(employee, EmployeeResponse.class);
    }

    @Override
    public EmployeeResponse updateEmployeeSalaryByJobLevel(Long employeeId) throws EmployeeDoesNotExistsException {
      return null;
    }

    @Override
    public Employee activateSuspendEmployeeByEmployeeId(Long employeeId) throws EmployeeDoesNotExistsException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) throw new EmployeeDoesNotExistsException("Employee with this "+ employeeId+" employee id does not exist");

        if (employee.getIsSuspended().equals(false)) employee.setIsSuspended(true);
        return employee;
    }

    @Override
    public Employee deactivateSuspendEmployeeByEmployeeId(Long employeeId) throws EmployeeDoesNotExistsException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) throw new EmployeeDoesNotExistsException("Employee with this "+ employeeId+" employee id does not exist");
        if (employee.getIsSuspended().equals(true)) employee.setIsSuspended(false);
        return employee;
    }

    @Override
    public Employee setEmployeeQualificationByEmployeeId(Long employeeId, QualificationRequest request) throws EmployeeDoesNotExistsException {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) throw new EmployeeDoesNotExistsException("Employee with this "+ employeeId+" employee id does not exist");

        Qualification qualify = mapper.map(request, Qualification.class);

        List<Qualification> qualification = List.of(qualify);
       return employee = Employee.builder()
                .employeeQualifications(qualification)
                .build();
    }

    @Override
    public Employee updateEmployee(String email, JsonPatch jsonpatch) throws EmployeeDoesNotExistsException,
            JsonPatchException, JsonProcessingException, EmployeeRequestException {

        Optional<Employee> query = findEmployeeByEmail(email);

        assert (query.isPresent());
        Employee foundEmployee = query.get();
        try{
            foundEmployee = applyPatchToEmployee(jsonpatch, foundEmployee);
            return employeeRepository.save(foundEmployee);
        }catch(JsonPatchException | JsonProcessingException e){
            throw new EmployeeRequestException("Update failed");
        }
    }

    private Employee applyPatchToEmployee(JsonPatch jsonpatch, Employee employee) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = jsonpatch.apply(objectMapper.convertValue(employee, JsonNode.class));
        return objectMapper.treeToValue(jsonNode, Employee.class);
    }

    @Override
    public String deleteAllEmployees() {
        employeeRepository.deleteAll();
        return "Successfully deleted";
    }

    @Override
    public String deleteEmployeeById(Long id) {
        if(id == null) throw new IllegalArgumentException("Field can be empty");

        if(employeeRepository.findById(id).isPresent()) employeeRepository.deleteById(id);

        return "Successfully deleted employee with id " + id;
    }

    @Override
    public Optional<Employee> findEmployeeByEmail(String email) throws EmployeeDoesNotExistsException {
        if(email == null) throw new IllegalArgumentException("Field can not be null");

        Optional<Employee> foundEmployee = employeeRepository.findByEmail(email);
        if(foundEmployee.isEmpty()) throw new EmployeeDoesNotExistsException("Employee with the email " + email + " doesn't exist");

        return foundEmployee;
    }

    @Override
    public List<Employee> findAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(Long id) throws EmployeeDoesNotExistsException {
        if(id == null) throw new IllegalArgumentException("Field can not be null");

        Optional<Employee> found = employeeRepository.findById(id);

        if(found.isEmpty()) throw new EmployeeDoesNotExistsException("Employee with the id " + id + " doesn't exist");

        return found;
    }
}

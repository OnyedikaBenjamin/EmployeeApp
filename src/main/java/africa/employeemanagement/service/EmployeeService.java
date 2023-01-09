package africa.semicolon.employeemanagement.service;

import africa.semicolon.employeemanagement.data.dto.request.EmployeeRequest;
import africa.semicolon.employeemanagement.data.dto.request.QualificationRequest;
import africa.semicolon.employeemanagement.data.dto.request.RoleRequest;
import africa.semicolon.employeemanagement.data.dto.response.EmployeeResponse;
import africa.semicolon.employeemanagement.data.model.Employee;
import africa.semicolon.employeemanagement.web.exception.EmployeeAlreadyExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeDoesNotExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest employeeDto) throws EmployeeAlreadyExistsException;
    Optional<Employee> findEmployeeByDepartmentName(String departmentName) throws EmployeeDoesNotExistsException;
    EmployeeResponse setEmployeeSalaryByJobLevel(Long employeeId) throws EmployeeDoesNotExistsException;
    EmployeeResponse updateEmployeeSalaryByJobLevel(Long employeeId) throws EmployeeDoesNotExistsException;
    Employee activateSuspendEmployeeByEmployeeId(Long employeeId) throws EmployeeDoesNotExistsException;
    Employee deactivateSuspendEmployeeByEmployeeId(Long employeeId) throws EmployeeDoesNotExistsException;
    Employee setEmployeeQualificationByEmployeeId(Long employeeId, QualificationRequest request) throws EmployeeDoesNotExistsException;
    Employee updateEmployee(String email, JsonPatch jsonpatch) throws EmployeeDoesNotExistsException, JsonPatchException, JsonProcessingException, EmployeeRequestException;
    String deleteAllEmployees();
    String deleteEmployeeById(Long id);
    Optional<Employee> findEmployeeByEmail(String name) throws EmployeeDoesNotExistsException;
    List<Employee> findAllEmployee();
    Optional<Employee> findEmployeeById(Long id) throws EmployeeDoesNotExistsException;

}

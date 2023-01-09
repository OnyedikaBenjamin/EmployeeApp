package africa.semicolon.employeemanagement.web.controller;

import africa.semicolon.employeemanagement.data.dto.request.EmployeeRequest;
import africa.semicolon.employeemanagement.data.dto.request.QualificationRequest;
import africa.semicolon.employeemanagement.data.dto.request.RoleRequest;
import africa.semicolon.employeemanagement.data.dto.response.EmployeeResponse;
import africa.semicolon.employeemanagement.data.model.Employee;
import africa.semicolon.employeemanagement.service.EmployeeService;
import africa.semicolon.employeemanagement.web.exception.EmployeeAlreadyExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeDoesNotExistsException;
import africa.semicolon.employeemanagement.web.exception.EmployeeRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createNewEmployee(@RequestBody EmployeeRequest employeeRequest){
        try{
            EmployeeResponse employee = employeeService.createEmployee(employeeRequest);
            return ResponseEntity.ok().body(employee);
        } catch (EmployeeAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getRecords")
    public ResponseEntity<?> getAllEmployeesRecord(){
        try{
            List<Employee> employee = employeeService.findAllEmployee();
            return ResponseEntity.ok().body(employee);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(value = "/update/{email}", consumes = {"application/json-patch+json"})
    public ResponseEntity<?> updateEmployeeRecord(@PathVariable String email, @RequestBody JsonPatch jsonpatch){
        try{
            Employee employee = employeeService.updateEmployee(email, jsonpatch);
            return ResponseEntity.ok().body(employee);
        }catch(EmployeeDoesNotExistsException | JsonPatchException |
                EmployeeRequestException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployeeRecord(@PathVariable Long id){
        try{
            String employee = employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }catch(IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/single/{id}")
    public ResponseEntity<?> getSingleEmployee(@PathVariable Long id) {
        try{
            Optional<Employee> employee = employeeService.findEmployeeById(id);
            return ResponseEntity.ok().body(employee);
        }catch(IllegalArgumentException | EmployeeDoesNotExistsException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value="/{departmentName}")
    public ResponseEntity<?> findEmployeeByDepartmentName(@PathVariable String departmentName){        try {
            Optional<Employee> employee = employeeService.findEmployeeByDepartmentName(departmentName);
            return ResponseEntity.ok().body(employee);
        } catch (EmployeeDoesNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping(value="/{employeeId}")
    public ResponseEntity<?> setEmployeeSalaryByJobLevel(@PathVariable Long employeeId){
        try{
            EmployeeResponse response = employeeService.setEmployeeSalaryByJobLevel(employeeId);
            return ResponseEntity.ok().body(response);
        }catch (EmployeeDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/updateSalary/{employeeId}")
    public ResponseEntity<?> updateEmployeeSalaryByJobLevel(@PathVariable Long employeeId){
        try{
            EmployeeResponse response = employeeService.updateEmployeeSalaryByJobLevel(employeeId);
            return ResponseEntity.ok().body(response);
        }catch (EmployeeDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/activate/{employeeId}")
    public ResponseEntity<?> activateSuspendEmployeeByEmployeeId(@PathVariable Long employeeId){
        try{
            Employee employee = employeeService.activateSuspendEmployeeByEmployeeId(employeeId);
            return ResponseEntity.ok().body(employee);
        }catch (EmployeeDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/deactivate/{employeeId}")
    public ResponseEntity<?> deactivateSuspendEmployeeByEmployeeId(@PathVariable Long employeeId){
        try{
            Employee employee = employeeService.deactivateSuspendEmployeeByEmployeeId(employeeId);
            return ResponseEntity.ok().body(employee);
        }catch (EmployeeDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping(value = "/employee_qualification/{employeeId}")
    public ResponseEntity<?> setEmployeeQualificationByEmployeeId(@PathVariable Long employeeId, @RequestBody QualificationRequest request){
        try{
            Employee employee = employeeService.setEmployeeQualificationByEmployeeId(employeeId, request);
            return ResponseEntity.ok().body(employee);
        }catch (EmployeeDoesNotExistsException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

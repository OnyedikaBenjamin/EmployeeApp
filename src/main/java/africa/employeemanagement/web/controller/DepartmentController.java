package africa.semicolon.employeemanagement.web.controller;

import africa.semicolon.employeemanagement.data.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagement.data.dto.response.DepartmentResponse;
import africa.semicolon.employeemanagement.data.model.Department;
import africa.semicolon.employeemanagement.service.departmentService.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<?> createDepartment(DepartmentRequest request){
      DepartmentResponse response = departmentService.createDepartment(request);
      return ResponseEntity.ok().body(response);
    }

    @PatchMapping(value = "/{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long departmentId){
        Department department = departmentService.updateDepartmentByDepartmentId(departmentId);
        return ResponseEntity.ok().body(department);
    }

    @DeleteMapping(path ="/{id}")
    public ResponseEntity<?> deleteDepartmentByDepartmentId(@PathVariable Long id){
        String dept = departmentService.deleteDepartmentByDepartmentId(id);
        return ResponseEntity.ok().body(dept);
    }

    @DeleteMapping(path ="/{departmentName}")
    public ResponseEntity<?> deleteDepartmentByDepartmentId(@PathVariable String departmentName){
        String dept = departmentService.deleteDepartmentByDepartmentName(departmentName);
        return ResponseEntity.ok().body(dept);
    }

    @GetMapping
    public ResponseEntity<?> findAllDepartment(){
        return ResponseEntity.ok().body(departmentService.findAllDepartment());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<?> findDepartmentById(@PathVariable Long departmentId){
        return ResponseEntity.ok().body(departmentService.findDepartmentById(departmentId));
    }

    @GetMapping("/{departmentName}")
    public ResponseEntity<?> findDepartmentById(@PathVariable String departmentName){
        return ResponseEntity.ok().body(departmentService.findDepartmentByName(departmentName));
    }
}

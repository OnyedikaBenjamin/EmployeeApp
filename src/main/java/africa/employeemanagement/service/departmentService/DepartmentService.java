package africa.semicolon.employeemanagement.service.departmentService;

import africa.semicolon.employeemanagement.data.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagement.data.dto.response.DepartmentResponse;
import africa.semicolon.employeemanagement.data.model.Department;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest request);
    Department updateDepartmentByDepartmentId(Long departmentId);
    String deleteDepartmentByDepartmentId(Long departmentId);
    String deleteDepartmentByDepartmentName(String departmentName);
    void deleteAllDepartments();
    List<Department> findAllDepartment();
    Department findDepartmentById(Long departmentId);
    Department findDepartmentByName(String departmentName);

}

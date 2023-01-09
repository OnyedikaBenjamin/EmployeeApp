package africa.semicolon.employeemanagement.service.departmentService;

import africa.semicolon.employeemanagement.data.dto.request.DepartmentRequest;
import africa.semicolon.employeemanagement.data.dto.response.DepartmentResponse;
import africa.semicolon.employeemanagement.data.model.Department;
import africa.semicolon.employeemanagement.data.repository.DepartmentRepository;
import africa.semicolon.employeemanagement.web.exception.DepartmentAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        Department dept = departmentRepository.findByFieldName(request.getFieldName().toString());
        if (dept != null) throw new DepartmentAlreadyExistsException("Department with this name "+request.getFieldName()+" already exists");
        Department requestDept = mapper.map(request, Department.class);
        Department savedDept = departmentRepository.save(requestDept);
        return mapper.map(savedDept, DepartmentResponse.class);
    }

    @Override
    public Department updateDepartmentByDepartmentId(Long departmentId) {
        return null;
    }

    @Override
    public String deleteDepartmentByDepartmentId(Long departmentId) {
        Department dept = departmentRepository.findById(departmentId).orElseThrow(
                ()-> new DepartmentAlreadyExistsException("Department with this id "+departmentId+" does not exists")
        );
        departmentRepository.deleteById(departmentId);
        return "Successfully Deleted";
    }

    @Override
    public String deleteDepartmentByDepartmentName(String departmentName) {
        Department dept = departmentRepository.findByFieldName(departmentName);
        if(dept == null) throw new DepartmentAlreadyExistsException("Department with this id "+departmentName+" does not exists");
        departmentRepository.deleteByFieldName(departmentName);
        return "Successfully Deleted";
    }

    @Override
    public void deleteAllDepartments() {
        departmentRepository.deleteAll();
    }

    @Override
    public List<Department> findAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(
                ()-> new DepartmentAlreadyExistsException("Department with this id "+departmentId+" does not exists")
        );
    }

    @Override
    public Department findDepartmentByName(String departmentName) {
        Department dept = departmentRepository.findByFieldName(departmentName);
        if(dept == null) throw new DepartmentAlreadyExistsException("Department with this id "+departmentName+" does not exists");
        return dept;
    }
}

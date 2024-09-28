package insurance_system_employee_service.contoller;

import insurance_system_employee_service.service.EmployeeServiceImpl;
import insurance_system_employee_service.vo.ClaimDTO;
import insurance_system_employee_service.vo.Employee;
import insurance_system_employee_service.service.EmployeeService;
import insurance_system_employee_service.vo.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;

    @GetMapping("/api/employee/login")
    public Employee employeeLogin(EmployeeDTO request) {
        return employeeService.login(new Employee(request.id, request.password, null, 0, null));
    }
    @PostMapping("api/employee/submitClaim")
    public Employee submitClaim(@RequestBody ClaimDTO request){
        Employee investigator = employeeService.submitClaim(request);
        return investigator;
    }
//    @PostMapping("/employee")
//    public Employee createEmployee(@RequestBody EmployeeCreateRequest request) {
//        return employeeService.createEmployee(request);
//    }
//
//    @GetMapping("/employees")
//    public List<Employee> getAllEmployees() {
//        return employeeService.getAllEmployees();
//    }
//
//    @PostMapping("/employee/update")
//    public Employee updateEmployee(@RequestBody EmployeeUpdateRequest request) {
//        return employeeService.updateEmployee(request);
//    }
//
//    @PostMapping("/employee/delete")
//    public void deleteEmployee(@RequestBody EmployeeDeleteRequest request) {
//        employeeService.deleteEmployee(request);
//    }
}
package insurance_system_employee_service.service;

import insurance_system_employee_service.vo.ClaimDTO;
import insurance_system_employee_service.vo.Employee;

import java.util.List;

public interface EmployeeService {
    Employee login(Employee employee);
    Employee submitClaim(ClaimDTO claimDTO);

}

package insurance_system_employee_service.service;

import insurance_system_employee_service.jpa.employee.EmployeeRepository;
import insurance_system_employee_service.jpa.employee.EmployeeEntity;
import insurance_system_employee_service.service.vo.EmployeeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeVO login(EmployeeVO employeeVO) {
        System.out.println(employeeVO.id + " " + employeeVO.password);
        String userPw = employeeRepository.getPassWordById(employeeVO.getId());
        if (userPw == null || !employeeVO.getPassword().equals(userPw)) return null;
        EmployeeEntity ent = employeeRepository.getEmployeeById(employeeVO.getId());
        return new EmployeeVO(ent.getId()
                , ent.getPassword()
                , ent.getName()
                , ent.getGender()
                , ent.getDepartment().toString());
    }
}

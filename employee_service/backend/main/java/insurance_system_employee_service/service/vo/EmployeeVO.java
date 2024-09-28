package insurance_system_employee_service.service.vo;

import insurance_system_employee_service.jpa.employee.EmployeeEntity;
import lombok.Data;

@Data
public class EmployeeVO  {

    public EmployeeVO(String id, String password, String name, int gender, String department) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.department = department;
    }

    public EmployeeVO(String id) {
        this.id = id;
    }

    public String id;
    public String password;
    public String name;
    public int gender;
    public String department;

}

package insurance_system_customer_service.service.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeVO {
    private String employee;

    public EmployeeVO(String employee){
        this.employee = employee;
    }
}

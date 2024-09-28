package insurance_system_employee_service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class EmployeeDTO {

    public String id;
    public String password;
    public String name;
    public int gender;
    public String department;

}

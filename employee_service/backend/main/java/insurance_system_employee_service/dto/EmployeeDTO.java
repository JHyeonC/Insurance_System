package insurance_system_employee_service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class EmployeeDTO {

    public EmployeeDTO(String id, String password, String name, Integer gender, String department) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.department = department;
    }

    public String id;
    public String password;
    public String name;
    public Integer gender;
    public String department;

}

package insurance_system_customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CustomerDTO {

    public CustomerDTO(String id, String password, String name, String birth,
                       Integer gender, Integer occupational_hazard, Integer smoking) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.occupational_hazard = occupational_hazard;
        this.smoking = smoking;
    }

    public String id;
    public String password;
    public String name;
    public String birth;
    public Integer gender;
    public Integer occupational_hazard;
    public Integer smoking;

}

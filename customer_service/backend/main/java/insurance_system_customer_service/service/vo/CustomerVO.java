package insurance_system_customer_service.service.vo;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class CustomerVO {

    public CustomerVO(String id, String name, String birth, int gender, int occupational_hazard, int smoking) {
        this.id = id;
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
    public int gender;
    public int occupational_hazard;
    public int smoking;

}

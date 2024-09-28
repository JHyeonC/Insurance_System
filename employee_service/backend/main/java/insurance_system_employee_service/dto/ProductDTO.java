package insurance_system_employee_service.dto;

import lombok.Data;

@Data
public class ProductDTO {

    public ProductDTO(String id, String name, Integer premium, Float senior_rate, Float male_rate,
                      Float female_rate, Float occupational_hazard_rate, Float smoking_rate, Integer released) {
        this.id = id;
        this.name = name;
        this.premium = premium;
        this.senior_rate = senior_rate;
        this.male_rate = male_rate;
        this.female_rate = female_rate;
        this.occupational_hazard_rate = occupational_hazard_rate;
        this.smoking_rate = smoking_rate;
        this.released = released;
    }

    public String id;
    public String name;
    public Integer premium;
    public Float senior_rate;
    public Float male_rate;
    public Float female_rate;
    public Float occupational_hazard_rate;
    public Float smoking_rate;
    public Integer released;

}

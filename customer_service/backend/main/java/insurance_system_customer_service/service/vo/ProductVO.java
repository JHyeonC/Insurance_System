package insurance_system_customer_service.service.vo;

import lombok.Getter;

@Getter
public class ProductVO {

    public ProductVO(String id, String name, int premium, float senior_rate, float male_rate,
                      float female_rate, float occupational_hazard_rate, float smoking_rate, int released) {
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
    public int premium;
    public float senior_rate;
    public float male_rate;
    public float female_rate;
    public float occupational_hazard_rate;
    public float smoking_rate;
    public int released;

}

package insurance_system_product_service.vo;

import lombok.Data;

@Data
public class ProductDTO {

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

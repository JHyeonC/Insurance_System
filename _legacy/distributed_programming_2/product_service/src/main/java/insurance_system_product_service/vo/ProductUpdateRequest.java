package insurance_system_product_service.vo;

import lombok.Data;

@Data
public class ProductUpdateRequest {
    public String id;
    public float seniorRate;
    public float maleRate;
    public float femaleRate;
    public float occupationHazardRate;
    public float smokingRate;
    public int released;
}

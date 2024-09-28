package insurance_system_employee_service.service.vo;

import insurance_system_employee_service.jpa.product.ProductEntity;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Setter;

@Data
public class ProductVO {

    public String id;
    public String name;
    public int premium;
    public float seniorRate;
    public float maleRate;
    public float femaleRate;
    public float occupationHazardRate;
    public float smokingRate;
    public int released;

//
    public ProductVO(
        String id, String name, int premium, float seniorRate, float maleRate,
        float femaleRate, float occupationHazardRate, float smokingRate, int released){
            this.id = id;
            this.name = name;
            this.premium = premium;
            this.seniorRate = seniorRate;
            this.maleRate = maleRate;
            this.femaleRate = femaleRate;
            this.occupationHazardRate = occupationHazardRate;
            this.smokingRate = smokingRate;
            this.released = released;
        }

        public ProductVO(String id , float seniorRate, float maleRate, float femaleRate, float occupationHazardRate, float smokingRate) {
            this.id = id;
            this.seniorRate = seniorRate;
            this.maleRate = maleRate;
            this.femaleRate = femaleRate;
            this.occupationHazardRate = occupationHazardRate;
            this.smokingRate = smokingRate;
        }

}

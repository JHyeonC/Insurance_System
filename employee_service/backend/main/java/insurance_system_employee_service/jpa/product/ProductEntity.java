package insurance_system_employee_service.jpa.product;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @Column(name ="id", nullable = false)
    public String id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "premium", nullable = false)
    public int premium;

    @Column(name = "senior_rate")
    public float seniorRate;

    @Column(name = "male_rate")
    public float maleRate;

    @Column(name = "female_rate")
    public float femaleRate;

    @Column(name = "occupational_hazard_rate")
    public float occupationHazardRate;

    @Column(name = "smoking_rate")
    public float smokingRate;

    @Column(name = "released", nullable = false)
    public int released;


    @Builder
    public ProductEntity(String id, String name, int premium, float seniorRate, float maleRate,
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

    public void setReleased(int released) {
        this.released = released;
    }
}

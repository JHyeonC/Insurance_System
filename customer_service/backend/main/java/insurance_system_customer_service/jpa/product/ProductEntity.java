package insurance_system_customer_service.jpa.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    public String id;
    @Column(name="name", nullable = false)
    public String name;
    @Column(name = "premium", nullable = false)
    public int premium;
    @Column(name = "senior_rate")
    public float senior_rate;
    @Column(name = "male_rate")
    public float male_rate;
    @Column(name = "female_rate")
    public float female_rate;
    @Column(name = "occupational_hazard_rate")
    public float occupational_hazard_rate;
    @Column(name = "smoking_rate")
    public float smoking_rate;
    @Column(name = "released")
    public int released;
}

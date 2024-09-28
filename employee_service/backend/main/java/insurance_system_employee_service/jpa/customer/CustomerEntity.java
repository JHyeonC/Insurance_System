package insurance_system_employee_service.jpa.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Entity
@Table(name="customer")
public class CustomerEntity {

    @Id
    private String id;
    @Column(name = "password")
    private String password;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "birth", nullable = false)
    private String birth;
    @Column(name = "gender", nullable = false)
    private int gender;
    @Column(name = "occupational_hazard", nullable = false)
    private int occupationalHazard;
    @Column(name = "smoking", nullable = false)
    private int smoking;

    @Builder
    public CustomerEntity(String id, String name, String birth, int gender, int occupationalHazard, int smoking) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.gender = gender;
        this.occupationalHazard = occupationalHazard;
        this.smoking = smoking;
    }

    public CustomerEntity() {
        this(null, null, null, 0, 0, 0);
    }
}

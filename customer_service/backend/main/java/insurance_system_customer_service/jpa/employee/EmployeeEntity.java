package insurance_system_customer_service.jpa.employee;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {
    public enum Department{
        MARKETING, INVESTIGATING, SUPPORTING, DEVELOPMENT, CONTRACT, UNDERWRITING
    }
    @Id
    private String id;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "gender", nullable = false)
    private int gender;
    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    private Department department;
}

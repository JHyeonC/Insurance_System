package insurance_system_employee_service.vo;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    public String id;
    @Column(name = "password")
    public String password;

    @Column(name = "name", nullable = false)
    public String name;
    @Column(name = "gender", nullable = false)
    public int gender;
    @Enumerated(EnumType.STRING)
    @Column(name = "department", nullable = false)
    public Department department;

    @Builder
    public Employee(String id, String password, String name, int gender, Department department){
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.department = department;
    }
}

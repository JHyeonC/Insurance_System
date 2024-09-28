package insurance_system_employee_service.jpa.employee;

import insurance_system_employee_service.service.vo.Department;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "employee")
public class EmployeeEntity implements Serializable {
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
    public EmployeeEntity(String id, String password, String name, int gender, String department){
        this.id = id;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.department = Department.valueOf(department);
    }
}

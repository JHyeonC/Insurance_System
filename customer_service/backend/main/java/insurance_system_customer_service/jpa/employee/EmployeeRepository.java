package insurance_system_customer_service.jpa.employee;

import insurance_system_customer_service.jpa.employee.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

    EmployeeEntity findById(String id);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.department = :department")
    List<EmployeeEntity> getEmployeesByDepartment(@Param("department") EmployeeEntity.Department department);
}

package insurance_system_employee_service.jpa.employee;

import insurance_system_employee_service.service.vo.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    @Query("SELECT e FROM EmployeeEntity e WHERE e.id = :id")
    EmployeeEntity getEmployeeById(@Param("id") String id);

    @Query("SELECT e.password FROM EmployeeEntity e WHERE e.id = :id")
    String getPassWordById(@Param("id") String id);

    @Query("SELECT e FROM EmployeeEntity e WHERE e.department = :department")
    List<EmployeeEntity> getEmployeesByDepartment(@Param("department") Department department);

    @Modifying
    @Transactional
    @Query("UPDATE EmployeeEntity e SET e.name = :name WHERE e.id = :id")
    EmployeeEntity updateEmployee(@Param("id") String id, @Param("name") String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM EmployeeEntity e WHERE e.id = :id")
    void deleteById(@Param("id") String id);
}
package insurance_system_employee_service.jpa;

import insurance_system_employee_service.vo.Department;
import insurance_system_employee_service.vo.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.id = :id")
    Employee getEmployeeById(@Param("id") String id);

    @Query("SELECT e.password FROM Employee e WHERE e.id = :id")
    String getPassWordById(@Param("id") String id);

    @Query("SELECT e FROM Employee e WHERE e.department = :department")
    List<Employee> getEmployeesByDepartment(@Param("department") Department department);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.name = :name WHERE e.id = :id")
    Employee updateEmployee(@Param("id") String id, @Param("name") String name);

    @Modifying
    @Transactional
    @Query("DELETE FROM Employee e WHERE e.id = :id")
    void deleteById(@Param("id") String id);
}
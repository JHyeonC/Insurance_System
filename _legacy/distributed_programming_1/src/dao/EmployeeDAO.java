package dao;

import common.Employee;
import common.Product;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends DAO {

    public EmployeeDAO(String daoName) {
        super(daoName);
        databaseName = "insurance_system";
        tableName = "employees";
    }

    public Employee getEmployeeById(String id) {
        String sql = "select * from " + tableName + " where " + "id=\'" + id + "\';";
        if (!executeQuery(sql)) return null;
        if (!next()) return null;
        Employee employee = new Employee();
        employee.id = getString("id");
        employee.name = getString("name");
        employee.gender = getInt("gender");
        employee.department = Employee.Department.valueOf(getString("department"));
        return employee;
    }

    public String getPasswordById(String id) {
        String sql = "select password from " + tableName + " where " + "id=\'" + id + "\';";
        if (!executeQuery(sql)) return null;
        if (!next()) return null;
        return getString("password");
    }

    public List<Employee> getEmployeesByDepartment(Employee.Department department) {
        String sql = "select * from " + tableName + " where department=\'" + department + "\';";
        if (!executeQuery(sql)) return null;
        if(!next()) return null;
        List<Employee> employees = new ArrayList<>();
        do {
            Employee employee = new Employee();
            employee.id = getString("id");
            employee.name = getString("name");
            employee.gender = getInt("gender");
            employee.department = Employee.Department.valueOf(getString("department"));
            employees.add(employee);
        } while(next());
        return employees;
    }
}

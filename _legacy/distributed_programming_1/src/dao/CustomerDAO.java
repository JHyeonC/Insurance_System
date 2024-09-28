package dao;

import common.Claim;
import common.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends DAO {

    public CustomerDAO(String daoName) {
        super(daoName);
        databaseName = "insurance_system";
        tableName = "customers";
    }

    public Customer getCustomerById(String id) {
        String sql = "select * from " + tableName + " where " + "id=\'" + id + "\';";
        if (!executeQuery(sql)) return null;
        if (!next()) return null;
        Customer customer = new Customer();
        customer.id = getString("id");
        customer.name = getString("name");
        customer.birth = getString("birth");
        customer.gender = getInt("gender");
        customer.occupationalHazard = getInt("occupational_hazard");
        customer.smoking = getInt("smoking");
        return customer;
    }

    public String getPasswordById(String id) {
        String sql = "select password from " + tableName + " where " + "id=\'" + id + "\';";
        if (!executeQuery(sql)) return null;
        if (!next()) return null;
        return getString("password");
    }

    public List<Customer> getCustomers() {
        String sql = "select * from " + tableName + ";";
        if (!executeQuery(sql)) return null;
        if (!next()) return null;
        List<Customer> customers = new ArrayList<>();
        System.out.println(getString("id"));
        do {
            Customer customer = new Customer();
            customer.id = getString("id");
            customer.name = getString("name");
            customer.birth = getString("birth");
            customer.gender = getInt("gender");
            customer.occupationalHazard = getInt("occupational_hazard");
            customer.smoking = getInt("smoking");
            customers.add(customer);
        } while (next());
        return customers;
    }
}

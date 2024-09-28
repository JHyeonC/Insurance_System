package dao;

import common.Customer;
import common.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends DAO {

    public ProductDAO(String daoName) {
        super(daoName);
        databaseName = "insurance_system";
        tableName = "products";
    }

    public boolean addProduct(String id, String name, int premium) {
        String sql = "insert into " + tableName + "(id, name, premium, released) values(\'" +
                id + "\', \'" + name + "\'," + premium + ", 0);";
        return executeUpdate(sql);
    }

    public List<Product> getProducts() {
        String sql = "select * from " + tableName + ";";
        if (!executeQuery(sql)) return null;
        if(!next()) return null;
        List<Product> products = new ArrayList<>();
        do {
            Product product = new Product();
            product.id = getString("id");
            product.name = getString("name");
            product.premium = getInt("premium");
            product.seniorRate = getFloat("senior_rate");
            product.maleRate = getFloat("male_rate");
            product.femaleRate = getFloat("female_rate");
            product.occupationalHazardRate = getFloat("occupational_hazard_rate");
            product.smokingRate = getFloat("smoking_rate");
            product.released = getInt("released");
            products.add(product);
        } while(next());
        return products;
    }

    public boolean updateProduct(Product product) {
        String sql = "update " + tableName + " set senior_rate=" + product.seniorRate + ", male_rate=" + product.maleRate +
                ", female_rate=" + product.femaleRate + ", occupational_hazard_rate=" + product.occupationalHazardRate + ", smoking_rate=" +
                product.smokingRate + ", released=" + product.released + " where id=\'" + product.id + "\';";
        return executeUpdate(sql);
    }

}

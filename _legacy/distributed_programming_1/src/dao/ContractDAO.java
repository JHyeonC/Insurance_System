package dao;

import common.Contract;
import common.Product;

import java.util.ArrayList;
import java.util.List;

public class ContractDAO extends DAO {

    public ContractDAO(String daoName) {
        super(daoName);
        databaseName = "insurance_system";
        tableName = "contracts";
    }

    public boolean addContract(String customerId, String productId, int premium) {
        String sql = "insert into " + tableName + " values(\'" + customerId + "\',\'" + productId + "\'," + premium + ");";
        return executeUpdate(sql);
    }

    public List<Contract> getContractsByCustomerId(String customerId) {
        String sql = "select * from " + tableName + " where customer_id=\'" + customerId + "\';";
        if (!executeQuery(sql)) return null;
        if(!next()) return null;
        List<Contract> contracts = new ArrayList<>();
        do {
            Contract contract = new Contract();
            contract.customerId = getString("customer_id");
            contract.productId = getString("product_id");
            contract.premium = getInt("premium");
            contracts.add(contract);
        } while (next());
        return contracts;
    }
}

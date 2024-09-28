package dao;

import common.Claim;
import common.Employee;

import java.util.ArrayList;
import java.util.List;

public class ClaimDAO extends DAO {

    public ClaimDAO(String daoName) {
        super(daoName);
        databaseName = "insurance_system";
        tableName = "claims";
    }

    public List<Claim> getClaimsByStatus(Claim.Status status) {
        String sql = "select * from " + tableName + " where status=\'" + status + "\';";
        if (!executeQuery(sql)) return null;
        if(!next()) return null;
        List<Claim> claims = new ArrayList<>();
        do {
            Claim claim = new Claim();
            claim.id = getString("id");
            claim.compensation = getInt("compensation");
            claim.customerId = getString("customer_id");
            claim.productId = getString("product_id");
            claim.date = getString("date");
            claim.description = getString("description");
            claim.investigator = getString("investigator");
            claim.report = getString("report");
            claim.reviewer = getString("reviewer");
            claim.status = Claim.Status.valueOf(getString("status"));
            claims.add(claim);
        } while(next());
        return claims;
    }

    public boolean addClaim(Claim claim) {
        String sql = "insert into " + tableName + " values(\'" + claim.id + "\'," + claim.compensation + ", \'" + claim.customerId + "\', \'" + claim.productId + "\',\'"
                + claim.date + "\', \'" + claim.description + "\', \'" + claim.investigator + "\', \'" + claim.report + "\', \'" + claim.reviewer + "\', \'" + claim.status + "\');";
        return executeUpdate(sql);
    }

    public List<Claim> getClaimsByInvestigator(String investigator) {
        String sql = "select * from " + tableName + " where investigator=\'" + investigator + "\';";
        if (!executeQuery(sql)) return null;
        if(!next()) return null;
        List<Claim> claims = new ArrayList<>();
        System.out.println(getString("id"));
       do  {
            Claim claim = new Claim();
            claim.id = getString("id");
            claim.compensation = getInt("compensation");
            claim.customerId = getString("customer_id");
            claim.productId = getString("product_id");
            claim.date = getString("date");
            claim.description = getString("description");
            claim.investigator = getString("investigator");
            claim.report = getString("report");
            claim.reviewer = getString("reviewer");
            claim.status = Claim.Status.valueOf(getString("status"));
            claims.add(claim);
        } while (next());
        return claims;
    }

    public boolean updateClaim(Claim claim) {
        String sql = "update " + tableName + " set report=\'" + claim.report + "\', compensation="
                + claim.compensation + ", status=\'" + claim.status + "\', reviewer=\'" + claim.reviewer + "\' where id=\'" + claim.id + "\';";
        return executeUpdate(sql);
    }
}

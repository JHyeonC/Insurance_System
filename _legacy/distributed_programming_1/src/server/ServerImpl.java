package server;

import common.*;
import dao.*;
import utils.RMIUtils;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerImpl extends UnicastRemoteObject implements Server {

    private static final int PORT = 40051;
    private static final String NAME = "SERVER";
    private static CustomerDAO customerDAO = new CustomerDAO("CustomerDAO");
    private static ProductDAO productDAO = new ProductDAO("ProductDAO");
    private static ContractDAO contractDAO = new ContractDAO("ContractDAO");
    private static EmployeeDAO employeeDAO = new EmployeeDAO("EmployeeDAO");
    private static ClaimDAO claimDAO = new ClaimDAO("ClaimDAO");
    private static BoardDAO boardDAO = new BoardDAO("BoardDAO");

    public ServerImpl() throws RemoteException {
        super();
    }

    public static void main(String[] args) {
        RMIUtils.initRegistry(PORT, NAME);
        System.out.println("서버가 시작되었습니다.");
    }

    @Override
    public Customer customerLogin(String id, String pw) {
        String userPw = customerDAO.getPasswordById(id);
        if (!pw.equals(userPw)) return null;
        return customerDAO.getCustomerById(id);
    }

    @Override
    public Employee employeeLogin(String id, String pw) throws RemoteException {
        String userPw = employeeDAO.getPasswordById(id);
        if (!pw.equals(userPw)) return null;
        return employeeDAO.getEmployeeById(id);
    }

    @Override
    public List<Product> getProducts() throws RemoteException {
        return productDAO.getProducts();
    }

    @Override
    public boolean registerProduct(String customerId, String productId, int premium) throws RemoteException {
        return contractDAO.addContract(customerId, productId, premium);
    }

    @Override
    public boolean developProduct(String id, String name, int premium) throws RemoteException {
        return productDAO.addProduct(id, name, premium);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    @Override
    public List<Contract> getContractsByCustomerId(String customerId) throws RemoteException {
        return contractDAO.getContractsByCustomerId(customerId);
    }

    @Override
    public Employee submitClaim(Claim claim) throws RemoteException {
        List<Employee> investigators = employeeDAO.getEmployeesByDepartment(Employee.Department.INVESTIGATING);
        Map<String, Integer> investigatingCount = new HashMap<>();
//        claim.investigator = investigators.get(0).id;
//        if(!claimDAO.addClaim(claim))  return null;
        for (Employee investigator : investigators) investigatingCount.put(investigator.id, 0);
        List<Claim> reportingClaims = claimDAO.getClaimsByStatus(Claim.Status.REPORTING);
        for (Claim reportingClaim : reportingClaims)
            investigatingCount.replace(reportingClaim.investigator, investigatingCount.get(reportingClaim.investigator) + 1);
        String investigator = investigators.get(0).id;
        int min = Integer.MAX_VALUE;
        for (String employeeId : investigatingCount.keySet()) {
            if (investigatingCount.get(employeeId) < min) {
                investigator = employeeId;
                min = investigatingCount.get(employeeId);
            }
        }
        claim.investigator = investigator;
        if (claimDAO.addClaim(claim)) return employeeDAO.getEmployeeById(investigator);
        return null;
    }

    @Override
    public List<Claim> getClaimsByInvestigator(String investigator) throws RemoteException {
        return claimDAO.getClaimsByInvestigator(investigator);
    }

    @Override
    public boolean reportClaim(Claim claim) throws RemoteException {
        return claimDAO.updateClaim(claim);
    }

    @Override
    public List<Claim> getClaimsByStatus(Claim.Status status) throws RemoteException {
        return claimDAO.getClaimsByStatus(status);
    }

    @Override
    public boolean evaluateClaim(Claim claim) throws RemoteException {
        return claimDAO.updateClaim(claim);
    }

    @Override
    public boolean payCompensation(Claim claim) throws RemoteException {
        return claimDAO.updateClaim(claim);
    }

    @Override
    public boolean writeBoard(Board board) throws RemoteException {
        return boardDAO.addBoard(board);
    }

    @Override
    public List<Board> getBoards() throws RemoteException {
        return boardDAO.getBoards();
    }

    @Override
    public boolean updateBoard(Board board) throws RemoteException {
        return boardDAO.updateBoard(board);
    }

    @Override
    public List<Customer> getCustomers() throws RemoteException {
        return customerDAO.getCustomers();
    }
}

package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Server extends Remote {

    Customer customerLogin(String id, String pw) throws RemoteException;

    Employee employeeLogin(String id, String pw) throws RemoteException;

    List<Product> getProducts() throws RemoteException;

    boolean registerProduct(String customerId, String productId, int premium) throws RemoteException;

    boolean developProduct(String id, String name, int premium) throws RemoteException;

    boolean updateProduct(Product product) throws RemoteException;

    List<Contract> getContractsByCustomerId(String id) throws RemoteException;

    Employee submitClaim(Claim claim) throws RemoteException;

    List<Claim> getClaimsByInvestigator(String investigator) throws RemoteException;

    boolean reportClaim(Claim claim) throws RemoteException;

    List<Claim> getClaimsByStatus(Claim.Status status) throws RemoteException;

    boolean evaluateClaim(Claim claim) throws RemoteException;

    boolean payCompensation(Claim claim) throws RemoteException;

    boolean writeBoard(Board board) throws RemoteException;

    List<Board> getBoards() throws RemoteException;

    boolean updateBoard(Board board) throws RemoteException;

    List<Customer> getCustomers() throws RemoteException;
}




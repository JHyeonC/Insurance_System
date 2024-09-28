package common;

import java.io.Serial;
import java.io.Serializable;

public class Contract implements Serializable {

    public String customerId;
    public String productId;
    public int premium;

    public static void printHeader() {
        System.out.println("[계약 목록]");
        System.out.printf("%-15s%-15s%-15s\n", "CustomerID", "ProductID", "Premium");
    }

    public void print() {
        System.out.printf("%-15s%-15s%-15d\n", customerId, productId, premium);
    }

}

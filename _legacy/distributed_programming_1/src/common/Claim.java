package common;

import java.io.Serializable;

public class Claim implements Serializable {

    public enum Status {
        REPORTING, REVIEWING, ACCEPTED, REJECTED, PAID
    }

    public String id;
    public int compensation;
    public String customerId;
    public String productId;
    public String date;
    public String description;
    public String investigator;
    public String report;
    public String reviewer;
    public Status status;

    public static void printHeader() {
        System.out.printf("%-40s%-20s%-15s%-15s%-15s%-30s%-15s%-30s%-10s%-15s\n", "ID", "Compensation", "CustomerID", "ProductID", "Date", "Description",
                "Investigator", "Report", "Reviewer", "Status");
    }

    public void print() {
        System.out.printf("%-40s%-20s%-15s%-15s%-15s%-30s%-15s%-30s%-10s%-15s\n", id, compensation, customerId, productId, date, description,
                investigator, report, reviewer, status);
    }

}

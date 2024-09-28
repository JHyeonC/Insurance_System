package common;

import java.io.Serializable;

public class Employee implements Serializable {

    public enum Department {
        MARKETING, INVESTIGATING, SUPPORTING, DEVELOPMENT, CONTRACT, UNDERWRITING;
    }

    public String id;
    public String name;
    public int gender;
    public Department department;

    public void print() {
        System.out.printf("%-10s: %s\n", "ID", id);
        System.out.printf("%-10s: %s\n", "Name", name);
        System.out.printf("%-10s: %s\n", "Department", department);
    }

}

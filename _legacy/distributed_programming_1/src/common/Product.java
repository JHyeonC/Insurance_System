package common;

import java.io.Serializable;

public class Product implements Serializable {

    public String id;
    public String name;
    public int premium;
    public float seniorRate;
    public float maleRate;
    public float femaleRate;
    public float occupationalHazardRate;
    public float smokingRate;
    public int released;

    public static void printHeader() {
        System.out.println("[상품 목록]");
        System.out.printf("%-5s%-20s%-20s%-20s%-20s%-20s%-30s%-20s\n", "ID", "Name", "Premium", "Senior Rate", "Male Rate",
                "Female Rate", "Occupational Hazard Rate", "Smoking Rate");
    }

    public void print() {
        System.out.printf("%-5s%-20s%-20s%-20s%-20s%-20s%-30s%-20s\n", id, name, premium, seniorRate, maleRate, femaleRate, occupationalHazardRate, smokingRate);
    }

}

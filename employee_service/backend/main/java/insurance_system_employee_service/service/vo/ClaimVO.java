package insurance_system_employee_service.service.vo;

import insurance_system_employee_service.jpa.claim.ClaimEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ClaimVO  {



    private String id;
    private int compensation;
    private String contract_id;
    private String date;
    private String description;
    private String investigator;
    private String reviewer;
    private String report;
    private String status;

    public ClaimVO(String id , int compensation , String contract_id , String date , String description , String investigator , String reviewer ,  String report , String status) {
        this.id = id;
        this.compensation = compensation;
        this.contract_id = contract_id;
        this.date = date;
        this.description = description;
        this.investigator = investigator;
        this.reviewer = reviewer;
        this.report = report;
        this.status = status;
    }
    public ClaimVO(String id, int compensation , String report) {
        this.id = id;
        this.compensation = compensation;
        this.report = report;
    }
    public ClaimVO(String id) {
        this.id = id;
    }

    public ClaimVO(String id, String status) {
        this.id = id;
        this.status = status;
    }
}

package insurance_system_customer_service.service.vo;

import lombok.Getter;

@Getter
public class ClaimVO {

    public ClaimVO(int compensation, String contract_id, String description){
        this.compensation = compensation;
        this.contract_id = contract_id;
        this.description = description;
    }

    public ClaimVO(String id, int compensation, String contract_id, String date, String description, String investigator,
                   String reviewer, String report, String status) {
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

    public String id;
    public int compensation;
    public String contract_id;
    public String date;
    public String description;
    public String investigator;
    public String reviewer;
    public String report;
    public String status;

}

package insurance_system_employee_service.dto;

import lombok.Data;

@Data
public class ClaimDTO {

    public ClaimDTO(String id, Integer compensation, String contract_id, String date, String description, String investigator,
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
    public Integer compensation;
    public String contract_id;
    public String date;
    public String description;
    public String investigator;
    public String reviewer;
    public String report;
    public String status;
}

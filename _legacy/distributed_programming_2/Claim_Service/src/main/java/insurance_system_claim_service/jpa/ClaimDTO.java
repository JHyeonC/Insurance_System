package insurance_system_claim_service.jpa;

import lombok.Data;

@Data
public class ClaimDTO {


    private String id;
    private int compensation;
    private String contract_id;
    private String date;
    private String description;
    private String report;
    private String status;

}

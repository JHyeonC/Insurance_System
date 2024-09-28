package insurance_system_employee_service.vo;

import lombok.Data;

@Data
public class ClaimManageDTO {
    private String claim_id;
    private String investigator;
    private String reviewer;
}

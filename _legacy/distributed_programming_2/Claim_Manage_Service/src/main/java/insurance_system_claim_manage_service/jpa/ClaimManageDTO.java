package insurance_system_claim_manage_service.jpa;

import lombok.Data;

@Data
public class ClaimManageDTO {
  private String claim_id;
  private String investigator;
  private String reviewer;
}

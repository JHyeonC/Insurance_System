package insurance_system_claim_manage_service.service;

import insurance_system_claim_manage_service.jpa.ClaimManage;
import insurance_system_claim_manage_service.jpa.ClaimManageDTO;

import java.util.List;

public interface ClaimManageService {
    List<ClaimManage> getAllClaimsManages();

    ClaimManage addClaimManage(ClaimManageDTO claimManageDTO);

    ClaimManage getClaimManageByClaimId(String claim_id);

    boolean deleteClaimByID(String id);

    ClaimManage updateClaimManage(ClaimManageDTO claimManageDTO);
}

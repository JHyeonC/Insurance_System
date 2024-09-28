package insurance_system_claim_service.service;

import insurance_system_claim_service.jpa.Claim;
import insurance_system_claim_service.jpa.ClaimDTO;

import java.util.List;

public interface ClaimService {
    public List<Claim> getAllClaimss();

    Claim addClaim(String contractId, String description);

    Claim getClaimsById(String contractId);

    boolean deleteClaimByID(String id);

    Claim updateClaim(ClaimDTO claimDTO);
}

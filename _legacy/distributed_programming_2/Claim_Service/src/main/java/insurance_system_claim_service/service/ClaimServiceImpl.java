package insurance_system_claim_service.service;


//import com.example.claim_service.DAO.ClaimDAO;

import insurance_system_claim_service.jpa.Claim;
import insurance_system_claim_service.jpa.ClaimDTO;
import insurance_system_claim_service.jpa.ClaimRepository;
import insurance_system_claim_service.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;

    @Override
    public List<Claim> getAllClaimss() {
        return claimRepository.findAll();
    }

    @Override
    public Claim addClaim(String contractId, String description) {
        Claim claim = new Claim();
        claim.setId(UUID.randomUUID().toString());
        claim.setDate(new Date().toString());
        claim.setCompensation(0);
        claim.setReport("");
        claim.setDescription(description);
        claim.setContract_id(contractId);
        claim.setReport("null");
        claim.setStatus(Claim.Status.REPORTING);
        return claimRepository.save(claim);
    }

    @Override
    public Claim getClaimsById(String contractId) {
        if (claimRepository.getClaimById(contractId) == null) throw new IllegalArgumentException();
        return claimRepository.getClaimById(contractId);
    }

    @Override
    public boolean deleteClaimByID(String id) {
        Claim claim = getClaimsById(id);
        claimRepository.delete(claim);
        return true;
    }

    @Override
    public Claim updateClaim(ClaimDTO claimDTO) {
        Claim claim = new Claim(claimDTO);
        return claimRepository.save(claim);
    }
}

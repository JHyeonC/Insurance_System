package insurance_system_claim_manage_service.service;

import insurance_system_claim_manage_service.jpa.ClaimManage;
import insurance_system_claim_manage_service.jpa.ClaimManageDTO;
import insurance_system_claim_manage_service.jpa.ClaimManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClaimManageServiceImpl implements ClaimManageService{

    private final ClaimManageRepository claimManageRepository;
    @Override
    public List<ClaimManage> getAllClaimsManages() {
        return claimManageRepository.findAll();
    }

    @Override
    public ClaimManage addClaimManage(ClaimManageDTO claimManageDTO) {
        ClaimManage claimManage = new ClaimManage(claimManageDTO);
        return claimManageRepository.save(claimManage);
    }

    @Override
    public ClaimManage getClaimManageByClaimId(String claim_id) {
        return claimManageRepository.getClaimManageById(claim_id);
    }

    @Override
    public boolean deleteClaimByID(String id) {
        ClaimManage claimManage = getClaimManageByClaimId(id);
        claimManageRepository.delete(claimManage);
        return true;
    }

    @Override
    public ClaimManage updateClaimManage(ClaimManageDTO claimManageDTO) {
        ClaimManage claimManage = claimManageRepository.getClaimManageById(claimManageDTO.getClaim_id());
        claimManage.setReviewer(claimManageDTO.getReviewer());
        claimManage.setInvestigator(claimManageDTO.getInvestigator());
        return claimManageRepository.save(claimManage);
    }
}

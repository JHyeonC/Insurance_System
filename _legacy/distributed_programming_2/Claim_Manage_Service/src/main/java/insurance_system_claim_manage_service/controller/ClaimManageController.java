package insurance_system_claim_manage_service.controller;

import insurance_system_claim_manage_service.jpa.ClaimManage;
import insurance_system_claim_manage_service.jpa.ClaimManageDTO;
import insurance_system_claim_manage_service.service.ClaimManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClaimManageController {
    private final ClaimManageService claimManageService;

    @GetMapping("api/claimManages")

    public List<ClaimManage> getAllClaimManages() {
        return claimManageService.getAllClaimsManages();
    }

    @PostMapping("api/claimManages/create")
    public ClaimManage addClaimManage(@RequestBody ClaimManageDTO claimManageDTO) {
        return claimManageService.addClaimManage(claimManageDTO);
    }

    @GetMapping("/claimManage/{id}")
    public ClaimManage getClaimManageById(@PathVariable String id) {
        return claimManageService.getClaimManageByClaimId(id);
    }

    @PostMapping("api/claimManage/update")
    public ClaimManage updateClaimManage(@RequestBody ClaimManageDTO claimManageDTO) {
        return claimManageService.updateClaimManage(claimManageDTO);
    }

    @DeleteMapping("/claimManage/{id}")
    public boolean deleteClaimManageByID(@PathVariable String id) {
        return claimManageService.deleteClaimByID(id);
    }
}

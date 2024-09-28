package insurance_system_claim_service.controller;

import insurance_system_claim_service.jpa.Claim;
import insurance_system_claim_service.jpa.ClaimDTO;
import insurance_system_claim_service.service.ClaimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClaimController {
    private final ClaimService claimService;

    @GetMapping("/claims")
    public List<Claim> getAllClaimss() {
        return claimService.getAllClaimss();
    }

    @PostMapping("/api/claim/create")
    public Claim createClaim(@RequestBody ClaimDTO claimDTO) {
        return claimService.addClaim(claimDTO.getContract_id(), claimDTO.getDescription());
    }

    @GetMapping("/claim/{id}")
    public Claim getClaimsByContractId(@PathVariable String id) {
        return claimService.getClaimsById(id);
    }
    @PutMapping("/claim")
    public Claim updateClaim(@RequestBody ClaimDTO claimDTO) {
        return claimService.updateClaim(claimDTO);
    }
//
    @DeleteMapping("/claim/{id}")
    public boolean deleteClaimByID(@PathVariable String id) {
        return claimService.deleteClaimByID(id);
    }
}


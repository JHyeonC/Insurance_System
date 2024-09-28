package insurance_system_claim_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim , String> {
    @Query("select c from Claim c where c.id = :claim_id")
    Claim getClaimById(@Param("claim_id") String id);

    @Query("SELECT c FROM Claim c WHERE c.status = :status")
    List<Claim> getClaimByStatus(@Param("status") Claim.Status status);
}

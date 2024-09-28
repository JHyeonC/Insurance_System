package insurance_system_customer_service.jpa.claim;

import insurance_system_customer_service.jpa.claim.ClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<ClaimEntity, String> {

    @Query("SELECT c FROM ClaimEntity c WHERE c.status = :status")
    List<ClaimEntity> getClaimsByStatus(@Param("status") ClaimEntity.Status status);
}

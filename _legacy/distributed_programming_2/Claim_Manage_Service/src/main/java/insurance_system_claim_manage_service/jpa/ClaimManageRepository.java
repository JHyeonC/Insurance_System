package insurance_system_claim_manage_service.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClaimManageRepository extends JpaRepository<ClaimManage , String> {
    @Query("select c from ClaimManage c where c.claim_id = :claim_id")
    ClaimManage getClaimManageById(@Param("claim_id") String claim_id);

    @Query("SELECT c FROM ClaimManage c WHERE c.claim_id IN :claimIds")
    List<ClaimManage> findByClaimId(@Param("claimIds") String[] claimIds);
}

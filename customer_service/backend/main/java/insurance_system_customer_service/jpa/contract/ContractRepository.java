package insurance_system_customer_service.jpa.contract;

import insurance_system_customer_service.jpa.contract.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, String> {

    @Query("SELECT c FROM ContractEntity c WHERE c.customer_id = :customerId AND c.product_id = :productId")
    ContractEntity findByCustomerIdAndProductId(@Param("customerId") String customerId, @Param("productId") String productId);

    @Query("SELECT c FROM ContractEntity c WHERE c.customer_id = :id")
    List<ContractEntity> findAllById(@Param("id") String id);
}

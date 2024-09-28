package insurance_system_customer_service.jpa.product;

import insurance_system_customer_service.jpa.product.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    ProductEntity findById(String id);
}

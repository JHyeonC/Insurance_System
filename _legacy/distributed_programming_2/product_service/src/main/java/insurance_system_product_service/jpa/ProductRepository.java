package insurance_system_product_service.jpa;

import insurance_system_product_service.vo.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Product getProductById(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.seniorRate = :seniorRate, p.maleRate = :maleRate, p.femaleRate = :femaleRate," +
            "p.occupationHazardRate = :occupationalHazardRate, p.smokingRate = :smokingRate, p.released = :released WHERE p.id = :id")
    Product updateProduct(@Param("id") String id, @Param("seniorRate") float seniorRate, @Param("maleRate") float maleRate,
                          @Param("femaleRate") float femaleRate, @Param("occupationalHazardRate") float occupationalHazardRate,
                          @Param("smokingRate") float smokingRate, @Param("released") int released);
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id = :id")
    void deleteById(@Param("id") String id);
}
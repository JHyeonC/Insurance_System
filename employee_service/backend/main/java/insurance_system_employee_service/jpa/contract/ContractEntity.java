package insurance_system_employee_service.jpa.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "contract")
public class ContractEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    @Column(name = "product_id", nullable = false)
    private String productId;
    @Column(name = "premium", nullable = false)
    private int premium;


    @Builder
    public ContractEntity(String id, String customerId, String productId, int premium) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.premium = premium;
    }
}

package insurance_system_customer_service.jpa.contract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "contract")
public class ContractEntity {
    @Id
    private String id;
    @Column(name = "customer_id", nullable = false)
    private String customer_id;
    @Column(name = "product_id", nullable = false)
    private String product_id;
    @Column(name = "premium", nullable = false)
    private int premium;

    @Builder
    public ContractEntity(String id, String customer_id, String product_id, int premium){
        this.id = id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.premium = premium;
    }
}

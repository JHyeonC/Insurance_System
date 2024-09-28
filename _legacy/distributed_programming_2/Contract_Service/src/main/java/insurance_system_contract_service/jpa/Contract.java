package insurance_system_contract_service.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contract {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "customer_id", nullable = false)
    private String customerId;
    @Column(name = "product_id", nullable = false)
    private String productId;
    @Column(name = "premium", nullable = false)
    private int premium;

    public Contract(ContractDTO contractDTO) {
        this.id = contractDTO.getId();
        this.customerId = contractDTO.getCustomer_id();
        this.productId = contractDTO.getProduct_id();
        this.premium = contractDTO.getPremium();
    }
}

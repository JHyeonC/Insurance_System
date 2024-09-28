package insurance_system_contract_service.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class ContractDTO {
    private String id;
    private String customer_id;
    private String product_id;
    private int premium;
}

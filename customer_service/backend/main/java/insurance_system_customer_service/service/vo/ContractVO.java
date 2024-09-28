package insurance_system_customer_service.service.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContractVO {

    public ContractVO(String id, String customer_id, String product_id, int premium) {
        this.id = id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.premium = premium;
    }

    public String id;
    public String customer_id;
    public String product_id;
    public int premium;

}

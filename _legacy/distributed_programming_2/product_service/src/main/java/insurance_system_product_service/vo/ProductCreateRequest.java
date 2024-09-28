package insurance_system_product_service.vo;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private String id;
    private String name;
    private int premium;
}

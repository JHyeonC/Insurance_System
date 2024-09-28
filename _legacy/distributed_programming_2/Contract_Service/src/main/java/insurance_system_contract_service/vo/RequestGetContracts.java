package insurance_system_contract_service.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestGetContracts {

    private String customerId;
    private String name;
    private String birth;
    private int gender;
    private int occupational_hazard;
    private int smoking;
}

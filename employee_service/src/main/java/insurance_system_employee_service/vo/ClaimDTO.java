package insurance_system_employee_service.vo;

import lombok.Data;

@Data
public class ClaimDTO {
    public String id;
    public int compensation;
    public String contract_id;
    public String date;
    public String description;
    public String report;
    public String status;
}

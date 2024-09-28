package insurance_system_employee_service.jpa.claim;
import insurance_system_employee_service.service.vo.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="claim")
public class ClaimEntity {



    @Id
    @Column(name = "id" , nullable = false)
    private String id;

    @Column(name = "compensation")
    private int compensation;

    @Column(name = "contract_id" , nullable = false)
    private String contract_id;

    @Column(name = "date" , nullable = false)
    private String date;

    @Column(name = "description" , nullable = false)
    private String description;

    @Column(name = "investigator")
    private String investigator;

    @Column(name = "reviewer")
    private String reviewer;

    @Column(name = "report")
    private String report;
    @Enumerated(EnumType.STRING)
    @Column(name = "status" , nullable = false)
    private Status status;

    public ClaimEntity(String id , int compensation , String contract_id , String date , String description , String investigator , String reviewer ,  String report , String status) {
        this.id = id;
        this.compensation = compensation;
        this.contract_id = contract_id;
        this.date = date;
        this.description = description;
        this.investigator = investigator;
        this.reviewer = reviewer;
        this.report = report;
        this.status = Status.valueOf(status);
    }
}


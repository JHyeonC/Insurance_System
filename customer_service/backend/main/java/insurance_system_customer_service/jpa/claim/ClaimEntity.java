package insurance_system_customer_service.jpa.claim;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "claim")
public class ClaimEntity {

    public enum Status{
        REPORTING, REVIEWING, ACCEPTED, REJECTED, PAID
    }

    @Id
    private String id;
    @Column(name = "compensation")
    private int compensation;
    @Column(name = "contract_id", nullable = false)
    private String contract_id;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "investigator", nullable = false)
    private String investigator;
    @Column(name = "reviewer", nullable = false)
    private String reviewer;
    @Column(name = "report")
    private String report;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Builder
    public ClaimEntity(String id, int compensation, String contract_id, String date, String description, String investigator,
                       String reviewer, String report, Status status){
        this.id = id;
        this.compensation = compensation;
        this.contract_id = contract_id;
        this.date = date;
        this.description = description;
        this.investigator = investigator;
        this.reviewer = reviewer;
        this.report = report;
        this.status = status;
    }
}

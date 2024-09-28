package insurance_system_claim_manage_service.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(ClaimManage.class)
@Table(name = "claim_manage")
public class ClaimManage implements Serializable {

    public ClaimManage(ClaimManageDTO claimManageDTO) {
        this.claim_id = claimManageDTO.getClaim_id();
        this.investigator = claimManageDTO.getInvestigator();
        this.reviewer = claimManageDTO.getReviewer();
    }
    @Id
    @Column(name = "claim_id" , nullable = false)
    private String claim_id;

    @Id
    @Column(name = "investigator" , nullable = false)
    private String investigator;

    @Id
    @Column(name = "reviewer" , nullable = false)
    private String reviewer;

    @Builder
    public ClaimManage(String claim_id, String investigator, String reviewer){
        this.claim_id = claim_id;
        this.investigator = investigator;
        this.reviewer = reviewer;
    }
}

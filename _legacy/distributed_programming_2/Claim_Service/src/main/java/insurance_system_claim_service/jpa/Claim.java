package insurance_system_claim_service.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "claim")
public class Claim {

        public Claim(ClaimDTO claimDTO) {
                this.id = claimDTO.getId();
                this.compensation = claimDTO.getCompensation();
                this.contract_id = claimDTO.getContract_id();
                this.date = claimDTO.getDate();
                this.description = claimDTO.getDescription();
                this.report = claimDTO.getReport();
                this.status = Status.valueOf(claimDTO.getStatus());
        }

        public enum Status {
                REPORTING, REVIEWING, ACCEPTED, REJECTED, PAID;

        }
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

        @Column(name = "report")
        private String report;

        @Column(name = "status" , nullable = false)
        @Enumerated(EnumType.STRING)
        private Status status;
}



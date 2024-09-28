package insurance_system_employee_service.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import insurance_system_employee_service.vo.ClaimManageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class KafkaConsumer {
    private List<ClaimManageDTO> claimManageDTOList;

    public KafkaConsumer(){
        this.claimManageDTOList = new ArrayList<>();
    }

    @KafkaListener(topics = "topic2", groupId = "bunsan")
    public synchronized void getClaimsByStatus(String kafkaMessage){
        try {
            log.info("Message : " + kafkaMessage);
            this.claimManageDTOList = new ObjectMapper().readValue(kafkaMessage, new TypeReference<List<ClaimManageDTO>>(){});
            for(ClaimManageDTO claim : this.claimManageDTOList){
                log.info("ClaimId = {}, investigator = {}, reviewer = {}", claim.getClaim_id(), claim.getInvestigator(), claim.getReviewer());
            }
            notify();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    public synchronized List<ClaimManageDTO> getReportingClaimList(){
        try {
            while (claimManageDTOList.isEmpty()) {
                wait();
            }
            return this.claimManageDTOList;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }
}

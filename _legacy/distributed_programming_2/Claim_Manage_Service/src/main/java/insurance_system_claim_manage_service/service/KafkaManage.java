package insurance_system_claim_manage_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import insurance_system_claim_manage_service.jpa.ClaimManage;
import insurance_system_claim_manage_service.jpa.ClaimManageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaManage {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ClaimManageRepository claimManageRepository;

    public KafkaManage(KafkaTemplate<String, Object> kafkaTemplate, ClaimManageRepository claimManageRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.claimManageRepository = claimManageRepository;
    }

    @KafkaListener(topics = "topic1", groupId = "bunsan")
    public void getReportingOfClaimId(String kafkaMessage){
        log.info("Message = {}", kafkaMessage);
        String[] claimKeyArr = kafkaMessage.split(" ");
        List<ClaimManage> claimManageList = claimManageRepository.findByClaimId(claimKeyArr);
        try{
            String jsonString = new ObjectMapper().writeValueAsString(claimManageList);
            kafkaTemplate.send("topic2", jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @KafkaListener(topics = "topic4", groupId = "bunsan")
    public void addClaimManage(String kafkaMessage){
        log.info("Message : " + kafkaMessage);
        String[] arr = kafkaMessage.split(" ");
        ClaimManage claimManage = ClaimManage.builder()
                .claim_id(arr[0])
                .investigator(arr[1])
                .reviewer(arr[2])
                .build();
        claimManageRepository.save(claimManage);
    }
}
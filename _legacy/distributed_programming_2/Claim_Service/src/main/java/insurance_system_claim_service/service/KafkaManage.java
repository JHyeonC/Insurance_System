package insurance_system_claim_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import insurance_system_claim_service.jpa.Claim;
import insurance_system_claim_service.jpa.ClaimRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class KafkaManage {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ClaimRepository claimRepository;

    public KafkaManage(KafkaTemplate<String, Object> kafkaTemplate, ClaimRepository claimRepository){
        this.kafkaTemplate = kafkaTemplate;
        this.claimRepository = claimRepository;
    }

    @KafkaListener(topics = "topic", groupId = "bunsan")
    public void getReportingClaimList(String kafkaMessage){
        log.info("Message = {}", kafkaMessage);
        Claim.Status status = null;
        if(kafkaMessage.equals("REPORTING")) {status = Claim.Status.REPORTING;}
        List<Claim> getList = claimRepository.getClaimByStatus(status);
        String id = "";
        for(Claim claim : getList){
            id = id + claim.getId() + " ";
        }
        kafkaTemplate.send("topic1", id.trim());
    }

    @KafkaListener(topics = "topic3", groupId = "bunsan")
    public void addClaim(String kafkaMessage){
        try{
            log.info("Message = {}", kafkaMessage);
            Claim claim = new ObjectMapper().readValue(kafkaMessage, new TypeReference<Claim>(){});
            claimRepository.save(claim);
        }catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
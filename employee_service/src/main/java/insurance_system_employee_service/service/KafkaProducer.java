package insurance_system_employee_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import insurance_system_employee_service.vo.ClaimDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaProducer(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper){
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(String kafkaTopic, String status){
        kafkaTemplate.send(kafkaTopic, status);
    }

    public void addClaim(String kafkaTopic, ClaimDTO claim){
        try{
            String jsonString = new ObjectMapper().writeValueAsString(claim);
            kafkaTemplate.send(kafkaTopic, jsonString);
        }catch(JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void addClaimManage(String kafkaTopic, String request){
        kafkaTemplate.send(kafkaTopic, request);
    }
}
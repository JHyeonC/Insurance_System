package insurance_system_customer_service.jpa.metric;

import com.ctc.wstx.shaded.msv_core.driver.textui.Debug;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class ServerMetricCollector {

    private static final Double CPU_USAGE_LIMIT = 0.5;
    private static final int HTTP_REQUEST_COUNT_LIMIT = 30;


    private final CustomerServerMetricRepository customerServerMetricRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public ServerMetricCollector(CustomerServerMetricRepository customerServerMetricRepository) {
        this.customerServerMetricRepository = customerServerMetricRepository;
        this.restTemplate = new RestTemplate();
    }

    private Double getMeasurementValue(String url) throws JsonProcessingException {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            String responseBody = responseEntity.getBody();
            JsonNode jsonNode = new ObjectMapper().readTree(responseBody);
            return jsonNode.get("measurements").get(0).get("value").asDouble();
        } else return null;
    }

    public void alert(CustomerServerMetric metric){
        if (metric.getCpuUsage() > CPU_USAGE_LIMIT) log.warn("CPU usage has exceeded the limit.");
        if (metric.getHttpRequestCount() > HTTP_REQUEST_COUNT_LIMIT) log.warn("HTTP Request Count has exceeded the limit.");
    }

    @Scheduled(fixedRate = 30000)
    public void saveMemoryMetrics() throws JsonProcessingException {
        CustomerServerMetric customerServerMetric = new CustomerServerMetric();
        customerServerMetric.setId(UUID.randomUUID().toString());
        customerServerMetric.setCpuUsage(getMeasurementValue("http://localhost:40021/actuator/metrics/system.cpu.usage"));
        customerServerMetric.setUsedMemory(getMeasurementValue("http://localhost:40021/actuator/metrics/jvm.memory.used"));
        customerServerMetric.setHttpRequestCount(getMeasurementValue("http://localhost:40021/actuator/metrics/http.server.requests"));
        customerServerMetric.setUpTime(getMeasurementValue("http://localhost:40021/actuator/metrics/process.uptime"));
        customerServerMetric.setTimestamp(new Date());
        alert(customerServerMetric);
        customerServerMetricRepository.save(customerServerMetric);
    }
}

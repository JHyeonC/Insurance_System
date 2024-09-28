package insurance_system_customer_service.jpa.metric;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Date;

@Entity
public class CustomerServerMetric {
    @Id
    private String id;
    private Double cpuUsage;
    private Double usedMemory;
    private Double httpRequestCount;
    private Double uptime;
    private Date timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(Double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public Double getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(Double usedMemory) {
        this.usedMemory = usedMemory;
    }

    public Double getHttpRequestCount() {
        return httpRequestCount;
    }

    public void setHttpRequestCount(Double httpRequestCount) {
        this.httpRequestCount = httpRequestCount;
    }

    public Double getUpTime() {
        return uptime;
    }

    public void setUpTime(Double uptime) {
        this.uptime = uptime;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}

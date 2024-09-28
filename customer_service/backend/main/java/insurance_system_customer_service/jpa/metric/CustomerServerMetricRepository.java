package insurance_system_customer_service.jpa.metric;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerServerMetricRepository extends JpaRepository<CustomerServerMetric, Long> {

}

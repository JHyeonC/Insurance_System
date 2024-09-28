package insurance_system_customer_service.jpa.customer;

import insurance_system_customer_service.jpa.customer.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    CustomerEntity findById(String id);

}
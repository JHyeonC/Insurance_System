package insurance_system_customer_service.service;

import insurance_system_customer_service.jpa.customer.CustomerEntity;
import insurance_system_customer_service.jpa.customer.CustomerRepository;
import insurance_system_customer_service.service.vo.CustomerVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerVO login(String inputId, String inputPassword) {
        if (inputId == null || inputPassword == null) return null;
        CustomerEntity entity = customerRepository.findById(inputId);
        if (entity == null) return null;
        if (entity.getPassword() == null) return null;
        if (!inputPassword.equals(entity.getPassword()))
            return null;
        return new CustomerVO(entity.getId(), null, entity.getName(),
                entity.getBirth(), entity.getGender(), entity.getOccupationalHazard(), entity.getSmoking());
    }
}

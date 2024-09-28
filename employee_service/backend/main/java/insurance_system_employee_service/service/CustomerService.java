package insurance_system_employee_service.service;


import insurance_system_employee_service.jpa.customer.CustomerEntity;
import insurance_system_employee_service.jpa.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Float getCustomerStat() {
        List<CustomerEntity> customers = customerRepository.findAll();
        List<Integer> ages = new ArrayList<>();
        for(CustomerEntity customer : customers) {
            String birthdayString = customer.getBirth();
            LocalDate birthday = LocalDate.parse(birthdayString);
            ages.add(Period.between(birthday, LocalDate.now()).getYears());
        }
        int sumAge = 0;
        for(int age : ages) sumAge += age;
        return (float) sumAge/ages.size();
    }
}

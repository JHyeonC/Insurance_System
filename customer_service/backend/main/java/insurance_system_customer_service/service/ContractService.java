package insurance_system_customer_service.service;

import insurance_system_customer_service.jpa.contract.ContractEntity;
import insurance_system_customer_service.jpa.contract.ContractRepository;
import insurance_system_customer_service.jpa.customer.CustomerEntity;
import insurance_system_customer_service.jpa.customer.CustomerRepository;
import insurance_system_customer_service.jpa.product.ProductEntity;
import insurance_system_customer_service.jpa.product.ProductRepository;
import insurance_system_customer_service.service.vo.ContractVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final KieContainer kieContainer;

    public boolean createContract(ContractVO vo) {
        ContractEntity contract = contractRepository.findByCustomerIdAndProductId(vo.getCustomer_id(), vo.getProduct_id());
        if(contract != null) return false;

        CustomerEntity customer = customerRepository.findById(vo.getCustomer_id());
        ProductEntity product = productRepository.findById(vo.getProduct_id());

        if(customer != null && product != null){
            excuteRules(customer, product, vo);
            ContractEntity entity = ContractEntity.builder()
                    .id(UUID.randomUUID().toString())
                    .customer_id(vo.getCustomer_id())
                    .product_id(vo.getProduct_id())
                    .premium(vo.getPremium())
                    .build();
            ContractEntity saveEntity = contractRepository.save(entity);
            if (saveEntity != null) return true;
        }return false;
    }

    public List<ContractVO> getAllContractsByCustomerId(String id) {
        List<ContractEntity> entityList = contractRepository.findAllById(id);
        if (entityList.isEmpty() || entityList == null) return null;
        List<ContractVO> contractVOList = new ArrayList<>();
        for (ContractEntity entity : entityList) {
            ContractVO vo = new ContractVO(entity.getId(), entity.getCustomer_id(), entity.getProduct_id(), entity.getPremium());
            contractVOList.add(vo);
        }
        return contractVOList;
    }

    public void excuteRules(CustomerEntity customer, ProductEntity product, ContractVO vo){
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(customer);
        kieSession.insert(product);
        kieSession.insert(vo);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}

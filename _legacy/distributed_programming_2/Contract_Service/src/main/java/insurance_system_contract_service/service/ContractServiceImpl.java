package insurance_system_contract_service.service;

import insurance_system_contract_service.jpa.Contract;
import insurance_system_contract_service.jpa.ContractDTO;
import insurance_system_contract_service.jpa.ContractRepository;
import insurance_system_contract_service.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;

    @Override
    public List<Contract> getAllContracts(String customerId) {
        System.out.println(customerId);
        List<Contract> contracts = contractRepository.findAll();
        List<Contract> customerContracts = new LinkedList<>();
        for (Contract contract : contracts)
            if (contract.getCustomerId().equals(customerId))
                customerContracts.add(contract);
        return customerContracts;
    }

    @Override
    public Contract addContract(ContractDTO contractDTO) {
        System.out.println(contractDTO);
        Contract contract = new Contract(contractDTO);
        contract.setId("CTRT" + new Random().nextInt(100));
        contract.setPremium(1000); // need IPC
        return contractRepository.save(contract);
    }
//
//    @Override
//    public void deleteContract(String id) {
//        Contract contract = contractRepository.getContractByID(id);
//        contractRepository.delete(contract);
//    }
//
//    @Override
//    public Contract getContractById(String id) {
//        return contractRepository.getContractByID(id);
//    }
//
//
//    @Override
//    public Contract updatePremium(ContractDTO contractDTO) {
//        Contract contract = new Contract(contractDTO);
//        return contractRepository.save(contract);
//    }


}

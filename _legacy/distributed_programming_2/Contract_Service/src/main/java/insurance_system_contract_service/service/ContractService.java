package insurance_system_contract_service.service;

import insurance_system_contract_service.jpa.Contract;
import insurance_system_contract_service.jpa.ContractDTO;

import java.util.List;

public interface ContractService {

    List<Contract> getAllContracts(String customerId);

    Contract addContract(ContractDTO contractDTO);
//
//    void deleteContract(String id);
//
//    public Contract getContractById(String id);
//    Contract updatePremium(ContractDTO contractDTO);


}

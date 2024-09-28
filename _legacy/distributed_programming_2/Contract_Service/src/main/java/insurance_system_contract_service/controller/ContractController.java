package insurance_system_contract_service.controller;

import insurance_system_contract_service.jpa.Contract;
import insurance_system_contract_service.jpa.ContractDTO;
import insurance_system_contract_service.service.ContractService;
import insurance_system_contract_service.service.KafkaProducer;
import insurance_system_contract_service.vo.RequestGetContracts;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ContractController {
    private final ContractService contractService;
    private final KafkaProducer kafkaProducer;

    @GetMapping("api/contracts")
    public List<Contract> getAllContracts(RequestGetContracts request) {
        return contractService.getAllContracts(request.getCustomerId());
    }

    @PostMapping("api/contract/create")
    public Contract addContract(@RequestBody ContractDTO contractDTO) {
        return contractService.addContract(contractDTO);
    }
//
//    @GetMapping("/contract/{id}")
//    public Contract getContractByrId(@PathVariable String id) {
//        return contractService.getContractById(id);
//    }
//
//    @DeleteMapping("/contract/{id}")
//    public void deleteContract(@PathVariable String id) {
//        contractService.deleteContract(id);
//    }
//
//    @PutMapping("/contarct")
//    public Contract updatePremium(@RequestBody ContractDTO contractDTO) {
//        return contractService.updatePremium(contractDTO);
//    }
}

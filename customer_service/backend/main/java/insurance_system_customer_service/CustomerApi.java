package insurance_system_customer_service;

import insurance_system_customer_service.dto.BoardDTO;
import insurance_system_customer_service.dto.ClaimDTO;
import insurance_system_customer_service.dto.ContractDTO;
import insurance_system_customer_service.dto.CustomerDTO;
import insurance_system_customer_service.dto.ProductDTO;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/customer-service")
public interface CustomerApi {

    @GetMapping("/customer/login")
    @ResponseStatus(HttpStatus.OK)
    CustomerDTO login(@RequestBody CustomerDTO dto);

    @PostMapping("/contract")
    @ResponseStatus(HttpStatus.CREATED)
    void createContract(@RequestBody ContractDTO dto);

    @GetMapping("/contracts")
    @ResponseStatus(HttpStatus.OK)
    List<ContractDTO> getAllContractsByCustomerId(@RequestBody CustomerDTO dto);

    @PostMapping("/claim")
    @ResponseStatus(HttpStatus.CREATED)
    void createClaim(@RequestBody ClaimDTO dto);

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    List<ProductDTO> getAllProducts();

    @PostMapping("/board")
    @ResponseStatus(HttpStatus.CREATED)
    void createBoard(@RequestBody BoardDTO dto);

    @GetMapping("/boards")
    @ResponseStatus(HttpStatus.OK)
    List<BoardDTO> getAllBoards();

}

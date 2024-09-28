package insurance_system_customer_service;

import insurance_system_customer_service.dto.*;
import insurance_system_customer_service.service.*;
import insurance_system_customer_service.service.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class CustomerController implements CustomerApi{

    private final CustomerService customerService;
    private final ContractService contractService;
    private final ProductService productService;
    private final BoardService boardService;
    private final ClaimService claimService;

    @Override
    public CustomerDTO login(CustomerDTO dto) {
        CustomerVO vo = customerService.login(dto.id, dto.password);
        if (vo == null) return null;
        return new CustomerDTO(vo.id, vo.password, vo.name, vo.birth, vo.gender, vo.occupational_hazard, vo.smoking);
    }

    @Override
    public void createContract(ContractDTO dto) {
        ContractVO vo = new ContractVO(dto.id, dto.customer_id, dto.product_id, dto.premium);
        contractService.createContract(vo);
    }

    @Override
    public List<ContractDTO> getAllContractsByCustomerId(CustomerDTO dto) {
        List<ContractVO> contractVOs = contractService.getAllContractsByCustomerId(dto.id);
        List<ContractDTO> contractDTOs = new ArrayList<>();
        if (contractVOs != null) {
            for (ContractVO vo : contractVOs) {
                String productName = productService.getProductById(vo.product_id).name;
                contractDTOs.add(new ContractDTO(vo.id, vo.customer_id, vo.product_id, productName, vo.premium));
            }
        }
        return contractDTOs;
    }

    @Override
    public void createClaim(ClaimDTO dto) {
        ClaimVO vo = new ClaimVO(dto.compensation, dto.contract_id, dto.description);
        claimService.createClaim(vo);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductVO> productVOs = productService.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        if (productVOs != null) {
            for (ProductVO vo : productVOs)
                productDTOs.add(new ProductDTO(vo.id, vo.name, vo.premium, vo.senior_rate, vo.male_rate, vo.female_rate,
                        vo.occupational_hazard_rate, vo.smoking_rate, vo.released));
        }
        return productDTOs;
    }

    @Override
    public void createBoard(BoardDTO dto) {
        BoardVO vo = new BoardVO(dto.author, dto.title, dto.content);
        boardService.createBoard(vo);
    }

    @Override
    public List<BoardDTO> getAllBoards() {
        List<BoardVO> boardVOs = boardService.getAllBoards();
        List<BoardDTO> boardDTOs = new ArrayList<>();
        if (boardVOs != null) {
            for (BoardVO vo : boardVOs)
                boardDTOs.add(new BoardDTO(vo.id, vo.author, vo.title, vo.content, vo.answer, vo.answerer, vo.is_answered));
        }
        return boardDTOs;
    }

}

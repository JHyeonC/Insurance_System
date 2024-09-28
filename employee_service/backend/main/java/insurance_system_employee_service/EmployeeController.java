package insurance_system_employee_service;

import insurance_system_employee_service.dto.*;
import insurance_system_employee_service.service.*;
import insurance_system_employee_service.service.exception.SameNameException;
import insurance_system_employee_service.service.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee-service")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ProductService productService;
    private final BoardService boardService;
    private final ClaimService claimService;
    private final CustomerService customerService;

    @GetMapping("employee/login")
    public EmployeeDTO login(EmployeeDTO dto) {
        System.out.println(dto);
        EmployeeVO vo = employeeService.login(new EmployeeVO(dto.id, dto.password, null, -1, null));
        if (vo == null) return null;
        return new EmployeeDTO(vo.id, null, vo.name, vo.gender, vo.department);
    }

    @PostMapping("product/create")
    public ProductDTO createProduct(ProductDTO dto) throws SameNameException {
        System.out.println(dto);
        ProductVO vo = productService.developProduct(new ProductVO(dto.getId(), dto.getName(), dto.getPremium(), -1, -1, -1, -1, -1, 0));
        return new ProductDTO(vo.getId() , dto.getName(), dto.getPremium(), dto.getSenior_rate(), dto.getMale_rate() , dto.getFemale_rate(), dto.getOccupational_hazard_rate(), dto.getSmoking_rate() ,dto.getReleased());
    }

    @GetMapping("product/get_all")
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        List<ProductVO> productVOS = productService.getAllProduct();
        for (ProductVO product : productVOS) {
            products.add(new ProductDTO(
                    product.getId()
                    , product.getName()
                    , product.getPremium()
                    , product.getSeniorRate()
                    , product.getMaleRate()
                    , product.getFemaleRate()
                    , product.getOccupationHazardRate()
                    , product.getSmokingRate()
                    , product.getReleased()
            ));
        }
        return products;
    }


    @PostMapping("product/underwrite")
    public boolean underwrite(ProductDTO dto) {
        System.out.println(dto);
        ProductVO productVO = new ProductVO(dto.getId()
                , dto.getSenior_rate()
                , dto.getMale_rate()
                , dto.getFemale_rate()
                , dto.getOccupational_hazard_rate()
                , dto.getSmoking_rate()
        );
        return productService.underwrite(productVO);
    }

    @GetMapping("board/get_all")
    public List<BoardDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    @PostMapping("board/answer")
    public boolean answer(BoardDTO dto) {
        System.out.println(dto);
        BoardVO boardVO = new BoardVO(
                dto.getId()
                , dto.getAuthor()
                , dto.getTitle()
                , dto.getContent()
                , dto.getAnswer()
                , dto.getAnswerer()
                , dto.getIs_answered()
        );
        return boardService.createAnswer(boardVO);
    }

    @GetMapping("claim/get_all_by_employee_id")
    public List<ClaimDTO> getManagingClaims(EmployeeDTO dto) {
        EmployeeVO employeeVO = new EmployeeVO(dto.getId());
        List<ClaimVO> claimVOS = claimService.getManagingClaims(employeeVO);
        List<ClaimDTO> claimDTOS = new ArrayList<>();
        for (ClaimVO claim : claimVOS) {
            claimDTOS.add(new ClaimDTO(claim.getId()
                    , claim.getCompensation()
                    , claim.getContract_id()
                    , claim.getDate()
                    , claim.getDescription()
                    , claim.getInvestigator()
                    , claim.getReviewer()
                    , claim.getReport()
                    , claim.getStatus().toString()));
        }
        return claimDTOS;
    }

    @PostMapping("claim/submit_report")
    public boolean submitReport(ClaimDTO dto) {
        System.out.println(dto);
        ClaimVO claimVO = new ClaimVO(dto.getId()
                , dto.getCompensation()
                , dto.getReport());
        return claimService.submitReport(claimVO);
    }

    @PostMapping("claim/review_claim")
    public boolean reviewClaim(ClaimDTO dto) {
        ClaimVO claimVO = new ClaimVO(dto.getId(), dto.getStatus());
        return claimService.reviewClaim(claimVO);
    }

    @GetMapping("claim/get_accepted")
    public List<ClaimDTO> getAcceptedClaims() {
        List<ClaimDTO> claimDTOS = new ArrayList<>();
        List<ClaimVO> claimVOS = claimService.getClaimsByStatus(Status.ACCEPTED);
        System.out.println(claimVOS.size());
        for (ClaimVO claimVO : claimVOS) {
            claimDTOS.add(new ClaimDTO(claimVO.getId()
                    , claimVO.getCompensation()
                    , claimVO.getContract_id()
                    , claimVO.getDate()
                    , claimVO.getDescription()
                    , claimVO.getInvestigator()
                    , claimVO.getReviewer()
                    , claimVO.getReport()
                    , claimVO.getStatus()));
        }
        return claimDTOS;
    }

    @PostMapping("claim/pay_compensation")
    public boolean payCompensation(ClaimDTO dto) {
        System.out.println(dto);
        ClaimVO claimVO = new ClaimVO(dto.getId());
        return claimService.payCompensation(claimVO);
    }

    @GetMapping("customer/get_statistics")
    public CustomerStatisticsDTO getCustomerStatistics() {
        CustomerStatisticsDTO dto = new CustomerStatisticsDTO();
        dto.average_age = customerService.getCustomerStat();
        return dto;
    }

    @GetMapping("claim/get_statistics")
    public ClaimStatisticsDTO getClaimStatistics() {
        ClaimStatisticsDTO dto = new ClaimStatisticsDTO();
        dto.average_compensation = claimService.getClaimStatistics();
        return dto;
    }

}

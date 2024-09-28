package insurance_system_employee_service.service;


import insurance_system_employee_service.jpa.product.ProductEntity;
import insurance_system_employee_service.jpa.product.ProductRepository;
import insurance_system_employee_service.service.exception.SameNameException;
import insurance_system_employee_service.service.vo.ClaimVO;
import insurance_system_employee_service.service.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final KieContainer kieContainer;


    public List<ProductVO> getAllProduct() {
        List<ProductVO> products = new ArrayList<>();
        List<ProductEntity> productEntities = productRepository.findAll();
        for(ProductEntity product : productEntities) {
            products.add(new ProductVO(
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

    public boolean underwrite(ProductVO vo) {
        if(vo.getOccupationHazardRate()<=0 || vo.getSeniorRate()<=0 || vo.getMaleRate() <=0 || vo.getFemaleRate()<=0) return false;
        ProductEntity temp = productRepository.getProductById(vo.getId());
         ProductEntity product = ProductEntity.builder()
                 .id(temp.getId())
                 .name(temp.getName())
                 .premium(temp.getPremium())
                .occupationHazardRate(vo.getOccupationHazardRate())
                .seniorRate(vo.getSeniorRate())
                .femaleRate(vo.getFemaleRate())
                .maleRate(vo.getMaleRate())
                .build();
         excuteRules(product);
        productRepository.save(product);
        return true;
    }

    public ProductVO developProduct(ProductVO vo) throws SameNameException {
        if(vo.getName().trim().length() == 0) throw new NullPointerException(); //상품 이름이 빈 칸일 떄
        List<ProductVO> temp =getAllProduct();
        try {
            for (ProductVO product : temp)
                if (vo.getName().trim().equals(product.getName().trim()))
                    throw new SameNameException(vo.getName(), product.getId()); // 같은 이름의 상품이 존재할떄
        } catch (SameNameException e) {
            return e.sameProductIsAlreadyExist();
        }
        String pID = temp.get(temp.size()-1).getId();
        pID = pID.substring(1).trim();
        ProductEntity product = ProductEntity.builder()
                .id("P"+(Integer.parseInt(pID)+1))
                .name(vo.getName().trim())
                .premium(vo.getPremium())
                .occupationHazardRate(-1)
                .seniorRate(-1)
                .femaleRate(-1)
                .maleRate(-1)
                .released(0)
                .build();
        vo.setId(product.getId());
        productRepository.save(product);
        return vo;
    }

    public void excuteRules(ProductEntity product) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(product);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}

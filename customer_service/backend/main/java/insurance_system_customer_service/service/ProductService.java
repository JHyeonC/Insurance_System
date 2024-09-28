package insurance_system_customer_service.service;

import insurance_system_customer_service.jpa.product.ProductEntity;
import insurance_system_customer_service.jpa.product.ProductRepository;
import insurance_system_customer_service.service.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductVO getProductById(String id) {
        ProductEntity entity = productRepository.findById(id);
        if (entity == null) return null;
        return new ProductVO(entity.id, entity.name, entity.premium, entity.senior_rate, entity.male_rate, entity.female_rate,
                entity.occupational_hazard_rate,
                entity.senior_rate, entity.released);
    }

    public List<ProductVO> getAllProducts() {
        List<ProductEntity> entityList = productRepository.findAll();
        if(entityList.isEmpty() || entityList == null) return null;
        List<ProductVO> voList = new ArrayList<>();
        for(ProductEntity entity : entityList){
            ProductVO vo = new ProductVO(entity.getId(), entity.getName(), entity.getPremium(),
                    entity.getSenior_rate(), entity.getMale_rate(), entity.getFemale_rate(),
                    entity.getOccupational_hazard_rate(), entity.getSmoking_rate(), entity.getReleased());
            voList.add(vo);
        }
        return voList;
    }
}

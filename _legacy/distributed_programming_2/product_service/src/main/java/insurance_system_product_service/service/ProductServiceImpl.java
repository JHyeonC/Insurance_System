package insurance_system_product_service.service;

import insurance_system_product_service.jpa.ProductRepository;
import insurance_system_product_service.vo.Product;
import insurance_system_product_service.vo.ProductCreateRequest;
import insurance_system_product_service.vo.ProductDeleteRequest;
import insurance_system_product_service.vo.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public Product developProduct(ProductCreateRequest request){
        Product product = Product.builder()
                .id(request.getId())
                .name(request.getName())
                .premium(request.getPremium())
                .released(0)
                .build();
        return productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product updateProduct(ProductUpdateRequest request){
        Product product = productRepository.getProductById(request.getId());
        if(product != null) {
            return productRepository.updateProduct(request.getId(), request.getSeniorRate(), request.getMaleRate(),
                    request.getFemaleRate(), request.getOccupationHazardRate(), request.getSmokingRate(), request.getReleased());
        }
        return null;
    }

    public void deleteProduct(ProductDeleteRequest request){
        Product product = productRepository.getProductById(request.getId());
        if(product != null) {
            productRepository.deleteById(request.getId());
        }
    }
}
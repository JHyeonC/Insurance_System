package insurance_system_product_service.service;

import insurance_system_product_service.vo.Product;
import insurance_system_product_service.vo.ProductCreateRequest;
import insurance_system_product_service.vo.ProductDeleteRequest;
import insurance_system_product_service.vo.ProductUpdateRequest;

import java.util.List;

public interface ProductService {
    Product developProduct(ProductCreateRequest request);
    List<Product> getAllProducts();
    Product updateProduct(ProductUpdateRequest request);
    void deleteProduct(ProductDeleteRequest request);
}

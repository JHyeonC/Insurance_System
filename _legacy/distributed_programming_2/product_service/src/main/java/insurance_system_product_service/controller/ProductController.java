package insurance_system_product_service.controller;

import insurance_system_product_service.service.ProductServiceImpl;
import insurance_system_product_service.vo.Product;
import insurance_system_product_service.vo.ProductCreateRequest;
import insurance_system_product_service.vo.ProductDeleteRequest;
import insurance_system_product_service.vo.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("/Product")
    public Product developProduct(@RequestBody ProductCreateRequest request) {
        return productService.developProduct(request);
    }

    @GetMapping("api/products")
    public List<Product> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/Product/update")
    public Product updateProduct(@RequestBody ProductUpdateRequest request) {
        return productService.updateProduct(request);
    }

    @PostMapping("/Product/delete")
    public void deleteEmployee(@RequestBody ProductDeleteRequest request) {
        productService.deleteProduct(request);
    }
}

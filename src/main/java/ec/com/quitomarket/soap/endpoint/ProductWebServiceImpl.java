package ec.com.quitomarket.soap.endpoint;

import ec.com.quitomarket.modules.catalog.domain.Category;
import ec.com.quitomarket.modules.catalog.domain.Product;
import ec.com.quitomarket.modules.catalog.service.ProductService;
import ec.com.quitomarket.soap.dto.ProductSoapDto;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@WebService(
        endpointInterface = "ec.com.quitomarket.soap.endpoint.ProductWebService",
        serviceName       = "ProductService",
        portName          = "ProductPort",
        targetNamespace   = "http://soap.quitomarket.com.ec/"
)
public class ProductWebServiceImpl implements ProductWebService {

    private final ProductService productService;

    @Override
    public ProductSoapDto createProduct(ProductSoapDto dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(Category.builder().name(dto.getCategoryName()).build())
                .build();
        return toSoapDto(productService.save(product));
    }

    @Override
    public ProductSoapDto getProductById(Long id) {
        return toSoapDto(productService.findById(id));
    }

    @Override
    public List<ProductSoapDto> getAllProducts() {
        return productService.findAll().stream()
                .map(this::toSoapDto)
                .collect(Collectors.toList());
    }

    private ProductSoapDto toSoapDto(Product p) {
        ProductSoapDto dto = new ProductSoapDto();
        dto.setId(p.getId());
        dto.setName(p.getName());
        dto.setPrice(p.getPrice());
        dto.setStock(p.getStock());
        if (p.getCategory() != null) {
            dto.setCategoryId(p.getCategory().getId());
            dto.setCategoryName(p.getCategory().getName());
        }
        return dto;
    }
}

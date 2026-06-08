package ec.com.quitomarket.modules.catalog.controller;

import ec.com.quitomarket.modules.catalog.domain.Product;
import ec.com.quitomarket.modules.catalog.dto.ProductRequestDTO;
import ec.com.quitomarket.modules.catalog.dto.ProductResponseDTO;
import ec.com.quitomarket.modules.catalog.mapper.ProductMapper;
import ec.com.quitomarket.modules.catalog.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductResponseDTO> getAll() {
        return productMapper.toResponseDtoList(productService.findAll());
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDTO create(@Valid @RequestBody ProductRequestDTO requestDto) {
        Product product = productMapper.toEntity(requestDto);
        return productMapper.toResponseDto(productService.save(product));
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO requestDto) {
        Product product = productService.findById(id);
        productMapper.updateEntity(requestDto, product);
        return productMapper.toResponseDto(productService.save(product));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}

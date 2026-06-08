package ec.com.quitomarket.modules.catalog.mapper;

import ec.com.quitomarket.modules.catalog.domain.Product;
import ec.com.quitomarket.modules.catalog.dto.ProductRequestDTO;
import ec.com.quitomarket.modules.catalog.dto.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponseDTO toResponseDto(Product product);
    
    List<ProductResponseDTO> toResponseDtoList(List<Product> products);

    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductRequestDTO requestDto);

    @Mapping(source = "categoryId", target = "category.id")
    void updateEntity(ProductRequestDTO requestDto, @MappingTarget Product product);
}

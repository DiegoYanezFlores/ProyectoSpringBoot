package ec.com.quitomarket.modules.catalog.mapper;

import ec.com.quitomarket.modules.catalog.domain.Product;
import ec.com.quitomarket.modules.catalog.dto.ProductRequestDTO;
import ec.com.quitomarket.modules.catalog.dto.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Builder; // Importación indispensable para desactivar el builder

import java.util.List;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true) // <-- AQUÍ SE ELIMINA EL ERROR DE RAÍZ
)
public interface ProductMapper {

    @Mapping(source = "category.name", target = "categoryName")
    ProductResponseDTO toResponseDto(Product product);

    List<ProductResponseDTO> toResponseDtoList(List<Product> products);

    // Ignoramos el id porque al crear un nuevo producto, PostgreSQL lo autogenera
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryId", target = "category.id")
    Product toEntity(ProductRequestDTO requestDto);

    // Ignoramos el id para que no intente sobreescribir la llave primaria del producto al actualizar
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "categoryId", target = "category.id")
    void updateEntity(ProductRequestDTO requestDto, @MappingTarget Product product);
}
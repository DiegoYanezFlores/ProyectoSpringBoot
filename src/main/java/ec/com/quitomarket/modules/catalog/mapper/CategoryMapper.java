package ec.com.quitomarket.modules.catalog.mapper;

import ec.com.quitomarket.modules.catalog.domain.Category;
import ec.com.quitomarket.modules.catalog.dto.CategoryRequestDTO;
import ec.com.quitomarket.modules.catalog.dto.CategoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Builder; // Importación obligatoria de MapStruct

import java.util.List;

@Mapper(
        componentModel = "spring",
        builder = @Builder(disableBuilder = true) // Resuelve el conflicto principal con Lombok
)
public interface CategoryMapper {

    CategoryResponseDTO toResponseDto(Category category);

    List<CategoryResponseDTO> toResponseDtoList(List<Category> categories);

    // Ignoramos 'id' y 'products' porque el RequestDTO de creación no suele tenerlos,
    // evitando así los molestos COMPILATION WARNINGS de Maven
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toEntity(CategoryRequestDTO requestDto);

    // Al actualizar, el ID ya viene en la URL o entidad, e ignoramos la lista de productos
    // para que no se borren las relaciones existentes en la base de datos
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntity(CategoryRequestDTO requestDto, @MappingTarget Category category);
}
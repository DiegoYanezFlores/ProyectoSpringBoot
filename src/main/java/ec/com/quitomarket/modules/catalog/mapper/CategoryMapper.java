package ec.com.quitomarket.modules.catalog.mapper;

import ec.com.quitomarket.modules.catalog.domain.Category;
import ec.com.quitomarket.modules.catalog.dto.CategoryRequestDTO;
import ec.com.quitomarket.modules.catalog.dto.CategoryResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryResponseDTO toResponseDto(Category category);
    List<CategoryResponseDTO> toResponseDtoList(List<Category> categories);
    Category toEntity(CategoryRequestDTO requestDto);
    void updateEntity(CategoryRequestDTO requestDto, @MappingTarget Category category);
}

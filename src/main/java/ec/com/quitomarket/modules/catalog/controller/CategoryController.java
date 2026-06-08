package ec.com.quitomarket.modules.catalog.controller;

import ec.com.quitomarket.modules.catalog.domain.Category;
import ec.com.quitomarket.modules.catalog.dto.CategoryRequestDTO;
import ec.com.quitomarket.modules.catalog.dto.CategoryResponseDTO;
import ec.com.quitomarket.modules.catalog.mapper.CategoryMapper;
import ec.com.quitomarket.modules.catalog.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryResponseDTO> getAll() {
        return categoryMapper.toResponseDtoList(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getById(@PathVariable Long id) {
        return categoryMapper.toResponseDto(categoryService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDTO create(@Valid @RequestBody CategoryRequestDTO requestDto) {
        Category category = categoryMapper.toEntity(requestDto);
        return categoryMapper.toResponseDto(categoryService.save(category));
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO update(@PathVariable Long id, @Valid @RequestBody CategoryRequestDTO requestDto) {
        Category category = categoryService.findById(id);
        categoryMapper.updateEntity(requestDto, category);
        return categoryMapper.toResponseDto(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}

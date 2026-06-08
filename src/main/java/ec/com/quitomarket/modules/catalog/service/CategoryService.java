package ec.com.quitomarket.modules.catalog.service;

import ec.com.quitomarket.exception.ResourceNotFoundException;
import ec.com.quitomarket.modules.catalog.domain.Category;
import ec.com.quitomarket.modules.catalog.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
    }

    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public void delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}

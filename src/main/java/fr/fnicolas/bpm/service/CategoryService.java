package fr.fnicolas.bpm.service;

import fr.fnicolas.bpm.entity.Category;
import fr.fnicolas.bpm.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Optional<Category> getCategory(final long id) {
        return this.categoryRepository.findById(id);
    }

    @Transactional
    public Category createCategory(final Category category) {
        return this.categoryRepository.save(category);
    }

}

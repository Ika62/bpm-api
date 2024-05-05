package fr.fnicolas.bpm.service;

import fr.fnicolas.bpm.entity.Category;
import fr.fnicolas.bpm.entity.Product;
import fr.fnicolas.bpm.repository.CategoryRepository;
import fr.fnicolas.bpm.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Page<Product> findProducts(final long categoryId, final int pageSize, final int pageNumber) {
        final Optional<Category> categoryOpt = this.categoryRepository.findById(categoryId);
        if (categoryOpt.isEmpty()) {
            throw new EntityNotFoundException(STR."Category \{ categoryId } was not found");
        }
        return this.productRepository.findAllByCategoryId(categoryId, Pageable.ofSize(pageSize).withPage(pageNumber));
    }

    @Transactional
    public Product addProductToCategory(@PathVariable("categoryId") final long categoryId,
                                                  @PathVariable("productId") final long productId) {
        final Optional<Product> productOpt = this.productRepository.findById(productId);
        final Optional<Category> categoryOpt = this.categoryRepository.findById(categoryId);
        if (productOpt.isPresent() && categoryOpt.isPresent()) {
            return this.saveProductCategory(productOpt.get(), categoryOpt.get());
        } else {
            throw new EntityNotFoundException(STR."Product \{productId} or category \{categoryId} was not found");
        }
    }

    private Product saveProductCategory(final Product product, final Category category) {
        product.setCategory(category);
        return this.productRepository.save(product);
    }
}

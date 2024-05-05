package fr.fnicolas.bpm.service;

import fr.fnicolas.bpm.entity.Product;
import fr.fnicolas.bpm.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Optional<Product> getProduct(final long id) {
        return this.productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(final Product product) {
        return this.productRepository.save(product);
    }

}

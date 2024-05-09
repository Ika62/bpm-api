package fr.fnicolas.bpm.service;

import fr.fnicolas.bpm.dto.DiscountCreationRequest;
import fr.fnicolas.bpm.entity.Discount;
import fr.fnicolas.bpm.entity.Product;
import fr.fnicolas.bpm.repository.DiscountRepository;
import fr.fnicolas.bpm.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;

    public List<Discount> findAll() {
        return discountRepository.findAll();
    }

    @Transactional
    public Discount createDiscount(final DiscountCreationRequest discountCreationRequest) {
        final Set<Product> products = productRepository.findAllByCategoryIdIn(discountCreationRequest.getCategories());
        final Discount discount = discountCreationRequest.getDiscount();
        discount.setId(null);
        discount.setProducts(products);
        return discountRepository.save(discount);
    }

}

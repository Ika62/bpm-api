package fr.fnicolas.bpm.repository;

import fr.fnicolas.bpm.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCategoryId(final Long categoryId, final Pageable pageable);

    Set<Product> findAllByCategoryIdIn(final List<Long> categoryIds);
}

package fr.fnicolas.bpm.repository;

import fr.fnicolas.bpm.entity.Discount;
import fr.fnicolas.bpm.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

}

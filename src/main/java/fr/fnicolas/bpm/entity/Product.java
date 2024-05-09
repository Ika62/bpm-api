package fr.fnicolas.bpm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private double price;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "products")
    private Set<Discount> discounts;

}

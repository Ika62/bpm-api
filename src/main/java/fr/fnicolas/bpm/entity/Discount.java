package fr.fnicolas.bpm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
public class Discount {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;
    @Min(1)
    @Max(100)
    private Integer percentage;
    @Min(0)
    private Double flat;

    @ManyToMany
    @JoinTable
    @JsonIgnore
    private Set<Product> products;

    @AssertFalse(message = "Discount should only have percentage or flat value")
    public boolean hasDiscountConflictingValues() {
        return (percentage != null && flat != null);
    }

    @AssertFalse(message = "Discount should have a value")
    public boolean hasDiscountNoValue() {
        return percentage == null && flat == null;
    }
}

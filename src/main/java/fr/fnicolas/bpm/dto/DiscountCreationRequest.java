package fr.fnicolas.bpm.dto;

import fr.fnicolas.bpm.entity.Discount;
import jakarta.validation.Valid;
import lombok.Data;

import java.util.List;

@Data
public class DiscountCreationRequest {
    private List<Long> categories;
    private List<Long> includedProductIds;
    private List<Long> excludedProductIds;
    @Valid
    private Discount discount;
}

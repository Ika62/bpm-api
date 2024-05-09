package fr.fnicolas.bpm.controller;

import fr.fnicolas.bpm.dto.DiscountCreationRequest;
import fr.fnicolas.bpm.entity.Discount;
import fr.fnicolas.bpm.service.DiscountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
@Validated
public class DiscountController {

    private final DiscountService discountService;


    @Operation(description = "Create a new discount",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Discount was created successfully")
            })
    @PostMapping
    public Discount createDiscount(@Valid @RequestBody final DiscountCreationRequest discountCreationRequest) {
        return this.discountService.createDiscount(discountCreationRequest);
    }


    @Operation(description = "Get discounts",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Discounts found")
            })
    @GetMapping
    public List<Discount> getDiscounts() {
        return this.discountService.findAll();
    }
}

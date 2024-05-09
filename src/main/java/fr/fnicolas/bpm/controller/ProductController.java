package fr.fnicolas.bpm.controller;

import fr.fnicolas.bpm.entity.Product;
import fr.fnicolas.bpm.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(
            description = "Gets a product by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product was found"),
                    @ApiResponse(responseCode = "404", description = "The product was not found")
            })
    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable("productId") final long productId) {
        final Optional<Product> product = this.productService.getProduct(productId);
        return product.orElseThrow(() -> new EntityNotFoundException(STR."Product \{productId} was not found"));
    }

    @Operation(description = "Create a new product",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product was created successfully")
            })
    @PostMapping
    public Product createProduct(@RequestBody final Product product) {
        return this.productService.createProduct(product);
    }

}

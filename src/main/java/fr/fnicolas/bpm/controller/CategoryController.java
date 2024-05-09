package fr.fnicolas.bpm.controller;

import fr.fnicolas.bpm.entity.Category;
import fr.fnicolas.bpm.entity.Product;
import fr.fnicolas.bpm.service.CategoryService;
import fr.fnicolas.bpm.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private final PagedResourcesAssembler<Product> pagedResourcesAssembler;

    @Operation(description = "Gets a category by id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Category was found"),
                    @ApiResponse(responseCode = "404", description = "Category was not found")
            })
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") final long categoryId) {
        final Optional<Category> category = this.categoryService.getCategory(categoryId);
        return category.orElseThrow(() -> new EntityNotFoundException(STR."Category \{categoryId} was not found"));
    }

    @Operation(description = "Create a new category",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Category created successfully")
            })
    @PostMapping
    public Category createCategory(@RequestBody final Category category) {
        return this.categoryService.createCategory(category);
    }

    @Operation(description = "Find products in a category",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Products returned")
            })
    @GetMapping("/{categoryId}/products")
    public PagedModel<EntityModel<Product>> findProducts(@PathVariable("categoryId") final long categoryId,
                                                         @RequestParam(value = "size", defaultValue = "10") final int size,
                                                         @RequestParam(value = "page", defaultValue = "0") final int page) {
        final var products = this.productService.findProducts(categoryId, size, page);
        return this.pagedResourcesAssembler.toModel(products);
    }

    @Operation(description = "Add a product in a category",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Product was added to the category"),
                    @ApiResponse(responseCode = "404", description = "Product or category was not found")
            })
    @PostMapping("/categories/{categoryId}/products/{productId}")
    public Product addProductToCategory(@PathVariable("categoryId") final long categoryId,
                                        @PathVariable("productId") final long productId) {
        return this.productService.addProductToCategory(productId, categoryId);
    }
}

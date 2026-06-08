package ec.com.quitomarket.modules.catalog.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    @Min(0)
    private Double price;

    @NotNull(message = "Stock is required")
    @Min(0)
    private Integer stock;

    @NotNull(message = "Category ID is required")
    private Long categoryId;
}

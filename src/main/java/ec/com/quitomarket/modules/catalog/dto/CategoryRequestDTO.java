package ec.com.quitomarket.modules.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;
}

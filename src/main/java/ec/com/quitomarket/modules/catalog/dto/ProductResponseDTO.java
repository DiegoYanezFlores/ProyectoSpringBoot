package ec.com.quitomarket.modules.catalog.dto;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private String categoryName;
}

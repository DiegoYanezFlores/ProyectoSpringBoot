package ec.com.quitomarket.soap.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductSoapDto {
    private Long id;
    private String name;
    private Double price;
    private Integer stock;
    private Long categoryId;
    private String categoryName;
}

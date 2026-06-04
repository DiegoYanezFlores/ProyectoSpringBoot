package org.example.dto.soap;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(namespace = "http://org.example/soap", name = "getProductoRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetProductoRequest {

    @XmlElement(namespace = "http://org.example/soap")
    private Long id;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
package org.example.dto.soap;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(namespace = "http://org.example/soap", name = "getProductoResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetProductoResponse {

    @XmlElement(namespace = "http://org.example/soap")
    private Long id;
    @XmlElement(namespace = "http://org.example/soap")
    private String nombre;
    @XmlElement(namespace = "http://org.example/soap")
    private Double precio;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}
package org.example.endpoint;

import org.example.dto.soap.GetProductoRequest;
import org.example.dto.soap.GetProductoResponse;
import org.example.entity.Producto;
import org.example.service.interfaces.IProductoService; // Ajusta según el nombre de tu interfaz
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ProductoEndpoint {

    private static final String NAMESPACE_URI = "http://org.example/soap";

    @Autowired
    private IProductoService productoService; // REUTILIZAMOS NUESTRO SERVICIO EXISTENTE

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductoRequest")
    @ResponsePayload
    public GetProductoResponse getProducto(@RequestPayload GetProductoRequest request) {
        GetProductoResponse response = new GetProductoResponse();

        // Llamamos a la lógica que ya lee de PostgreSQL (Inyección de dependencias limpia)
        Producto producto = productoService.findById(request.getId());

        // Mapeamos los datos de la Entidad al DTO XML de respuesta
        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());

        return response;
    }
}
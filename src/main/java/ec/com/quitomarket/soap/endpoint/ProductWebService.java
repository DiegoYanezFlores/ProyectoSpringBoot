package ec.com.quitomarket.soap.endpoint;

import ec.com.quitomarket.soap.dto.ProductSoapDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService(targetNamespace = "http://soap.quitomarket.com.ec/")
public interface ProductWebService {

    @WebMethod
    ProductSoapDto createProduct(@WebParam(name = "product") ProductSoapDto dto);

    @WebMethod
    ProductSoapDto getProductById(@WebParam(name = "id") Long id);

    @WebMethod
    List<ProductSoapDto> getAllProducts();
}

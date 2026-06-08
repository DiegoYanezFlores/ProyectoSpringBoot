package ec.com.quitomarket.config;

import ec.com.quitomarket.soap.endpoint.ProductWebService;
import lombok.RequiredArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.xml.ws.Endpoint;

@Configuration
@RequiredArgsConstructor
public class CxfConfig {

    private final Bus bus;
    private final ProductWebService productWebService;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, productWebService);
        endpoint.publish("/products");
        return endpoint;
    }
}

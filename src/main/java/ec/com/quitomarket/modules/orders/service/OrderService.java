package ec.com.quitomarket.modules.orders.service;

import ec.com.quitomarket.exception.BusinessException;
import ec.com.quitomarket.modules.catalog.domain.Product;
import ec.com.quitomarket.modules.catalog.service.ProductService;
import ec.com.quitomarket.modules.orders.domain.Order;
import ec.com.quitomarket.modules.orders.domain.OrderItem;
import ec.com.quitomarket.modules.orders.repository.OrderRepository;
import ec.com.quitomarket.modules.users.domain.Customer;
import ec.com.quitomarket.modules.users.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CustomerRepository customerRepository;

    @Transactional
    public Order placeOrder(Order order) {
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new BusinessException("Customer not found"));
        
        order.setCustomer(customer);
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("PROCESSED");

        double total = 0.0;
        for (OrderItem item : order.getItems()) {
            Product product = productService.findById(item.getProduct().getId());
            
            if (product.getStock() < item.getQuantity()) {
                throw new BusinessException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - item.getQuantity());
            productService.save(product);

            item.setUnitPrice(product.getPrice());
            item.setSubtotal(product.getPrice() * item.getQuantity());
            item.setProduct(product);
            item.setOrder(order);
            total += item.getSubtotal();
        }

        order.setTotalAmount(total);
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}

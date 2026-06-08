package ec.com.quitomarket.modules.catalog.repository;

import ec.com.quitomarket.modules.catalog.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

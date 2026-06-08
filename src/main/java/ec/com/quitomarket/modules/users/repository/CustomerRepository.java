package ec.com.quitomarket.modules.users.repository;

import ec.com.quitomarket.modules.users.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

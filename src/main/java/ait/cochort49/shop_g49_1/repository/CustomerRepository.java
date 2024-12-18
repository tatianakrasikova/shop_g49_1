package ait.cochort49.shop_g49_1.repository;


import ait.cochort49.shop_g49_1.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

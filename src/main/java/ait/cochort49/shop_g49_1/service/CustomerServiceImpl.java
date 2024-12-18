package ait.cochort49.shop_g49_1.service;



import ait.cochort49.shop_g49_1.model.entity.Cart;
import ait.cochort49.shop_g49_1.model.entity.Customer;
import ait.cochort49.shop_g49_1.repository.CustomerRepository;
import ait.cochort49.shop_g49_1.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public Customer save(Customer customer) {
        customer.setId(null);
        customer.setActive(true);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);


        return customerRepository.save(customer);

    }

    @Override
    public List<Customer> getAllActiveCustomers() {
        return List.of();
    }
}

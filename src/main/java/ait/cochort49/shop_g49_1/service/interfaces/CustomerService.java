package ait.cochort49.shop_g49_1.service.interfaces;





import ait.cochort49.shop_g49_1.model.entity.Customer;

import java.util.List;



public interface CustomerService {

    Customer save(Customer customer);
    List<Customer> getAllActiveCustomers();
}

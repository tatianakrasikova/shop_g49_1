package ait.cochort49.shop_g49_1.repository;



import ait.cochort49.shop_g49_1.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface ProductRepository extends JpaRepository<Product, Long> {

    // Автоматически сгенерирован запрос для получения всех продуктов, у которых
    // поле active имеет значение true  true
    List<Product> findByActiveTrue();

}

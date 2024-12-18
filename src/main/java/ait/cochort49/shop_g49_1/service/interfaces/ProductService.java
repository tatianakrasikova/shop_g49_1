package ait.cochort49.shop_g49_1.service.interfaces;




import ait.cochort49.shop_g49_1.model.dto.ProductDTO;

import java.math.BigDecimal;
import java.util.List;




public interface ProductService {

    ProductDTO saveProduct(ProductDTO product);

    List<ProductDTO> getAllActiveProducts();
    ProductDTO getProductById(Long id);

    ProductDTO updateProduct(Long id, ProductDTO product);
    ProductDTO deleteProduct(Long id);

    ProductDTO deleteProductByTitle(String title);

    ProductDTO restoreProductById(Long id);

    long getProductsCount();

    BigDecimal getTotalPrice();
    BigDecimal getAveragePrice();


}

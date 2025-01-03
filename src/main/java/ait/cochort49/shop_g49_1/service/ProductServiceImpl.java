package ait.cochort49.shop_g49_1.service;


import ait.cochort49.shop_g49_1.exceprionHandling.exceptions.ThirdTestException;
import ait.cochort49.shop_g49_1.model.dto.ProductDTO;
import ait.cochort49.shop_g49_1.model.entity.Product;
import ait.cochort49.shop_g49_1.repository.ProductRepository;
import ait.cochort49.shop_g49_1.service.interfaces.ProductService;
import ait.cochort49.shop_g49_1.service.mapping.ProductMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMappingService mappingService;

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDto) {
        logger.info("Saving product in Service work");
        Product product = mappingService.mapDtoToEntity(productDto);
        product.setActive(true);
        return mappingService.mapEntityToDto(repository.save(product));
    }

    @Override
    public List<ProductDTO> getAllActiveProducts() {
        return repository.findAll().stream()
                .filter(Product::isActive)
                .map(mappingService::mapEntityToDto)
                .toList();
        // .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = repository.findById(id).orElse(null);
        if (product == null || !product.isActive()) {
            throw new ThirdTestException("This is the third test exception");
//            return null;
        }
        return mappingService.mapEntityToDto(product);
    }

//    @Override
//    public ProductDTO getProductById(Long id) {
//        logger.info("Method  getProductById called with parameter: id= {}", id);
//        logger.warn("Method  getProductById called with parameter: id= {}", id);
//        logger.error("Method  getProductById called with parameter: id= {}", id);
//        Product product = repository.findById(id).orElse(null);
//        if (product == null || !product.isActive()) {
//            return null;
//        }
//        return mappingService.mapEntityToDto(product);
//    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO product) {
        return null;
    }

    @Override
    public ProductDTO deleteProduct(Long id) {
        return null;
    }

    @Override
    public ProductDTO deleteProductByTitle(String title) {
        return null;
    }

    @Override
    public ProductDTO restoreProductById(Long id) {
        return null;
    }

    @Override
    public long getProductsCount() {
        return 0;
    }

    @Override
    public BigDecimal getTotalPrice() {
        return null;
    }

    @Override
    public BigDecimal getAveragePrice() {
        return null;
    }
}

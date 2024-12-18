package ait.cochort49.shop_g49_1.service.mapping;



import ait.cochort49.shop_g49_1.model.dto.ProductDTO;
import ait.cochort49.shop_g49_1.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Service;



@Mapper(componentModel = "spring")
public interface ProductMappingService {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", constant = "true")
    Product mapDtoToEntity(ProductDTO dto);

    ProductDTO mapEntityToDto (Product entity);

    // Ручной маппинг
//    public Product mapDtoToEntity(ProductDTO dto){
//        Product entity = new Product();
//        entity.setTitle(dto.getTitle());
//        entity.setPrice(dto.getPrice());
//        return entity;
//    }
//
//    public ProductDTO mapEntityToDto (Product entity){
//            ProductDTO dto = new ProductDTO();
//            dto.setId(entity.getId());
//            dto.setTitle(entity.getTitle());
//            dto.setPrice(entity.getPrice());
//            return dto;
//    }
}

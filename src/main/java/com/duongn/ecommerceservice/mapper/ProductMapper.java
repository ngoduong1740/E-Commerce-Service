package com.duongn.ecommerceservice.mapper;

import com.duongn.ecommerceservice.dto.request.ProductRequest;
import com.duongn.ecommerceservice.dto.response.ProductResponse;
import com.duongn.ecommerceservice.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", ignore = true)
    Product toProduct(ProductRequest request);

    @Mapping(target = "categoryId", source = "category.id")
    ProductResponse toProductResponse(Product product);

    @Mapping(target = "category", ignore = true)
    void updateProduct(@MappingTarget Product product, ProductRequest request);
}

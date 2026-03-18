package com.duongn.ecommerceservice.mapper;

import com.duongn.ecommerceservice.dto.request.CategoryRequest;
import com.duongn.ecommerceservice.dto.response.CategoryResponse;
import com.duongn.ecommerceservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);

    void updateCategory(@MappingTarget Category category, CategoryRequest request);
}

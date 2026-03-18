package com.duongn.ecommerceservice.mapper;

import com.duongn.ecommerceservice.dto.response.CartItemResponse;
import com.duongn.ecommerceservice.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(target = "productName", source = "product.name")
    @Mapping(target = "imageUrl", source = "product.imageUrl")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "totalPrice", expression = "java(calculateTotal(cartItem))")
    CartItemResponse toResponse(CartItem cartItem);

    List<CartItemResponse> toResponseList(List<CartItem> cartItems);

    default BigDecimal calculateTotal(CartItem cartItem) {
        return cartItem.getProduct()
                .getPrice()
                .multiply(BigDecimal.valueOf(cartItem.getQuantity()));
    }
}

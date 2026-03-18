package com.duongn.ecommerceservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemResponse {

    String productName;

    String imageUrl;

    BigDecimal price;

    Integer quantity;

    BigDecimal totalPrice;
}

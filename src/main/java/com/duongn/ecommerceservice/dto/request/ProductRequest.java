package com.duongn.ecommerceservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequest {

    String name;

    String description;

    BigDecimal price;

    Integer stock;

    String imageUrl;

    Long categoryId;
}

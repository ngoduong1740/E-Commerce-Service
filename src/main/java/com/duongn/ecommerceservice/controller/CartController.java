package com.duongn.ecommerceservice.controller;

import com.duongn.ecommerceservice.dto.request.CartRequest;
import com.duongn.ecommerceservice.dto.response.ApiResponse;
import com.duongn.ecommerceservice.dto.response.CartResponse;
import com.duongn.ecommerceservice.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Cart")
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartController {

    CartService cartService;

    @GetMapping
    public ApiResponse<CartResponse> getCart() {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.getCart())
                .build();
    }

    @PostMapping
    public ApiResponse<CartResponse> addToCart(@RequestBody CartRequest request) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.addToCart(request))
                .build();
    }

    @PutMapping
    public ApiResponse<CartResponse> updateQuantity(@RequestBody CartRequest request) {
        return ApiResponse.<CartResponse>builder()
                .data(cartService.updateQuantity(request))
                .build();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse<String> removeItem(@PathVariable Long productId) {
        cartService.removeItem(productId);
        return ApiResponse.<String>builder()
                .data("Item has been removed")
                .build();
    }
}

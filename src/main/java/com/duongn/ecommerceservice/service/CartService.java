package com.duongn.ecommerceservice.service;

import com.duongn.ecommerceservice.dto.request.CartRequest;
import com.duongn.ecommerceservice.dto.response.CartResponse;
import com.duongn.ecommerceservice.entity.Cart;
import com.duongn.ecommerceservice.entity.CartItem;
import com.duongn.ecommerceservice.entity.Product;
import com.duongn.ecommerceservice.exception.AppException;
import com.duongn.ecommerceservice.exception.ErrorCode;
import com.duongn.ecommerceservice.mapper.CartMapper;
import com.duongn.ecommerceservice.repository.CartItemRepository;
import com.duongn.ecommerceservice.repository.CartRepository;
import com.duongn.ecommerceservice.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CartService {

    CartMapper cartMapper;

    CartRepository cartRepository;

    CartItemRepository cartItemRepository;

    ProductRepository productRepository;

    public CartResponse getCart() {
        String username = getCurrentUsername();

        Cart cart = cartRepository.findByUserUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        return cartMapper.toCartResponse(cart);
    }

    public CartResponse addToCart(CartRequest request) {
        String username = getCurrentUsername();

        Cart cart = cartRepository.findByUserUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        CartItem cartItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), product.getId())
                .orElse(null);

        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
        } else {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
        }

        cartItemRepository.save(cartItem);

        return getCart();
    }

    public CartResponse updateQuantity(CartRequest request) {
        String username = getCurrentUsername();

        Cart cart = cartRepository.findByUserUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        CartItem cartItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), request.getProductId())
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        cartItem.setQuantity(request.getQuantity());

        cartItemRepository.save(cartItem);

        return getCart();
    }

    public void removeItem(Long productId) {
        String username = getCurrentUsername();

        Cart cart = cartRepository.findByUserUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));

        CartItem cartItem = cartItemRepository
                .findByCartIdAndProductId(cart.getId(), productId)
                .orElseThrow(() -> new AppException(ErrorCode.CART_ITEM_NOT_FOUND));

        cartItemRepository.deleteById(cartItem.getId());
    }

    private String getCurrentUsername() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return authentication.getName();
    }
}

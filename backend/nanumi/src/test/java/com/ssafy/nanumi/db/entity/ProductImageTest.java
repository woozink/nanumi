package com.ssafy.nanumi.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductImageTest {

    private ProductImage productImage;
    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .name("MacBook")
                .content("MacBookTest")
                .build();

        productImage = ProductImage.builder()
                .id(1L)
                .imageUrl("https://google.com/image.jpg")
                .product(product)
                .build();
    }

    @Test
    void testProductImageBuilder() {
        assertEquals(1L, productImage.getId());
        assertEquals("https://sample.com/image.jpg", productImage.getImageUrl());
        assertEquals(product, productImage.getProduct());
    }
}

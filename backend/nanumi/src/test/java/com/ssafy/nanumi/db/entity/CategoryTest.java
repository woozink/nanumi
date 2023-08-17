package com.ssafy.nanumi.db.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = Category.builder()
                .id(1L)
                .name("Electronics")
                .build();
    }

    @Test
    void testId() {
        long id = 1L;
        assertEquals(id, category.getId());
    }

    @Test
    void testName() {
        String name = "Electronics";
        assertEquals(name, category.getName());
    }

    @Test
    void testProducts() {
        Product product1 = Product.builder()
                .id(1L)
                .name("Laptop")
                .content("This is a laptop")
                .isClosed(false)
                .isDeleted(false)
                .address(null)  // for simplicity, you can add mock address here
                .user(null)     // for simplicity, you can add mock user here
                .category(category)
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .name("Mobile Phone")
                .content("This is a mobile phone")
                .isClosed(false)
                .isDeleted(false)
                .address(null)  // for simplicity, you can add mock address here
                .user(null)     // for simplicity, you can add mock user here
                .category(category)
                .build();

        category.getProducts().add(product1);
        category.getProducts().add(product2);

        assertTrue(category.getProducts().contains(product1));
        assertTrue(category.getProducts().contains(product2));
    }

    @Test
    void testEntityInitialization() {
        assertNotNull(category);
    }
}

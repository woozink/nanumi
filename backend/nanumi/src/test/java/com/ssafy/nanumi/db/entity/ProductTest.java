package com.ssafy.nanumi.db.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .name("Laptop")
                .content("This is a laptop")
                .isClosed(false)
                .isDeleted(false)
                .address(null)  // for simplicity, you can add mock address here
                .user(null)     // for simplicity, you can add mock user here
                .category(null) // for simplicity, you can add mock category here
                .build();
    }

    @Test
    void testId() {
        long id = 1L;
        assertEquals(id, product.getId());
    }

    @Test
    void testName() {
        String name = "Laptop";
        assertEquals(name, product.getName());
    }

    @Test
    void testContent() {
        String content = "This is a laptop";
        assertEquals(content, product.getContent());
    }

    @Test
    void testIsClosed() {
        assertFalse(product.isClosed());
        product.close();
        assertTrue(product.isClosed());
    }

    @Test
    void testIsDeleted() {
        assertFalse(product.isDeleted());
        product.delete();
        assertTrue(product.isDeleted());
    }


    @Test
    void testEntityInitialization() {
        assertNotNull(product);
    }
}

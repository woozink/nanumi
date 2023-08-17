package com.ssafy.nanumi.db.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddressTest {

    private Address address;

    @BeforeEach
    void setUp() {
        // Builder 패턴을 사용하여 address 객체 초기화
        address = Address.builder()
                .id(1L)
                .si("Incheon")
                .guGun("Yeonsu")
                .dong("Songdo")
                .build();
    }

    @Test
    void testId() {
        long expectedId = 1L;
        assertEquals(expectedId, address.getId());
    }

    @Test
    void testSi() {
        String expectedSi = "Incheon";
        assertEquals(expectedSi, address.getSi());
    }

    @Test
    void testGuGun() {
        String expectedGuGun = "Yeonsu";
        assertEquals(expectedGuGun, address.getGuGun());
    }

    @Test
    void testDong() {
        String expectedDong = "Songdo";
        assertEquals(expectedDong, address.getDong());
    }

    @Test
    void testEntityInitialization() {
        assertNotNull(address);
    }
}

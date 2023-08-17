package com.ssafy.nanumi.db.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthorityTest {

    private Authority authority;

    @BeforeEach
    void setUp() {
        authority = Authority.builder()
                .id(1L)
                .name("ADMIN")
                .user(null)
                .build();
    }

    @Test
    void testId() {
        assertEquals(1L, authority.getId());
    }

    @Test
    void testName() {
        assertEquals("ADMIN", authority.getName());
    }

    @Test
    void testUser() {
        User mockUser = new User(); // 실제 User 생성 또는 mock 객체 생성
        authority.setUser(mockUser);
        assertEquals(mockUser, authority.getUser());
    }

    @Test
    void testEntityInitialization() {
        assertNotNull(authority);
    }
}

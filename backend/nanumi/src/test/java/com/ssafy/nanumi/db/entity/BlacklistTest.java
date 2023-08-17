package com.ssafy.nanumi.db.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BlacklistTest {

    private Blacklist blacklist;

    @BeforeEach
    void setUp() {
        User mockBlocker = new User();
        User mockTarget = new User();

        blacklist = Blacklist.builder()
                .id(1L)
                .isBlocked(false)
                .blocker(mockBlocker)
                .target(mockTarget)
                .build();
    }

    @Test
    void testId() {
        assertEquals(1L, blacklist.getId());
    }

    @Test
    void testIsBlockedInitiallyFalse() {
        assertFalse(blacklist.isBlocked());
    }

    @Test
    void testBlockUser() {
        blacklist.blockUser();
        assertTrue(blacklist.isBlocked());
    }

    @Test
    void testBlockCancel() {
        blacklist.blockUser();
        blacklist.blockCancel();
        assertFalse(blacklist.isBlocked());
    }

    @Test
    void testBlockerUser() {
        assertNotNull(blacklist.getBlocker());
    }

    @Test
    void testTargetUser() {
        assertNotNull(blacklist.getTarget());
    }

    @Test
    void testEntityInitialization() {
        assertNotNull(blacklist);
    }
}

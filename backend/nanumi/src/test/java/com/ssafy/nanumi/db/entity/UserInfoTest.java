package com.ssafy.nanumi.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UserInfoTest {

    private UserInfo userInfo;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockUser = mock(User.class);
        userInfo = UserInfo.builder()
                .id(1L)
                .starTotal(0)
                .starCount(0)
                .ratingTotal(0)
                .ratingCount(0)
                .tier("새싹")
                .temperature(0.0)
                .visitCount(0L)
                .giveCount(0)
                .givenCount(0)
                .reportedTotalCount(0)
                .user(mockUser)
                .build();
    }

    @Test
    public void testUpdateBanUser() {
        LocalDateTime banDate = LocalDateTime.now();
        userInfo.updateBanUser(banDate);

        assertEquals(1, userInfo.getReportedTotalCount());
        assertEquals(banDate, userInfo.getStopDate());
    }

    @Test
    public void testPlusGiveCount() {
        userInfo.plusGiveCount();
        assertEquals(1, userInfo.getGiveCount());
    }

    @Test
    public void testUpdateStar() {
        userInfo.updateStar(5);
        assertEquals(1, userInfo.getStarCount());
        assertEquals(5, userInfo.getStarTotal());
    }

    @Test
    public void testUpdateRating() {
        userInfo.updateRating(3);
        assertEquals(1, userInfo.getRatingCount());
        assertEquals(3, userInfo.getRatingTotal());
    }

    @Test
    public void testUpdateTier() {
        userInfo.updateTier("업데이트");
        assertEquals("업데이트", userInfo.getTier());
    }

    @Test
    public void testUpdateTemperature() {
        userInfo.updateTemperature(5.5);
        assertEquals(5.5, userInfo.getTemperature());
        userInfo.updateTemperature(-11.0);
        assertEquals(0.0, userInfo.getTemperature());
    }

    @Test
    public void testUpdateRefreshToken() {
        userInfo.updateRefreshToken("newToken");
        assertEquals("newToken", userInfo.getRefreshToken());
    }

}

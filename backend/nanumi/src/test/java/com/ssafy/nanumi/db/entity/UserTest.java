package com.ssafy.nanumi.db.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class UserTest {

    private User user;
    private Address mockAddress;
    private LoginProvider mockLoginProvider;
    private UserInfo mockUserInfo;

    @BeforeEach
    public void setUp() {
        mockAddress = mock(Address.class);
        mockLoginProvider = mock(LoginProvider.class);
        mockUserInfo = mock(UserInfo.class);

        user = User.builder()
                .id(1L)
                .email("k99743844@gmail.com")
                .nickname("송도 김우진")
                .profileUrl("https://naver.com/profile.jpg")
                .password("1234")
                .isDeleted(false)
                .address(mockAddress)
                .loginProvider(mockLoginProvider)
                .userInfo(mockUserInfo)
                .fcmToken("fcmToken1234")
                .build();
    }

    @Test
    public void testUserCreation() {
        assertEquals(1L, user.getId());
        assertEquals("k99743844@gmail.com", user.getEmail());
        assertEquals("송도 김우진", user.getNickname());
        assertEquals("https://naver.com/profile.jpg", user.getProfileUrl());
        assertEquals("1234", user.getPassword());
        assertFalse(user.isDeleted());
        assertEquals(mockAddress, user.getAddress());
        assertEquals(mockLoginProvider, user.getLoginProvider());
        assertEquals(mockUserInfo, user.getUserInfo());
        assertEquals("fcmToken1234", user.getFcmToken());
    }

    @Test
    public void testUserDelete() {
        user.delete();
        assertTrue(user.isDeleted());
    }

    @Test
    public void testUserUpdateAddress() {
        Address newAddress = mock(Address.class);
        user.updateAddress(newAddress);
        assertEquals(newAddress, user.getAddress());
    }

    @Test
    public void testUserUpdateUserInfo() {
        user.updateUserInfo("송도 김우진", "https://naver.com/profile.jpg");
        assertEquals("송도 김우진", user.getNickname());
        assertEquals("https://naver.com/profile.jpg", user.getProfileUrl());
    }

    @Test
    public void testSetRoles() {
        Authority mockAuthority = mock(Authority.class);
        user.setRoles(Collections.singletonList(mockAuthority));
        assertTrue(user.getTiers().contains(mockAuthority));
    }

    @Test
    public void testUpdateFcmToken() {
        user.updateFcmToken("newFcmToken");
        assertEquals("newFcmToken", user.getFcmToken());
    }

}

package com.ssafy.nanumi.db.entity;

import com.ssafy.nanumi.common.provider.Provider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginProviderTest {

    private LoginProvider loginProvider;

    @BeforeEach
    void 초기화() {
        loginProvider = new LoginProvider();
    }

    @Test
    void 아이디_테스트() {
        int id = 1;
        loginProvider = LoginProvider.builder().id(id).build();
        assertEquals(id, loginProvider.getId());
    }

    @Test
    void 제공자_테스트() {
        Provider provider = Provider.kakao;
        loginProvider = LoginProvider.builder().provider(provider).build();
        assertEquals(provider, loginProvider.getProvider());
    }
}

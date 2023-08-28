package com.ssafy.nanumi.db.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Slf4j
@Testcontainers
@DisplayName("@Container 어노테이션 없이 가장 기본 사용 방법, 직접 start(), stop()으로 매 테스트마다 도커 띄우기")
class BasicMySQLContainer {

    MySQLContainer mySQLContainer = new MySQLContainer("mysql:8"); // MySQLContainer 객체 생성

    @BeforeEach
    void setUp() {
        mySQLContainer.start(); // 매 테스트 시작 시 컨테이너 시작
    }

    @AfterEach
    void tearDown() {
        mySQLContainer.stop(); // 매 테스트 끝난 뒤 컨테이너 종료
    }

    @Test
    void test1() {
        log.info("로그 getJdbcDriverInstance {} ", mySQLContainer.getJdbcDriverInstance());
        log.info("로그 getJdbcUrl {} ", mySQLContainer.getJdbcUrl());
        log.info("로그 getMappedPort {} ", mySQLContainer.getMappedPort(3306));
        log.info("로그 getHost {} ", mySQLContainer.getHost());
        log.info("로그 getUsername {} ", mySQLContainer.getUsername());
        log.info("로그 getPassword {} ", mySQLContainer.getPassword());
    }

    @Test
    void test2() {
        log.info("로그 getJdbcDriverInstance {} ", mySQLContainer.getJdbcDriverInstance());
        log.info("로그 getJdbcUrl {} ", mySQLContainer.getJdbcUrl());
        log.info("로그 getMappedPort {} ", mySQLContainer.getMappedPort(3306));
        log.info("로그 getHost {} ", mySQLContainer.getHost());
        log.info("로그 getUsername {} ", mySQLContainer.getUsername());
        log.info("로그 getPassword {} ", mySQLContainer.getPassword());
    }
}
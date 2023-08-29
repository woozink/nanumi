//package com.ssafy.nanumi.db.repository;
//
//import com.ssafy.nanumi.db.entity.Blacklist;
//import com.ssafy.nanumi.db.entity.User;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@Slf4j
//@Testcontainers
//@DisplayName("@Container 어노테이션 없이 가장 기본 사용 방법, 직접 start(), stop()으로 매 테스트마다 도커 띄우기")
//class BlacklistRepositoryTest {
//
//    MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0.26"); // MySQLContainer 객체 생성, 버전 명시
//
//    @Autowired
//    private BlacklistRepository blacklistRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        mySQLContainer.start(); // 매 테스트 시작 시 컨테이너 시작
//    }
//
//    @AfterEach
//    void tearDown() {
//        mySQLContainer.stop(); // 매 테스트 끝난 뒤 컨테이너 종료
//    }
//
//    @DisplayName("특정 blocker에 대한 차단 목록을 조회한다.")
//    @Test
//    void findByBlockerIdAndIsBlockedTrue() {
//        User blocker = User.builder()
//                .email("blocker2@test.com")
//                .nickname("Blocker2")
//                .password("password")
//                .build();
//        blocker = userRepository.save(blocker);
//
//        User target1 = User.builder()
//                .email("target1@test.com")
//                .nickname("Target1")
//                .password("password")
//                .build();
//        target1 = userRepository.save(target1);
//
//        User target2 = User.builder()
//                .email("target2@test.com")
//                .nickname("Target2")
//                .password("password")
//                .build();
//        target2 = userRepository.save(target2);
//
//        blacklistRepository.save(Blacklist.builder()
//                .isBlocked(true)
//                .blocker(blocker)
//                .target(target1)
//                .build());
//        blacklistRepository.save(Blacklist.builder()
//                .isBlocked(true)
//                .blocker(blocker)
//                .target(target2)
//                .build());
//
//        List<Blacklist> blacklists = blacklistRepository.findByBlockerIdAndIsBlockedTrue(blocker.getId());
//        assertThat(blacklists.size()).isEqualTo(2);
//    }
//}

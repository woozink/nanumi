package com.ssafy.nanumi.db.repository;

import com.ssafy.nanumi.db.entity.Blacklist;
import com.ssafy.nanumi.db.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BlacklistRepositoryTest {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Autowired
    private UserRepository userRepository;

    @DisplayName("특정 blocker와 target에 대한 Blacklist를 조회한다.")
    @Test
    void findByBlockIdAndTargetId() {
        User blocker = User.builder()
                .email("blocker@test.com")
                .nickname("Blocker")
                .password("password")
                .build();
        blocker = userRepository.save(blocker);

        User target = User.builder()
                .email("target@test.com")
                .nickname("Target")
                .password("password")
                .build();
        target = userRepository.save(target);

        Blacklist blacklist = Blacklist.builder()
                .isBlocked(true)
                .blocker(blocker)
                .target(target)
                .build();
        blacklistRepository.save(blacklist);

        Optional<Blacklist> foundBlacklist = blacklistRepository.findByBlockIdAndTargetId(blocker.getId(), target.getId());
        assertThat(foundBlacklist.isPresent()).isTrue();
        assertThat(foundBlacklist.get().getBlocker()).isEqualTo(blocker);
        assertThat(foundBlacklist.get().getTarget()).isEqualTo(target);
    }

    @DisplayName("특정 blocker에 대한 차단 목록을 조회한다.")
    @Test
    void findByBlockerIdAndIsBlockedTrue() {
        User blocker = User.builder()
                .email("blocker2@test.com")
                .nickname("Blocker2")
                .password("password")
                .build();
        blocker = userRepository.save(blocker);

        User target1 = User.builder()
                .email("target1@test.com")
                .nickname("Target1")
                .password("password")
                .build();
        target1 = userRepository.save(target1);

        User target2 = User.builder()
                .email("target2@test.com")
                .nickname("Target2")
                .password("password")
                .build();
        target2 = userRepository.save(target2);

        blacklistRepository.save(Blacklist.builder()
                .isBlocked(true)
                .blocker(blocker)
                .target(target1)
                .build());
        blacklistRepository.save(Blacklist.builder()
                .isBlocked(true)
                .blocker(blocker)
                .target(target2)
                .build());

        List<Blacklist> blacklists = blacklistRepository.findByBlockerIdAndIsBlockedTrue(blocker.getId());
        assertThat(blacklists.size()).isEqualTo(2);
    }
//
//    @DisplayName("특정 사용자를 차단한 사람들의 ID 목록을 조회한다.")
//    @Test
//    void findBlockerId() {
//        User user1 = User.builder()
//                .email("user1@test.com")
//                .nickname("User1")
//                .password("password")
//                .build();
//        user1 = userRepository.save(user1);
//
//        User user2 = User.builder()
//                .email("user2@test.com")
//                .nickname("User2")
//                .password("password")
//                .build();
//        user2 = userRepository.save(user2);
//
//        // user1 is blocked by user2
//        blacklistRepository.save(Blacklist.builder()
//                .isBlocked(true)
//                .blocker(user2)
//                .target(user1)
//                .build());
//
//        List<Long> blockerIds = blacklistRepository.findBlockerId(user1.getId());
//        assertThat(blockerIds).containsExactly(user2.getId());
//    }
//
//
//    @DisplayName("특정 사용자가 차단한 사람들의 ID 목록을 조회한다.")
//    @Test
//    void findTargetId() {
//        User user1 = User.builder()
//                .email("user3@test.com")
//                .nickname("User3")
//                .password("password")
//                .build();
//        user1 = userRepository.save(user1);
//
//        User user2 = User.builder()
//                .email("user4@test.com")
//                .nickname("User4")
//                .password("password")
//                .build();
//        user2 = userRepository.save(user2);
//
//        // user1 blocks user2
//        blacklistRepository.save(Blacklist.builder()
//                .isBlocked(true)
//                .blocker(user1)
//                .target(user2)
//                .build());
//
//        List<Long> targetIds = blacklistRepository.findTargetId(user1.getId());
//        assertThat(targetIds).containsExactly(user2.getId());
//    }

}
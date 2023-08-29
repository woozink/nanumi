package com.ssafy.nanumi.db.repository;

import com.ssafy.nanumi.db.entity.ChatMessageEntity;
import com.ssafy.nanumi.common.ChatMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @BeforeEach
    void setUp() {
        chatRepository.deleteAll();
    }

    @DisplayName("채팅방의 최근 20개 메시지를 찾는다.")
    @Test
    void findTop20ByRoomIdOrderBySendTimeDesc() {
        long roomId = 1L;

        for (int i = 0; i < 25; i++) {
            ChatMessageEntity message = ChatMessageEntity.builder()
                    .roomId(roomId)
                    .type(ChatMessageDTO.MessageType.TALK) // assuming a type, modify if necessary
                    .sendTime(LocalDateTime.now().plusSeconds(i).format(DateTimeFormatter.ISO_DATE_TIME))
                    .build();

            chatRepository.save(message);
        }

        List<ChatMessageEntity> messages = chatRepository.findTop20ByRoomIdOrderBySendTimeDesc(roomId);

        assertThat(messages).hasSize(20);
        assertThat(LocalDateTime.parse(messages.get(0).getSendTime(), DateTimeFormatter.ISO_DATE_TIME))
                .isAfter(LocalDateTime.parse(messages.get(19).getSendTime(), DateTimeFormatter.ISO_DATE_TIME));
    }

    @DisplayName("채팅방의 마지막 메시지를 찾는다.")
    @Test
    void findTop1ByRoomIdOrderBySendTimeDesc() {
        long roomId = 1L;

        for (int i = 0; i < 5; i++) {
            ChatMessageEntity message = ChatMessageEntity.builder()
                    .roomId(roomId)
                    .type(ChatMessageDTO.MessageType.TALK) // assuming a type, modify if necessary
                    .sendTime(LocalDateTime.now().plusSeconds(i).format(DateTimeFormatter.ISO_DATE_TIME))
                    .build();

            chatRepository.save(message);
        }

        List<ChatMessageEntity> messages = chatRepository.findTop1ByRoomIdOrderBySendTimeDesc(roomId);

        assertThat(messages).hasSize(1);
        assertThat(LocalDateTime.parse(messages.get(0).getSendTime(), DateTimeFormatter.ISO_DATE_TIME))
                .isEqualTo(LocalDateTime.now().plusSeconds(4));
    }
}

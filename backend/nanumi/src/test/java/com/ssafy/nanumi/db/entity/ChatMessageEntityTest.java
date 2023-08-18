package com.ssafy.nanumi.db.entity;

import com.ssafy.nanumi.common.ChatMessageDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChatMessageEntityTest {

    private ChatMessageEntity chatMessage;

    @BeforeEach
    void setUp() {
        chatMessage = ChatMessageEntity.builder().build();
    }

    @Test
    void testId() {
        String id = "Id";
        chatMessage.set_id(id);
        assertEquals(id, chatMessage.get_id());
    }

    @Test
    void testMessageType() {
        ChatMessageDTO.MessageType type = ChatMessageDTO.MessageType.TALK;
        chatMessage.setType(type);
        assertEquals(type, chatMessage.getType());
    }

    @Test
    void testRoomId() {
        long roomId = 1L;
        chatMessage.setRoomId(roomId);
        assertEquals(roomId, chatMessage.getRoomId());
    }

    @Test
    void testSender() {
        long sender = 1L;
        chatMessage.setSender(sender);
        assertEquals(sender, chatMessage.getSender());
    }

    @Test
    void testMessage() {
        String message = "우진";
        chatMessage.setMessage(message);
        assertEquals(message, chatMessage.getMessage());
    }
}

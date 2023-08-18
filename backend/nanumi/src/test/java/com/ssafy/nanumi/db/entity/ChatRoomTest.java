package com.ssafy.nanumi.db.entity;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChatRoomTest {

    private ChatRoomEntity chatRoom;

    @BeforeEach
    void 초기화() {
        chatRoom = ChatRoomEntity.builder().build();
    }

    @Test
    void 아이디_테스트() {
        ObjectId id = new ObjectId();
        chatRoom.set_id(id);
        assertEquals(id, chatRoom.get_id());
    }

    @Test
    void 채팅방_시퀀스_테스트() {
        long chatroomSeq = 1L;
        chatRoom.setChatroomSeq(chatroomSeq);
        assertEquals(chatroomSeq, chatRoom.getChatroomSeq());
    }

    @Test
    void 유저리스트_테스트() {
        long[] userList = {1L, 2L};
        chatRoom.setUserList(userList);
        assertArrayEquals(userList, chatRoom.getUserList());
    }

    @Test
    void 활성화_테스트() {
        boolean isActivate = false;
        chatRoom.setActivate(isActivate);
        assertEquals(isActivate, chatRoom.isActivate());
    }
}

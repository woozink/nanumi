package com.ssafy.nanumi.db.repository;

import com.ssafy.nanumi.db.entity.ChatMessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<ChatMessageEntity, String> {
    List<ChatMessageEntity> findTop20ByRoomIdOrderBySendTimeDesc(long roomId);
    List<ChatMessageEntity> findTop1ByRoomIdOrderBySendTimeDesc(long chatroomSeq);
}

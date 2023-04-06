package com.nikitalipatov.chatclient.repository;

import com.nikitalipatov.chatclient.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Integer> {
}

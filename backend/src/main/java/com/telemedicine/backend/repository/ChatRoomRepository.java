package com.telemedicine.backend.repository;

import com.telemedicine.backend.entity.ChatRoom;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, UUID> {
}
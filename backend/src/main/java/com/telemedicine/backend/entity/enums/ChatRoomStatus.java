package com.telemedicine.backend.entity.enums;

public enum ChatRoomStatus {
    ACTIVE, // Đang mở, cho phép 2 bên nhắn tin
    READ_ONLY, // Đã hết hạn (hết 24h), khóa chat, chỉ cho xem lại lịch sử
    CLOSED // Ẩn/Xóa phòng chat (nếu cần)
}
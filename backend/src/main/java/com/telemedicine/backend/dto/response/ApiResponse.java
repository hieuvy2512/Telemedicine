package com.telemedicine.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Dòng này siêu hay: Nếu trường nào bị null (ví dụ data bị null do lỗi), nó sẽ
// giấu luôn dòng đó trên JSON cho đẹp!
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int code; // Mã trạng thái (Ví dụ: 200, 400, 404)

    private String message; // Lời nhắn cho Frontend

    private T data; // Chữ T (Type) ở đây là "Cái đĩa vạn năng". Bạn ném List Bác sĩ hay Chi tiết 1
                    // Bác sĩ vào nó đều đựng được hết!
}
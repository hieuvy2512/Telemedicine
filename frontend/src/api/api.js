import axios from 'axios';

// Tạo một instance của axios
const apiClient = axios.create({
    // Bạn thay link Render của bạn vào chỗ này nhé!
    // Sau này quen rồi mình sẽ hướng dẫn bạn giấu link này vào file .env cho xịn hơn
    baseURL: 'https://telemedicine-backend.onrender.com',
    headers: {
        'Content-Type': 'application/json',
    },
});

// Thêm một "Trạm kiểm tra" (Interceptor) trước khi gửi Request
apiClient.interceptors.request.use(
    (config) => {
        // Sau này làm chức năng Đăng nhập, bạn sẽ nhét Token (chìa khóa) vào đây
        // const token = localStorage.getItem('token');
        // if (token) {
        //     config.headers.Authorization = `Bearer ${token}`;
        // }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default apiClient;
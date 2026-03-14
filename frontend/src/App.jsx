import React from 'react';

function App() {
  return (
    <div className="min-h-screen bg-blue-50">
      {/* Thanh điều hướng (Navbar) */}
      <nav className="bg-white shadow-md p-4 flex justify-between items-center">
        <div className="text-2xl font-bold text-blue-600">
          🏥 TeleHealth
        </div>
        <div>
          <button className="text-gray-600 hover:text-blue-600 font-semibold mr-4">
            Dịch vụ
          </button>
          <button className="text-gray-600 hover:text-blue-600 font-semibold mr-4">
            Bác sĩ
          </button>
          <button className="bg-blue-600 text-white px-5 py-2 rounded-full font-semibold hover:bg-blue-700 transition">
            Đăng nhập
          </button>
        </div>
      </nav>

      {/* Phần giới thiệu chính (Hero Section) */}
      <main className="flex flex-col items-center justify-center text-center mt-20 px-4">
        <h1 className="text-5xl font-extrabold text-gray-800 mb-6">
          Chăm sóc sức khỏe từ xa <br /> <span className="text-blue-600">Mọi lúc, Mọi nơi</span>
        </h1>
        <p className="text-lg text-gray-600 mb-8 max-w-2xl">
          Kết nối trực tiếp với các bác sĩ chuyên khoa hàng đầu qua Video Call.
          Nhận tư vấn, kê đơn thuốc và theo dõi sức khỏe ngay tại nhà của bạn.
        </p>
        <div className="flex gap-4">
          <button className="bg-blue-600 text-white px-8 py-3 rounded-full text-lg font-bold shadow-lg hover:bg-blue-700 hover:-translate-y-1 transition transform">
            Đặt lịch khám ngay
          </button>
          <button className="bg-white text-blue-600 border border-blue-600 px-8 py-3 rounded-full text-lg font-bold shadow-sm hover:bg-blue-50 transition">
            Tìm hiểu thêm
          </button>
        </div>
      </main>
    </div>
  );
}

export default App;
import React from 'react';

export default function Navbar() {
    return (
        <nav className="bg-white shadow-sm px-6 py-3 flex justify-between items-center sticky top-0 z-40">
            <div className="text-2xl font-bold text-blue-600 flex items-center gap-2 cursor-pointer">
                <span className="text-3xl">🏥</span> TeleHealth
            </div>
            <div className="hidden md:flex items-center gap-8">
                <a href="#" className="text-gray-600 hover:text-blue-600 font-medium transition-colors">Đặt khám</a>
                <a href="#" className="text-gray-600 hover:text-blue-600 font-medium transition-colors">Tư vấn trực tuyến</a>
                <a href="#" className="text-gray-600 hover:text-blue-600 font-medium transition-colors">Tin Y tế</a>
                <button className="border border-blue-600 text-blue-600 px-5 py-2 rounded-lg font-semibold hover:bg-blue-50 transition-colors">
                    Đăng nhập
                </button>
            </div>
        </nav>
    );
}
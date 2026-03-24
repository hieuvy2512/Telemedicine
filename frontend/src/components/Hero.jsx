import React from 'react';
import { Search } from 'lucide-react';

export default function Hero() {
    return (
        <div className="bg-blue-600 w-full py-20 px-4 flex flex-col items-center justify-center text-center relative overflow-hidden">
            <div className="absolute top-0 right-0 w-96 h-96 bg-blue-500 rounded-full mix-blend-multiply filter blur-3xl opacity-50 animate-blob"></div>
            <div className="absolute top-0 left-10 w-72 h-72 bg-blue-400 rounded-full mix-blend-multiply filter blur-3xl opacity-50 animate-blob animation-delay-2000"></div>

            <h1 className="text-4xl md:text-5xl font-bold text-white mb-4 relative z-10">
                Ứng dụng đặt khám
            </h1>
            <p className="text-blue-100 text-lg mb-8 relative z-10 max-w-2xl">
                Đặt khám với hơn 1000 bác sĩ, 25 bệnh viện, 100 phòng khám trên hệ thống một cách nhanh chóng và dễ dàng.
            </p>

            <div className="relative w-full max-w-3xl z-10">
                <input
                    type="text"
                    placeholder="Triệu chứng, bác sĩ, bệnh viện..."
                    className="w-full py-4 pl-6 pr-14 bg-white text-gray-800 rounded-full text-lg shadow-xl focus:outline-none focus:ring-4 focus:ring-blue-300 transition"
                />
                <button className="absolute right-2 top-1/2 -translate-y-1/2 bg-blue-600 text-white p-3 rounded-full hover:bg-blue-700 transition-colors shadow-md">
                    <Search size={20} />
                </button>
            </div>
        </div>
    );
}
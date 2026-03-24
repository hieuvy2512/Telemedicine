import React from 'react';
import { Calendar, ShieldCheck, MapPin, Clock, X, Heart, Star, Info } from 'lucide-react';

const TIME_SLOTS = [
    "17:00 - 17:30", "17:30 - 18:00", "18:00 - 18:30", "18:30 - 19:00",
    "19:00 - 19:30", "19:30 - 20:00"
];

export default function DoctorModal({ doctor, onClose }) {
    if (!doctor) return null;

    // Format tiền tệ VNĐ
    const formatCurrency = (amount) => {
        return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(amount);
    };

    return (
        <div className="fixed inset-0 z-50 flex justify-center items-center bg-black/60 backdrop-blur-sm p-4 animate-fadeIn">
            <div className="bg-gray-50 rounded-2xl w-full max-w-4xl max-h-[90vh] overflow-hidden shadow-2xl flex flex-col relative animate-slideUp">
                <button onClick={onClose} className="absolute top-4 right-4 z-20 bg-white/80 backdrop-blur p-2 rounded-full text-gray-500 hover:text-red-500 hover:bg-red-50 shadow-sm border border-gray-100 transition-all">
                    <X size={20} />
                </button>

                <div className="overflow-y-auto flex-grow p-4 md:p-8 custom-scrollbar">

                    {/* Thông tin cơ bản & Avatar */}
                    <div className="flex flex-col md:flex-row gap-8 bg-white p-6 md:p-8 rounded-2xl shadow-sm border border-gray-100 mb-6 relative overflow-hidden">

                        {/* Hình ảnh Avatar */}
                        <div className="shrink-0 flex justify-center md:justify-start">
                            <div className="relative">
                                <img
                                    src={doctor.avatarUrl || '[https://via.placeholder.com/150](https://via.placeholder.com/150)'}
                                    alt={doctor.fullName}
                                    className="w-32 h-32 md:w-40 md:h-40 rounded-3xl object-cover border-4 border-blue-50 shadow-md"
                                />
                                <div className="absolute -bottom-3 -right-3 bg-white rounded-full p-2.5 shadow-lg border border-gray-50">
                                    <ShieldCheck className="w-6 h-6 text-blue-600" />
                                </div>
                            </div>
                        </div>

                        <div className="flex-1 z-10 text-center md:text-left flex flex-col justify-center">
                            <h2 className="text-2xl md:text-3xl font-bold text-gray-800 flex items-center justify-center md:justify-start gap-2">
                                {doctor.title} {doctor.fullName}
                            </h2>

                            {/* Rating & Kinh nghiệm */}
                            <div className="flex flex-wrap items-center justify-center md:justify-start gap-3 mt-3 mb-5">
                                <span className="flex items-center gap-1.5 bg-yellow-50 text-yellow-700 px-3 py-1.5 rounded-lg text-sm font-semibold border border-yellow-100">
                                    <Star size={16} className="fill-current" />
                                    {doctor.ratingAverage || '5.0'} ({doctor.ratingCount || '100+'} đánh giá)
                                </span>
                                <span className="flex items-center gap-1.5 bg-blue-50 text-blue-700 px-3 py-1.5 rounded-lg text-sm font-semibold border border-blue-100">
                                    <Clock size={16} />
                                    {doctor.experienceYears || '10'} năm kinh nghiệm
                                </span>
                            </div>

                            {/* Chi tiết công tác */}
                            <div className="grid grid-cols-1 md:grid-cols-2 gap-y-3 text-sm bg-gray-50 p-4 rounded-xl border border-gray-100">
                                <p className="flex items-center"><span className="text-gray-500 w-24 shrink-0 font-medium">Chuyên khoa:</span> <span className="font-bold text-blue-600">{doctor.specialtyName || 'Đa khoa'}</span></p>
                                <p className="flex items-center"><span className="text-gray-500 w-24 shrink-0 font-medium">Chức vụ:</span> <span className="font-medium text-gray-800">Bác sĩ chuyên khoa</span></p>
                                <p className="md:col-span-2 flex items-start"><span className="text-gray-500 w-24 shrink-0 font-medium mt-0.5">Nơi làm việc:</span> <span className="font-medium text-gray-800 leading-snug">{doctor.workplace || 'Bệnh viện Đa khoa'}</span></p>
                            </div>
                        </div>
                    </div>

                    {/* Phần giới thiệu (Bio) */}
                    {(doctor.bio || doctor.description) && (
                        <div className="bg-white p-6 md:p-8 rounded-2xl shadow-sm border border-gray-100 mb-6">
                            <h3 className="text-lg font-bold text-gray-800 flex items-center gap-2 mb-3">
                                <Info className="text-blue-600" size={20} /> Giới thiệu chuyên môn
                            </h3>
                            <p className="text-gray-600 text-sm md:text-base leading-relaxed">
                                {doctor.bio || doctor.description || 'Bác sĩ có nhiều năm kinh nghiệm trong việc khám và điều trị chuyên sâu. Luôn tận tâm và hết lòng vì sức khỏe của bệnh nhân.'}
                            </p>
                        </div>
                    )}

                    {/* Lịch khám */}
                    <div className="bg-white p-6 md:p-8 rounded-2xl shadow-sm border border-gray-100 mb-6">
                        <div className="flex justify-between items-center mb-5">
                            <h3 className="text-lg font-bold text-gray-800 flex items-center gap-2">
                                <Calendar className="text-blue-600" /> Lịch khám hiện trống
                            </h3>
                        </div>
                        <div className="grid grid-cols-2 sm:grid-cols-3 md:grid-cols-6 gap-3">
                            {TIME_SLOTS.map((time, idx) => (
                                <button key={idx} className="border border-blue-100 bg-blue-50/50 py-2.5 rounded-xl text-sm font-semibold text-blue-800 hover:bg-blue-600 hover:text-white transition-all duration-200 shadow-sm">{time}</button>
                            ))}
                        </div>
                    </div>
                </div>

                {/* Footer Xác nhận đặt khám */}
                <div className="bg-white border-t border-gray-200 p-4 px-6 md:px-8 flex flex-col sm:flex-row justify-between items-center z-10 gap-4 sm:gap-0 rounded-b-2xl">
                    <div className="text-center sm:text-left w-full sm:w-auto">
                        <p className="text-sm text-gray-500 font-medium mb-1">Giá khám dự kiến</p>
                        <p className="text-2xl font-bold text-red-500">
                            {doctor.consultationFee ? formatCurrency(doctor.consultationFee) : '300.000 ₫'}
                        </p>
                    </div>
                    <button onClick={() => alert('Đang chuyển hướng đặt khám...')} className="w-full sm:w-auto bg-blue-600 text-white px-10 py-3.5 rounded-xl font-bold text-lg shadow-lg shadow-blue-600/30 hover:bg-blue-700 hover:-translate-y-0.5 transition-all">
                        XÁC NHẬN ĐẶT KHÁM
                    </button>
                </div>
            </div>
        </div>
    );
}
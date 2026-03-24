import React, { useRef } from 'react';
import { ChevronRight, ChevronLeft, ShieldCheck, MapPin } from 'lucide-react';

export default function DoctorList({ doctors, onSelectDoctor }) {
    const sliderRef = useRef(null);

    const scroll = (direction) => {
        if (sliderRef.current) {
            const scrollAmount = direction === 'left' ? -300 : 300;
            sliderRef.current.scrollBy({ left: scrollAmount, behavior: 'smooth' });
        }
    };

    return (
        <main className="max-w-6xl mx-auto py-16 px-4">
            <div className="text-center mb-10">
                <h2 className="text-3xl font-bold text-gray-800">Đặt lịch khám trực tuyến</h2>
                <p className="text-gray-500 mt-2">Tìm Bác sĩ chính xác - Đặt lịch khám dễ dàng</p>
            </div>

            <div className="flex justify-between items-end mb-4">
                <h3 className="text-xl font-bold text-gray-800">Bác sĩ nổi bật</h3>
                <div className="flex items-center gap-3">
                    <button onClick={() => scroll('left')} className="p-2 rounded-full bg-white shadow-sm border border-gray-100 hover:bg-blue-50 text-blue-600 transition">
                        <ChevronLeft size={20} />
                    </button>
                    <button onClick={() => scroll('right')} className="p-2 rounded-full bg-white shadow-sm border border-gray-100 hover:bg-blue-50 text-blue-600 transition">
                        <ChevronRight size={20} />
                    </button>
                </div>
            </div>

            <div ref={sliderRef} className="flex gap-6 overflow-x-auto snap-x snap-mandatory hide-scrollbar pb-8 pt-2 px-2 -mx-2" style={{ scrollbarWidth: 'none', msOverflowStyle: 'none' }}>
                {doctors.map((doctor) => (
                    <div key={doctor.id} className="snap-start shrink-0 w-72 md:w-64 lg:w-72 bg-white rounded-2xl border border-gray-100 shadow-sm hover:shadow-xl transition-all duration-300 cursor-pointer overflow-hidden group flex flex-col" onClick={() => onSelectDoctor(doctor)}>
                        <div className="p-6 flex flex-col items-center text-center flex-grow">
                            <div className="relative mb-4">
                                <img src={doctor.avatarUrl} alt={doctor.fullName} className="w-24 h-24 rounded-full object-cover border-4 border-blue-50 group-hover:border-blue-100 transition duration-300" />
                                <div className="absolute -bottom-2 -right-2 bg-white rounded-full p-1.5 shadow-md">
                                    <ShieldCheck className="w-4 h-4 text-blue-600" />
                                </div>
                            </div>
                            <h4 className="font-bold text-lg text-gray-800 mb-1 line-clamp-2 h-14 flex items-center justify-center">
                                {doctor.title} {doctor.fullName}
                            </h4>
                            <p className="text-blue-600 font-medium text-sm mb-3 bg-blue-50 px-3 py-1 rounded-full">{doctor.specialtyName}</p>
                            <p className="text-gray-500 text-sm flex items-center justify-center gap-1 line-clamp-1">
                                <MapPin size={14} className="shrink-0" /> <span className="truncate">{doctor.workplace}</span>
                            </p>
                        </div>

                        <div className="border-t border-gray-100 p-4 bg-gray-50 group-hover:bg-blue-50 transition-colors flex justify-between items-center mt-auto">
                            <span className="text-sm font-semibold text-gray-600 group-hover:text-blue-700">Đặt lịch ngay</span>
                            <div className="bg-white p-1 rounded-full shadow-sm group-hover:bg-blue-600 group-hover:text-white transition-colors">
                                <ChevronRight size={16} className="text-gray-400 group-hover:text-white" />
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </main>
    );
}

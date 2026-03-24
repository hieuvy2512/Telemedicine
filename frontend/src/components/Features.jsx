import React from 'react';
import { Calendar, Video, ShieldCheck } from 'lucide-react';

function FeatureItem({ icon, title }) {
    return (
        <div className="flex flex-col md:flex-row items-center gap-3 text-center md:text-left cursor-pointer hover:-translate-y-1 transition transform p-2 w-full justify-center md:justify-start">
            <div className="bg-gray-50 p-3.5 rounded-2xl shadow-sm border border-gray-100">{icon}</div>
            <span className="font-bold text-gray-700">{title}</span>
        </div>
    );
}

export default function Features() {
    return (
        <div className="max-w-6xl mx-auto -mt-10 relative z-20 px-4">
            <div className="bg-white rounded-2xl shadow-lg p-6 flex flex-col md:flex-row justify-around items-center border border-gray-100 gap-4 md:gap-0">
                <FeatureItem icon={<Video className="w-8 h-8 text-blue-500" />} title="Khám Online 24/7" />
                <div className="h-12 w-px bg-gray-200 hidden md:block"></div>
                <FeatureItem icon={<Calendar className="w-8 h-8 text-green-500" />} title="Chọn giờ tiện lợi" />
                <div className="h-12 w-px bg-gray-200 hidden md:block"></div>
                <FeatureItem icon={<ShieldCheck className="w-8 h-8 text-purple-500" />} title="Bảo mật hồ sơ" />
            </div>
        </div>
    );
}
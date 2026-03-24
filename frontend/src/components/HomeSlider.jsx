import { Swiper, SwiperSlide } from 'swiper/react';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
// BẮT BUỘC phải có các dòng CSS này
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import { ChevronRight, Calendar, Video, ShieldCheck } from 'lucide-react';

// Import Swiper styles
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

const slides = [
    {
        title: "Tư vấn bác sĩ trực tuyến 24/7",
        desc: "Kết nối với các chuyên gia đầu ngành mọi lúc, mọi nơi qua Video Call.",
        icon: <Video className="w-12 h-12 text-blue-500" />,
        bg: "bg-blue-50"
    },
    {
        title: "Đặt lịch khám nhanh chóng",
        desc: "Chủ động chọn khung giờ phù hợp, không còn cảnh xếp hàng chờ đợi.",
        icon: <Calendar className="w-12 h-12 text-green-500" />,
        bg: "bg-green-50"
    },
    {
        title: "Bảo mật hồ sơ bệnh án",
        desc: "Dữ liệu sức khỏe của bạn được mã hóa và bảo mật tuyệt đối.",
        icon: <ShieldCheck className="w-12 h-12 text-purple-500" />,
        bg: "bg-purple-50"
    }
];

export default function HomeSlider() {
    return (
        <div className="w-full max-w-5xl mx-auto py-10">
            <Swiper
                modules={[Navigation, Pagination, Autoplay]}
                spaceBetween={30}
                slidesPerView={1}
                navigation
                pagination={{ clickable: true }}
                autoplay={{ delay: 5000 }}
                className="rounded-2xl shadow-lg overflow-hidden"
            >
                {slides.map((slide, index) => (
                    <SwiperSlide key={index}>
                        <div className={`flex flex-col md:flex-row items-center p-12 ${slide.bg} min-h-[350px]`}>
                            <div className="flex-1 space-y-4">
                                <div className="p-3 bg-white w-fit rounded-full shadow-sm mb-4">
                                    {slide.icon}
                                </div>
                                <h2 className="text-3xl font-bold text-gray-800">{slide.title}</h2>
                                <p className="text-gray-600 text-lg leading-relaxed">{slide.desc}</p>
                                <button className="mt-6 flex items-center gap-2 bg-blue-600 text-white px-6 py-3 rounded-lg font-semibold hover:bg-blue-700 transition">
                                    Bắt đầu ngay <ChevronRight size={20} />
                                </button>
                            </div>
                            <div className="hidden md:block flex-1 text-center">
                                {/* Ở đây bạn có thể đặt ảnh minh họa y tế */}
                                <div className="text-9xl opacity-10 font-bold uppercase select-none">
                                    Health
                                </div>
                            </div>
                        </div>
                    </SwiperSlide>
                ))}
            </Swiper>
        </div>
    );
}
import React, { useState, useEffect } from 'react';

// Import các components đã chia nhỏ
import Navbar from './components/Navbar';
import Hero from './components/Hero';
import Features from './components/Features';
import DoctorList from './components/DoctorList';
import DoctorModal from './components/DoctorModal';

export default function App() {
  const [doctors, setDoctors] = useState([]);
  const [selectedDoctor, setSelectedDoctor] = useState(null);

  // Vẫn gọi API ở component cha cùng nhất
  useEffect(() => {
    fetch('http://localhost:8080/api/v1/public/doctors')
      .then(res => res.json())
      .then(data => {
        if (data && Array.isArray(data)) {
          setDoctors(data);
        }
      })
      .catch(err => {
        console.error("Lỗi:", err);
        setDoctors([]);
      });
  }, []);

  return (
    <div className="min-h-screen bg-gray-50 font-sans text-gray-800">

      {/* Gọi các mảnh ghép vào */}
      <Navbar />
      <Hero />
      <Features />

      {/* Truyền dữ liệu xuống thông qua Props */}
      <DoctorList
        doctors={doctors}
        onSelectDoctor={setSelectedDoctor}
      />

      {/* Truyền state và hàm đóng Modal qua Props */}
      <DoctorModal
        doctor={selectedDoctor}
        onClose={() => setSelectedDoctor(null)}
      />
    </div>
  );
}

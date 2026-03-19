package com.telemedicine.backend;

import com.telemedicine.backend.entity.Doctor;
import com.telemedicine.backend.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(DoctorRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Doctor d1 = new Doctor();
                d1.setName("BS. Nguyễn Văn A");
                d1.setSpecialty("Tim mạch");
                d1.setImageUrl(
                        "https://img.freepik.com/free-photo/doctor-with-his-arms-crossed-white-background_1368-5790.jpg");
                d1.setDescription("Hơn 10 năm kinh nghiệm tại BV Chợ Rẫy. Chuyên gia về can thiệp tim mạch.");

                Doctor d2 = new Doctor();
                d2.setName("BS. Trần Thị B");
                d2.setSpecialty("Nhi khoa");
                d2.setImageUrl(
                        "https://img.freepik.com/free-photo/pleased-young-female-doctor-wearing-medical-robe-with-stethoscope-around-neck-standing-with-folded-arms_1194-58235.jpg");
                d2.setDescription("Tốt nghiệp Đại học Y Dược TP.HCM. Rất yêu trẻ em và tận tâm.");

                Doctor d3 = new Doctor();
                d3.setName("BS. Lê Văn C");
                d3.setSpecialty("Thần kinh");
                d3.setImageUrl("https://img.freepik.com/free-photo/doctor-smiling-with-stethoscope_1154-202.jpg");
                d3.setDescription("Chuyên điều trị các bệnh lý về đau nửa đầu và mất ngủ kinh niên.");

                repository.saveAll(List.of(d1, d2, d3));
                System.out.println(">> Đã chèn dữ liệu bác sĩ mẫu thành công!");
            }
        };
    }
}
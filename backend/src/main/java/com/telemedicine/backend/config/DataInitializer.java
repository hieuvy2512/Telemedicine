package com.telemedicine.backend.config;

import com.telemedicine.backend.entity.*;
import com.telemedicine.backend.entity.enums.AppointmentStatus;
import com.telemedicine.backend.entity.enums.ScheduleStatus;
import com.telemedicine.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * CommandLineRunner là một Interface của Spring Boot.
 * Code trong hàm run() sẽ TỰ ĐỘNG CHẠY 1 LẦN duy nhất ngay sau khi ứng dụng
 * khởi động xong.
 * Rất thích hợp để tạo dữ liệu mẫu (Seed Data) cho môi trường Dev/Test.
 */
@Component
@RequiredArgsConstructor
@Slf4j // Dùng để ghi log ra Terminal cho Pro
public class DataInitializer implements CommandLineRunner {

    // Inject toàn bộ các Repository cần thiết
    private final RoleRepository roleRepository;
    private final SpecialtyRepository specialtyRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final DoctorReviewRepository doctorReviewRepository;

    @Override
    @Transactional // Đảm bảo nếu lỗi ở giữa chừng thì rollback lại toàn bộ, không bị rác DB
    public void run(String... args) throws Exception {
        log.info("--- BẮT ĐẦU KIỂM TRA VÀ KHỞI TẠO DỮ LIỆU MẪU ---");

        // 1. CHỈ TẠO DỮ LIỆU NẾU BẢNG ROLES ĐANG TRỐNG
        if (roleRepository.count() == 0) {
            log.info("Chưa có dữ liệu. Đang tiến hành tạo Roles, Specialties, Users, Appointments...");
            seedData();
            log.info("--- ĐÃ TẠO XONG TOÀN BỘ DỮ LIỆU MẪU THÀNH CÔNG! ---");
        } else {
            log.info("Dữ liệu đã tồn tại. Bỏ qua bước khởi tạo để tránh trùng lặp.");
        }
    }

    private void seedData() {
        // ==========================================
        // 1. TẠO DANH MỤC: ROLES (VAI TRÒ)
        // ==========================================
        Role adminRole = roleRepository
                .save(Role.builder().name("ADMIN").description("Quản trị viên hệ thống").build());
        Role doctorRole = roleRepository.save(Role.builder().name("DOCTOR").description("Bác sĩ khám bệnh").build());
        Role patientRole = roleRepository.save(Role.builder().name("PATIENT").description("Bệnh nhân").build());

        // ==========================================
        // 2. TẠO DANH MỤC: SPECIALTIES (CHUYÊN KHOA)
        // ==========================================
        Specialty cardio = specialtyRepository.save(Specialty.builder()
                .name("Tim mạch")
                .slug("tim-mach")
                .description("Chuyên khám và điều trị các bệnh lý về tim, huyết áp.")
                .iconUrl("https://res.cloudinary.com/demo/image/upload/cardio-icon.png")
                .build());

        Specialty pediatrics = specialtyRepository.save(Specialty.builder()
                .name("Nhi khoa")
                .slug("nhi-khoa")
                .description("Chăm sóc sức khỏe và điều trị bệnh cho trẻ sơ sinh, trẻ em.")
                .build());

        specialtyRepository.save(Specialty.builder().name("Da liễu").slug("da-lieu").build()); // Tạo nhanh 1 cái nữa

        // ==========================================
        // 3. TẠO USERS (TÀI KHOẢN ĐĂNG NHẬP)
        // ==========================================
        // Lưu ý: Ở môi trường thực tế, passwordHash phải được mã hóa bằng BCrypt.
        // Ở đây để demo nhanh, ta lưu chuỗi tĩnh (Ví dụ: đã mã hóa chữ '123456')
        String fakeHashedPassword = "$2a$10$xyz123fakehashpassword_khong_dung_trong_thuc_te";

        // Tài khoản Bác sĩ
        User doctorUser = userRepository.save(User.builder()
                .phoneNumber("0901234567")
                .email("doctor.tran@hospital.com")
                .passwordHash(fakeHashedPassword)
                .role(doctorRole)
                .isActive(true)
                .isVerifiedPhone(true)
                .avatarUrl("https://res.cloudinary.com/demo/image/upload/doctor-avatar.jpg")
                .build());

        // Tài khoản Bệnh nhân
        User patientUser = userRepository.save(User.builder()
                .phoneNumber("0987654321")
                .email("benhnhan.nguyen@gmail.com")
                .passwordHash(fakeHashedPassword)
                .role(patientRole)
                .isActive(true)
                .isVerifiedPhone(true)
                .build());

        // ==========================================
        // 4. TẠO HỒ SƠ CHI TIẾT (DOCTORS & PATIENT_PROFILES)
        // ==========================================
        // Hồ sơ Bác sĩ (Nối với User bác sĩ và Chuyên khoa Tim mạch)
        Doctor doctorProfile = doctorRepository.save(Doctor.builder()
                .user(doctorUser)
                .specialty(cardio)
                .title("ThS. BS")
                .bio("Chuyên gia tim mạch với 15 năm kinh nghiệm tại Bệnh viện Chợ Rẫy.")
                .consultationFee(new BigDecimal("250000.00")) // 250k VND
                .experienceYears(15)
                .isVerifiedDoctor(true) // Đã được admin duyệt
                .build());

        // Hồ sơ Bệnh nhân (Nối với User bệnh nhân)
        PatientProfile patientProfile = patientProfileRepository.save(PatientProfile.builder()
                .user(patientUser)
                .lastName("Nguyễn Thị").firstName("B")
                .dob(LocalDate.of(1990, 5, 20))
                .gender("FEMALE")
                .address("123 Nguyễn Huệ, Quận 1")
                .city("Hồ Chí Minh")
                .bloodGroup("O+")
                .weightKg(new BigDecimal("55.5"))
                .heightCm(160)
                .medicalHistory("Tiền sử gia đình có người bị cao huyết áp.")
                .allergies("Dị ứng hải sản")
                .build());

        // ==========================================
        // 5. TẠO LỊCH LÀM VIỆC CỦA BÁC SĨ (DOCTOR_SCHEDULES)
        // ==========================================
        // Lịch hẹn khám ngày mai, từ 08:00 đến 09:00
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DoctorSchedule schedule1 = doctorScheduleRepository.save(DoctorSchedule.builder()
                .doctor(doctorProfile)
                .workingDate(tomorrow)
                .startTime(LocalTime.of(8, 0))
                .endTime(LocalTime.of(9, 0))
                .status(ScheduleStatus.AVAILABLE)
                .build());

        // Một lịch khác từ 09:00 đến 10:00 (Đã bị đặt)
        DoctorSchedule schedule2 = doctorScheduleRepository.save(DoctorSchedule.builder()
                .doctor(doctorProfile)
                .workingDate(tomorrow)
                .startTime(LocalTime.of(9, 0))
                .endTime(LocalTime.of(10, 0))
                .status(ScheduleStatus.BOOKED) // Sẽ dùng cho cuộc hẹn bên dưới
                .build());

        // ==========================================
        // 6. TẠO CUỘC HẸN KHÁM BỆNH (APPOINTMENTS)
        // ==========================================
        // Bệnh nhân B đặt lịch số 2 của Bác sĩ A
        Appointment appointment = appointmentRepository.save(Appointment.builder()
                .patientProfile(patientProfile)
                .doctor(doctorProfile)
                .schedule(schedule2)
                .status(AppointmentStatus.COMPLETED) // Giả sử cuộc khám này đã khám xong
                .patientNotes("Dạo này tôi hay bị tức ngực, khó thở khi leo cầu thang.")
                .paymentTransactionId("VNPAY_987654321")
                .build());

        // ==========================================
        // 7. TẠO KẾT QUẢ SAU KHÁM (MEDICAL_RECORDS & DOCTOR_REVIEWS)
        // ==========================================
        // Bác sĩ trả kết quả chẩn đoán (Bệnh án)
        medicalRecordRepository.save(MedicalRecord.builder()
                .appointment(appointment)
                .patientProfile(patientProfile)
                .doctor(doctorProfile)
                .diagnosis("Bệnh nhân bị rối loạn thần kinh tim dạng nhẹ do căng thẳng.")
                .treatmentPlan("1. Nghỉ ngơi điều độ. \n2. Uống thuốc theo đơn. \n3. Tái khám sau 1 tháng.")
                .prescriptionUrl("https://res.cloudinary.com/demo/image/upload/don-thuoc-tim-mach.pdf")
                .build());

        // Bệnh nhân để lại đánh giá 5 sao cho Bác sĩ
        doctorReviewRepository.save(DoctorReview.builder()
                .appointment(appointment)
                .patientProfile(patientProfile)
                .doctor(doctorProfile)
                .ratingScore(5)
                .comment("Bác sĩ A tư vấn rất nhiệt tình, nhẹ nhàng. Tôi cảm thấy rất yên tâm.")
                .build());

        // Cập nhật lại điểm đánh giá trung bình cho Bác sĩ (Thực tế nên dùng Trigger ở
        // DB hoặc logic trong Service)
        doctorProfile.setRatingAverage(new BigDecimal("5.0"));
        doctorProfile.setRatingCount(1);
        doctorRepository.save(doctorProfile);
    }
}
package com.telemedicine.backend.config;

import com.telemedicine.backend.entity.*;
import com.telemedicine.backend.entity.enums.*;
import com.telemedicine.backend.repository.*; // Đảm bảo bạn đã tạo các class Repository
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    // Inject toàn bộ Repositories
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PatientProfileRepository patientProfileRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorFreelanceProfileRepository freelanceProfileRepository;
    private final ClinicRepository clinicRepository;
    private final ClinicServiceRepository clinicServiceRepository;
    private final DoctorScheduleRepository doctorScheduleRepository;
    private final ClinicScheduleRepository clinicScheduleRepository;
    private final AppointmentRepository appointmentRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final ReviewRepository reviewRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Kiểm tra xem database đã có dữ liệu chưa, nếu có rồi thì bỏ qua để tránh duplicate
        if (roleRepository.count() > 0) {
            log.info("Dữ liệu mẫu đã tồn tại. Bỏ qua quá trình Seeding.");
            return;
        }

        log.info("Bắt đầu khởi tạo dữ liệu mẫu (Seeding Data)...");

        // -------------------------------------------------------------------
        // 1. TẠO ROLE (Vai trò)
        // -------------------------------------------------------------------
        Role rolePatient = roleRepository.save(Role.builder().name("ROLE_PATIENT").description("Bệnh nhân").build());
        Role roleDoctor = roleRepository.save(Role.builder().name("ROLE_DOCTOR").description("Bác sĩ").build());
        Role roleClinicAdmin = roleRepository.save(Role.builder().name("ROLE_CLINIC_ADMIN").description("Quản trị phòng khám").build());

        // -------------------------------------------------------------------
        // 2. TẠO CHUYÊN KHOA
        // -------------------------------------------------------------------
        Specialty cardio = specialtyRepository.save(Specialty.builder()
                .name("Nội tim mạch").slug("noi-tim-mach").description("Khám các bệnh về tim").build());
        Specialty derma = specialtyRepository.save(Specialty.builder()
                .name("Da liễu").slug("da-lieu").description("Khám các bệnh về da").build());

        // -------------------------------------------------------------------
        // 3. TẠO USER ACCOUNT
        // (Trong thực tế mật khẩu phải được mã hóa bằng PasswordEncoder)
        // -------------------------------------------------------------------
        User userPatient = userRepository.save(User.builder()
                .firstName("Nguyễn Văn").lastName("Bệnh Nhân")
                .phoneNumber("0901234567").email("patient@gmail.com")
                .password("123456").role(rolePatient).isActive(true).build());

        User userDoctor = userRepository.save(User.builder()
                .firstName("Trần Thị").lastName("Bác Sĩ")
                .phoneNumber("0909876543").email("doctor@gmail.com")
                .password("123456").role(roleDoctor).isActive(true).build());

        User userClinicAdmin = userRepository.save(User.builder()
                .firstName("Lê Quản").lastName("Lý")
                .phoneNumber("0911223344").email("admin@clinic.com")
                .password("123456").role(roleClinicAdmin).isActive(true).build());

        // -------------------------------------------------------------------
        // 4. TẠO HỒ SƠ CHI TIẾT (Profiles)
        // -------------------------------------------------------------------
        // Hồ sơ bệnh nhân
        PatientProfile patientProfile = patientProfileRepository.save(PatientProfile.builder()
                .user(userPatient).fullName("Nguyễn Văn Bệnh Nhân")
                .dob(LocalDate.of(1995, 5, 20)).gender("Nam")
                .phoneNumber("0901234567").address("Quận 1, TP.HCM").build());

        // Hồ sơ Bác sĩ (Core)
        Doctor doctor = doctorRepository.save(Doctor.builder()
                .user(userDoctor).specialty(cardio).title("Thạc sĩ - Bác sĩ")
                .experienceYears(10).bio("Bác sĩ chuyên khoa tim mạch hàng đầu.")
                .verificationStatus(VerificationStatus.APPROVED)
                .ratingAverage(5.0).ratingCount(1).build());

        // Hồ sơ khám tự do của Bác sĩ (Giải pháp 2)
        DoctorFreelanceProfile freelanceProfile = freelanceProfileRepository.save(DoctorFreelanceProfile.builder()
                .doctor(doctor).consultationFee(new BigDecimal("300000"))
                .practiceAddress("Trực tuyến tại nhà").isPaymentRequired(true).build());

        // -------------------------------------------------------------------
        // 5. TẠO PHÒNG KHÁM & DỊCH VỤ
        // -------------------------------------------------------------------
        Clinic clinic = clinicRepository.save(Clinic.builder()
                .adminUser(userClinicAdmin).name("Phòng khám Đa khoa Quốc tế")
                .address("123 Lê Lợi").city("TP.HCM").district("Quận 1")
                .verificationStatus(VerificationStatus.APPROVED)
                .doctors(Set.of(doctor)) // Bác sĩ này cũng làm ở phòng khám
                .specialties(Set.of(cardio, derma)).build());

        ClinicService service = clinicServiceRepository.save(ClinicService.builder()
                .clinic(clinic).name("Đo điện tâm đồ (ECG)")
                .price(new BigDecimal("500000")).build());

        // -------------------------------------------------------------------
        // 6. TẠO LỊCH KHÁM (Schedules)
        // -------------------------------------------------------------------
        // Lịch khám tự do (Buổi tối)
        DoctorSchedule docSchedule = doctorScheduleRepository.save(DoctorSchedule.builder()
                .freelanceProfile(freelanceProfile)
                .workingDate(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(19, 0)).endTime(LocalTime.of(19, 30))
                .slotDuration(30).status(ScheduleStatus.AVAILABLE).build());

        // Lịch khám tại phòng khám (Buổi sáng)
        ClinicSchedule clinSchedule = clinicScheduleRepository.save(ClinicSchedule.builder()
                .clinic(clinic).doctor(doctor).specialty(cardio)
                .workingDate(LocalDate.now().plusDays(1))
                .startTime(LocalTime.of(8, 0)).endTime(LocalTime.of(12, 0))
                .slotDuration(30).status(ScheduleStatus.AVAILABLE).build());

        // -------------------------------------------------------------------
        // 7. TẠO CUỘC HẸN (Appointments)
        // -------------------------------------------------------------------
        // Bệnh nhân đặt lịch khám tự do với bác sĩ
        Appointment appointment1 = appointmentRepository.save(Appointment.builder()
                .patientProfile(patientProfile).doctorSchedule(docSchedule)
                .doctor(doctor).specialty(cardio)
                .status(AppointmentStatus.COMPLETED) // Giả sử ca này đã khám xong
                .paymentStatus(PaymentStatus.PAID).build());

        // Bệnh nhân đặt lịch khám tại phòng khám
        Appointment appointment2 = appointmentRepository.save(Appointment.builder()
                .patientProfile(patientProfile).clinic(clinic)
                .clinicSchedule(clinSchedule).clinicService(service)
                .doctor(doctor).specialty(cardio)
                .status(AppointmentStatus.PENDING) // Ca này đang chờ khám
                .paymentStatus(PaymentStatus.UNPAID).build());

        // -------------------------------------------------------------------
        // 8. TẠO BỆNH ÁN & ĐÁNH GIÁ (Sau khi khám xong)
        // -------------------------------------------------------------------
        // Tạo bệnh án cho cuộc hẹn 1 (Đã hoàn thành)
        MedicalRecord record = medicalRecordRepository.save(MedicalRecord.builder()
                .appointment(appointment1).patientProfile(patientProfile)
                .doctor(doctor).diagnosis("Rối loạn nhịp tim nhẹ")
                .treatmentPlan("Uống thuốc, nghỉ ngơi, theo dõi thêm.").build());

        // Bệnh nhân đánh giá cuộc hẹn 1
        Review review = reviewRepository.save(Review.builder()
                .appointment(appointment1).patientProfile(patientProfile)
                .doctor(doctor).ratingScore(5)
                .comment("Bác sĩ tư vấn rất nhiệt tình và kỹ lưỡng!").build());

        // -------------------------------------------------------------------
        // 9. TẠO PHÒNG CHAT (Hỗ trợ tư vấn từ xa)
        // -------------------------------------------------------------------
        ChatRoom chatRoom = chatRoomRepository.save(ChatRoom.builder()
                .patient(userPatient).doctor(doctor)
                .status(ChatRoomStatus.ACTIVE)
                .expiresAt(LocalDateTime.now().plusDays(1)).build());

        log.info("Khởi tạo dữ liệu mẫu THÀNH CÔNG! Đã nạp đủ tất cả các bảng.");
    }
}
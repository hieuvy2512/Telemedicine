# Dùng môi trường Java 17
FROM eclipse-temurin:17-jdk-alpine

# Khai báo thư mục làm việc
WORKDIR /app

# Copy toàn bộ code vào Docker
COPY . .

# Cấp quyền và đóng gói code Java
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

# Lệnh khởi động Backend
CMD ["sh", "-c", "java -jar target/*.jar"]
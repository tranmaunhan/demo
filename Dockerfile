# Stage 1: Build stage
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# 1. Chỉ copy pom.xml và thư mục .mvn để download dependencies trước
# Việc này giúp nếu bạn chỉ sửa code mà không thêm thư viện mới, Docker sẽ bỏ qua bước download cực lâu này
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN chmod +x mvnw

# Download dependencies (offline mode) - bước này sẽ được cache lại
RUN ./mvnw dependency:go-offline -B

# 2. Sau khi đã có dependencies, mới copy source code vào để build
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# Stage 2: Runtime stage (Dùng JRE cho nhẹ)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Tạo một user không có quyền root để chạy app (tăng tính bảo mật)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy file jar từ stage build
# Lưu ý: Nên chỉ định tên cụ thể nếu bạn biết, hoặc dùng wildcard
COPY --from=build /app/target/*.jar app.jar

# Port của Spring Boot
EXPOSE 8080

# Tối ưu RAM cho Java trong Docker
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
<<<<<<< HEAD
﻿# Demo Spring Boot Docker & CI/CD
=======
# Demo Spring Boot Docker & CI/CD
>>>>>>> cb76211944e337f35064adf9caaa43f50ba17c88

## Tổng quan
Đây là dự án Spring Boot tối giản để thực hành Docker và CI/CD với GitHub Actions. Ứng dụng cung cấp 2 endpoint đơn giản.

## API
- `GET /hello` -> `hello everyonee`
- `GET /haha` -> `hello everyonee`

## Yêu cầu
- Java 21 (nếu chạy local)
- Docker (nếu build/chạy container)

## Chạy local (Maven Wrapper)
```bash
# Mac/Linux
./mvnw spring-boot:run

# Windows PowerShell
.\mvnw.cmd spring-boot:run
```

Kiểm tra nhanh:
```bash
curl http://localhost:8080/hello
```

## Build JAR
```bash
./mvnw clean package
java -jar target/*.jar
```

## Docker
```bash
docker build -t demo-java-app .
docker run --rm -p 8080:8080 demo-java-app
```

## CI/CD (GitHub Actions)
Workflow: `.github/workflows/main.yml`

- Trigger: push lên nhánh `master`
- Job `test`: `./mvnw clean verify -B`
- Job `build-and-push`: build và đẩy image lên Docker Hub
- Job `deploy`: SSH vào VPS, kéo image mới, restart container

Secrets cần có:
- `DOCKERHUB_USERNAME`
- `DOCKERHUB_TOKEN`
- `SERVER_HOST`
- `SSH_PRIVATE_KEY`

Image được đẩy lên Docker Hub:
- `<DOCKERHUB_USERNAME>/demo-java-app:latest`

Nếu nhánh chính không phải `master`, hãy sửa lại trong workflow.

## Ghi chú
- Dockerfile dùng multi-stage build (Maven builder + JRE runtime).
- Tên ứng dụng được đặt trong `src/main/resources/application.properties`.
- Đã khai báo dependency Actuator; nếu muốn mở thêm endpoints, hãy cấu hình `management.endpoints.web.exposure.include`.

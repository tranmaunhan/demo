# Demo Spring Boot Docker

Má»¥c tiÃªu cá»§a project nÃ y lÃ  há»c Docker vÃ  CI/CD vá»›i GitHub Actions. á»¨ng dá»¥ng Spring Boot cÃ³ má»™t endpoint Ä‘Æ¡n giáº£n:

- `GET /hello` tráº£ vá» `helloworld`

## YÃªu Cáº§u

- Docker Ä‘Ã£ Ä‘Æ°á»£c cÃ i Ä‘áº·t

## Build Image

```bash
docker build -t demo .
```

## Cháº¡y á»¨ng Dá»¥ng

```bash
docker run --rm -p 8080:8080 demo
```

## Kiá»ƒm Tra Endpoint

```bash
curl http://localhost:8080/hello
```

## CI/CD với GitHub Actions

Workflow đã được tạo sẵn: `.github/workflows/ci-cd.yml`

- CI: Chạy test (`./mvnw test`) cho mọi pull request và push.
- CD: Khi push lên nhánh `main`, workflow sẽ build và đẩy Docker image lên GitHub Container Registry (GHCR).

Image sẽ có dạng:

- `ghcr.io/<owner>/<repo>:latest`
- `ghcr.io/<owner>/<repo>:sha-<short>` (tag theo commit)

Lưu ý:

- Nếu nhánh chính không phải `main`, hãy đổi trong workflow.
- Vào `Settings` -> `Actions` -> `General` -> bật `Workflow permissions` là "Read and write" để GHCR nhận image.

## Ghi ChÃº

- Dockerfile dÃ¹ng multi-stage build Ä‘á»ƒ giáº£m kÃ­ch thÆ°á»›c image.
- Java runtime lÃ  `eclipse-temurin:21-jre`.

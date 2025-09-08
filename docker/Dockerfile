# 1. 베이스 이미지 지정 (JDK 21)
FROM eclipse-temurin:21-jdk-alpine

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. jar 파일 복사 (경로 수정 및 와일드카드 사용)
COPY build/libs/*.jar app.jar

# 4. 포트 노출 (Spring Boot 기본 포트)
EXPOSE 8080

# 5. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.config.additional-location=file:/app/config/"]

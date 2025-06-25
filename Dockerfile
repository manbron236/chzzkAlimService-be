# 1. 베이스 이미지 지정 (JDK 17)
FROM eclipse-temurin:17-jdk-alpine

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. jar 파일 복사
COPY build/libs/chzzk-backend-0.0.1-SNAPSHOT.jar app.jar

# 4. 포트 노출 (Spring Boot 기본 포트)
EXPOSE 8080

# 5. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]

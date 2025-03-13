# Sử dụng Maven để build
FROM maven:3.9.9-amazoncorretto-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

# Build ứng dụng mà không chạy test
RUN mvn package -DskipTests

# Sử dụng Amazon Corretto 21 để chạy ứng dụng
FROM amazoncorretto:21.0.6

WORKDIR /app

# Copy file .jar từ quá trình build
COPY --from=build /app/target/*.jar app.jar

# Cấu hình môi trường (chỉ cần nếu bạn dùng biến môi trường trong application.properties)
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/mydatabase?useSSL=false&allowPublicKeyRetrieval=true
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

ENTRYPOINT ["java", "-jar", "app.jar"]

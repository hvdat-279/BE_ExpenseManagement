#Sử dụng image Maven 3.9.9 với JDK Amazon Corretto 21 để build project.
FROM maven:3.9.9-amazoncorretto-21 AS build

#Thiết lập thư mục làm việc trong container là /app.
WORKDIR /app

#COPY pom.xml . → Sao chép file pom.xml (định nghĩa dependencies).
#COPY src ./src → Sao chép toàn bộ mã nguồn (src) vào container.
COPY pom.xml .
COPY src ./src

#Build ứng dụng bằng Maven, tạo file .jar trong thư mục target/.
#-DskipTests bỏ qua unit tests để giảm thời gian build.
RUN mvn package -DskipTests


#Sử dụng Amazon Corretto 21 (JDK 21) làm runtime để chạy ứng dụng.
#Không cần Maven trong runtime → giúp giảm kích thước image.
FROM amazoncorretto:21.0.6

#Đặt thư mục làm việc là /app.
WORKDIR /app
#Sao chép file .jar đã build từ stage build vào /app/ của container.
#--from=build lấy dữ liệu từ giai đoạn đầu.
COPY --from=build /app/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
************** Cách sử dụng **************
- Tạo docker image
*** docker build -t <ten o hubDocker>/MYCONTAINER:0.0.1 .

- Đưa docker image lên trên docker hub
*** docker image push dat279205/expense-management:0.0.1

- Tạo mạng chung
*** docker network create mynetwork 
mynetwork: là tên do mình đặt

- Chạy MySQL trong mạng trên
*** docker run -d --name mysql-container --network=mynetwork  -e MYSQL_DATABASE=ExpenseManagement -e MYSQL_ROOT_PASSWORD=root  mysql:8.0.40-debian
  Đặt tên container là mysql-container.
  Kết nối container này vào mạng mynetwork, giúp nó có thể giao tiếp với các container khác trong cùng network.
  Thiết lập biến môi trường để tạo database tên ExpenseManagement khi container khởi động lần đầu.
  Thiết lập mật khẩu cho user root của MySQL là root. Đây là tài khoản quản trị cao nhất.

- Chạy ứng dụng
*** docker run --name expense-management --network=mynetwork -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql-container:3306/ExpenseManagement expense-management:0.0.1
  Đặt tên container là expense-management để dễ quản lý.
  Kết nối container vào mạng mynetwork để nó có thể giao tiếp với MySQL container (đã chạy trên cùng network).
  Thiết lập biến môi trường cho container:
  + Chỉ định URL kết nối database MySQL.
  + mysql-container là tên của container MySQL trong cùng network.
  + 3306 là cổng mặc định của MySQL.
  + ExpenseManagement là tên database cần kết nối.
  Image của ứng dụng Spring Boot, đã được build trước đó với tag 0.0.1.
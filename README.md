# Bookstore Management API

RESTful API quản lý nhà sách, xây dựng với Spring Boot và Spring Data JPA.

## Features

- CRUD Author, Category và Book
- Quan hệ `Author 1 ---- N Book N ---- 1 Category`
- Validation, duplicate detection, error response nhất quán
- Tìm kiếm theo title/author, filter theo author/category, pagination và sorting
- Spring Data derived queries và JPQL `@Query`
- JPA auditing cho `createdAt`/`updatedAt`
- Swagger UI tại `/swagger-ui/index.html`

## Technologies

Java 21, Spring Boot 3.4, Spring Web, Spring Data JPA/Hibernate, MySQL, Jakarta Validation, OpenAPI, Maven.

## Architecture

`controller -> service -> repository -> JPA/Hibernate -> MySQL` với DTO tách khỏi entity và `@RestControllerAdvice` xử lý lỗi.

## Project Structure

`entity/`, `repository/`, `dto/`, `service/`, `controller/`, `exception/`.

## Database Setup

```sql
CREATE DATABASE bookstore;
```

Mặc định ứng dụng kết nối MySQL localhost và dùng `spring.jpa.hibernate.ddl-auto=update` cho mục đích practice. Production nên dùng migration tool như Flyway/Liquibase, không phụ thuộc vào `update`.

## Environment Variables

`DB_HOST` (localhost), `DB_PORT` (3306), `DB_NAME` (bookstore), `DB_USERNAME` (root), `DB_PASSWORD` (empty), `DDL_AUTO` (update). Không commit password thật.

## How to Run

```bash
mvn spring-boot:run
```

## API Endpoints

| Method | Endpoint | Mô tả |
|---|---|---|
| POST/GET | `/api/authors` | Tạo / liệt kê author |
| GET/PUT/DELETE | `/api/authors/{id}` | Chi tiết / sửa / xóa author |
| POST/GET | `/api/categories` | Tạo / liệt kê category |
| GET/PUT/DELETE | `/api/categories/{id}` | Chi tiết / sửa / xóa category |
| POST/GET | `/api/books` | Tạo / tìm kiếm sách |
| GET/PUT/DELETE | `/api/books/{id}` | Chi tiết / sửa / xóa sách |

Book list hỗ trợ `keyword`, `authorId`, `categoryId`, `page`, `size`, `sort`, ví dụ: `/api/books?keyword=spring&page=0&size=10&sort=price,asc`.

## Sample Requests

```http
POST /api/authors
{"name":"Craig Walls","email":"craig@example.com","biography":"Spring author"}

POST /api/categories
{"name":"Programming","description":"Software books"}

POST /api/books
{"title":"Spring in Action","isbn":"9781617297571","price":45.99,"stock":20,"publishedDate":"2025-05-10","authorId":1,"categoryId":1}

GET /api/books?keyword=spring
GET /api/books?authorId=1&categoryId=1&page=0&size=10&sort=price,asc
GET /api/books/1
PUT /api/books/1
DELETE /api/books/1
```

## Testing

```bash
mvn clean test
mvn clean package
```

Test profile dùng H2 in-memory; runtime vẫn dùng MySQL.

## Notes

Không cascade delete từ Author/Category xuống Book. Nếu còn sách tham chiếu, API trả `409 Conflict` để tránh mất dữ liệu.

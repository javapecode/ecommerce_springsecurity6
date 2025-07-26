# ecommerce_springsecurity66y
A secure e-commerce web application built with Spring Boot 3, Spring Security 6, and JWT authentication. Features user registration, login, product listing, cart, and role-based access control.

# 🛍️ E-Commerce Spring Security Project

This is a **secure e-commerce web application** built using:

- ✅ Spring Boot 3
- ✅ Spring Security 6 (with JWT)
- ✅ JPA & Hibernate
- ✅ MySQL or H2
- ✅ RESTful API
- ✅ Role-based access (ADMIN, USER)

## 📦 Features

- 👥 User Registration & Login
- 🔐 JWT Authentication
- 🛒 Product Listing & Cart API
- 🧑‍💼 Admin Access for Product Management
- 🚫 Role-based URL protection
- 🌐 Cross-Origin Resource Sharing (CORS)
- 📄 API Documentation (optional: Swagger)

## 🛠️ Tech Stack

| Layer          | Technology         |
|----------------|--------------------|
| Backend        | Spring Boot        |
| Security       | Spring Security 6  |
| Auth           | JWT                |
| Database       | MySQL / H2         |
| Build Tool     | Maven              |

## ▶️ Getting Started

### 1. Clone the repo
```bash
git clone https://github.com/javapecode/ecommerce_springsecurity.git
2. Configure database
Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=yourpassword

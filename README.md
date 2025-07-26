# OnTheWay — Navigation-Based Food Ordering Backend

## Project Overview

**OnTheWay** is a full-stack-ready Java Spring Boot backend built for a GPS-driven food ordering platform.  
It powers real-time interactions between **Users**, **Merchants**, and **Admins**—designed to eventually integrate with navigation apps like **Google Maps** or **Apple Maps** for seamless in-route ordering.

---

## Core Features

- JWT Authentication & Role-based Authorization (`User`, `Merchant`, `Admin`)
- Menu Management for Merchants and Live Order Processing
- Stripe/Razorpay Payment Hook Integration (future-ready)
- Real-time Order Tracking via REST endpoints
- Swagger UI for interactive API testing
- Layered Architecture with DTO-Entity separation & centralized exception handling

---

## Project Structure
```
ontheway-backend/
│
├── src/
│   ├── main/
│   │   ├── java/com/ontheway/
│   │   │   ├── config/           # JWT, Swagger, DB config
│   │   │   ├── controller/       # REST Controllers per module
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   ├── model/            # JPA Entities
│   │   │   ├── repository/       # JPA Repos (DAOs)
│   │   │   ├── service/          # Business Logic
│   │   │   ├── security/         # JWT, Auth logic
│   │   │   ├── exception/        # Global error handling
│   │   │   ├── util/             # Helpers (e.g. route/notification utils)
│   │   │   └── OnthewayApplication.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── static/
│   └── test/
│       └── java/com/ontheway/    # Unit & Integration tests
│
├── docs/                        # OpenAPI spec (Swagger), ERD diagrams
├── postman/                     # Sample Postman collection
├── README.md
├── Dockerfile
└── pom.xml
```
---

## ⚙Setup Instructions

### 1. Prerequisites

- Java 17
- Maven
- MySQL running locally
- IDE (IntelliJ, VS Code, etc.)

---

### 2. MySQL Setup

Create the database:

```sql
CREATE DATABASE onthewaydb;
```
### 3. Configure application.yml

Located in src/main/resources/application.yml:
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/onthewaydb?useSSL=false&serverTimezone=UTC
    username: your sql username 
    password: your sql password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
```
### 4. Build & Run the Project

Build the project with:
```
mvn clean install -s custom-m2/settings.xml -U
```
Run the Spring Boot app:
```
mvn -s custom-m2/settings.xml spring-boot:run
```
By default, the server runs at: http://localhost:8080
Swagger API Testing

Once the app is running, open Swagger:

URL: ```http://localhost:8080/swagger-ui.html```
	•	Click on the Authorize 🔒 button
	•	Use token format: Bearer <your_token_here>
 
### Key Highlights
```
✅ Multi-role login (User, Merchant, Admin)
✅ Secure JWT-based authentication
✅ Real-time food ordering logic
✅ Payment support: Stripe & Razorpay (optional)
✅ Swagger-powered REST API testing
✅ MapStruct for efficient DTO ↔ Entity conversion
✅ Easily integrable with map-based frontends
```
### License

Bit License
Copyright (c) 2025 Manohar Eldhandi
All rights reserved. No part of this project may be reproduced, distributed, or used for commercial or non-commercial purposes without explicit permission from the author.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND.

### Built with Spring Boot magic, Google/Apple Maps dreams, and a dash of Swagger
---
Let me know if you want badges, deployment instructions, or a short project demo GIF added!


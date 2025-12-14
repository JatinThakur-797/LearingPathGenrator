# LearningPathGenerator

A **backend application** that generates personalized learning paths based on user input. This service exposes REST APIs consumed by the frontend (e.g., `LearingPathGenrator-Frontend`) and uses secure authentication.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [REST API Endpoints](#rest-api-endpoints)
- [Data Models](#data-models)
- [How to Run](#how-to-run)
- [Environment Variables](#environment-variables)
- [Integration](#integration)
- [Testing](#testing)
- [Future Work](#future-work)
- [Contact](#contact)

## About

This service powers the **learning path generation logic** and serves dynamic, personalized recommendations based on user goals, skills, and preferences. It is designed as a scalable backend with secure authentication, integration with frontend clients, and reusable API logic.

## Features

- User signup and login
- Secure token-based authentication (JWT)
- Protected APIs
- Learning path generation logic
- Role-based access support (optional)
- Modular, layered architecture

## Architecture

This project follows a **layered REST architecture**:

- **Controller Layer** – Handles HTTP requests/responses
- **Service Layer** – Implements business logic
- **Repository Layer** – Data access (database/collections)
- **Security Layer** – JWT auth and route protection

## Technology Stack

- **Java**
- **Spring Boot**
- **Spring Security (JWT)**
- **REST APIs**
- **Maven**
- **MySQL / MongoDB / any persistence**
- **Swagger/OpenAPI (optional)**

## REST API Endpoints

### Authentication

| Method | Path                  | Description |
|--------|------------------------|-------------|
| POST   | `/api/auth/signup`     | Create a new user |
| POST   | `/api/auth/login`      | User login, returns JWT |
| POST   | `/api/auth/refresh`    | Refresh access token |

### Learning Path

| Method | Path                         | Auth       | Description |
|--------|------------------------------|------------|-------------|
| POST   | `/api/paths/generate`        | Required   | Generate learning path based on user profile |
| GET    | `/api/paths/{userId}`        | Required   | Get saved/generated path by user |

(Actual paths may vary by implementation)

## Data Models (Example)

```java
class User {
    String id;
    String username;
    String email;
    String password;
    Role role;
}

class LearningPathRequest {
    String goal;
    List<String> skills;
    int experienceLevel;
}

class LearningPath {
    String userId;
    List<String> recommendedTopics;
}
How to Run
Prerequisites
JDK 11 or higher

Maven

Database (MySQL / MongoDB) configured

IDE (IntelliJ IDEA / Eclipse)

Steps
Clone the repository:

bash
Copy code
git clone https://github.com/JatinThakur-797/LearingPathGenrator.git
Import as a Maven project in your IDE.

Set environment variables (see next section).

Build and run:

bash
Copy code
mvn clean install
mvn spring-boot:run
Service will start on:

arduino
Copy code
http://localhost:8080
Environment Variables
Create a .env or application.properties with:

properties
Copy code
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/learningdb
spring.datasource.username=<your_db_user>
spring.datasource.password=<your_db_pass>

jwt.secret=<your_jwt_secret>
jwt.expiration=3600000
Adjust according to your DB choice or JWT settings.

Integration
Connect this backend with:

Frontend: React/Vite (LearingPathGenrator-Frontend)

Mobile clients if required

Postman / Swagger for API testing

Example frontend base API URL:

bash
Copy code
VITE_API_BASE_URL=http://localhost:8080/api
Testing
Use Postman or Swagger UI

Test auth first (login/signup)

Protect endpoints using tokens

Validate the learning path generation logic

Future Work
Add database persistence for generated paths

Add role-based access (admin/user)

Add API documentation (Swagger/OpenAPI)

Add caching

Add rate limiting

Write integration tests

Contact
Maintained by Jatin Thakur
GitHub: https://github.com/JatinThakur-797

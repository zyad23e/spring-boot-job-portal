# Spring Boot Job Portal

A full-stack Spring Boot web application implementing a job portal using Spring MVC, Thymeleaf, Spring Security, and JPA/Hibernate with MySQL persistence.

This project demonstrates the design and extension of a server-rendered web platform with authentication, authorization, relational data modeling, and a layered MVC architecture. The application was built from a guided foundation and independently extended with additional recruiter and candidate workflows, transactional fixes, and data integrity improvements.

---

## Current Features

- User authentication and authorization with Spring Security
- Role-based access control (Recruiter and Candidate)
- Recruiter job posting and management
- Recruiter-owned job deletion with authorization checks
- Candidate profiles and job applications
- Candidate saved-jobs management (add and remove saved jobs)
- Transaction-safe delete operations using Spring `@Transactional`
- Refactored JPA relationships to prevent unsafe cascade deletes
- Server-side rendered views with Thymeleaf
- Relational database persistence with MySQL

---

## Architecture Overview

The application follows a layered architecture:

- **Controller Layer** – Handles HTTP requests, authorization checks, and view rendering
- **Service Layer** – Contains business logic and transactional operations
- **Repository Layer** – Data access using Spring Data JPA
- **View Layer** – Server-side rendered Thymeleaf templates

This structure promotes separation of concerns, maintainability, and testability.

---

## Key Technologies

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf
- Hibernate / JPA
- MySQL
- Maven

---

## How to Run

### Prerequisites
- Java (JDK 17+ recommended)
- Maven
- MySQL

### Setup & Run
1. Clone the repository
2. Create a MySQL database (e.g. `jobportal`)
3. Configure database credentials in `application.properties`
4. Run the application using your IDE or Maven:
   ```bash
   mvn spring-boot:run


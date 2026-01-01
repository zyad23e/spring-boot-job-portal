# Spring Boot Job Portal

A full-stack Spring Boot web application implementing a job portal using Spring MVC, Thymeleaf, Spring Security, and JPA/Hibernate with MySQL persistence.

This project demonstrates the design and implementation of a complete server-rendered web application, including authentication, authorization, data persistence, and MVC architecture.

![Last Commit](https://img.shields.io/github/last-commit/zyad23e/spring-boot-job-portal?style=flat-square)

---

## Project Scope

- User authentication and authorization
- Role-based access control
- Job posting and management
- Server-side rendered views with Thymeleaf
- Relational database persistence

---

## Key Concepts & Technologies Practiced

- **Java & OOP** – domain modeling, encapsulation, layered design  
- **Spring Boot** – application configuration and auto-configuration  
- **Spring MVC** – controllers, request mapping, form handling, validation  
- **Thymeleaf** – server-side templates, layouts, and dynamic views  
- **Spring Security** – authentication, authorization, role-based access  
- **Hibernate & JPA** – entity mapping, repositories, relationships  
- **SQL & Database Design** – relational modeling, MySQL integration  
- **Persistence Layer** – CRUD operations, transactional boundaries  
- **Build & Config** – Maven, application.properties  

---

## Architecture Overview

The application follows a standard layered architecture:

- **Controller Layer** – handles HTTP requests and view rendering  
- **Service Layer** – business logic and transactional operations  
- **Repository Layer** – data access via Spring Data JPA  
- **View Layer** – Thymeleaf templates rendered on the server  

---

## Progress

- [ ] Project scaffolding and configuration  
- [ ] MVC controllers and view templates  
- [ ] Authentication and authorization  
- [ ] Job entity modeling and persistence  
- [ ] CRUD workflows for job listings  
- [ ] Security hardening and validation  

(Progress will be updated as features are implemented.)

---

## How to Run

### Prerequisites
- Java (JDK)
- Maven
- MySQL

### Run
1. Clone the repository  
2. Configure database credentials in `application.properties`  
3. Run the application using:

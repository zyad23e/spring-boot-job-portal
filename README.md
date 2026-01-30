# Spring Boot Career Portal

Live Demo: http://job-portal-env.eba-g3y2ufbh.us-west-1.elasticbeanstalk.com/

A full-stack Spring Boot web application implementing a job portal using Spring MVC, Thymeleaf, Spring Security, and JPA/Hibernate with MySQL persistence.

This project demonstrates the design and extension of a server-rendered web platform with authentication, authorization, relational data modeling, and a layered MVC architecture. The application was built on a guided foundation and independently extended with additional recruiter and candidate workflows, transactional fixes, and data-integrity improvements.

---

## Features

- User authentication and role-based authorization with Spring Security
- Distinct user roles: Recruiter and Candidate
- Recruiter job posting, management, and ownership-enforced deletion
- Candidate profiles, job applications, and saved-job workflows
- Transaction-safe delete operations using Spring `@Transactional`
- Refactored JPA relationships to prevent unsafe cascade deletes
- Server-side rendered views using Thymeleaf
- Relational persistence with MySQL

---

## Architecture Overview

The application follows a layered architecture:

- **Controller Layer** – Handles HTTP requests, authorization checks, and view rendering
- **Service Layer** – Encapsulates business logic and transactional boundaries
- **Repository Layer** – Data access via Spring Data JPA
- **View Layer** – Server-side rendered Thymeleaf templates

This structure enforces separation of concerns, maintainability, and predictable data access patterns.

---

## Deployment

The application is deployed on **AWS Elastic Beanstalk** as a packaged Spring Boot JAR and runs behind an Nginx reverse proxy.  
Application data is persisted in **Amazon RDS (MySQL)**.

Production configuration (database credentials, profiles, ports) is managed via environment variables and is not committed to version control.

---

## Technologies Used

- Java
- Spring Boot
- Spring MVC
- Spring Security
- Hibernate / JPA
- Thymeleaf
- MySQL
- Maven
- AWS Elastic Beanstalk
- AWS RDS

---

## Running Locally

### Prerequisites
- Java 17+
- Maven
- MySQL

### Setup
1. Clone the repository
2. Create a MySQL database (e.g. `jobportal`)
3. Configure database credentials in `application.properties`
4. Run the application:
   ```bash
   mvn spring-boot:run


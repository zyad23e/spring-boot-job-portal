# Spring Boot Job Portal

A full-stack Spring Boot web application implementing a job portal using Spring MVC, Thymeleaf, Spring Security, and JPA/Hibernate with MySQL persistence.

This application demonstrates the design and implementation of a server-rendered web platform, including authentication, authorization, relational data modeling, and a layered MVC architecture.

## Current Features
- User authentication and authorization
- Role-based access control (Recruiter and Candidate)
- Recruiter job posting and management
- Candidate profiles and job applications
- Server-side rendered views with Thymeleaf
- Relational database persistence with MySQL

## Planned Enhancements
- Advanced job search and filtering
- Pagination and sorting for job listings
- Profile management improvements
- Resume upload and management
- Enhanced validation and global error handling

## Architecture Overview
The application follows a layered architecture:

- **Controller Layer** – handles HTTP requests and view rendering
- **Service Layer** – contains business logic and transactional operations
- **Repository Layer** – data access using Spring Data JPA
- **View Layer** – Thymeleaf templates rendered on the server

## Key Technologies
- Java
- Spring Boot
- Spring MVC
- Spring Security
- Thymeleaf
- Hibernate / JPA
- MySQL
- Maven

## How to Run

### Prerequisites
- Java (JDK)
- Maven
- MySQL

### Run
1. Clone the repository
2. Configure database credentials in `application.properties`
3. Run the application using your IDE or Maven

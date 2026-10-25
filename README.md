# Food Ordering Backend

A REST API for a food ordering application built with Java and Spring Boot.

## Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA and Hibernate
- PostgreSQL
- Maven

## Features

- Food management REST APIs
- Shopping cart and cart item operations
- User registration and login
- Password hashing with BCrypt
- JWT-based authentication
- Centralized exception handling

## Getting Started

### Prerequisites

- Java 17
- PostgreSQL

### Setup

1. Clone this repository.
2. Create a PostgreSQL database.
3. Configure the required environment variables:
    - `DB_URL`
    - `DB_USERNAME`
    - `DB_PASSWORD`
    - `JWT_SECRET`
4. Run the application on Windows:

   ```bash
   mvnw.cmd spring-boot:run
   ```

## API Endpoints

The application provides endpoints for authentication, food management, and cart operations. See the controller classes in `src/main/java` for the implemented routes.

## Project Status

This project is under development. Some features and error-handling flows may require further testing.

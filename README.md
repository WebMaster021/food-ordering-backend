# Food Ordering Backend

A RESTful backend for a food-ordering application built with Java and Spring Boot. The project focuses on backend development fundamentals, including REST APIs, relational database management, authentication, and business logic.

The application supports food management, shopping cart operations, user registration, and JWT-based authentication.

## Tech Stack

- **Language:** Java 17
- **Framework:** Spring Boot
- **Security:** Spring Security, JWT
- **Database:** PostgreSQL
- **ORM:** Spring Data JPA, Hibernate
- **Build Tool:** Maven
- **Password Hashing:** BCrypt
- **API Testing:** Postman

## Features

### User Authentication and Security

- User registration with email and password.
- Password hashing using BCrypt.
- User login with credential verification.
- JWT generation after successful login.
- Stateless authentication using Spring Security.
- Protected API endpoints requiring a valid JWT.
- JWT signature and expiration validation.
- Custom user loading through Spring Security's `UserDetailsService`.

### Food Management

- Create food items.
- Retrieve all food items.
- Retrieve an individual food item by ID.
- Delete food items.
- Persist food data in PostgreSQL.

### Shopping Cart

- Create a cart associated with an authenticated user.
- Add food items to a cart.
- Increase the quantity of an existing cart item.
- Retrieve cart information and cart items.
- Update cart item quantities.
- Remove a cart item by setting its quantity to zero.
- Validate food and cart existence through service-layer logic.

### Error Handling

- Centralized exception handling using `@RestControllerAdvice`.
- Custom exceptions for missing resources, invalid quantities, duplicate registrations, and invalid credentials.
- Structured error responses.

## API Endpoints

All routes below describe the application's intended API surface. Cart ownership enforcement and the associated retrieval error are still being worked on, so not every endpoint should be considered fully verified.

### Authentication

| Method | Endpoint | Description | Authentication |
|---|---|---|---|
| POST | `/auth/register` | Register a new user | Not required |
| POST | `/auth/login` | Authenticate and receive a JWT | Not required |

### Food

| Method | Endpoint | Description |
|---|---|---|
| POST | `/foods` | Create a food item |
| GET | `/foods` | Retrieve all food items |
| GET | `/foods/{id}` | Retrieve a food item by ID |
| DELETE | `/foods/{id}` | Delete a food item |

### Cart

| Method | Endpoint | Description |
|---|---|---|
| POST | `/carts` | Create a cart for the authenticated user |
| GET | `/carts/{cartId}` | Retrieve cart information |
| POST | `/carts/{cartId}/items` | Add a food item to a cart |
| GET | `/carts/{cartId}/items` | Retrieve cart items |
| PATCH | `/carts/{cartId}/items/{foodId}` | Update quantity or remove an item |

Cart routes require authentication at the Spring Security level. However, **cart ownership authorization is still under development** and must be verified for every cart operation before the API can be considered secure against cross-user access.

## Authentication Flow

1. A user registers through `/auth/register`.
2. The application hashes the password using BCrypt before storing the user.
3. The user logs in through `/auth/login`.
4. The application verifies the submitted credentials.
5. On successful login, the application returns a signed JWT with a 15-minute expiration.
6. The client sends the token in the `Authorization` header when accessing protected endpoints.

Example authorization header:

```http
Authorization: Bearer <your-jwt-token>
```

The application validates the token and loads the associated user before establishing the authenticated security context.

## Getting Started

### Prerequisites

Install the following:

- Java 17
- PostgreSQL
- Git

The project includes the Maven Wrapper, so a separate Maven installation is not required.

### 1. Clone the repository

```bash
git clone <repository-url>
cd food-ordering
```

Replace `<repository-url>` with this repository's HTTPS URL.

### 2. Create a PostgreSQL database

Create a database for the application using PostgreSQL.

For example:

```sql
CREATE DATABASE food_ordering;
```

Use the database name and credentials appropriate for your local setup.

### 3. Configure environment variables

The application expects these environment variables:

| Variable | Purpose |
|---|---|
| `DB_URL` | PostgreSQL JDBC connection URL |
| `DB_USERNAME` | PostgreSQL username |
| `DB_PASSWORD` | PostgreSQL password |
| `JWT_SECRET` | Base64-encoded secret key used to sign JWTs |

Configure these variables in your local environment or IDE run configuration. Do not commit real credentials or secret keys to the repository.

For example, the JDBC URL may follow this format:

```text
jdbc:postgresql://localhost:5432/food_ordering
```

Ensure the JWT secret is a valid Base64-encoded key of sufficient length for the configured signing algorithm.

### 4. Run the application

On Windows, using PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

The application will use the configured database connection and JWT secret.

## Testing the API

Use Postman or another HTTP client to test the endpoints.

A recommended manual testing sequence is:

1. Register a user.
2. Log in and obtain a JWT.
3. Send the token in the `Authorization` header.
4. Create a cart for the authenticated user.
5. Create or retrieve food items.
6. Add food items to the cart and update their quantities.
7. Test requests with missing, expired, or invalid JWTs.
8. Test whether one authenticated user can access another user's cart.

The final ownership tests are particularly important and remain part of the current development work.

To run the automated Maven test suite on Windows:

```powershell
.\mvnw.cmd clean test
```

Tests should be run with the required environment variables configured in the same environment from which Maven is launched.

## Current Development Status

This project is actively being developed as a practical backend learning project.

**Implemented:**
- REST API structure with controller, service, repository, entity, and DTO layers.
- PostgreSQL persistence through Spring Data JPA and Hibernate.
- User registration and login.
- BCrypt password hashing.
- JWT-based stateless authentication.
- Protected endpoint authentication and token validation.
- Food management and shopping cart operations.
- Centralized exception handling.

**Currently working on:**
- Completing and verifying cart ownership authorization.
- Ensuring users can access and modify only their own carts.
- Debugging an HTTP 500 error encountered while retrieving a cart after introducing the ownership check.
- Rechecking the complete API flow and automated tests after resolving the issue.

## Future Improvements

- Complete and test authorization for every cart operation.
- Add automated unit and integration tests.
- Improve API documentation with OpenAPI/Swagger.
- Review validation and error responses across all endpoints.

## Purpose

This project is intended to demonstrate practical experience with Java backend development, Spring Boot, REST API design, relational databases, and application security while continuing to improve correctness, maintainability, and test coverage.

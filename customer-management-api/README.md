# Customer Management API (Spring Boot 3 + JWT + H2)

CRUD for customers, secured with JWT. Java 17, Maven, H2 database (no install needed).

## Run
1. No database setup needed: H2 runs in memory (data resets on restart).
   Console: http://localhost:8080/h2-console, JDBC URL `jdbc:h2:mem:customerdb`, user `sa`, empty password.
   For persistence use the `jdbc:h2:file:./data/customerdb` URL noted in `application.properties`.
2. Open the folder in IntelliJ (as a Maven project) and run `CustomerJwtApplication`,
   or: `mvn spring-boot:run`

A default admin is created on startup: `admin` / `admin123` (change in application.properties).

## Endpoints
| Method | URL | Auth | Notes |
|---|---|---|---|
| POST | /api/auth/register | public | creates a USER, returns token |
| POST | /api/auth/login | public | returns token |
| POST | /api/customers | Bearer | create |
| GET | /api/customers | Bearer | list |
| GET | /api/customers/{id} | Bearer | read |
| PUT | /api/customers/{id} | Bearer | update |
| DELETE | /api/customers/{id} | Bearer + ADMIN | delete |

## Postman quick test
1. POST `http://localhost:8080/api/auth/login`
   ```json
   { "username": "admin", "password": "admin123" }
   ```
2. Copy `token` from the response.
3. In other requests: Authorization tab -> Bearer Token -> paste it.
4. POST `http://localhost:8080/api/customers`
   ```json
   { "name": "Ravi Kumar", "email": "ravi@example.com", "phone": "9876543210", "address": "Lucknow" }
   ```

## Before production
- Replace `jwt.secret` (generate: `openssl rand -base64 48`) and keep it out of source control.
- Change the default admin password.

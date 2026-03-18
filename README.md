# E-Commerce Service

## Overview

RESTful API for managing users, products, categories, authentication, and shopping cart for an e-commerce system.

---

## Tech Stack

* Java
* Spring boot
* PostgreSQL

---

## Base URL

```
http://localhost:8080/api
```

---

## API Documentation

Interactive API docs are available via Swagger UI:

```
http://localhost:8080/api/swagger-ui/index.html
```

Use Swagger to explore endpoints, request/response schemas, and test APIs directly.

---

## Authentication

1. Login to get access token:

```
POST /auth/login
```

2. Use token for protected routes:

```
Authorization: Bearer <access_token>
```

---

## Environment Variables

```bash
DBMS_CONNECTION=jdbc:postgresql://localhost:5432/your_db
DBMS_USERNAME=your_database_username
DBMS_PASSWORD=your_database_password
JWT_SIGNER_KEY=your_secret_key
```

Notes:

* Ensure all environment variables are set before running the application.
* You can generate a secure `JWT_SIGNER_KEY` from: [https://generate-random.org/encryption-keys](https://generate-random.org/encryption-keys)

---

## Project Structure

```
src/
 ├── configuration/
 ├── controller/
 ├── dto/
 ├── entity/
 ├── enums/
 ├── exception/
 ├── mapper/
 ├── repository/
 ├── service/
 └── ECommerceApplication.java
```

---

## Features

* User management
* Product management
* Category management
* Authentication (JWT)
* Shopping cart

---

## Future Improvements

* Pagination, filtering, sorting
* Payment
* Unit & integration tests
* CI/CD pipeline

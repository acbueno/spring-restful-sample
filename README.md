# Spring RESTful Sample

This repository contains a sample RESTful application developed with Spring Boot. The project's goal is to demonstrate best practices for creating RESTful APIs using Java and the Spring Framework.

## Main Features

- **Full CRUD** for system entities.
- **Relational database** integration using Spring Data JPA.
- **Data validation** with Bean Validation.
- **Standardized API responses**.
- **Logging mechanism** configured.

## Technologies Used

- **Java 17**
- **Spring Boot**
  - Spring Data JPA
  - Spring Web
  - Spring Validation
- **H2 Database** (In-memory database for testing)
- **Maven**

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.springrestfulsample
│   │   │       ├── controller  # REST controllers
│   │   │       ├── dto         # Data Transfer Objects (DTOs)
│   │   │       ├── model       # Domain models/entities
│   │   │       ├── repository  # Repository interfaces
│   │   │       ├── service     # Business logic and services
│   │   │       └── SpringRestfulSampleApplication.java  # Main class
│   │   └── resources
│   │       ├── application.properties  # Spring Boot configurations
│   │       └── data.sql                 # Initial data for testing
│   └── test
│       └── java
│           └── com.example.springrestfulsample
│               └── *  # Unit and integration tests
└── pom.xml  # Dependency manager
```

## Setup and Execution

### Prerequisites

Make sure you have the following installed:
- **Java 17**
- **Maven**

### How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/acbueno/spring-restful-sample.git
   cd spring-restful-sample
   ```

2. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```

3. Access the API at:
   ```
   http://localhost:8080
   ```

### Key Endpoints

| Method | Endpoint           | Description                |
|--------|--------------------|----------------------------|
| GET    | `/api/bikes`       | List all bikes             |
| POST   | `/api/bikes`       | Create a new bike          |
| GET    | `/api/bikes/{id}`  | Fetch a bike by ID         |
| PUT    | `/api/bikes/{id}`  | Update a bike              |
| DELETE | `/api/bikes/{id}`  | Delete a bike              |

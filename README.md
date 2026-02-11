# Core Banking System

A REST API implementation for a simplified Core Banking system, managing Accounts and Transactions.

## 📋 Prerequisites

Before running the project, ensure you have the following installed:

* **Docker & Docker Compose**: Necessary to run the application and the database in a containerized environment (Recommended).
* **Java 21**: Required if you plan to compile or run the application locally.
* **Maven 3.9+**: Required for building the project and running tests locally.

---

## 🏗️ Technical and Architectural Decisions

The project was developed inspired by **Domain-Driven Design (DDD)** and **Clean Architecture** concepts to ensure long-term maintainability and clear separation of concerns.

* **Domain Layer:**
    * Contains the core business logic and entities.
    * **Financial Integrity:** Transaction sign conversion is encapsulated within the domain to ensure that purchase/withdrawal operations are always stored as negative values, regardless of the entry point.
* **Service Layer:**
    * Acts as an orchestrator, managing transactions, ensuring account existence, and handling business exceptions (e.g., preventing duplicate accounts).
* **Infrastructure & API Layer:**
    * Handles external communication via REST.
    * **Global Exception Handling:** A centralized controller advice manages errors, converting business exceptions into standard, clean JSON responses (404, 422, 400).
* **Persistence:**
    * Uses Spring Data JPA with PostgreSQL.
    * **Identity Management**: Adopted GENERATED ALWAYS AS IDENTITY for primary keys to ensure ANSI SQL compliance and seamless integration with Hibernate 6, avoiding legacy sequence issues.

### Why this architecture?
This structure ensures that the **business rules are protected** from infrastructure changes. By isolating the logic of transaction signs and account validation, the system becomes trivial to test and highly resilient to bugs.

## 📚 Justification for Libraries

1.  **SpringDoc OpenAPI (2.8.5):**
    * Chosen to provide a "Living Documentation" via Swagger UI. Version 2.8.5 was specifically selected to mitigate CVE-2025-41242 (Path Traversal vulnerability) and ensure compatibility with Spring 6.2.
2.  **Lombok:**
    * Used to reduce boilerplate code, keeping the focus on the actual business logic rather than getters, setters, and constructors.
3.  **Spring Validation:**
    * Ensures data integrity at the edge of the application, preventing malformed requests from reaching the service layer.

## 🚀 How to Run

### Option 1: Using Docker (Recommended)
The entire environment is orchestrated to ensure the application and database communicate seamlessly.

1.  **Build and Start:**
    ```sh
    docker-compose up --build
    ```
2.  **Access:**
    * API: `http://localhost:8080`
    * Swagger UI: `http://localhost:8080/swagger-ui/index.html`

### Option 2: Local Development
* Ensure a PostgreSQL instance is running on port 5432.
* Run: `mvn spring-boot:run`

## ✅ Testing Strategy

* **Unit Tests:** High coverage of the domain and service layers using JUnit 5.

To run all tests:
```sh
mvn test
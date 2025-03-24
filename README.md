## How to start the app

- Run "docker-compose up"
- Export the postMan collection (glady_postman_collection) to postMan
- Use Postman collection to : create user/company, credit company, gift/meal deposit to a user's balance

# 💸 Wedoogift Clone — Gift & Meal Deposit Platform

This project is a Wedoogift-inspired backend application that handles the distribution of **gift** and **meal** deposits to users on behalf of companies. The system includes logic for **company balances**, **user deposit tracking**, **deposit expiry**, and **distributed architecture** using **Hexagonal Architecture** (also known as Onion or Clean Architecture).

---

## 🚀 Tech Stack

| Layer            | Technology                                                        |
|------------------|-------------------------------------------------------------------|
| Language         | Java 21                                                           |
| Build Tool       | Gradle                                                            |
| Framework        | Spring Boot 3.4+                                                  |
| Persistence      | PostgreSQL                                                        |
| ORM              | Spring Data JPA + Hibernate                                       |
| Message Broker   | Kafka (event-driven architecture) (If microservices architecture) |
| Containerization | Docker + Docker Compose                                           |
| Architecture     | Hexagonal Architecture (Ports & Adapters)                         |
| API              | RESTful JSON API                                                  |
| Testing          | JUnit 5 + Mockito                                                 |

---

## 🧱 Hexagonal Architecture (Onion / Clean)

This project is designed around **Hexagonal Architecture**, which separates **domain logic** from infrastructure and delivery mechanisms, improving **testability**, **modularity**, and **maintainability**.

                     [ Infrastructure Layer ]
                          Controllers
                      Kafka Consumers/Producers
                      JPA Repositories, DTOs
                               │
                               ▼
                     [ Application Layer ]
                      Use Cases / Services
                (Orchestrates calls to the domain)
                               │
                               ▼
                     [ Domain Layer (Core) ]
     Entities, Value Objects, Enums, and Port Interfaces


### ✅ Benefits:

- Domain logic is completely **independent** of frameworks and infrastructure.
- Infrastructure can be easily swapped (e.g., PostgreSQL → Mongo, REST → GraphQL).
- Business rules are **testable in isolation**.

---

## ✅ Design Patterns in Use

| Pattern                      | Usage                                                                 |
|-----------------------------|------------------------------------------------------------------------|
| **Hexagonal Architecture**  | Core design structure separating domain, application, and infrastructure |
| **Repository Pattern**      | `UserRepository`, `CompanyRepository`, `DepositRepository` interfaces and their adapters |
| **Strategy Pattern**        | Abstract `Deposit` class with `GiftDeposit` and `MealDeposit` implementations |
| **DTO Pattern**             | `DepositDto`, `DistributeDepositRequestDTO`, `CreditBalanceRequestDTO` |
| **Factory (Simple)**        | In `DistributeDepositService`, a `switch` creates different `Deposit` objects |
| **Mapper Pattern**          | `UserMapper`, `CompanyMapper`, `DepositMapper` convert between entities and domain models |
| **Service Pattern**         | All use cases are implemented as Spring `@Service` classes |
| **Dependency Injection**    | Spring manages and injects all dependencies via constructors |
| **Record Pattern**          | DTOs are implemented as Java `record` types for conciseness and immutability |

---

## 🧩 Modules Overview

### Domain Layer (`domain`)
- `Company`, `User`, `Deposit`, `GiftDeposit`, `MealDeposit`
- Ports (`DepositRepository`, `UserRepository`, `CompanyRepository`)
- Business rules like balance checking, deposit expiry, etc.

### Application Layer (`application`)
- Use cases like:
    - `DistributeDepositUseCase`
    - `GetUserBalanceUseCase`
- These orchestrate the domain layer via the ports.

### Infrastructure Layer (`infrastructure`)
- Implements repositories with Spring Data JPA
- REST controllers (`DepositController`, `UserController`, `CompanyController`)
- Kafka integration (if using micro services)
- PostgreSQL setup

---

## 💼 Transaction Management

This application uses Spring's declarative transaction management to ensure data integrity across multiple operations.

For example, in the DistributeDepositService, the process of:

    Debiting the company's balance,

    Creating and assigning a deposit to the user,

    Saving the updated company, user, and deposit,

...is wrapped in a single transaction using Spring's @Transactional annotation (to be added at the class or method level). This guarantees that if any step fails (e.g., insufficient company balance), none of the changes are persisted, preventing partial updates or data inconsistencies.

    🔒 This protects your system from scenarios like a deposit being created without the corresponding company debit, or vice versa.


## 🛠️ Running the Project

### Pre-requisites

- Java 21
- Docker & Docker Compose
- Gradle

### 1. Start App with Docker compose

```bash
docker-compose up --build
```

### 2. Access swagger :
 👉 http://localhost:8080/swagger-ui.html or http://localhost:8080/swagger-ui/index.html

### 3. Import postman_collection.json to YOUR postman

- Have fun testing ! :)

### 4. Launcing tests with Jacoco test coverage report :

```bash 
 ./gradlew test jacocoTestReport
```

And then open the file in "/build/jacocoHtml/index.html" inside your web-browser

----

## Routes

- **UserController**
  - `POST /api/users` - Create a new user
  - `GET /api/users/{userId}/balance` - Get user balance
  - `GET /api/users/{userId}/{type}/balance` - Get user balance for specific type of deposit
  - `GET /api/users/{userId}/deposits` - Get all deposits for a user

- **CompanyController**
  - `POST /api/companies` - Create a new company
  - `POST /api/companies/{companyId}/credit` - Credit company balance
  - `GET /api/companies/{companyId}/balance` - Get company balance

- **DepositController**
  - `POST /api/deposits/distribute` - Distribute a gift or meal deposit


## 🔧 (Possible) Future Enhancements in a real scenario

   -  Add authentication/authorization (JWT, OAuth2, etc.)

   - Implement domain events (Observer/Event pattern)

   - Add support for deposit history and cancellation

   - Integrate Kafka or messaging for asynchronous events (with micro-services based architecture)
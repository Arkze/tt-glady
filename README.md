## How to start the app

- Run "docker-compose up"
- Export the postMan collection to postMan
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

## 🛠️ Running the Project

### Pre-requisites

- Java 21
- Docker & Docker Compose
- Gradle

### 1. Start App with Docker compose

```bash
docker-compose up --build

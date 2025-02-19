# DevStore API

## Description
The **DevStore API** is a REST API developed in **Java Spring Boot** for an e-commerce platform specializing in developer products. It offers functionalities for managing users, orders, and products, along with **JWT authentication** and asynchronous communication via **RabbitMQ**.

## Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **RabbitMQ**
- **Swagger (springdoc-openapi-starter-webmvc-ui)**
- **PostgreSQL**
- **Docker**

## Project Structure
The project follows a **layered architecture**, with the following packages:
- `controller` - Contains API endpoints
- `service` - Contains business logic
- `repository` - Handles database interactions
- `entidade` - Data models (JPA Entities)
- `DTO` - Data Transfer Objects
- `config` - Security and integration configurations

## Features
### Users
- User registration
- JWT authentication
- Update user details
- Delete account

### Products
- Product registration
- Product listing
- Update product information
- Delete products

### Orders
- Order creation
- Retrieve user orders
- Update order status
- Cancel orders

### Integrations
- **Swagger UI**: Documentation available at [`http://localhost:8083/swagger-ui/index.html`](http://localhost:8083/swagger-ui/index.html)
- **RabbitMQ**: Asynchronous order event processing
- **Docker**: Containerized deployment

## How to Run
### Clone the repository
```bash
git clone https://github.com/your-username/devstore-api.git
cd devstore-api

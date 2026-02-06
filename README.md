# Assignment 1 – Team 3 (PROG3240 Sec 3)

## Greg Grondin, Jonathan McDowell, Martin Van Raay, Ryan Dooley

This project is a microservices-based e-commerce system featuring:
•	Product Service – manages products with an in-memory H2 database
•	Order Service – manages orders, validates product availability via Product Service

Both services are containerized with Docker and include a GitHub Actions CI/CD pipeline.

### Technologies
	•	Java 17, Spring Boot 3.2.2
	•	Maven
	•	H2 Database (in-memory)
	•	Docker & Docker Compose
	•	GitHub Actions (CI/CD)

### Project Structure
assignment1_team3_prog3240_sec3/
├─ product_service/      # Product Service microservice
├─ order_service/        # Order Service microservice
└─ docker-compose.yml    # Compose file for running both services

### Setup & Run Locally

1. Build & test Product Service:
````
cd product_service
mvn clean test
````

2. Build & test Order Service:
````
cd product_service
mvn clean test
````

Docker Deployment
1.	Start Docker Desktop
2.	From the project root:
```
docker-compose up --build
```

3.	Verify running containers
```
docker ps
```

Health Endpoints

Check that services are healthy:
```
curl http://localhost:8081/actuator/health
curl http://localhost:8082/actuator/health
```
Expected response:
```
{"status":"UP"}
```

### Product & Order Flow
•	Add a product:
```
curl -X POST http://localhost:8081/products \
-H "Content-Type: application/json" \
-d '{"name":"Widget","price":12.99,"quantity":100}'
```
•	Place an order:
```
curl -X POST http://localhost:8082/orders \
-H "Content-Type: application/json" \
-d '{"productId":1,"quantity":2}'
```

### CI/CD Pipeline
	•	GitHub Actions workflow triggers on:
	•	Push to main
	•	Pull requests targeting main
	•	Jobs:
	        1.	Build & Test – compiles and runs unit tests for both services
	        2.	Docker Build – builds Docker images and starts containers
	        3.	Integration Tests – verifies services respond correctly (curl requests)
	        4.	(Optional) Code Quality – linter/static analysis
	•	Logs and workflow runs can be seen in the Actions tab of the GitHub repository.

test ci works
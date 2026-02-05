# Item Management API

A RESTful API for managing e-commerce items built with Spring Boot 3.5.10 and Java 17.


**Deployed URL:** https://dsvtask-production.up.railway.app/api/items

**Quick Test Links:**
- [Health Check](https://dsvtask-production.up.railway.app/api/items/health)
- [View All Items](https://dsvtask-production.up.railway.app/api/items)
- [View Single Item](https://dsvtask-production.up.railway.app/api/items/1)
- [View Electronics Category](https://dsvtask-production.up.railway.app/api/items/category/Electronics)

---

## 📋 Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [API Endpoints](#api-endpoints)
- [Quick Start](#quick-start)
- [Testing](#testing)
- [Project Structure](#project-structure)

---

##  Features

-  RESTful API design following industry best practices
-  CRUD operations (Create, Read, Update, Delete)
-  Input validation with detailed error messages
-  Global exception handling
-  Layered architecture (Controller → Service → Repository)
-  In-memory data storage (ArrayList)
-  Pre-loaded sample data (5 items)
-  Comprehensive API documentation
-  CORS enabled for cross-origin requests
-  Consistent JSON response format

---

# API Usage Examples

## Quick Reference

**Base URL:** https://dsvtask-production.up.railway.app/api/items

---

## Example 1: Create Item (Required)

**Request:**
```bash
curl -X POST https://dsvtask-production.up.railway.app/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Gaming Keyboard",
    "description": "Mechanical gaming keyboard with RGB backlighting",
    "price": 129.99,
    "stock": 45,
    "category": "Electronics"
  }'
```

**Response:**
```json
{
  "success": true,
  "message": "Item created successfully",
  "data": {
    "id": 6,
    "name": "Gaming Keyboard",
    "description": "Mechanical gaming keyboard with RGB backlighting",
    "price": 129.99,
    "stock": 45,
    "category": "Electronics"
  },
  "timestamp": "2026-02-05T10:30:00"
}
```

---

## Example 2: Get Item by ID (Required)

**Request:**
```bash
curl https://dsvtask-production.up.railway.app/api/items/1
```

**Response:**
```json
{
  "success": true,
  "message": "Item retrieved successfully",
  "data": {
    "id": 1,
    "name": "Laptop",
    "description": "High-performance laptop with 16GB RAM",
    "price": 899.99,
    "stock": 15,
    "category": "Electronics"
  },
  "timestamp": "2026-02-05T10:30:00"
}
```

---

## Example 3: Validation Error

**Request:**
```bash
curl -X POST https://dsvtask-production.up.railway.app/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "A",
    "description": "Too short",
    "price": -10,
    "stock": -5,
    "category": ""
  }'
```

**Response:**
```json
{
  "success": false,
  "message": "Validation failed",
  "data": {
    "name": "Name must be between 2 and 100 characters",
    "description": "Description must be between 10 and 500 characters",
    "price": "Price must be greater than 0",
    "stock": "Stock cannot be negative",
    "category": "Category is required"
  },
  "timestamp": "2026-02-05T10:30:00"
}
```

---

## Example 4: Item Not Found

**Request:**
```bash
curl https://dsvtask-production.up.railway.app/api/items/999
```

**Response:**
```json
{
  "success": false,
  "message": "Item not found with id: 999",
  "data": null,
  "timestamp": "2026-02-05T10:30:00"
}
```

---

## All Available Endpoints

1. **Health Check:** `GET /api/items/health`
2. **Get All Items:** `GET /api/items`
3. **Get by ID:** `GET /api/items/1`
4. **Create Item:** `POST /api/items`
5. **Update Item:** `PUT /api/items/1`
6. **Delete Item:** `DELETE /api/items/1`
7. **Get by Category:** `GET /api/items/category/Electronics`
8. **Check Stock:** `GET /api/items/1/in-stock`
9. **Update Stock:** `PATCH /api/items/1/stock`
10. **Get Count:** `GET /api/items/count`

##  Technologies

- **Java 17**
- **Spring Boot 3.5.10**
- **Maven**
- **Spring Web** - REST API
- **Spring Validation** - Input validation
- **Jakarta Validation** - Bean validation

---

##  API Endpoints

### Base URL
```
https://dsvtask-production.up.railway.app/api/items
```

### Required Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| **POST** | `/api/items` | Create new item |
| **GET** | `/api/items/{id}` | Get item by ID |

### Additional Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/items` | Get all items |
| PUT | `/api/items/{id}` | Update item |
| DELETE | `/api/items/{id}` | Delete item |
| GET | `/api/items/category/{category}` | Get items by category |
| GET | `/api/items/{id}/in-stock` | Check stock status |
| PATCH | `/api/items/{id}/stock` | Update stock quantity |
| GET | `/api/items/count` | Get total count |
| GET | `/api/items/health` | Health check |

---

##  Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Running Locally
```bash
# Clone the repository
git clone https://github.com/Gourav4k/dsvtask.git
cd dsvtask

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Access locally
http://localhost:8080/api/items
```

---

##  Testing

### Option 1: Browser (Easiest)

Simply open these URLs:
```
https://dsvtask-production.up.railway.app/api/items/health
https://dsvtask-production.up.railway.app/api/items
https://dsvtask-production.up.railway.app/api/items/1
```

### Option 2: cURL Commands

**Get all items:**
```bash
curl https://dsvtask-production.up.railway.app/api/items
```

**Create new item:**
```bash
curl -X POST https://dsvtask-production.up.railway.app/api/items \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Wireless Mouse",
    "description": "Ergonomic wireless mouse with USB receiver",
    "price": 29.99,
    "stock": 100,
    "category": "Electronics"
  }'
```

**Get item by ID:**
```bash
curl https://dsvtask-production.up.railway.app/api/items/1
```


---

##  Response Format

### Success Response
```json
{
  "success": true,
  "message": "Operation successful message",
  "data": { /* response data */ },
  "timestamp": "2026-02-05T10:30:00"
}
```

### Error Response
```json
{
  "success": false,
  "message": "Error description",
  "data": null,
  "timestamp": "2026-02-05T10:30:00"
}
```

---

##  Project Structure
```
src/main/java/com/dsv/
├── DsvApplication.java          # Main application class
├── controller/
│   └── ItemController.java      # REST endpoints
├── service/
│   ├── ItemService.java         # Service interface
│   └── ItemServiceImpl.java     # Service implementation
├── repository/
│   └── ItemRepository.java      # Data access layer
├── model/
│   └── Item.java                # Item entity
├── dto/
│   └── ApiResponse.java         # Response wrapper
└── exception/
    ├── ItemNotFoundException.java
    └── GlobalExceptionHandler.java
```


---


##  Features Implemented

 Item Model with proper validation
 In-memory ArrayList storage
 POST endpoint - Add new item
 GET endpoint - Get item by ID
 Input validation with error messages
 Global exception handling
 Comprehensive documentation
 RESTful conventions

---

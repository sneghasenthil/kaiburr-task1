# 🚀 Kaiburr Assessment – Task 1: Java REST API with MongoDB

This project is a Spring Boot-based REST API that allows users to manage "Task" objects, each representing a shell command. Users can create, update, delete, execute, and view execution history of tasks. Data is stored in MongoDB.

---

## 🧠 Developed By

- **Name:** Snegha S  
- **Degree:** BCA (2023), MCA (2025)  
- **Assessment:** Kaiburr Assessment 2025 – Task 1  
- **GitHub:** [https://github.com/sneghasenthil/kaiburr-task1] (https://github.com/sneghasenthil/kaiburr-task1)

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- MongoDB
- Maven
- Postman (for API testing)
- MongoDB Compass (GUI)
- VS Code

---

## 📦 Project Structure
taskmanager/
├── src/
│ ├── main/
│ │ ├── java/com/task1/taskmanager/
│ │ │ ├── controller/
│ │ │ ├── model/
│ │ │ └── repository/
├── pom.xml
└── README.md
---

## ⚙️ How to Run This Project

### ✅ Prerequisites

- Java 17+ installed (`java -version`)
- Maven installed (`mvn -version`)
- MongoDB running (via Compass or service)
- VS Code or IntelliJ

### 🧪 Run Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/your-username/kaiburr-task1.git
   cd kaiburr-task1
Start MongoDB (if not already running)

Run the Spring Boot app:
mvn spring-boot:run

App will start on:
http://localhost:8080

API can be tested with Postman or curl.

🔗 MongoDB Details
URI: mongodb://localhost:27017/taskdb

Collection: tasks

Database is auto-created on first save.

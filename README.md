# 🏥 Patient-Management-System

A **Spring Boot REST API** for managing patient information.  
The application is **dockerized** and uses **PostgreSQL** as the primary database (H2 removed).

---

## 🧰 Tech Stack

- Java 21
- Spring Boot 3.5.x
- Spring Data JPA (Hibernate)
- PostgreSQL
- Docker & Docker Desktop
- Maven

---

## 📦 Services

| Service Name | Description |
|-------------|-------------|
| `patient-service` | Spring Boot REST API |
| `patient-service-db` | PostgreSQL database |

---

## 🐳 Docker Images

| Image | Purpose |
|-----|--------|
| `postgres:latest` | PostgreSQL database |
| `patient-service` | Application image (built locally) |

---

## 🔑 Environment Variables

### 🔹 patient-service (Spring Boot)

These environment variables are **required** for the application to connect to PostgreSQL.

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://patient-service-db:5432/db
SPRING_DATASOURCE_USERNAME=admin_user
SPRING_DATASOURCE_PASSWORD=password
SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver

SPRING_JPA_HIBERNATE_DDL_AUTO=update
SPRING_JPA_SHOW_SQL=true
SPRING_SQL_INIT_MODE=never
📌 Notes

Environment variables must use underscores (_), not hyphens (-)

patient-service-db is the PostgreSQL container name, not localhost

🔹 patient-service-db (PostgreSQL)
env
Copy code
POSTGRES_DB=db
POSTGRES_USER=admin_user
POSTGRES_PASSWORD=password
🔌 Port Bindings (Bind Ports)
Container	Internal Port	Host Port	Purpose
patient-service	4000	4000	REST API
patient-service-db	5432	5000	PostgreSQL access

Example:

makefile
Copy code
5000:5432
💾 Volume Bindings (Bind Mounts)
PostgreSQL data is persisted using a Docker volume:

kotlin
Copy code
patient-service-db:/var/lib/postgresql/data
✔ Prevents data loss
✔ Keeps database data persistent across restarts

🌐 Docker Network
Both containers run on a shared Docker network:

csharp
Copy code
--network internal
Why this is needed
Allows patient-service to communicate with patient-service-db

Enables container-to-container hostname resolution

▶️ Run Instructions (CLI)
1️⃣ Start PostgreSQL container
bash
Copy code
docker run -d \
  --name patient-service-db \
  --network internal \
  -p 5000:5432 \
  -e POSTGRES_DB=db \
  -e POSTGRES_USER=admin_user \
  -e POSTGRES_PASSWORD=password \
  -v patient-service-db:/var/lib/postgresql/data \
  postgres:latest
2️⃣ Build patient-service image
bash
Copy code
docker build -t patient-service .
3️⃣ Start patient-service container
bash
Copy code
docker run -d \
  --name patient-service \
  --network internal \
  -p 4000:4000 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://patient-service-db:5432/db \
  -e SPRING_DATASOURCE_USERNAME=admin_user \
  -e SPRING_DATASOURCE_PASSWORD=password \
  patient-service
🔍 Verify Database
Connect to PostgreSQL container
bash
Copy code
docker exec -it patient-service-db psql -U admin_user -d db
List tables
sql
Copy code
\dt
Expected output:

cpp
Copy code
public | patient
🧪 API Verification
Swagger UI

bash
Copy code
http://localhost:4000/swagger-ui.html
Use Create Patient API to insert data

Verify data using:

sql
Copy code
SELECT * FROM patient;
❗ Common Pitfalls
❌ Using localhost inside Docker for DB connection

❌ Using hyphens (-) in environment variable names

❌ Keeping H2 dependency in pom.xml

❌ Running tests during Docker build (use -DskipTests)

✅ Summary
Application is fully dockerized

PostgreSQL is used instead of H2

Hibernate auto-creates tables

Database data is persisted using Docker volumes

Ready for local development and further CI/CD setup

🚀 Future Improvements
Add docker-compose.yml

Add Flyway for DB migrations

Separate profiles for dev and docker

Add authentication & authorization

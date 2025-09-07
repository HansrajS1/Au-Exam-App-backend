#  AU Exam App Backend

This is the backend service for the [AU Exam App](https://github.com/HansrajS1/Au-Exam-App), built with **Spring Boot**. It provides RESTful APIs for managing academic paper submissions, including metadata, file references, and preview images. Designed to integrate seamlessly with the React Native frontend and Appwrite authentication.

---

##  Features

-  Submit academic papers with subject, topic, grade/branch
-  Upload and link preview images
-  Retrieve papers by filters or ID
-  Secured endpoints with Appwrite token validation
-  CORS-enabled for mobile frontend integration

---

##  Tech Stack

| Layer       | Technology             |
|-------------|------------------------|
| Language    | Java 17+               |
| Framework   | Spring Boot 3.x        |
| Database    | PostgreSQL             |
| Auth        | Appwrite JWT (optional)|
| File Storage| Appwrite / Cloudinary  |
| Build Tool  | Maven                  |
| Dev Tools   | Postman                |

---

##  Installation

```bash
git clone https://github.com/your-username/au-exam-backend.git
cd au-exam-backend
./mvnw clean install
```

Or with Gradle:

```bash
./gradlew build
```

---

##  Running the App

```bash
./mvnw spring-boot:run
```

Or:

```bash
java -jar target/au-exam-backend.jar
```

---

##  Environment Configuration

Create a `.env` or use `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/au_exam
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password

appwrite.endpoint=https://cloud.appwrite.io/v1
appwrite.projectId=your_project_id
appwrite.apiKey=your_api_key
```

 Use Spring’s `@Value` or `@ConfigurationProperties` to inject these.

---

##  API Endpoints

| Method | Endpoint             | Description                  |
|--------|----------------------|------------------------------|
| POST   | `/api/papers`        | Submit a new paper           |
| GET    | `/api/papers`        | List all papers              |
| GET    | `/api/papers/{id}`   | Get paper by ID              |
| DELETE | `/api/papers/{id}`   | Delete paper                 |

Example JSON payload:

```json
{
  "university": "Alliance University",
  "subject": "Physics",
  "topic": "Quantum Mechanics",
  "grade": "B.Tech CSE",
  "fileUrl": "https://cdn.appwrite.io/files/xyz.pdf",
  "previewImageUrl": "https://cdn.appwrite.io/files/preview.jpg"
}
```

---

##  Testing

Use Postman :

```
http://localhost:8080/
```

✅ Includes auto-generated docs from `springdoc-openapi`.

---

## 🛠️ Troubleshooting

- **CORS errors from mobile app**  
  → Enable CORS in `WebMvcConfigurer`

- **Appwrite token validation fails**  
  → Check project ID and API key in headers

- **Database connection issues**  
  → Verify JDBC URL and credentials

- **File upload fails**  
  → Ensure Appwrite bucket permissions are set correctly

---

## 🧠 Folder Structure

```
src/
 ├── main/
 │   ├── java/com/au/exam/
 │   │   ├── controller/     # REST endpoints
 │   │   ├── service/        # Business logic
 │   │   ├── model/          # DTOs and entities
 │   │   └── config/         # CORS, security, etc.
 │   └── resources/
 │       └── application.properties
```

---

##  License

MIT — feel free to fork, extend, and deploy.

---

##  Author

**HANS RAJ**
 bengaluru, India  


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
| Hosting     | Azure                  |
| Database    | PostgreSQL             |
| File Storage| Cloudinary             |
| Build Tool  | Maven/Docker           |
| Dev Tools   | Postman                |

---

##  Installation

```bash
git clone https://github.com/HansrajS1/Au-Exam-App-backend
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
cloudinary.api_key=cloudinaryapi
cloudinary.api_secret=api_secret
cloudinary.cloud_name=cloud_name
server.port=${PORT:8080}
server.tomcat.max-http-post-size=26214400
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.servlet.multipart.max-file-size=20MB
spring.servlet.multipart.max-request-size=25MB
```

 Use Spring’s `@Value` or `@ConfigurationProperties` to inject these.

---

##  API Endpoints

| Method | Endpoint                          | Description                  |
|--------|-----------------------------------|------------------------------|
| POST   | `/api/papers`                     | Submit a new paper           |
| GET    | `/api/papers`                     | List all papers              |
| GET    | `/api/papers/{id}`                | Get paper by ID              |
| GET    | `/api/papers/search?subject=Math `| Get paper by Search          |
| DELETE | `/api/papers/{id}`                | Delete paper                 |

Example JSON payload:

```json
{
 "id": 2,
 "college": "Alliance University",
 "course": "Btech",
 "semester": 5,
 "subject": "Physics ",
 "description": "Question ",
 "fileUrl": "https://res.cloudinary.com/dsfzvqzit/image/upload/v175753/wbioiv2mmwfeyf5ss3ir.pdf",
 "previewImageUrl": "https://res.cloudinary.com/dsfzvqzit/image/upload/v57070752/hzumdullmib5a2a7ed3p.jpg",
 "userEmail": "hansrajvvs@gmail.com"
 }
```

---

##  Testing

Use Postman :

```
http://localhost:8080/
```

 Includes auto-generated docs from `springdoc-openapi`.

---

##  License

MIT — feel free to fork, extend, and deploy

---

##  Author

**HANS RAJ**
 bengaluru, India  


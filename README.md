# 📚 Book Lending Application

A full-stack web application for managing book lending in a library or classroom setting. Admins can view which books have been borrowed and manage users and inventory.

## ✅ Functional Requirements

| Requirement         | Description                                     | API's Supporting them |
|---------------------|-------------------------------------------------|----------------|
| 📚 List all books   | Users can see available and borrowed books      | `GET /api/books`, Angular `BookListComponent` |
| 🧍 List all users   | Admin can view registered users                 | `GET /api/users`, Angular `UserListComponent` |
| 📝 Add new user     | Admin can create new users                      | `POST /api/users`, Angular `AddUserComponent` |
| ✅ Reserve a book   | Users can reserve available books               | `POST /api/books/{id}/reserve` with `userId` |
| 🔁 Return a book    | Users can return borrowed books                 | `POST /api/books/{id}/return` |
| 👤 Borrower name    | Show who borrowed a book                        | Backend lookup + `borrowedByName` in response |
| 🧪 Seed data        | Demo data for users and books                   | `schema.sql`, `data.sql` |

---

## 🚀 Non-Functional Requirements

| Category         | Requirement                     | How to Achieve |
|------------------|----------------------------------|----------------|
| 📈 Performance   | Fast API/UX                     | In-memory cache, async |
| ⚖️ Scalability   | Handle 1000s of users           | Stateless APIs + DB indexing |
| ⏱ Availability  | Always online                   | via health checks |
| 📦 Portability   | Run locally or in cloud         | can be easily run in Docker, modular services |
| 📜 Documentation | Clear usage/API info            | `README.md`, Postman, Swagger |
| 🔍 Observability | Monitor usage/errors            | SLF4J, metrics, exception handling |

---

## 🧰 Tech Stack

### 🌐 Frontend – Angular 19
- Angular CLI 19
- Routing with `AppModule` (non-standalone)
- `HttpClientModule` for REST integration
- Bootstrap-compatible CSS
- Deployed via `ng serve` or Angular build system

### 🖥 Backend – Spring Boot 3.2 (Java 21)
- Spring Web MVC
- PostgreSQL (or H2 for dev)
- JPA/Hibernate
- REST API following standard HTTP verbs
- GlobalExceptionHandler via `@RestControllerAdvice`
- Seed data via `data.sql`

---

## 🔧 Folder Structure

```
book-lending/
├── book-lending-ui/         # Angular 19 frontend (users & books)
└── book-lending-backend/    # Spring Boot backend (REST API)
```

---

## ⚙️ How to Run Locally

### 🚀 1. Backend (Spring Boot)

```bash
cd book-lending-backend
mvn clean install
mvn spring-boot:run
```

- App runs on: `http://localhost:8080`
- H2 Console (dev only): `http://localhost:8080/h2-console`

### 🧪 2. Frontend (Angular 19)

```bash
cd book-lending-ui
npm install
ng serve
```

- App runs on: `http://localhost:4200`
- Connects to backend at `http://localhost:8080/api`

---

## 🔁 RESTful API Pattern (Spring Web)

| Action             | Endpoint                      | Method | Payload         |
|--------------------|-------------------------------|--------|------------------|
| List all books     | `/api/books`                  | GET    | –                |
| Reserve a book     | `/api/books/{id}/reserve`     | POST   | `{ userId }`     |
| Return a book      | `/api/books/{id}/return`      | POST   | –                |
| List all users     | `/api/users`                  | GET    | –                |
| Add new user       | `/api/users`                  | POST   | `{ user details }` |


## 🗂️ Project Highlights

- ✅ User-friendly UI for reserving & returning books
- ✅ User management features
- ✅ Full-stack, modular, and easy to extend
- ✅ Optional in-memory DB for quick bootstrapping
- ✅ Clean API design for frontend/backend separation

---

## 🧪 Sample Seed Data

- 10 preloaded users
- 20 books (with availability status)
- All data auto-loaded via `data.sql` on app startup

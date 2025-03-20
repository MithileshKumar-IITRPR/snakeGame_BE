# Snake Game Backend

## Overview
This backend is built using Spring Boot and serves as the API for the 2D Snake Game. It handles user authentication (login), score persistence, and supports recording the game mode (easy, medium, or hard) along with the score.

## Tech Stack
- **Java** (JDK 17)
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security** with JWT for authentication
- **H2 Database** (in-memory for demo purposes)
- **Gradle** for build management

## Architecture & Design Patterns
- **Layered Architecture:** Separation into Controller, Service, Repository, and Model layers.
- **MVC Pattern:** REST Controllers manage incoming HTTP requests, Services encapsulate business logic, and Repositories handle persistence.
- **Dependency Injection:** Spring’s DI manages bean creation.
- **JWT Authentication:** Implemented with a custom JWT utility and filter.
  
## Assumptions
- User login is simple (name-only) and JWT tokens are used for subsequent authentication.
- The scoreboard is stored in an H2 in-memory database.
- Endpoints are secured (except login) using Spring Security with JWT.

## Features Supported
- **User Login:** Creates or retrieves a user by name and returns a JWT token.
- **Score Saving:** Saves the user score along with the selected game mode.
- **Scoreboard:** Retrieves the top 10 scores.
- **JWT Authentication:** All endpoints (except login) are protected by JWT.

## How to Run
1. **Build and Run:**
   - Navigate to the `backend` folder.
   - Run the following command to start the application:
     ```bash
     ./gradlew bootRun
     ```
2. **Access API:**
   - The backend runs on [http://localhost:8080](http://localhost:8080).

## Endpoints
- **POST /api/login?name={name}**  
  Logs in a user and returns a JWT token along with user details.
- **POST /api/score?userId={userId}&score={score}&mode={mode}**  
  Saves the user's score along with the game mode.
- **GET /api/scores**  
  Retrieves the top 10 scores.

## Additional Information
- **Security:** JWT is used for stateless authentication.
- **Database:** Uses an H2 in-memory database for demo; for production, replace with a persistent DB.
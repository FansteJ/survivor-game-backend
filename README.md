# Survivor Game Backend

Backend service for a survival-style game built with **Spring Boot** and **PostgreSQL**.  
This project handles authentication, player progression, game sessions, economy, leaderboard, and quest systems.

The game concept:  
The player survives against waves of enemies for as long as possible. After each run, rewards are calculated based on survival time and enemies killed. Players can use gold and gems to upgrade their character and survive longer in future runs.

Unity client integration is in progress.

---

## 🚀 Tech Stack

- Java 21+
- Spring Boot 4.0.2
- Spring Security (JWT Authentication)
- Spring Data JPA
- PostgreSQL
- Maven

---

## 🔐 Authentication System

- User Registration
- User Login
- JWT-based authentication
- Secure API endpoints
- Secure password hashing (BCrypt)

Each user has an associated `UserProfile` containing:
- Level
- Experience
- Gold
- Gems

---

## 🎮 Game Session System

Players can:
- Start a game session
- Finish a game session

When a session ends:
- Rewards are calculated based on:
  - Survival time (will be implemented in the future)
  - Enemies killed
- Gold and experience are granted
- Level progression is updated

---

## 🏆 Leaderboard System

- Global ranking based on player progress
- Sorted by level and/or total score
- Supports fetching top players
- Includes "My Rank" functionality

---

## 🛒 Shop System

Players can:
- Spend gold or gems
- Purchase upgrades

The backend validates:
- Sufficient currency
- Upgrade availability
- Ownership logic

---

## 📅 Daily Quest System

Each player receives daily quests.

Supported quest types:
- Play a run
- Kill enemies
- Earn gold
- Reach a specific level

Quest logic:
- Tracks progress automatically
- Marks quest as completed when goal is reached
- Players can claim rewards
- Rewards are added to the player's profile

---

## 🗄 Database Structure (High Level)

Main entities:
- User
- UserProfile
- GameSession
- UserQuest
- QuestType
- EnemyType
- GameSessionEnemyKill
- UserUpgrade
- UpgradeType

Relationships:
- One User -> One UserProfile
- One UserProfile -> Many GameSession
- One UserProfile -> Many UserQuest
- One QuestType -> Many UserQuest
- One UserProfile -> Many UserUpgrade
- One UpgradeType -> Many UserUpgrade
- One UserProfile -> Many GameSessionEnemyKill
- One GameSession -> Many GameSessionEnemyKill
- One EnemyType -> Many GameSessionEnemyKill


---

## 📡 API Overview

Main endpoint groups:

```
/api/auth
/api/profile
/api/game
/api/leaderboard
/api/player
/api/quests
```

All protected endpoints require JWT authentication.

---

## 🧠 Design Goals

- Clean separation of concerns (Controller → Service → Repository)
- DTO usage for request/response isolation
- Scalable architecture ready for Unity integration
- Extendable quest and economy systems

---

## 🔄 Future Plans

- Full Unity 3D integration
- Matchmaking / multiplayer support
- Seasonal leaderboard
- Advanced economy balancing
- Achievements system

---

## ⚙️ How to Run

1. Clone the repository

```
git clone https://github.com/FansteJ/survivor-game-backend.git
```


2. Configure PostgreSQL database

3. Update `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/survivor
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Run the project

```
mvn spring-boot:run
```

---

## 📌 Project Status

Backend core systems complete.  
Unity client integration in progress.

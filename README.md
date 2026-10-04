Pro Kabaddi Team Management System
A full-stack web application developed as a second-year academic project to manage Pro Kabaddi players and team information efficiently.
The system provides functionality to add, view, filter, sort, and delete player records through a user-friendly web interface. The project was developed using React.js for the frontend and Spring Boot for the backend.
About the Project
Managing player information manually can become difficult when dealing with multiple teams and player roles.
The Pro Kabaddi Team Management System provides a centralized platform where player information can be stored and managed efficiently.
This project helped me gain practical experience in full-stack development, REST APIs, CRUD operations, frontend-backend integration, and database management.
Features
- Add new players
- View all registered players
- Delete player records
- Filter players by role
- Sort players by team
- Store player details such as age and total points
- REST API-based frontend and backend communication
- User-friendly interface
Player Information
Field	Description
Player Name	Name of the player
Team	Team represented by the player
Role	Raider, Defender, or All-Rounder
Age	Age of the player
Total Points	Total points scored by the player


Tech Stack
Frontend
- React.js
- JavaScript
- HTML
- CSS
Backend
- Java
- Spring Boot
- REST API
Database
- SQL / Relational Database
Tools
- Git
- GitHub
- Maven
- Postman
System Architecture
┌─────────────────────┐
│      React.js       │
│      Frontend       │
└──────────┬──────────┘
           │
           │ HTTP / REST API
           ▼
┌─────────────────────┐
│    Spring Boot      │
│      Backend        │
└──────────┬──────────┘
           │
           │ CRUD Operations
           ▼
┌─────────────────────┐
│      Database       │
│   Player Records    │
└─────────────────────┘

Application Workflow
User
  ↓
React Frontend
  ↓
REST API Request
  ↓
Spring Boot Backend
  ↓
Database
  ↓
Response
  ↓
React UI

Project Structure
pro-kabaddi-team-management/
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   ├── services/
│   │   └── App.js
│   └── package.json
│
├── backend/
│   ├── src/main/java/
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   └── pom.xml
│
└── README.md

Getting Started
Prerequisites
- Java
- Maven
- Node.js
- npm
- Git
Clone the Repository
git clone <your-repository-url>
cd pro-kabaddi-team-management

Run the Backend
cd backend
mvn spring-boot:run

The backend runs on:
http://localhost:8080

Run the Frontend
Open another terminal:
cd frontend
npm install
npm start

The frontend will communicate with the Spring Boot backend through REST APIs.
Concepts Implemented
- Full-stack web development
- Java and Spring Boot
- React.js
- RESTful APIs
- CRUD operations
- Database connectivity
- Component-based development
- Client-server architecture
- API testing
- Git and GitHub
Project Purpose
This project was developed during my second year of engineering as an academic project to understand how frontend applications communicate with backend services and databases.
It provided hands-on experience with Java, Spring Boot, React, REST APIs, database operations, and full-stack application development.
Future Enhancements
- Player performance analytics
- Team statistics dashboard
- Match management
- Player search functionality
- Authentication and authorization
- Admin dashboard
- Advanced filtering
- Responsive design
- Cloud deployment
# bfabcadddbdfbaeebddbeceabcefefcd
https://sonar.server.examly.io/dashboard?id=iamneo-production_bfabcadddbdfbaeebddbeceabcefefcd&amp;codeScope=overall

# Task Management API

A lightweight task management system built with Spring Boot, inspired by Trello. This application allows users to create, update, assign, and manage tasks, complete with states and comments. It demonstrates the use of design patterns, such as the Facade and FSM (Finite State Machine) patterns, to simplify task management workflows.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Data Models](#data-models)
- [Future Enhancements](#future-enhancements)

## Features

- Create, update, delete tasks with states (TODO, DOING, DONE).
- Assign tasks to users.
- Add, update, and manage comments on tasks.
- Centralized Facade pattern to simplify task and user operations.
- FSM pattern to enforce controlled task state transitions.
- Persistent storage with MySQL.

## Technologies

- **Backend**: Spring Boot, Spring Data JPA
- **Database**: MySQL
- **Build Tool**: Maven
- **Testing**: JUnit, Postman

## Getting Started

### Prerequisites

- **Java** (version 17 or higher recommended)
- **Maven** for dependency management
- **MySQL** database

### Setup

1. **Clone the repository**:

    ```
    git clone https://github.com/yourusername/task-management-api.git
    cd task-management-api
    ```

2. **Database Configuration**:
   - Create a MySQL database named `trello_db`.
   - Update the database connection properties in `application.properties`:

        ```
        spring.datasource.url=jdbc:mysql://localhost:3306/trello_db
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        ```

3. **Build and Run the Application**:

    ```
    mvn clean install
    mvn spring-boot:run
    ```

4. **Testing**:
   - Test the API endpoints using Postman or similar tools.

## API Endpoints

### Task Endpoints

- **POST /tasks** - Create a new task
  - Example JSON:
    ```json
    {
        "state": "TODO",
        "description": "Walk Lizard",
        "assignedTo": {
            "id": 7
        },
        "comments": [
            {
                "text": "He Might run away"
            },
            {
                "text": "He's slow though"
            }
        ]
    }
    ```

- **GET /tasks** - Retrieve all tasks
- **GET /tasks/{id}** - Retrieve a specific task by ID
- **PUT /tasks/{id}/advanceState** - Move task to the next possible state (TODO → DOING → DONE)
- **DELETE /tasks/{id}** - Delete a specific task

### User Endpoints

- **POST /users** - Create a new user
- **GET /users** - Retrieve all users
- **GET /users/{id}** - Retrieve a specific user by ID
- **DELETE /users/{id}** - Delete a specific user

## Data Models

### Task

- `id`: Unique identifier (Long)
- `state`: State of the task (Enum: TODO, DOING, DONE)
- `description`: Description of the task
- `assignedTo`: User assigned to the task
- `comments`: List of comments associated with the task
- `createdDate`: Date the task was created

### User

- `id`: Unique identifier (Long)
- `name`: Name of the user
- `tasks`: List of tasks assigned to the user

### Comment

- `id`: Unique identifier (Long)
- `text`: Comment text
- `task`: Associated task

### States Enum

Defines task states and valid transitions (FSM logic).

- **TODO** - Initial state
- **DOING** - Intermediate state
- **DONE** - Final state

## Future Enhancements

- Add more complex task filters (e.g., by assigned user, state).
- Implement user authentication and authorization.
- Add notifications for task updates.
- Improve error handling and response consistency.
- Integrate with frontend (React or Angular) for a complete UI.

---

Feel free to contribute by opening issues or submitting pull requests. For any questions, please contact the project maintainer.

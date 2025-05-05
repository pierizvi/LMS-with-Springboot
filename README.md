# Library Management System

A Spring Boot CRUD application for managing a library with books and authors.

## Overview

This project is a Spring Boot web application implementing a simple Library Management System. It demonstrates CRUD operations (Create, Read, Update) for two entities - Author and Book - in a one-to-many relationship.

## Features

- Create, view, and update Authors
- Create, view, and update Books
- View books by a specific author
- Special feature: View books with authors using an inner join query
- Form validation with error handling
- Responsive UI using Bootstrap

## Technology Stack

- Java 17
- Spring Boot 2.7.x
- Spring Data JPA
- Spring MVC
- H2 Database (in-memory)
- JSP for view templates
- Maven for dependency management
- Bootstrap 4.5.2 for UI styling

## Prerequisites

- JDK 17 or later
- Maven 3.6.x or later
- IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/library-management-system.git
cd library-management-system
```

### Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

The application will be available at `http://localhost:8081`

### Database

The application uses an in-memory H2 database that is initialized with sample data on startup. You can access the H2 console at:

```
http://localhost:8081/h2-console
```

Connection details:
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

## Project Structure

```
spring-boot-crud-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── springbootcrudapp/
│   │   │               ├── config/
│   │   │               ├── controller/
│   │   │               ├── dto/
│   │   │               ├── entity/
│   │   │               ├── exception/
│   │   │               ├── repository/
│   │   │               └── service/
│   │   ├── resources/
│   │   └── webapp/
│   │       └── WEB-INF/
│   │           └── views/
│   └── test/
│       └── java/
└── pom.xml
```

## Entity Relationship

- Author (1) ----< Book (N)
  - One Author can have multiple Books
  - Each Book belongs to exactly one Author

## Usage

1. **Home Page**
   - Navigate to the home page to see options for managing books and authors

2. **Managing Authors**
   - View all authors: Click on "View All Authors"
   - Add a new author: Click on "Add New Author"
   - Update an author: Click on "Edit" button next to the author
   - View author details: Click on "View" button next to the author
   - View author's books: Click on "Books" button next to the author

3. **Managing Books**
   - View all books: Click on "View All Books"
   - Add a new book: Click on "Add New Book"
   - Update a book: Click on "Edit" button next to the book
   - View book details: Click on "View" button next to the book

4. **Special Feature**
   - View books with their authors through an inner join: Click on "Books with Authors"

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Spring Framework Team
- Bootstrap Team
- H2 Database

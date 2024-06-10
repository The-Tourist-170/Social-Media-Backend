# Social Media Backend
## Introduction
Welcome to the Social Media Backend project! This application is designed to provide a robust backend for a social media platform, enabling users to create accounts, log in, create posts and comments, like posts and comments, and more. This project is built with scalability and performance in mind, ensuring a seamless experience for users.

## Features
- User Authentication: Secure account creation and login functionality using Spring Security.
- Post Management: Create, edit, and delete posts.
- Comment System: Add, edit, and delete comments on posts.
- Like System: Like and unlike posts and comments.
- User Profiles: View and edit user profiles.
- Feed: View a feed of posts from followed users.
- Technologies Used
- Backend Framework: Spring Boot
- Database: PostgreSQL
- Authentication: Spring Security with JWT (JSON Web Tokens)
- API Documentation: Swagger or similar tools for API documentation

## Getting Started
### Prerequisites
Ensure you have the following installed:

- Java Development Kit (JDK) 11 or higher
- Maven
- PostgreSQL
- Installation

### Clone the repository:

git clone https://github.com/your-username/social-media-backend.git
cd social-media-backend

### Set up PostgreSQL database:

- Create a new PostgreSQL database.
- Update the application.properties file with your database configuration.
- properties

spring.application.name=toddle
server.port=5454
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/toddle
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

### Build and run the application:

mvn clean install
mvn spring-boot:run
The server will start on "http://localhost:5454".

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes.

## Contact
For any inquiries or feedback, please contact [ayushya.official@gmail.com].

# Servlet MVC Project DriverDeport for Software Development Project 

This is a Java Servlet-based MVC web application built with Maven and deployed using Apache Tomcat.  
It demonstrates a layered architecture (Controller, Service, Repository, Model).

## How To Run

1. Make sure Maven is installed.
2. Navigate to the project root (where pom.xml is).
3. Run:

   mvn tomcat7:run

4. Open in browser:

   http://localhost:8080/v1

## Database Configuration

Database settings are stored in:
src/main/resources/db.properties


## Technologies

- Java
- Servlets (javax)
- Apache Tomcat (embedded for now- further deployment on local apache server)
- Maven
- JDBC
- JSP

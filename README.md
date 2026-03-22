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

- Java (JDK 21)
- Servlets (javax)
- Apache Tomcat (embedded for now- further deployment on local apache server)
- Maven
- JDBC
- JSP

---

## US.3 & US.5 — Customer Inquiry and Staff Notifications (Emuvoke Aghegho)

This section covers the customer inquiry form (US.3) and the staff notifications portal (US.5).

### Prerequisites

- JDK 21
- MySQL 8.0
- Eclipse IDE (or any IDE with Maven support)

### Database Setup

1. Open MySQL Workbench and connect as root
2. Run the following to create the database and user:

```sql
CREATE DATABASE IF NOT EXISTS vehiclemanagement;
CREATE USER IF NOT EXISTS 'cst8319'@'localhost' IDENTIFIED BY 'cst8319';
GRANT ALL PRIVILEGES ON vehiclemanagement.* TO 'cst8319'@'localhost';
FLUSH PRIVILEGES;
```

3. Switch to the database and run the schema script:

```sql
USE vehiclemanagement;
```

Then open and run the full script at:

```
v1/src/main/resources/sql/notifications.sql
```

This creates the notifications table and inserts 8 rows of test data.

### Configuration

Database credentials are stored in:

```
v1/src/main/resources/database.properties
```

This file has been updated — make sure your local MySQL matches:

```
jdbc.url=jdbc:mysql://localhost:3306/vehiclemanagement
jdbc.username=cst8319
jdbc.password=cst8319
```

### Running the Application

**Via Eclipse:**

1. Right click the v1 project in Project Explorer
2. Run As > Maven Build...
3. In the Goals field type: tomcat7:run
4. Click Run
5. Wait for: INFO: Starting ProtocolHandler ["http-bio-8080"]

**Via terminal (if Maven is on your PATH):**

```
cd v1
mvn tomcat7:run
```

### Pages Added

| Page | URL | Description |
|------|-----|-------------|
| Customer Inquiry Form | http://localhost:8080/v1/submitInquiry | Customer submits a vehicle inquiry |
| Staff Notifications Portal | http://localhost:8080/v1/notifications | Staff views and deletes inquiries |

### Demo Flow

1. Open http://localhost:8080/v1/submitInquiry
2. Vehicle is pre-filled and locked (simulates clicking Enquire on a listing)
3. Fill in name, email, and optional message
4. Click Submit Inquiry — green success banner confirms submission
5. Open http://localhost:8080/v1/notifications
6. New inquiry appears at the top of the table
7. Click Delete on any row to remove it — page reloads with updated table

### Note for Teammates

The database.properties file has been updated from the original cst8288/vehiclemanagment credentials.
Please update your local MySQL setup to match the credentials above before running.

When US.2 (vehicle search) is complete, the hardcoded vehicle value in SubmitInquiryServlet.java
can be replaced with a URL parameter. This is a two line change and all other code remains the same.

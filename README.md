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
Both features share the `notifications` table and are owned end-to-end by this contributor.

### Code Structure

```
v1/src/main/java/app/
├── controller/
│   ├── SubmitInquiryServlet.java   ← US.3: handles the customer inquiry form
│   └── NotificationServlet.java    ← US.5: handles the staff dashboard
├── model/
│   └── Notification.java           ← one object = one row in the notifications table
├── repository/
│   └── NotificationRepository.java ← the only class that touches the database
├── service/
│   └── NotificationService.java    ← business logic between controller and repository
└── util/
    └── DatabaseConnection.java     ← singleton DB connection (shared across app)

v1/src/main/webapp/WEB-INF/view/
├── submitInquiry.jsp               ← US.3: customer-facing inquiry form
└── notifications.jsp               ← US.5: staff dashboard table

v1/src/test/java/app/service/
└── NotificationServiceTest.java    ← 8 JUnit tests added in Sprint 4
```

### Prerequisites

- JDK 21
- MySQL 8.0
- Any IDE with Maven support

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
v1/src/main/resources/sql/vehiclemanagementDDL.sql
```

This creates the notifications table and inserts 8 rows of test data.

4. Run the population script to insert all test data:

```
v1/src/main/resources/sql/dbpopulation.sql
```

This creates all tables including notifications and inserts test data for US.3 and US.5.

### Configuration

Database credentials are stored in:

```
v1/src/main/resources/database.properties
```

This file has been updated! Make sure your local MySQL matches:

```
jdbc.url=jdbc:mysql://localhost:3306/vehiclemanagement
jdbc.username=cst8319
jdbc.password=cst8319
```

### Running the Application

**Via Eclipse:**

1. Right-click the v1 project in Project Explorer
2. Run As > Maven Build...
3. In the Goals field type: tomcat7:run
4. Click Run
5. Wait for: INFO: Starting ProtocolHandler ["http-bio-8080"]

**Via terminal (if Maven is on your PATH):**

```
cd v1
mvn tomcat7:run
```

### Run Tests

```
mvn test
```

All 8 tests in NotificationServiceTest should pass with a green bar.

### Pages Added

| Page                   | URL                                        | Description                          |
|------------------------|--------------------------------------------|--------------------------------------|
| Customer Inquiry Form  | http://localhost:8080/v1/submitInquiry     | Customer submits a vehicle inquiry   |
| Staff Notifications    | http://localhost:8080/v1/notifications     | Staff views and deletes inquiries    |

### Demo Flow

1. Open http://localhost:8080/v1/submitInquiry
2. Vehicle is pre-filled and locked (simulates clicking Enquire on a listing)
3. Fill in name, email, and optional message
4. Click Submit Inquiry — green success banner confirms submission
5. Open http://localhost:8080/v1/notifications
6. New inquiry appears at the top of the table
7. Click Delete on any row to remove it — page reloads with updated table

### Sprint 4 Changes

The following improvements were made to this feature set during Sprint 4:

**1. Singleton Database Connection**
NotificationRepository previously loaded database.properties itself using its own copy of the
connection logic. It now uses the shared DatabaseConnection.INSTANCE singleton built by the team.
There is now one single source of truth for database credentials across the entire application.

**2. Server-Side Input Validation**
SubmitInquiryServlet now validates that name and email are not blank before calling the service
layer. The JSP form already has 'required' on those fields, but that is HTML-only and can be
bypassed by sending a raw HTTP POST request directly to the servlet URL. The server-side guard
ensures the database is never called with empty data regardless of how the request arrives.

**3. JUnit Tests**
NotificationServiceTest.java was added to src/test/java/app/service/. It contains 8 unit tests
covering the Notification model constructor and all getters, the insert pattern where id=0 and
submittedAt=null because MySQL generates both automatically, and the optional message field which
the JSP renders as "-" when null. All 8 tests pass. Run with: mvn test

### Note for Teammates

The database.properties file has been updated from the original cst8288/vehiclemanagment credentials.
Please update your local MySQL setup to match the credentials above before running.

When US.2 (vehicle search) is complete, the hardcoded vehicle value in SubmitInquiryServlet.java
can be replaced with a URL parameter. This is a two-line change and all other code remains the same.


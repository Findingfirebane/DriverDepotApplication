# DriverDepot
 
A Java Servlet-based MVC web application built with Maven and deployed using Apache Tomcat.  
It demonstrates a layered architecture (Controller, Service, Repository, Model).
 
## How To Run
## DOCKER:
1. Docker Desktop is installed
2. Open terminal in project root folder.
3. Check that: jdbc.url=jdbc:mysql://localhost:3306/vehiclemanagement 
is changed to: jdbc.url=jdbc:mysql://db:3306/vehiclemanagement in the database.properties file
4. run: `docker-compose up --build`
5. Access the application at:  ` http://localhost:8080/v1`



## LOCALLY:

 
1. Make sure Maven is installed.
2. Navigate to the project root (where pom.xml is).
3. Run:
   mvn tomcat7:run
4. Open in browser:
   http://localhost:8080/v1
## Technologies
 
- Java (JDK 21)
- Servlets (javax)
- Apache Tomcat (embedded)
- Maven
- JDBC
- JSP
- Bootstrap 5
- Docker
---
 
## MVP Features
 
### US.1 - Authentication: Sign In & Sign Up (Karan Grover)
As a user, I want to log in with my credentials so that I can access my specific portal.
Karan implemented the full authentication workflow including the sign-in and sign-up forms,
credential validation against the MySQL database, HTTP session creation on successful login,
and logout session invalidation.
 
### US.1 - Role-based Dashboards & Routing (Sizwe Magubane)
Once authenticated, users are redirected to their role-appropriate portal. Customers access
the Customer Portal and staff access the Staff Dashboard. Session-based conditional rendering
controls navigation visibility throughout the application, with staff and customer views
differing in available actions and data displayed.
 
### US.2 - Vehicle Search (Sizwe Magubane)
As a customer, I want to search and filter by brand/model so that I can find a car quickly.
Supports filtering by make, model, year, and price range. Staff see vehicle IDs in results;
customers do not.
 
### US.3 - Customer Inquiry (Emuvoke Aghegho)
As a customer, I want to submit an inquiry so that I can receive more info from a dealer.
Customers can submit vehicle inquiries via a validated form. Server-side validation ensures
no blank submissions reach the database.
 
### US.4 - Staff Vehicle Inventory CRUD (Merve Gurses & Bashar Onisey)
As a staff member, I want to add or delete vehicle records so that the inventory is accurate.
Staff can create, read, update, and delete vehicle records from /manageVehicles,
gated behind the staff role (roleId == 2).
 
### US.5 - Staff Notifications (Emuvoke Aghegho)
As a staff member, I want to manage notification records so that I can track customer leads.
Staff can view and delete customer inquiries from the Staff Dashboard via the notifications table.
 
---
 
## Code Structure
 
```
v1/src/main/java/app/
├── controller/
│   ├── HomeServlet.java
│   ├── SignInServlet.java
│   ├── SignUpServlet.java
│   ├── LogoutServlet.java
│   ├── CustomerDashboardServlet.java
│   ├── VehicleServlet.java
│   ├── VehicleCrudServlet.java
│   ├── SubmitInquiryServlet.java
│   └── NotificationServlet.java
├── model/
│   ├── User.java
│   ├── Vehicle.java
│   └── Notification.java
├── repository/
│   ├── UserDAO.java
│   ├── VehicleDAO.java
│   └── NotificationRepository.java
├── service/
│   ├── UserService.java
│   ├── VehicleService.java
│   └── NotificationService.java
└── util/
    └── DatabaseConnection.java
```
 
---
 
## Prerequisites
 
- JDK 21
- MySQL 8.0
- Any IDE with Maven support
- Docker (optional, for containerised deployment)
## Database Setup
 
1. Open MySQL Workbench and connect as root.
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
 
Then open and run: `v1/src/main/resources/sql/vehiclemanagementDDL.sql`
 
4. Run the population script to insert test data:
   `v1/src/main/resources/sql/dbpopulation.sql`
## Configuration
 
Database credentials are stored in `v1/src/main/resources/database.properties`  
Make sure your local MySQL matches:
 
```
jdbc.url=jdbc:mysql://localhost:3306/vehiclemanagement
jdbc.username=cst8319
jdbc.password=cst8319
```
 
## Running the Application
 
### Via Eclipse
 
1. Right-click the v1 project in Project Explorer
2. Run As > Maven Build...
3. In the Goals field type: `tomcat7:run`
4. Click Run
5. Wait for: `INFO: Starting ProtocolHandler ["http-bio-8080"]`
### Via Terminal
 
```
cd v1
mvn tomcat7:run
```
 
 
## Run Tests
 
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


 
## Pages
 
| Page | URL | Description |
|------|-----|-------------|
| Home | http://localhost:8080/v1 | Landing page with vehicle listings |
| Sign In | http://localhost:8080/v1/signIn | User login |
| Sign Up | http://localhost:8080/v1/signUp | User registration |
| Vehicle Search | http://localhost:8080/v1/searchVehicle | Search vehicles by make, model, year, price |
| Customer Portal | http://localhost:8080/v1/customerDashboard | Customer dashboard and inquiries |
| Staff Dashboard | http://localhost:8080/v1/notifications | Staff notifications and inquiries |
| Manage Vehicles | http://localhost:8080/v1/manageVehicles | Staff vehicle CRUD |
| Submit Inquiry | http://localhost:8080/v1/submitInquiry | Customer vehicle inquiry form |
 
## Potential Errors
 
- **Port 8080 already in use** - stop any other Tomcat instances or change the port in pom.xml
- **Database connection failed** - verify MySQL is running and credentials in database.properties match
- **Table not found** - ensure you have run both SQL scripts in the correct order (DDL first, then population)
- **Access denied to /manageVehicles** - you must be signed in as a staff user to access this page

# Village Family Survey Portal

A **Spring Boot web application** for managing village family survey data.
The system helps administrators record, manage, and analyze family information collected during village surveys.

---

## Features

* Admin login using **Spring Security**
* Add and manage **families**
* Add and manage **family members**
* **Education summary dashboard**
* **BPL / APL classification**
* **Analytics dashboard**

---

## Tech Stack

* **Java**
* **Spring Boot**
* **Thymeleaf**
* **MySQL**
* **Gradle**

---

## How to Run the Project

### 1. Install MySQL

Make sure MySQL is installed and running.

### 2. Create Database

Run the following command in MySQL:

```
CREATE DATABASE village_survey_portal;
```

### 3. Configure Database

Update your MySQL username and password in:

```
src/main/resources/application.properties
```

### 4. Run the Application

Run the main class:

```
VillageSurveyApplication.java
```

### 5. Open in Browser

```
http://localhost:8080/login
```

---

## Default Admin Login

Username: **admin**
Password: **admin123**

*(You can change these credentials in the database for production use.)*

---

## Screenshots

Screenshots of the project output are available in the **screenshots** folder of this repository.

---

## Author

Developed as a **Spring Boot full-stack project** for learning backend development and building real-world web applications.

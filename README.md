# Village Family Survey Portal

A Spring Boot web application for managing village family survey data.

## Features

* Admin login using Spring Security
* Add and manage families
* Add and manage family members
* Education summary dashboard
* BPL / APL classification
* Analytics dashboard

## Tech Stack

Java, Spring Boot, Thymeleaf, MySQL, Gradle

## How to Run

1. Install MySQL
2. Create database:

CREATE DATABASE village_survey_portal;

3. Update database username and password in:

src/main/resources/application.properties

4. Run the application:

VillageSurveyApplication.java

5. Open browser:

http://localhost:8080/login

## Admin Login

Username: admin
Password: admin123

# User Registration & DevOps Pipeline

A Spring Boot event registration application integrated with a Jenkins CI/CD pipeline for automated build, testing, and deployment.

## Overview

This project provides a simple event registration form where users can enter their details and register for an event.

The application is built using **Spring Boot** and **Maven**, while **Jenkins** automates the build, test, and deployment process.

## Features

* Event registration form
* REST API for user registration
* Spring Boot backend
* Maven-based build system
* Automated testing with JUnit
* Jenkins CI/CD pipeline
* Automated deployment using Linux `systemd`
* Application runs on port `8081`

## Technologies Used

* **Java 17**
* **Spring Boot 3.5.4**
* **Maven**
* **HTML, CSS & JavaScript**
* **JUnit**
* **Jenkins**
* **Git & GitHub**
* **Linux systemd**

## Project Structure

```text
Jenkins_Pipeline/
├── src/
│   ├── main/
│   │   ├── java/com/example/userservice/
│   │   │   ├── User.java
│   │   │   ├── UserController.java
│   │   │   └── UserServiceApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   └── index.html
│   │       └── application.properties
│   └── test/
│       └── java/com/example/userservice/
│           └── UserServiceApplicationTests.java
├── pom.xml
├── Jenkinsfile
└── README.md
```

## Application Architecture

```text
Browser
   |
   | HTTP Request
   v
Spring Boot Application
   |
   | POST /users/register
   v
UserController
   |
   v
User Model
```

The frontend sends registration details as JSON to the REST API.

## REST API

### Register User

```text
POST /users/register
```

Example request:

```json
{
  "name": "Sukruti",
  "email": "user@example.com",
  "phone": "9876543210",
  "event": "Hackathon"
}
```

Example response:

```text
User registered successfully: Sukruti
```

## Running the Application Locally

Clone the repository:

```bash
git clone https://github.com/24wh1a0562/Jenkins_Pipeline.git
cd Jenkins_Pipeline
```

Build the project:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

The application will be available at:

```text
http://localhost:8081
```

## Jenkins CI/CD Pipeline

The Jenkins pipeline automates the application's lifecycle.

```text
GitHub
   |
   v
Checkout
   |
   v
Maven Build & Test
   |
   v
Create Spring Boot JAR
   |
   v
Deploy
   |
   v
systemd Restart
   |
   v
Running Application
```

### Pipeline Stages

**1. Checkout**

Jenkins retrieves the latest source code from the GitHub repository.

**2. Build & Test**

Maven executes:

```bash
mvn clean install
```

This compiles the application, runs the tests, and creates the executable Spring Boot JAR.

**3. Deploy**

Jenkins restarts the application's `systemd` service:

```bash
sudo systemctl restart userservice
```

This automatically deploys the newly built application.

## Jenkinsfile

The pipeline is defined in the `Jenkinsfile` stored in the root of the repository.

```groovy
pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'java'
    }

    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Deploy') {
            steps {
                sh 'sudo systemctl restart userservice'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Build failed. Check logs above.'
        }
    }
}
```

## Deployment

The application is managed as a Linux `systemd` service.

Check its status with:

```bash
sudo systemctl status userservice
```

Restart it manually with:

```bash
sudo systemctl restart userservice
```

Once deployed, access the application at:

```text
http://localhost:8081
```

## CI/CD Workflow

Whenever changes are pushed to the `main` branch:

```text
Developer
    |
    | git push
    v
GitHub
    |
    v
Jenkins
    |
    +--> Checkout
    |
    +--> Build
    |
    +--> Test
    |
    +--> Package JAR
    |
    +--> Deploy
    |
    v
Spring Boot Application
```

This removes the need to manually build and restart the application after every change.

## Future Improvements

* Add a database for persistent user registrations
* Add input validation
* Add automated API tests
* Add Docker containerization
* Add deployment to a cloud platform
* Add Jenkins webhook-based automatic builds
* Add monitoring and application health checks

## Author

**Sukruti**


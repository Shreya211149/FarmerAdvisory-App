# Farmer Advisory System

A secure, weather-based advisory platform designed to help farmers make informed agricultural decisions using real-time and forecasted weather data.

This project is inspired by real challenges faced in rural farming communities and focuses on delivering district-wise rainfall advisories through a secure, scalable, cloud-deployed system.

---

## Project Overview

The Farmer Advisory System enables farmers to:

- Register and log in securely
- Access real-time district-level weather information
- Receive automated rainfall-based advisories
- View alerts on a simple, responsive dashboard

The application follows a REST-based architecture with strong emphasis on security, scalability, and real-world usability.

---

## Live URLs

- **Backend (Render):**  
  https://farmeradvisory-app.onrender.com

- **Frontend (Netlify):**  
  https://krishisalah.netlify.app

- **API Documentation (Swagger UI):**  
  https://farmeradvisory-app.onrender.com/swagger-ui/index.html

---

## Features Implemented

### Authentication and Security
- User registration and login
- JWT-based authentication
- Spring Security integration
- Fully secured REST APIs

### Weather Integration
- Open-Meteo API integration
- Real-time district-wise weather data
- Rainfall data extraction and processing
- Weather provider abstraction for easy future migration

### Alert Generation
- Rainfall threshold-based alert logic
- Automated advisory creation
- Alerts mapped to districts and farmer groups
- Alert history persisted in the database

### Farmer Dashboard
- Displays current weather conditions
- Shows rainfall-based advisories
- Built using HTML, CSS, and JavaScript
- Responsive UI for multiple device sizes

### Database Layer
- MySQL database hosted on Aiven
- Entities for farmers, alerts, and weather data
- Proper relationships and timestamps
- Secure cloud-managed database with backups

### Backend Architecture
- Spring Boot REST APIs
- Layered architecture:
  - Controller
  - Service
  - Repository
- DTOs for clean separation of concerns
- Production profile for cloud deployment

---

## Deployment Architecture

### Frontend
- Platform: Netlify
- Hosts static frontend (HTML, CSS, JavaScript)
- Communicates with backend using REST APIs

### Backend
- Platform: Render
- Hosts Spring Boot application
- Handles authentication, weather processing, and alert logic
- Secured using JWT and Spring Security

### Database
- Platform: Aiven
- Managed MySQL database
- Secure SSL connections
- Automated backups

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Security
- JWT
- REST APIs

### Frontend
- HTML
- CSS
- JavaScript

### Database
- MySQL (Aiven)

### Tools and Platforms
- Render
- Netlify
- Aiven
- Maven
- Postman
- Git and GitHub

---

## Features in Progress

- Scheduled background jobs for periodic weather updates
- Automatic cleanup of outdated alerts 
- Edge-case handling for districts with no registered farmers

---

## Why This Project Matters

- Solves a real agricultural problem
- Built from a farmer-centric perspective
- Demonstrates real-world backend engineering skills:
  - Secure authentication
  - External API integration
  - Cloud deployment
  - Scalable system design
- Actively evolving with production-ready features

---

## Future Enhancements

- Crop-specific advisories
- SMS and WhatsApp notifications
- Multi-language support for rural users
- Analytics dashboard for weather and advisory trends

---

## Author

Shreya Midya

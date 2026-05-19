#  Farmer Advisory System

A secure, weather-based advisory platform built to help farmers make informed agricultural decisions using real-time and forecasted weather data.

This project is inspired by real challenges faced in rural farming communities and focuses on delivering **district-wise rainfall advisories** through a secure and scalable system.

---

## Project Overview

The Farmer Advisory System enables farmers to:
- Register and log in securely
- Access real-time district-level weather data
- Receive automated rainfall-based advisories
- View alerts on a simple, responsive dashboard

The system is designed using a **REST-based architecture** with strong emphasis on **security, scalability, and real-world applicability**.

---

## Features Implemented

###  Authentication & Security
- User registration and login
- JWT-based authentication
- Spring Security integration
- Secure REST APIs

---

###  Weather Integration
- Integrated **OpenWeather API**
- Real-time district-wise weather data
- Rainfall data extraction and processing

---

###  Alert Generation
- Rainfall threshold–based alert logic
- Automated advisory creation
- Alerts linked to district and farmer groups
- Alert history stored in the database

---

### Farmer Dashboard
- Displays current weather information
- Shows rainfall-based advisories
- Built with HTML, CSS, and JavaScript
- Responsive UI for accessibility

---

###  Database Layer
- MySQL database
- Entities for farmers, alerts, and weather data
- Proper relationships and timestamps

---

### Backend Architecture
- Spring Boot REST APIs
- Layered architecture (Controller → Service → Repository)
- DTOs and transformers for clean separation of concerns

---

## Features In Progress

- **7-day weather forecast integration**
-  Migration to alternative weather APIs (e.g., Meteostat)
-  Scheduled jobs for periodic weather updates
-  Automatic cleanup of outdated alerts and forecasts
- Edge-case handling for districts with no registered farmers

---

## Deployment Plan

The application will be deployed using a modern cloud-native setup:

###  Frontend
- **Platform:** Netlify  
- Hosts the static frontend (HTML, CSS, JavaScript)
- Connected to backend via REST APIs

---

###  Backend
- **Platform:** Render  
- Hosts Spring Boot application
- Handles authentication, weather processing, and alert logic
- Secured using JWT

---

### Database
- **Platform:** Aiven  
- Managed MySQL database
- Cloud-hosted with automated backups and secure connections

---

##  Tech Stack

**Backend**
- Java
- Spring Boot
- Spring Security
- JWT
- REST APIs

**Frontend**
- HTML
- CSS
- JavaScript

**Database**
- MySQL (Aiven)

**Deployment & Tools**
- Netlify
- Render
- Aiven
- Maven
- Postman

---

##  Why This Project Matters

- Addresses a **real agricultural problem**
- Built from a **farmer-centric perspective**
- Demonstrates real-world backend skills:
  - API integration
  - Authentication & security
  - Cloud deployment planning
- Actively evolving with new features

---

##  Future Enhancements
- Crop-specific advisories
- SMS / WhatsApp notifications
- Multi-language support for rural users
- Analytics dashboard for trends and insights

---

##  Author

**Shreya Midya**  

---

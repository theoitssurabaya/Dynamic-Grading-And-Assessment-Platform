# Dynamic Grading & Assessment Platform

By **Ignasius Deva**

This project is a web-based application designed to provide educators with a flexible and powerful tool for creating dynamic student score sheets. Moving beyond the limitations of rigid spreadsheets, this platform empowers teachers to design their own grading structures from the ground up, tailored precisely to their curriculum and evaluation methodology.

## 🌟 Key Features

- **Complete Customizability**: Define an unlimited number of grading "aspects" (e.g., "Homework," "Final Project," "Class Participation") and assign a specific weight to each.
- **Granular Assessment**: Break down assessments by adding unlimited "parameters" (e.g., "Essay," "Presentation," "Source Code Quality"), each with its own relative weight.
- **Automated Calculation**: As teachers input grades for each parameter, the system automatically computes the weighted score for each aspect and aggregates them into a final numerical grade.
- **Instant Indicators**:
  - **Letter Grade Predicate**: Automatically assigns a grade of A, B, C, D, or E based on final scores.
  - **Academic Status**: Automatically determines if the student has "Passed" or "Failed."
- **Customizable Thresholds**: All thresholds for letter grades and the pass/fail status are fully configurable, adapting to any school's specific grading scale.

This platform aims to save teachers significant time on administrative work while providing a more accurate and transparent assessment of student performance.

## 📂 Project Structure

This repository uses a monorepo structure separating the frontend and backend applications:

- **`frontend/`**: The web application built with Next.js, React, and Tailwind CSS.
- **`backend/`**: The RESTful API service built with Java and Spring Boot.

## 🚀 Getting Started

### Prerequisites
- **Node.js** (v18 or newer)
- **Java JDK** (v17 or newer)
- **Maven** (v3.8 or newer)

### 1. Backend Setup (Spring Boot)

1. Navigate to the backend directory:
   ```bash
   cd backend
   ```
2. Build and run the Spring Boot application using the provided Maven wrapper:
   ```bash
   # On Linux/macOS
   ./mvnw spring-boot:run

   # On Windows
   mvnw.cmd spring-boot:run
   ```
3. The backend will typically start on `http://localhost:8080`.

### 2. Frontend Setup (Next.js)

1. Open a new terminal and navigate to the frontend directory:
   ```bash
   cd frontend
   ```
2. Install the dependencies:
   ```bash
   npm install
   # or yarn install
   # or pnpm install
   ```
3. Run the development server:
   ```bash
   npm run dev
   # or yarn dev
   # or pnpm dev
   ```
4. Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

## 🛠️ Technology Stack

- **Frontend**: Next.js 15, React 19, Tailwind CSS v4, Zod, React Hook Form, Lucide Icons.
- **Backend**: Java, Spring Boot.

## 🤝 Contributing
Feedback and contributions are welcome to make this platform even better for educators worldwide.

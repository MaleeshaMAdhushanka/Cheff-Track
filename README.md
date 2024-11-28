# **Cheff Track**

Cheff Track is a robust digital system designed for The Culinary Academy in Sri Lanka. It streamlines student registration and program management, offering a modern solution to replace outdated manual processes. This system empowers administrators and admissions coordinators to handle tasks efficiently, ensuring a seamless experience for students and staff.


---

## **Features**

### **Core Functionalities**
- 👨‍🎓 **Student Registration:**
    - Streamlined process to enroll students into their preferred culinary programs.
    - Capture and store essential student details such as name, contact information, and documents.
    - Support for enrolling a single student into multiple programs.

- 📚 **Course Management:**
    - Comprehensive management of all culinary programs offered by the academy.
    - Add, update, and remove programs with details like duration, fees, and unique program IDs.
    - Ensure program data integrity with real-time validation and user-friendly interfaces.

- 👩‍💼 **Role-Based Access Control:**
    - Separate sections for **Admin** and **Admissions Coordinator** roles.
    - **Admin Capabilities:** Manage courses, user roles, and view high-level system reports.
    - **Coordinator Capabilities:** Handle student registration, document verification, and payment entries.

- 💰 **Payment Management:**
    - Record upfront payments during student registration.
    - Track payment histories for all students and generate receipts.
    - Ensure accurate financial records and prevent duplicate payment entries.

- 📊 **Dashboard Overview:**
    - Intuitive and visually appealing dashboard providing a snapshot of:
        - Total student registrations.
        - Enrollment statistics by program.
        - Outstanding payments and financial summaries.
    - Dynamic charts and graphs powered by JavaFX to enhance user experience.

- 🔒 **Secure Authentication and Data Protection:**
    - Securely store user credentials using encryption algorithms like **BCrypt**.
    - Implement robust validation for user inputs, including email formats, phone numbers, and required fields.
    - Protect sensitive data with secure database connections and error handling mechanisms.

---

## **Technology Stack**
- **Frontend:** JavaFX, css for dynamic and responsive user interfaces.
- **Backend:** Java with Hibernate ORM for seamless database interactions.
- **Database:** MySQL for storing and managing structured data.
- **Libraries and Tools:**
    - BCrypt for password encryption.
    - IntelliJ IDEA for efficient development.
    - MaterialFX for UI enhancements.

---

## **Setup and Installation**

### Prerequisites
- JDK 11 or higher installed on your system.
- MySQL Server running and configured.
- IntelliJ IDEA or any Java-compatible IDE.

### Steps
1. Clone the repository:
   ```bash
   https://github.com/MaleeshaMAdhushanka/Cheff-Track.git

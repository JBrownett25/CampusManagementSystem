
# 🎓 Campus Management System

[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()
[![License: MIT](https://img.shields.io/badge/license-MIT-lightgrey.svg)]()

A desktop-based **Campus Management System** built using **Java Swing**, designed to manage students, rooms, and library resources efficiently.  
The system uses **CSV files** for lightweight and portable data storage.

---

## ✨ Features

### 🧑‍🏫 Student Management
- Add, edit, and remove student records
- View all students in a dynamic, scrollable table
- Data stored in `Students.csv`

### 🏫 Room Management
- Book rooms by name
- View all available rooms with details
- Data stored in `Rooms.csv`

### 📚 Library Management
- Search for books by title
- View book details: title, author, course, available copies
- Reserve books with confirmation pop-ups
- Data stored in `Library.csv`

### 📋 User Interface
- Java Swing GUI with Card Layout for smooth navigation
- Simple and intuitive design
- Interactive error handling and user feedback

---

## 🚀 Getting Started

### Prerequisites
- [Java Development Kit (JDK) 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [JUnit 5](https://junit.org/junit5/) (for running unit tests)

### Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/campus-management-system.git
   cd campus-management-system
   ```

2. **Compile the project:**
   ```bash
   javac *.java
   ```

3. **Run the application:**
   ```bash
   java StudentManagementApp
   ```

4. **(Optional) Run tests:**
   - Using your IDE's built-in test runner (JUnit 5)
   - Or using Maven/Gradle if added later

---

## 🛠 Project Structure

```plaintext
├── src/
│   ├── BookRoomGUI.java
│   ├── Library.java
│   ├── LibraryTest.java
│   ├── RoomGUI.java
│   ├── RoomManagement.java
│   ├── RoomManagementTest.java
│   ├── Student.java
│   ├── StudentDAO.java
│   └── StudentManagementApp.java
└── CampusManagementSystem2.iml
```

**CSV Files (Data):**
- `Students.csv`
- `Rooms.csv`
- `Library.csv`

*(Created automatically if missing.)*

---

## 💪 Testing

The project includes **unit tests** for core functionalities:
- `LibraryTest.java` — tests book search operations
- `RoomManagementTest.java` — tests room listing formatting

Tests are written using **JUnit 5**.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).  
Feel free to use, modify, and distribute for personal or commercial purposes.

---

## 🤝 Contributing

Contributions are welcome!  
Please open an Issue or submit a Pull Request to suggest improvements or report bugs.

---

## 📬 Contact

For queries or collaborations, feel free to reach out:
- GitHub: [your-username](https://github.com/your-username)

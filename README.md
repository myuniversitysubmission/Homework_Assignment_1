#Home Work Assignment 1

# Waste Management System – Java Console Project

This project is part of **Java Assignment – Basic I/O and Regular Expressions**.  
It simulates an **Automated Waste Sorting & Task Management System** using **Object-Oriented Programming (OOP)** principles in Java.

---

## ✅ Features
✔ Console-based Java application  
✔ Object-Oriented Design  
✔ File Handling (Create/Read/Write/Delete/Move)  
✔ Uses **Byte Streams** and **Character Streams**  
✔ **Daily Logs** per device  
✔ **Search logs using Regular Expressions (Regex)**  
✔ **Archive logs (.zip)**  
✔ **Log categories:** Storage Vehicle, Charging Station, System  
✔ Folder auto-creation for log organization  

---

## ✅ Project Structure

src/
wastesystem/
Main.java
LogManager.java
TaskManager.java
StorageVehicle.java
ChargingStation.java
Robot.java
WasteItem.java

logs/
storage/
charging/
system/
archive/

---

## ✅ How to Run
### Prerequisites
- Java 8+ or Java 11/17/21
- Eclipse IDE or IntelliJ

### Steps
1. Clone this repo  
   ```bash
   git clone https://github.com/yourusername/WasteManagementSystem.git
2. Open in Eclipse
3. Run Main.java as Java Application
4. Follow console menu

✅ Console Menu Options
1 - Simulate: Robot picks up waste and deposits
2 - Create manual system log entry
3 - List all logs
4 - Search logs by regex
5 - Read a log file
6 - Delete a log
7 - Move log to archive
8 - Archive logs by date (zip)
9 - Show folder structure
0 - Exit

✅ Example Logs Generated
Files are stored automatically under logs/ like:
logs/storage/vehicle1_2025-10-27.log
logs/charging/station1_2025-10-27.log
logs/system/system_2025-10-27.log

✅ Classes Overview
Class	Responsibility
Main	Runs the program
LogManager	Handles all log operations
TaskManager	Assigns tasks to robots
Robot	Picks and deposits waste
StorageVehicle	Stores collected waste
ChargingStation	Charges robot
WasteItem	Waste model

✅ Assignment Requirements Covered
✅ File I/O
✅ Byte & Character Streams
✅ Regex
✅ Daily Logs
✅ File Create/Delete/Move
✅ Archive Logs (.zip)
✅ Console Interaction
✅ OOP Structure ✅

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
📘 Project Description (Homework Explanation)

This Java console application simulates a Waste Management System where robots pick up and deposit waste into storage vehicles while generating logs for every activity.
It uses Object-Oriented Programming (OOP), File Handling (Basic I/O), and Regular Expressions (Regex) to manage and search daily logs for each component.
Each log entry records actions such as robots picking up waste, depositing it, or charging. Logs are separated by category (system, storage, charging) and organized by date.

🧩 Class Explanations and Functionalities
1. Main.java
Entry point of the project.
Displays a menu-based console interface.
Handles user input and calls appropriate methods.
Demonstrates all assignment requirements: file creation, reading, regex search, deletion, and archiving.

Main functionalities:
Simulate robot waste handling
Create manual logs
List/search/read/delete logs
Archive or move logs
Display log directory tree

2. LogManager.java
Central class handling all log file operations.
Uses byte streams (FileOutputStream) and character streams (FileWriter, BufferedWriter) for writing.
Supports log creation, reading, deletion, movement, and archiving (ZIP).
Handles regex-based search to find logs by name or date.
Auto-creates required log directories.

Main methods:
createStorageLog() → Creates vehicle logs
createChargingLog() → Creates charging station logs
createSystemLog() → Creates system logs
appendLogUsingStreams() → Writes using byte + character streams
readLog() → Reads and displays log content
deleteLog() → Deletes a log file
moveLogToArchive() → Moves log to archive folder
archiveLogsByDate() / archiveAllLogs() → Creates ZIP archive

3. TaskManager.java
Manages robots, vehicles, and charging stations.
Registers all devices in the system.
Assigns tasks to robots and logs every action.
Demonstrates inter-class communication and composition.

Main methods:
registerStorageVehicle()
registerChargingStation()
registerRobot()
assignPickAndDeposit() → Simulates full robot operation cycle

4. Robot.java
Represents an autonomous robot performing waste operations.
Interacts with StorageVehicle and ChargingStation.
Logs all actions like pickup, deposit, and charging.

Main methods:
pickUp(WasteItem item)
deposit(StorageVehicle vehicle, WasteItem item)
goCharge(ChargingStation station)

5. StorageVehicle.java
Simulates a storage vehicle that collects waste.
Has capacity limits and logs each deposit.

Main methods:
deposit(WasteItem item) → Logs deposit details

6. ChargingStation.java
Simulates robot charging operation.
Logs charging start and completion events.

Main methods:
chargeRobot(String robotId)

7. WasteItem.java
Simple data class representing a waste item with ID and type.

Attributes:
id → Unique waste identifier
type → Category/type of waste

⚙️ Functional Workflow
System initializes and registers all components.
User simulates a task → Robot picks and deposits waste.
Log files are created in logs/ automatically.
User can:
View logs
Search logs using Regex
Move/delete/archive logs
Create manual entries
All actions are recorded daily and archived as .zip files.

🧠 Learning Outcomes
This assignmnet demonstrates:
Basic I/O in Java
Byte Streams & Character Streams
Regular Expressions
File organization and metadata management
Object-Oriented Programming principles
Real-world system simulation using Java console

🗂 Example Output (Logs Folder)
logs/
  storage/
    vehicle1_2025-10-27.log
    vehicle2_2025-10-27.log
  charging/
    station1_2025-10-27.log
  system/
    system_2025-10-27.log
  archive/
    vehicle1_2025-10-27.log
    archive_2025-10-27.zip
---

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
---

📋 Project Requirements
🎯 Objective
Develop a Java console-based system to simulate Automated Waste Management, demonstrating:
Java Basic I/O (File Handling)
Byte and Character Streams
Regular Expressions for search
Daily log generation and archiving
Object-Oriented Programming principles

🧠 Functional Requirements
1. System should manage daily logs for:
   Each Storage Vehicle
   Each Charging Station
   Overall System
3. Logs must include activities like:
   Robot picking up waste
   Depositing waste into storage
   Charging process
   System events

4. User can:
   View logs
   Search logs by date or name (Regex)
   Delete or move logs
   Archive logs into .zip files
   Data should be stored under a logs/ directory.

---
Role & Responsibilities:
1. Adesh: Introduction, Overview & Class Diagram 
2. Dnyaneshwar: OOP structure and main classes
3. Harsha: Program Demonstration
4. Anil: File I/O, Regex & Learning Outcome
---
Class Diagram:
<img width="2458" height="1900" alt="Blank diagram (3)" src="https://github.com/user-attachments/assets/09a090d1-fbd7-43b5-b3fb-9abe31ce79f7" />

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


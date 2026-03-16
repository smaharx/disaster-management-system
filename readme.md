# Disaster Management System

![Java](https://img.shields.io/badge/Java-8%2B-orange)
![Database](https://img.shields.io/badge/Database-MySQL-blue)
![License](https://img.shields.io/badge/License-Not%20Specified-lightgrey)
![Status](https://img.shields.io/badge/Project-Active-green)

A Java desktop application designed to assist disaster relief organizations in managing affected families, tracking relief inventory, and generating operational reports. The system provides a structured workflow for registering families, managing supplies, and monitoring distribution activities.

---

## Overview

Disaster response efforts require coordination, organization, and clear tracking of resources. This project provides a lightweight desktop-based solution to manage critical aspects of disaster relief operations.

The system allows administrators and operators to register affected families, manage relief inventory, monitor stock levels, and generate reports to help coordinate aid distribution.

The application is implemented using Java Swing for the user interface and MySQL for persistent data storage.

---

## Key Features

* User authentication and login interface
* Registration and management of affected families
* Inventory tracking for relief supplies
* Restocking management
* Inventory monitoring dashboard
* Data-driven reports
* MySQL database integration
* Simple desktop deployment with minimal setup

---

## System Architecture

The application follows a simplified layered architecture:

1. **Presentation Layer** — Java Swing UI components
2. **Business Logic Layer** — Inventory and operational management
3. **Data Access Layer** — JDBC-based database connectivity
4. **Database Layer** — MySQL relational database

This separation helps maintain modularity and simplifies future extension of the system.

---

## Technology Stack

| Component             | Technology              |
| --------------------- | ----------------------- |
| Programming Language  | Java                    |
| GUI Framework         | Java Swing              |
| Database              | MySQL                   |
| Database Connectivity | JDBC                    |
| External Libraries    | MySQL Connector, rs2xml |

---

## Project Structure

```
DISASTER-MANAGEMENT-SYS
│
├── database
│   └── sqlcode.sql
│
├── disasterrelief
│   ├── database
│   │   ├── DBConnection.java
│   │   └── InventoryManager.java
│   │
│   ├── models
│   │   ├── Family.java
│   │   └── InventoryItem.java
│   │
│   └── views
│       ├── LoginUI.java
│       ├── MainUI.java
│       ├── InventoryUI.java
│       ├── manageFamiliesUI.java
│       ├── RegisterFamilyUI.java
│       ├── RestockUI.java
│       ├── ViewInventoryUI.java
│       └── report.java
│
├── icon
│   └── login.png
│
├── lib
│   ├── mysql-connector-j-9.2.0.jar
│   └── rs2xml.jar
│
└── .gitignore
```

---

## Installation

Clone the repository:

```
git clone https://github.com/ShahzaibMahar007/disaster-management-sys.git
cd disaster-management-sys
```

Ensure Java JDK 8 or later is installed.

---

## Database Setup

Create the database:

```sql
CREATE DATABASE disaster_relief;
```

Import the schema:

```
mysql -u root -p disaster_relief < database/sqlcode.sql
```

---

## Configuration

Open the file:

```
disasterrelief/database/DBConnection.java
```

Update the database connection details:

```
jdbc:mysql://localhost:3306/disaster_relief
username
password
```

---

## Running the Application

Compile the project:

```
javac -cp "lib/*" -d out $(find . -name "*.java")
```

Run the program:

```
java -cp "out:lib/*" MainUI
```

---

## Application Modules

### Login Module

Handles authentication of system users and restricts access to authorized personnel.

### Family Management

Allows registration and modification of disaster-affected households.

### Inventory Management

Tracks available relief resources and supply quantities.

### Restocking Module

Allows administrators to add new inventory or replenish existing supplies.

### Reporting

Generates reports for families and inventory records.

---

## Data Models

### Family

Represents affected households.

Attributes may include:

* Family ID
* Head of family
* Address
* Contact information
* Household size

### InventoryItem

Represents relief items stored in the inventory.

Attributes may include:

* Item ID
* Item name
* Description
* Quantity

---

## Dependencies

The project relies on the following libraries:

* mysql-connector-j
* rs2xml

Both libraries are included in the `/lib` directory.

---

## Contributing

Contributions are welcome.

Steps:

```
1. Fork the repository
2. Create a new branch
3. Commit your changes
4. Push to your fork
5. Open a Pull Request
```

---

## Security Considerations

* Avoid storing database credentials in source code for production systems.
* Use parameterized SQL queries to prevent SQL injection.

---

## Troubleshooting

Database connection errors may occur if:

* MySQL service is not running
* Database credentials are incorrect
* JDBC driver is missing from the classpath

---

## Future Improvements

Potential improvements for future versions:

* Web-based interface
* Cloud database integration
* Role-based access control
* Audit logging
* Advanced analytics and reporting

---

## Authors

- Shahzaib Mahar
- Najaf
- Deepak

---

## Contributors

See the full list of contributors on GitHub:
https://github.com/ShahzaibMahar007/graphs/contributors

---

## License

This repository currently does not include an open-source license. Add an appropriate license before distributing the software.

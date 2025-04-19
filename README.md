# Student Management System

A comprehensive Java EE web application for managing students and courses, built with JSP, Servlets, MVC architecture, MySQL, and deployed on Apache Tomcat via NetBeans IDE.

## Prerequisites

- **JDK 21**  
- **NetBeans IDE 17** (or later)  
- **Apache Tomcat 10.1** (configured in NetBeans)  
- **MySQL Server 8.0+**  
- **MySQL Connector/J JDBC Driver**  

## Getting Started
1. **Set up Database**\
	Open MySQL command line or workbench\
	Run the following SQL commands:
	
	```sql
	-- Create database
	CREATE DATABASE studentdb;
	USE studentdb;

	-- Students table
	CREATE TABLE students (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
	course VARCHAR(50),
	registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP );

	-- Courses table
	CREATE TABLE courses (
        id INT AUTO_INCREMENT PRIMARY KEY,
        code VARCHAR(10) UNIQUE NOT NULL,
        name VARCHAR(100) NOT NULL,
        description TEXT,
        credits INT NOT NULL );

	-- Add student data (optional)
	INSERT INTO students (name, email, course) VALUES
	('John Doe', 'john@example.com', 'Computer Science'),
	('Jane Smith', 'jane@example.com', 'Information Technology'),
	('Mike Johnson', 'mike@example.com', 'Software Engineering'),
	('Duke Ng','sodjiasd@game.com','Software Engineering');

	-- Add course data (optional)
	INSERT INTO courses (id, code, name, description,credits) VALUES
	('1','CS','Computer Science','Nein','130'),
	('2','IT','Information Technology','Nein','90'),
	('3','SE','Software Engineering','Nein','115'),
	('4','DS','Data Science','Nein','116'),
	('5','CySec','Cybersecurity','Nein','123');

	-- Add Foreign Key
	ALTER TABLE students ADD COLUMN course_id INT;

	UPDATE students s
	JOIN courses c ON s.course = c.name
	SET s.course_id = c.id;

	ALTER TABLE students
	ADD CONSTRAINT fk_students_course_id
	FOREIGN KEY (course_id) REFERENCES courses(id)
	ON DELETE SET NULL
	ON UPDATE CASCADE;
	```
***You can optionally download sql file or backup at [release page](https://github.com/minhduckd5/student-management-system/releases/tag/SQL-Queries "Title")***\
2. **Clone the repository**  
   ```bash
   git clone https://github.com/minhduckd5/student-management-system.git
   cd student-management-system
```

3. **Configure Database Credentials**\
Open *src/main/java/com/university/util/DBUtil.java*\
Update the **URL**, **USER**, and **PASSWORD*** constants to match your MySQL setup.

4. **Import into NetBeans**\
In NetBeans, choose **File > Open Project**\
Navigate to the cloned directory\
Select the project (it will detect the Maven Web Application)

5. **Build & Deploy**\
Right-click the project -> **Clean and Build**\
Ensure Apache Tomcat is configured under **Tools > Servers**\
Right -> click the project > **Run**\
The application will compile, deploy to Tomcat, and open in your default browser.\

***Note**: you can get the ZIP file to import from [release page](https://github.com/minhduckd5/student-management-system/releases/tag/SQL-Queries "Title")*

## Features
### Student Management
*  **List All Students**: Browse the complete roster.
* **View Student Details**: Inspect individual student profiles, including registration date.
* **Add New Student**: Register students with name, email, and course assignment.
* **Edit Student**: Update personal info and reassign courses.
* **Delete Student**: Remove obsolete records.
### Course Management
* **List All Courses**: View available course catalog.
* **View Course Details**: Inspect course code, description, and credit value.
* **Add New Course**: Define new offerings with code, name, description, and credits.
* **Edit Course**: Modify course metadata.
* **Delete Course**: Clean up deprecated courses.
* **Student Enrollment Dropdown**: Dynamic course list in student form for accurate FK assignment.

## Some Screenshots
### Index page
![Screenshot 2025-04-19 155914](https://github.com/user-attachments/assets/41323337-3cf1-4b32-8d3c-5b47e13d7495)
### Add Student
![Screenshot 2025-04-19 160026](https://github.com/user-attachments/assets/d4939b5a-729d-4544-8948-c64b98ad0a6c)
### Add Course
![Screenshot 2025-04-19 160111](https://github.com/user-attachments/assets/de9d02b0-8790-4aef-9211-ddcd80189206)



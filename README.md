Identity-Based Scheme Deployment System (IBSDS)
This repository contains the source code for the Identity-Based Scheme Deployment System (IBSDS), an application designed to streamline government scheme management in India.
IBSDS leverages Aadhaar integration for beneficiary registration and verification (placeholder for future implementation), offering a user-friendly platform for both beneficiaries and government officials.
Prerequisites:

●	Java Development Kit (JDK) 8 or later (https://www.oracle.com/java/technologies/javase/downloads/)
●	Java IDE with project management capabilities (e.g., Eclipse, IntelliJ IDEA)
●	MySQL Connector/J library (https://dev.mysql.com/downloads/connector/j/)
●	JCalendar library (https://sourceforge.net/projects/jcalendar/)

Installation:

1.	Clone this repository: git clone 
https://github.com/dhruvravii/scheme_deployment.git
2.	Navigate to the project directory: cd scheme_deployment
3.	Include Libraries:
○	Download the mysql-connector-java.jar file from the MySQL Connector/J download page.
○	Download the jcalendar.jar file from the JCalendar project page.
○	Place both downloaded .jar files in a directory within your project (e.g., lib).
4.	Configure Build Path:
○	Open your IDE and navigate to project settings (e.g., Project Structure in IntelliJ IDEA).
○	Locate the Libraries section and click on Add JARs....
○	Select the downloaded mysql-connector-java.jar and jcalendar.jar files from the chosen directory (step 3) and include them in your project's build path.
Running the Application:

1.	Open your IDE and locate the file MainWindow.java
2.	Use your IDE's functionalities to run
3.	On running, you will see two options- Citizen and Government. Choose either preference to continue to the Citizen Portal or Government Portal correspondingly.
 
Datasets:

The application utilizes the following CSV datasets located in the data directory:

●	GovtTable.csv: Stores information about government schemes.
●	GovtStats.csv: Contains statistics related to government schemes.
●	Dataset(NULL_AS_BLANK).csv: Contains the data of the citizens that we have used in the application, for various schemes.
Current Functionality:

Note: The current version of IBSDS does not yet have the functionality to create new government schemes. The existing schemes within the GovtTable.csv dataset have been populated manually based on certain arbitrary eligibility constraints. Functionality to add new schemes and manage their details is planned for future development.

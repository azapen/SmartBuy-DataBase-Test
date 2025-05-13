# AppTest – Selenium, JDBC, and TestNG Integration

This project demonstrates how to integrate **Selenium WebDriver**, **TestNG**, and **JDBC** to automate a web-based registration form using customer data retrieved from a MySQL database.

---

##  Project Overview

The Java-based test suite performs the following operations:

1. **Database Insertion** – Adds a new customer to the database.
2. **Database Update** – Modifies the credit limit of the inserted customer.
3. **Data Retrieval + Form Automation** – Reads customer data from the database and fills a registration form on [SmartBuy](https://smartbuy-me.com/account/register).
4. **Record Deletion** – Removes the test data after execution.

---

##  Technologies Used

- **Java**
- **TestNG** (Test framework)
- **Selenium WebDriver** (Browser automation)
- **JDBC (MySQL Connector)** (Database connectivity)
- **ChromeDriver** (WebDriver for Chrome)

---

## 🛠 Prerequisites

Before running the tests, make sure the following are set up:

### 1. Database

You need a MySQL database named `classicmodels` with a `customers` table. Example schema:

```sql
CREATE TABLE customers (
  customerNumber INT PRIMARY KEY,
  customerName VARCHAR(50),
  contactLastName VARCHAR(50),
  contactFirstName VARCHAR(50),
  phone VARCHAR(20),
  addressLine1 VARCHAR(100),
  city VARCHAR(50),
  country VARCHAR(50),
  salesRepEmployeeNumber INT,
  creditLimit DECIMAL(10,2)
);
```

### 2. ChromeDriver

Download the ChromeDriver from: [https://chromedriver.chromium.org/downloads](https://chromedriver.chromium.org/downloads)

Then set it in your environment `PATH` or configure it in code:

```java
System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
```

### 3. Java Dependencies

If you're using Maven, add the following dependencies to your `pom.xml`:

```xml
<dependencies>
  <dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.18.1</version>
  </dependency>
  <dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.10.1</version>
    <scope>test</scope>
  </dependency>
  <dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
  </dependency>
</dependencies>
```

---

##  How to Run the Project

1. Clone the repository or copy the Java class to your IDE.
2. Start your MySQL server and ensure the `classicmodels` database exists.
3. Ensure the target form URL `https://smartbuy-me.com/account/register` is reachable.
4. Run the TestNG test class `AppTest.java`.

> ℹ You can run the tests from your IDE (with TestNG plugin) or via Maven:
```bash
mvn test
```

---

##  Test Execution Flow

| Priority | Method                | Description                                               |
|----------|------------------------|-----------------------------------------------------------|
| 1        | `InsertIntoDataBase()` | Inserts a dummy customer into the database                |
| 2        | `UpdateDateBase()`     | Updates the customer's credit limit                       |
| 3        | `ReadDateBase()`       | Reads customer data and fills the SmartBuy registration form |
| 4        | `DeleteDateBase()`     | Deletes the inserted test record from the database        |

---

##  Important Notes

- Ensure database credentials are correct in the code:
  ```java
  DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "1234");
  ```

- The test email is dynamically generated using:
  ```java
  CustomerEmail = (FirstName + LastName).replaceAll("\s+", "") + "@gmail.com";
  ```

- If the SmartBuy registration page structure changes, form locators may need updating.

---

##  License

This project is intended for educational and testing purposes only. Use responsibly.

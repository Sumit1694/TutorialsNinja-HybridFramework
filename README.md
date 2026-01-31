# Selenium Hybrid Automation Framework

## Overview
This is a **Hybrid Test Automation Framework** built using **Selenium WebDriver, Java, TestNG, Maven, and Cucumber BDD**.  
The framework supports **UI automation, API testing, data-driven testing, retry mechanism, parallel execution, CI/CD integration, and Selenium Grid execution using Docker**.

It is designed to be **scalable, maintainable, and enterprise-ready**, following industry best practices.

---

## Tech Stack
- **Programming Language:** Java  
- **UI Automation:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **Build Tool:** Maven  
- **Design Pattern:** Page Object Model (POM) + PageFactory  
- **Reporting:** Extent Reports  
- **Logging:** Log4j2  
- **CI/CD:** Jenkins  
- **Containerization:** Docker + Selenium Grid  
- **Version Control:** Git  

---

## Framework Architecture

The framework follows a **layered architecture** to ensure clean separation of concerns.

```
Test Layer
└── TestNG Test Classes
└── Assertions & Validations

Business Layer
└── Page Object Classes
└── PageFactory Locators
└── Business Methods

Core Layer
└── BaseClass (Driver setup & teardown)
└── RetryAnalyzer
└── Screenshot Utility
└── Wait Utilities

Utility Layer
└── ExcelUtility (Data Driven Testing)
└── ConfigReader
└── Logger Utility

Reporting Layer
└── TestNG Listeners
└── Extent Report Manager

Execution Layer
└──  Maven
└── Jenkins
└── Selenium Grid (Docker)
```


---

## Project Structure
```
src/test/java
├── testCases
├── pageObjects
├── utilities
│ ├── RetryAnalyzer
│ ├── RetryListener
│ ├── ExcelUtility
│ ├── ExtentReportManager
│ ├── ConfigReader
│ └── Logger
├── testBase
│ └── BaseClass

src/test/resources
├── config.properties
├── log4j2.xml
├── testng.xml
└── docker-compose.yml
```


---

## Retry Mechanism
- Automatically retries failed test cases **without modifying individual test methods**  
- Implemented using **IRetryAnalyzer + IAnnotationTransformer**  
- Failed tests are retried immediately before moving to the next test  

> **Note:**  
> TestNG marks intermediate retry attempts as **SKIPPED**. Only the **final execution** is marked as **PASS or FAIL** in the report.

---

## Reporting – Extent Reports
- HTML execution reports with:
  - Test steps  
  - Failure reasons  
  - Screenshots on failure  
- Automatically generated after execution  
- Reports stored under: /reports/Test-Report-<timestamp>.html


---

## Logging
- Centralized logging using **Log4j2**  
- Logs generated for:
  - Test execution flow  
  - Failures and exceptions  
- Log file location: /logs/automation.log


---

## Data Driven Testing
- Test data maintained in **Excel files**  
- Supports:
  - Multiple test scenarios  
  - Positive & negative cases  
- Excel handled via custom `ExcelUtility` class

---

## Cross Browser Testing
Supported browsers:  
- Chrome  
- Firefox  
- Edge  

## Run tests in a specific browser:  
bash 
mvn test -Dbrowser=chrome

##Parallel Execution
TestNG supports parallel execution
Controlled via testng.xml: <suite name="Suite" parallel="tests" thread-count="5">

##Selenium Grid with Docker:
Start Selenium Grid: docker compose up -d

##Execute tests on Grid:
mvn test -DexecutionMode=grid

###Supported Grid browsers:
Chrome
Firefox
Edge

##Configuration Management

Environment-specific configuration via config.properties
Supports:

Multiple environments (QA, Staging)
Browser selection
Grid or local execution

How to Execute Tests

Local Execution: mvn clean test

Specific Browser: mvn test -Dbrowser=edge

Grid Execution: mvn test -DexecutionMode=grid

##Key Highlights

Hybrid Automation Framework

Page Object Model + PageFactory

Retry mechanism without test-level changes

Data-driven testing using Excel

Extent Reports with screenshots

Logging using Log4j2

Parallel execution support

Jenkins CI/CD integration

Selenium Grid with Docker

UI

---

## Conclusion
This framework is designed to meet **real-world enterprise automation requirements**.  
It is scalable, maintainable, and suitable for continuous testing in CI/CD pipelines.

---

---

## Author
**Sumit Rane** – Automation Test Analyst  
- 4+ years experience in Manual and Automation Testing  
- Skilled in Selenium, Java, TestNG, Maven, REST-assured, Cucumber BDD  
- Experience in Hybrid Automation Framework, Data Driven Testing, Extent Reports, Logging  
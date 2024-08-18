# GroupMart: Selenium-TestNG-Cucumber-Hybrid-Testing-Framework

## Overview

The **GroupMart: Selenium-TestNG-Cucumber-Hybrid-Testing-Framework** is a comprehensive test automation solution designed for testing web applications. This framework integrates Selenium WebDriver with TestNG and includes Cucumber for behavior-driven development (BDD) test cases, creating a hybrid approach to test automation.

## Features

- **Selenium WebDriver** for rubust multiple browsers automation
- **TestNG** for strategic BDD like data-driven testing (DDT) bsed on individual test type .xml profiles supporting parallel execution, flexible configurations, test grouping, and dependencies management for efficient and robust maven test suites
- **Cucumber** for showcasing behavior-driven development (BDD) of test cases
- **Implicit and Explicit wait mechanisms** for automating natural flow of AUT
- **Page-Object-Model (POM) implementation using `Page Factory`** to make tests more readable, reusable, easier to maintain and code redundency safe
- **Excellent HTML Test Reporting** using `Extent Reports` to visualize test results statistics
- **Management of external test and internal app json data** using `Jaskson Databind` 
- **Soft Assertions** usage specially in clean-up process after test execution for seemless program flow
- **Retry Mechanism** using `IAnnotationTransformer` and `IRetryAnalyzer` interfaces to avoid test flakiness
- **Fail Test Screenshot** using `ITestListener` to enhance test reporting
- **Concurrency Problem** resolution for parallel test execution using `ThreadLocal` java class
- **Java Streams** usage for code complexity reduction and test coherance enhancement
- Easy **integration with CI/CD pipelines** using **Jenkins** and **GitHub Webhooks**
- Tested and Compatible with **Cross Browser Testing**. Follow instruction on [Getting started with Selenium Grid](https://www.selenium.dev/documentation/grid/getting_started/)

## Dependencies

- **Java Development Kit (JDK)** 8 or higher
- [selenium-java](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java) - version 4.21.0 or higher 
- **Apache Maven** configuration of project for dependency management
- [testng](https://mvnrepository.com/artifact/org.testng/testng) - latest version 6 for java 8 or version 7 or higher for java 11 or higher
- [webdrivermanager](https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager) - version 5.8.0 or higher
- [commons-io](https://mvnrepository.com/artifact/commons-io/commons-io) - version 2.16.1 or higher
- [jackson-databind](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind) - version 2.17.1 or higher
- [extentreports](https://mvnrepository.com/artifact/com.aventstack/extentreports) - version 5.1.2 or higher
- [cucumber-java](https://mvnrepository.com/artifact/io.cucumber/cucumber-java) - version 7.18.0 or higher
- [cucumber-testng](https://mvnrepository.com/artifact/io.cucumber/cucumber-testng) - version 7.18.0 or higher
- [json](https://mvnrepository.com/artifact/org.json/json) - version 20240303 or higher
- [maven commands](https://maven.apache.org/download.cgi) - download **apache-maven-x.x.x-src.tar.gz** file for mac O.S and **apache-maven-x.x.x-src.zip** for win O.S. 
- [How to Install Maven and Configure Environment Variables](https://www.qamadness.com/knowledge-base/how-to-install-maven-and-configure-environment-variables/) - Install and system path setting guidance
- [Triggering TestNG tests from mvn commands](https://maven.apache.org/surefire/maven-surefire-plugin/examples/testng.html#using-suite-xml-files) - plugin to trigger TestNG tests from maven commands
- **Cucumber Eclipse Plugin** by Cucumber MIT to support writting cucumber feature files. Could be downloaded from **Eclipse Marketplace** 
- **IDE** such as IntelliJ IDEA or Eclipse IDE
- **Web Browser** (Chrome, Firefox, Edge)

## Project Structure

`src/main/java/` folder Contains:
   - `groupmart.AbstractComponents/` - This folder contains utility java class for all the common app functions
   - `groupmart.pageobjects/` - This folder contains the POM classes for all the web pages of app
   - `groupmart.resources/` - This folder contains Extent Report generator utility and Global Properties config file 

`src/test/java/` folder Contains:
   - `cucumber/` - This folder contains cucumber test runner and cucumber tests feature files
   - `groupmart.data/` - This folder contains json files for app environmental data and user profile data for TestNG test parameterization
   - `groupmart.stepDefinitions/` - This folder contains cucumber tests step definations
   - `groupmart.TestComponents/` - This folder contains test utility base class for all common test methods along with TestNG listner to capture screen shot of test failing web element and Retry mechanism for failing or flaky test
   - `groupmart.Tests/` - This folder contains actual e2e and Regression test scripts

`Reports/` - This folder contains HTML Extent Reports to view test results on web

`Screenshots/` - This folder contains screenshot of the web element where the test is failing to support Defect Detection

`target/` - This folder contains cucumber HTML test report

`testSuite/` - This folder contains .xml execution configuration file for individual test suites i.e e2e and Regression

`pom.xml` - Entry point to test suites. This file contains all maven configuration and dependencies along with the individual test profiles to be executed with maven commands

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Jzsls/GroupMart-Selenium-TestNG-Cucumber-Hybrid-Testing-Framework.git
2. **Download Maven:**
[maven download](https://maven.apache.org/download.cgi) - download **apache-maven-x.x.x-src.tar.gz** file for mac O.S and **apache-maven-x.x.x-src.zip** for win O.S. 
3. **Install maven and set system path**  
[How to Install Maven and Configure Environment Variables](https://www.qamadness.com/knowledge-base/how-to-install-maven-and-configure-environment-variables/)
4. **Navigate to the project directory and install all required maven dependencies**
   ```bash
   mvn clean install

## Running Tests

- To run all tests:
   ```bash
   mvn test
- To run specific TestNG test profile with browser of choice:
    ```bash
   mvn test -p`ProfileName` -Dbrowser=`browserName`
here profileName could be **`e2e`** or **`Regression`** and browserName could be **`chrome`**, **`firefox`** or **`edge`**
- To run Cucumber tests:
    ```bash
   mvn test -pCucumberTests -Dbrowser=`browserName`

## Contribution and feedback are welcomed

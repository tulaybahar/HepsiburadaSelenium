# HepsiburadaSelenium

**Description**
This project is a test automation suite for the Hepsiburada website. It uses Selenium WebDriver for browser automation, Java as the programming language,an d IntelliJ for code editor and TestNG as the testing framework. The project is structured based on the Page Object Model (POM) design pattern to enhance code readability and maintainability.
Under test/.../pomdesignpattern you will find my code files.



**Project Structure:**
**base:** Contains base classes and utilities used across the project.
BasePage.java: The base class for all page objects, providing common methods and properties.
BaseTest.java: The base class for all test classes, handling setup and teardown processes.

**pages:** Contains page object classes representing different pages of the Hepsiburada website.
CommentPage.java: Represents the comment page of the website.
ProductSearchPage.java: Represents the search product results page.
SellerPage.java: Represents the other seller details page.
CartPage.java:Represents the other cart details page.

**tests:** Contains test classes where the actual test cases are written.
CoreTest.java: The main test runner class used to execute the test suite.


**Running the Tests:**
You can run the tests using the CoreTest class. Ensure that all dependencies are correctly configured in your build tool (Maven). Execute the tests by running the CoreTest class from your IDE or command line.

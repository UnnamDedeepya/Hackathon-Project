# Final Hackathon Project - E-Commerce & Entertainment Automation Framework

This repository houses the final training capstone project, transitioning from hybrid automation structures to an advanced, industry-standard **Behavior-Driven Development (BDD)** test framework. The framework is engineered using **Selenium WebDriver**, **Cucumber JVM**, and **TestNG** to address multi-page functional flows over complex web ecosystems like **District** (https://www.district.in/).

---

## 🎯 Problem Statement & Scope

The core objective is to execute an end-to-end automation suite for city-specific event parsing, metadata collection, and exception validations.

### 📋 Detailed Test Scenarios Automated:
1. **Weekend Sports Activities Filter:**
   * Navigate to the target web platform (**District**).
   * Extract all sports activities scheduled for the **upcoming weekend** in the selected city.
   * Sort and display the names, dates, and pricing details of the activities with the **lowest charges highlighted on top**.
2. **Movie Metadata Extraction:**
   * Traverse the entertainment/movie sections.
   * Dynamically fetch all available languages for currently running movies and archive them cleanly into a programmatic Java `List` structure for console reporting.
3. **Invalid Authentication Validation:**
   * Invoke the Google Sign-In pipeline via authentication windows/frames.
   * Inject intentionally invalid account credentials to capture and validate structural error/warning text patterns.

### 🔍 Key Automation Capabilities Handled:
* Seamless switching across system alerts, multi-layered inline **iFrames**, and dynamic window handles.
* Complete page DOM traversing, dynamic collection storage, layout scrolling, and nested menu navigation.

---

## ⚙️ Core Technical Concepts Used

* **Design Pattern:** Page Object Model (POM) combined with Behavior-Driven Development (BDD).
* **Behavior Specification:** Cucumber Gherkin Feature Files mapping natural language assertions to executable step definitions.
* **Execution Engine:** TestNG configured for **Parallel Test Execution** to drastically optimize test pipeline velocity.
* **Component Hooks:** Structural `@Before` and `@After` Cucumber Hooks managing clean setup environments and safe WebDriver teardown routines.
* **Infrastructure Management:** Dynamic WebDriver Management automatically handling variable binary architectures.
* **Logging Ecosystem:** Integrated **Log4j2** framework yielding fine-grained runtime application logs for robust execution auditing.
* **Build tool:** Apache Maven handling comprehensive dependency resolution and phase lifecycles.

---

## 📂 Project Directory Architecture

The framework utilizes a dedicated testing structure as mapped below:

```text
new/
│
├── src/test/
│   ├── java/                         # Functional Test Execution Packages
│   │   ├── pages/                    # Encapsulated Page Objects and Web Elements
│   │   ├── runners/                  # TestNG Cucumber Runner classes orchestrating suites
│   │   ├── steps/                    # Step Definitions implementing Gherkin sentences
│   │   │   └── Hooks.java            # Execution lifecycle initializers & teardowns
│   │   ├── tests/                    # Complementary or core assertions
│   │   └── utils/                    # Shared utilities (Log4j2 helpers, Config readers)
│   │
│   └── resources/                    # Test Assets
│       ├── features/                 # .feature specification files written in Gherkin
│       └── log4j2.xml                # Logging configurations and append properties
│
├── target/                           # Compiled classes and built artifacts
├── test-output/                      # Comprehensive TestNG & Cucumber execution reports
├── testng.xml                        # Master suite execution config (Parallel configurations)
└── pom.xml                           # Central Maven dependencies build configuration
# Playwright Java Test Framework

## Setup Steps

### 1. Install Playwright Browsers
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

### 2. Run Tests
```bash
mvn clean test
```

### 3. Run Specific Test
```bash
mvn test -Dtest=SampleTest
```

## Project Structure
```
playwright-test-framework/
├── src/
│   ├── main/java/          # Framework code
│   └── test/java/          # Test classes
├── learning/               # Reference documentation
├── test-results/           # Screenshots and videos
└── pom.xml
```

## Features
- ✅ TestNG integration
- ✅ Page Object Model ready
- ✅ Screenshot capture
- ✅ Browser context isolation
- ✅ Reusable base test class
- ✅ Java 21 support

## Useful Commands
- `mvn compile` - Compile the project
- `mvn test` - Run all tests
- `mvn clean` - Clean build artifacts
- `mvn test -Dtest=TestClassName` - Run specific test

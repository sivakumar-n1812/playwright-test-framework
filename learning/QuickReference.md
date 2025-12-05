# Playwright Browser Interactions - Quick Reference

## Browser Launch
```java
// Launch browser
Browser browser = playwright.chromium().launch();
Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
    .setHeadless(false)
    .setSlowMo(100));
```

## Navigation
```java
page.navigate("https://example.com");
page.goBack();
page.goForward();
page.reload();
```

## Element Interactions
```java
// Click
page.click("button");
page.locator("button").click();

// Fill input
page.fill("#username", "john");

// Type with delay
page.type("#input", "text");

// Select dropdown
page.selectOption("select#country", "USA");

// Checkbox
page.check("#terms");
page.uncheck("#terms");
```

## Locators
```java
// CSS Selectors
page.locator("#id");
page.locator(".class");
page.locator("[data-testid='btn']");

// Text-based
page.getByText("Login");
page.getByRole(AriaRole.BUTTON);
page.getByLabel("Email");
page.getByPlaceholder("Enter email");
```

## Waiting
```java
page.waitForSelector("button");
page.waitForLoadState();
page.waitForURL("**/dashboard");
```

## Assertions
```java
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

assertThat(page.locator("h1")).isVisible();
assertThat(page.locator("h1")).containsText("Welcome");
assertThat(page).hasTitle("Home");
assertThat(page).hasURL("https://example.com");
```

## Screenshots
```java
page.screenshot(new Page.ScreenshotOptions()
    .setPath(Paths.get("screenshot.png"))
    .setFullPage(true));
```

For complete reference, see: https://playwright.dev/java/docs/intro

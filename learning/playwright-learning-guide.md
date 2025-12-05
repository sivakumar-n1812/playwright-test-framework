# Playwright Browser Interaction Methods - Complete Learning Guide

## Table of Contents
1. [Browser & Context Methods](#browser--context-methods)
2. [Navigation Methods](#navigation-methods)
3. [Element Interaction Methods](#element-interaction-methods)
4. [Locator Strategies](#locator-strategies)
5. [Wait Strategies](#wait-strategies)
6. [Assertion Methods](#assertion-methods)
7. [Advanced Interactions](#advanced-interactions)

---

## 1. Browser & Context Methods

### Creating Playwright Instance
```java
// Create Playwright instance
Playwright playwright = Playwright.create();

// Close Playwright
playwright.close();
```

### Launching Browsers
```java
// Launch Chromium
Browser browser = playwright.chromium().launch();

// Launch Firefox
Browser browser = playwright.firefox().launch();

// Launch WebKit (Safari)
Browser browser = playwright.webkit().launch();

// Launch with options
Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
    .setHeadless(false)           // Run in headed mode
    .setSlowMo(100)                // Slow down by 100ms
    .setDevtools(true)             // Open devtools
    .setChannel("chrome"));        // Use Chrome instead of Chromium
```

### Browser Context
```java
// Create new context (isolated session)
BrowserContext context = browser.newContext();

// Create context with options
BrowserContext context = browser.newContext(new Browser.NewContextOptions()
    .setViewportSize(1920, 1080)
    .setUserAgent("Custom User Agent")
    .setLocale("en-US")
    .setTimezoneId("America/New_York")
    .setGeolocation(new Geolocation(37.774929, -122.419416))
    .setPermissions(Arrays.asList("geolocation"))
    .setColorScheme(ColorScheme.DARK)
    .setOffline(true)              // Simulate offline mode
    .setHttpCredentials("username", "password"));

// Close context
context.close();
```

### Creating Pages
```java
// Create new page
Page page = context.newPage();

// Create page directly from browser
Page page = browser.newPage();

// Get all pages in context
List<Page> pages = context.pages();

// Close page
page.close();
```

### Multiple Browser Contexts
```java
// Create isolated contexts for different users
BrowserContext adminContext = browser.newContext();
BrowserContext userContext = browser.newContext();

Page adminPage = adminContext.newPage();
Page userPage = userContext.newPage();

// Each context has isolated cookies, storage, etc.
```

---

## 2. Navigation Methods

### Basic Navigation
```java
// Navigate to URL
page.navigate("https://example.com");

// Navigate with options
page.navigate("https://example.com", new Page.NavigateOptions()
    .setTimeout(30000)
    .setWaitUntil(WaitUntilState.NETWORKIDLE));

// Go back
page.goBack();

// Go forward
page.goForward();

// Reload page
page.reload();

// Reload with options
page.reload(new Page.ReloadOptions()
    .setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
```

### Wait for Navigation
```java
// Wait for URL pattern
page.waitForURL("**/dashboard");
page.waitForURL("https://example.com/products/**");

// Wait for load state
page.waitForLoadState();                          // Default: load
page.waitForLoadState(LoadState.DOMCONTENTLOADED);
page.waitForLoadState(LoadState.NETWORKIDLE);

// Wait for specific URL after action
page.waitForURL("**/login", () -> {
    page.click("text=Login");
});
```

### URL Information
```java
// Get current URL
String url = page.url();

// Get page title
String title = page.title();

// Get page content
String content = page.content();
```

---

## 3. Element Interaction Methods

### Finding Elements

#### CSS Selectors
```java
// By ID
page.locator("#username");

// By Class
page.locator(".login-button");

// By Attribute
page.locator("[type='submit']");
page.locator("[data-testid='login-btn']");

// Complex selectors
page.locator("div.container > button.primary");
page.locator("input[name='email']:visible");
```

#### Text-Based Locators
```java
// Exact text
page.getByText("Login");

// Partial text
page.getByText("Log", new Page.GetByTextOptions().setExact(false));

// By role (Accessibility)
page.getByRole(AriaRole.BUTTON);
page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"));

// By label
page.getByLabel("Email address");
page.getByLabel("Password");

// By placeholder
page.getByPlaceholder("Enter your email");

// By alt text
page.getByAltText("Company logo");

// By title
page.getByTitle("Close dialog");

// By test ID
page.getByTestId("login-button");
```

### Clicking Elements
```java
// Simple click
page.click("button");

// Click with options
page.click("button", new Page.ClickOptions()
    .setButton(MouseButton.RIGHT)        // Right click
    .setClickCount(2)                    // Double click
    .setDelay(100)                       // Delay between mousedown and mouseup
    .setPosition(10, 20)                 // Click at specific position
    .setModifiers(Arrays.asList(KeyboardModifier.SHIFT))
    .setForce(true)                      // Force click even if element is not ready
    .setTimeout(5000));

// Double click
page.dblclick("button");

// Right click
page.click("button", new Page.ClickOptions().setButton(MouseButton.RIGHT));

// Click using locator
page.locator("text=Submit").click();
```

### Typing and Filling
```java
// Fill input (clears first)
page.fill("#username", "john.doe");

// Type (character by character)
page.type("#username", "john.doe");

// Type with delay
page.type("#username", "john.doe", new Page.TypeOptions().setDelay(100));

// Press key
page.press("#input", "Enter");
page.press("#input", "Control+A");
page.press("#input", "Backspace");

// Clear input
page.fill("#username", "");

// Using locator
page.locator("#email").fill("test@example.com");
```

### Checkbox and Radio
```java
// Check checkbox
page.check("#terms");

// Uncheck checkbox
page.uncheck("#terms");

// Toggle checkbox
page.setChecked("#terms", true);
page.setChecked("#terms", false);

// Check if checked
boolean isChecked = page.isChecked("#terms");

// Check radio button
page.check("input[value='male']");
```

### Dropdowns
```java
// Select by value
page.selectOption("select#country", "USA");

// Select by label
page.selectOption("select#country", new SelectOption().setLabel("United States"));

// Select by index
page.selectOption("select#country", new SelectOption().setIndex(2));

// Select multiple options
page.selectOption("select#skills", new String[]{"java", "python", "javascript"});

// Get selected value
String value = page.inputValue("select#country");
```

### Hover
```java
// Hover over element
page.hover("button");

// Hover with options
page.hover("button", new Page.HoverOptions()
    .setPosition(10, 20)
    .setForce(true));
```

### Focus
```java
// Focus element
page.focus("#username");

// Blur element
page.evaluate("document.querySelector('#username').blur()");
```

### Drag and Drop
```java
// Drag and drop
page.dragAndDrop("#source", "#target");

// With options
page.dragAndDrop("#source", "#target", new Page.DragAndDropOptions()
    .setSourcePosition(10, 10)
    .setTargetPosition(20, 20));
```

---

## 4. Locator Strategies

### Chaining Locators
```java
// Chain locators
page.locator(".container")
    .locator("button")
    .first()
    .click();

// Filter by text
page.locator("button")
    .filter(new Locator.FilterOptions().setHasText("Submit"))
    .click();

// Filter by another locator
page.locator("article")
    .filter(new Locator.FilterOptions()
        .setHas(page.locator(".highlight")))
    .first();
```

### Multiple Elements
```java
// Get all matching elements
Locator buttons = page.locator("button");

// Count elements
int count = buttons.count();

// Iterate through elements
for (int i = 0; i < buttons.count(); i++) {
    System.out.println(buttons.nth(i).textContent());
}

// Get first/last element
buttons.first().click();
buttons.last().click();
buttons.nth(2).click();  // Third element (0-indexed)
```

### Element State
```java
// Check if element is visible
boolean isVisible = page.isVisible("button");
page.locator("button").isVisible();

// Check if element is hidden
boolean isHidden = page.isHidden("button");

// Check if element is enabled
boolean isEnabled = page.isEnabled("button");

// Check if element is disabled
boolean isDisabled = page.isDisabled("button");

// Check if element is editable
boolean isEditable = page.isEditable("input");
```

### Getting Element Info
```java
// Get text content
String text = page.textContent("h1");
String text = page.locator("h1").textContent();

// Get inner text
String innerText = page.locator("div").innerText();

// Get inner HTML
String html = page.locator("div").innerHTML();

// Get attribute
String href = page.getAttribute("a", "href");
String classAttr = page.locator("button").getAttribute("class");

// Get input value
String value = page.inputValue("#email");

// Get all text contents
List<String> texts = page.locator("li").allTextContents();

// Get all inner texts
List<String> innerTexts = page.locator("li").allInnerTexts();
```

---

## 5. Wait Strategies

### Explicit Waits
```java
// Wait for selector
page.waitForSelector("button");
page.waitForSelector("button", new Page.WaitForSelectorOptions()
    .setState(WaitForSelectorState.VISIBLE)
    .setTimeout(5000));

// Wait for element to be visible
page.locator("button").waitFor(new Locator.WaitForOptions()
    .setState(WaitForSelectorState.VISIBLE));

// Wait for element to be hidden
page.locator(".loader").waitFor(new Locator.WaitForOptions()
    .setState(WaitForSelectorState.HIDDEN));

// Wait for element to be attached
page.locator("div").waitFor(new Locator.WaitForOptions()
    .setState(WaitForSelectorState.ATTACHED));
```

### Wait for Events
```java
// Wait for response
Response response = page.waitForResponse("**/api/users", () -> {
    page.click("button#loadUsers");
});

// Wait for request
Request request = page.waitForRequest("**/api/login", () -> {
    page.click("button#login");
});

// Wait for popup
Page popup = page.waitForPopup(() -> {
    page.click("a[target='_blank']");
});

// Wait for download
Download download = page.waitForDownload(() -> {
    page.click("a#downloadLink");
});

// Wait for console message
ConsoleMessage message = page.waitForConsoleMessage(() -> {
    page.click("button#log");
});
```

### Wait for Function
```java
// Wait for custom condition
page.waitForFunction("() => document.querySelector('.item').length > 5");

// With argument
page.waitForFunction("count => document.querySelectorAll('.item').length >= count", 10);

// Wait with polling
page.waitForFunction("() => window.loadComplete === true", null,
    new Page.WaitForFunctionOptions().setPollingInterval(100));
```

### Timeouts
```java
// Set default timeout for page
page.setDefaultTimeout(60000);  // 60 seconds

// Set navigation timeout
page.setDefaultNavigationTimeout(30000);

// Action with custom timeout
page.click("button", new Page.ClickOptions().setTimeout(5000));
page.waitForSelector("div", new Page.WaitForSelectorOptions().setTimeout(10000));
```

---

## 6. Assertion Methods

### Built-in Assertions
```java
import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;

// Assert element is visible
assertThat(page.locator("button")).isVisible();

// Assert element is hidden
assertThat(page.locator(".loader")).isHidden();

// Assert element contains text
assertThat(page.locator("h1")).containsText("Welcome");
assertThat(page.locator("h1")).hasText("Welcome to our site");

// Assert element has attribute
assertThat(page.locator("a")).hasAttribute("href", "https://example.com");

// Assert element has class
assertThat(page.locator("button")).hasClass("btn-primary");

// Assert element has value
assertThat(page.locator("input")).hasValue("john@example.com");

// Assert element count
assertThat(page.locator("li")).hasCount(5);

// Assert page title
assertThat(page).hasTitle("Home Page");
assertThat(page).hasTitle(java.util.regex.Pattern.compile(".*Home.*"));

// Assert page URL
assertThat(page).hasURL("https://example.com/dashboard");
assertThat(page).hasURL(java.util.regex.Pattern.compile(".*/dashboard"));

// Assert element is enabled/disabled
assertThat(page.locator("button")).isEnabled();
assertThat(page.locator("button")).isDisabled();

// Assert element is editable
assertThat(page.locator("input")).isEditable();

// Assert element is checked
assertThat(page.locator("#terms")).isChecked();
```

### TestNG Assertions
```java
import static org.testng.Assert.*;

// Assert equals
assertEquals(page.title(), "Expected Title");
assertEquals(page.locator("h1").textContent(), "Welcome");

// Assert true/false
assertTrue(page.isVisible("button"));
assertFalse(page.isHidden("div"));

// Assert contains
assertTrue(page.url().contains("/dashboard"));
assertTrue(page.textContent("p").contains("success"));

// Assert not null
assertNotNull(page.locator("div").textContent());
```

---

## 7. Advanced Interactions

### Frames and IFrames
```java
// Get frame by name
Frame frame = page.frame("frameName");

// Get frame by URL
Frame frame = page.frameByUrl("https://example.com/frame");

// Get frame by selector
Frame frame = page.frameLocator("iframe#myframe").owner();

// Interact with elements in frame
page.frameLocator("iframe").locator("button").click();

// Get all frames
List<Frame> frames = page.frames();
```

### File Upload
```java
// Upload single file
page.setInputFiles("input[type='file']", Paths.get("file.pdf"));

// Upload multiple files
page.setInputFiles("input[type='file']", new Path[]{
    Paths.get("file1.pdf"),
    Paths.get("file2.pdf")
});

// Clear file input
page.setInputFiles("input[type='file']", new Path[]{});
```

### Screenshots
```java
// Full page screenshot
page.screenshot(new Page.ScreenshotOptions()
    .setPath(Paths.get("screenshot.png"))
    .setFullPage(true));

// Element screenshot
page.locator("div").screenshot(new Locator.ScreenshotOptions()
    .setPath(Paths.get("element.png")));

// Screenshot to byte array
byte[] screenshot = page.screenshot();
```

### PDF Generation
```java
// Generate PDF (Chromium only)
page.pdf(new Page.PdfOptions()
    .setPath(Paths.get("page.pdf"))
    .setFormat("A4")
    .setMargin(new Margin().setTop("1cm").setBottom("1cm"))
    .setPrintBackground(true));
```

### JavaScript Execution
```java
// Execute JavaScript
Object result = page.evaluate("() => document.title");
String title = (String) page.evaluate("() => document.title");

// Execute with arguments
int sum = (int) page.evaluate("(a, b) => a + b", 5, 3);

// Execute on element
page.locator("button").evaluate("el => el.textContent");

// Expose function to page
page.exposeFunction("multiply", args -> {
    return (int) args[0] * (int) args[1];
});
```

### Network Interception
```java
// Route requests
page.route("**/api/users", route -> {
    route.fulfill(new Route.FulfillOptions()
        .setStatus(200)
        .setBody("{\"users\": []}"));
});

// Abort requests
page.route("**/*.png", route -> route.abort());

// Continue with modifications
page.route("**/api/login", route -> {
    route.continue_(new Route.ContinueOptions()
        .setHeaders(Map.of("Authorization", "Bearer token")));
});

// Listen to requests
page.onRequest(request -> {
    System.out.println(">> " + request.method() + " " + request.url());
});

// Listen to responses
page.onResponse(response -> {
    System.out.println("<< " + response.status() + " " + response.url());
});
```

### Dialog Handling
```java
// Handle alerts
page.onDialog(dialog -> {
    System.out.println("Dialog message: " + dialog.message());
    dialog.accept();  // or dialog.dismiss();
});

// Handle prompt with input
page.onDialog(dialog -> {
    dialog.accept("User input text");
});

// Handle confirm
page.onDialog(dialog -> {
    if (dialog.type() == Dialog.Type.CONFIRM) {
        dialog.accept();
    }
});
```

### Emulation
```java
// Device emulation
BrowserContext context = browser.newContext(new Browser.NewContextOptions()
    .setDeviceScaleFactor(2)
    .setViewportSize(375, 667)
    .setUserAgent("Mozilla/5.0 (iPhone...)"));

// Geolocation
context.setGeolocation(new Geolocation(37.774929, -122.419416));

// Timezone
context = browser.newContext(new Browser.NewContextOptions()
    .setTimezoneId("Europe/Paris"));

// Locale
context = browser.newContext(new Browser.NewContextOptions()
    .setLocale("de-DE"));

// Color scheme
context = browser.newContext(new Browser.NewContextOptions()
    .setColorScheme(ColorScheme.DARK));
```

### Storage State (Cookies & Local Storage)
```java
// Save storage state
context.storageState(new BrowserContext.StorageStateOptions()
    .setPath(Paths.get("state.json")));

// Load storage state
BrowserContext context = browser.newContext(new Browser.NewContextOptions()
    .setStorageStatePath(Paths.get("state.json")));

// Add cookies
context.addCookies(Arrays.asList(
    new Cookie("session", "abc123")
        .setDomain("example.com")
        .setPath("/")
));

// Get cookies
List<Cookie> cookies = context.cookies();
List<Cookie> cookies = context.cookies("https://example.com");
```

---

## Best Practices

1. **Use Auto-Waiting**: Playwright automatically waits for elements to be ready
2. **Prefer User-Facing Locators**: Use `getByRole()`, `getByText()`, `getByLabel()`
3. **Create Page Object Models**: Organize locators and actions in page classes
4. **Use Browser Contexts**: Isolate tests with separate contexts
5. **Take Screenshots**: Capture screenshots on failures for debugging
6. **Handle Dialogs**: Always handle alerts/confirms to prevent test hangs
7. **Close Resources**: Always close pages, contexts, browsers, and Playwright
8. **Use Test Hooks**: Utilize `@BeforeMethod` and `@AfterMethod` for setup/teardown

---

## Common Patterns

### Login and Save State
```java
// Login once and save state
BrowserContext context = browser.newContext();
Page page = context.newPage();
page.navigate("https://example.com/login");
page.fill("#username", "user");
page.fill("#password", "pass");
page.click("button[type='submit']");
page.waitForURL("**/dashboard");

// Save authenticated state
context.storageState(new BrowserContext.StorageStateOptions()
    .setPath(Paths.get("auth.json")));
context.close();

// Reuse state in tests
BrowserContext authContext = browser.newContext(new Browser.NewContextOptions()
    .setStorageStatePath(Paths.get("auth.json")));
Page authPage = authContext.newPage();
authPage.navigate("https://example.com/dashboard");  // Already logged in!
```

### Retry on Failure
```java
// Retry action
for (int i = 0; i < 3; i++) {
    try {
        page.click("button", new Page.ClickOptions().setTimeout(2000));
        break;
    } catch (TimeoutError e) {
        if (i == 2) throw e;
        page.reload();
    }
}
```

---

This guide covers the essential Playwright Java methods. Refer to the [official documentation](https://playwright.dev/java/docs/intro) for more advanced features!
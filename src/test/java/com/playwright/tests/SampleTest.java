package com.playwright.tests;

import com.microsoft.playwright.Page;
import com.playwright.framework.base.BaseTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SampleTest extends BaseTest {

    @Test(priority = 1, description = "Verify page title")
    public void testPageTitle() {
        page.navigate("https://playwright.dev");
        
        // Wait for page to load
        page.waitForLoadState();
        
        // Get page title
        String title = page.title();
        System.out.println("Page Title: " + title);
        
        // Assertion
        assertTrue(title.contains("Playwright"), "Page title should contain 'Playwright'");
        
        // Take screenshot
        takeScreenshot("homepage");
    }

//    @Test(priority = 2, description = "Test navigation and button click")
//    public void testNavigation() {
//        page.navigate("https://playwright.dev");
//
//        // Click on "Get Started" button using text
//        page.getByRole(com.microsoft.playwright.options.AriaRole.LINK,
//            new Page.GetByRoleOptions().setName("Get started"))
//            .click();
//
//        // Wait for navigation
//        page.waitForURL("**/docs/intro");
//
//        // Verify URL
//        assertTrue(page.url().contains("/docs/intro"), "Should navigate to docs page");
//    }
//
//    @Test(priority = 3, description = "Test form interaction")
//    public void testFormInteraction() {
//        page.navigate("https://the-internet.herokuapp.com/login");
//
//        // Fill form
//        page.fill("#username", "tomsmith");
//        page.fill("#password", "SuperSecretPassword!");
//
//        // Click button
//        page.click("button[type='submit']");
//
//        // Wait for success message
//        page.waitForSelector(".flash.success");
//
//        // Verify login success
//        String successMsg = page.textContent(".flash.success");
//        assertTrue(successMsg.contains("You logged into a secure area!"));
//
//        takeScreenshot("login-success");
//    }
//
//    @Test(priority = 4, description = "Test dropdown and checkbox")
//    public void testDropdownAndCheckbox() {
//        page.navigate("https://the-internet.herokuapp.com/dropdown");
//
//        // Select from dropdown
//        page.selectOption("#dropdown", "2");
//
//        // Navigate to checkboxes
//        page.navigate("https://the-internet.herokuapp.com/checkboxes");
//
//        // Check first checkbox
//        page.check("input[type='checkbox']:nth-of-type(1)");
//
//        // Verify checkbox is checked
//        assertTrue(page.isChecked("input[type='checkbox']:nth-of-type(1)"));
//    }
//
//    @Test(priority = 5, description = "Test multiple tabs")
//    public void testMultipleTabs() {
//        page.navigate("https://the-internet.herokuapp.com/windows");
//
//        // Get current context
//        Page newPage = context.waitForPage(() -> {
//            page.click("a[href='/windows/new']");
//        });
//
//        // Wait for new page to load
//        newPage.waitForLoadState();
//
//        // Verify new page title
//        System.out.println("New Page Title: " + newPage.title());
//        assertTrue(newPage.title().contains("New Window"));
//
//        // Close new page
//        newPage.close();
//    }
}
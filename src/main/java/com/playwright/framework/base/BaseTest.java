package com.playwright.framework.base;

import com.microsoft.playwright.*;
import org.testng.annotations.*;
import java.nio.file.Paths;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeClass
    public void setupPlaywright() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(100));
    }

    @BeforeMethod
    public void createContextAndPage() {
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        page = context.newPage();
    }

    @AfterMethod
    public void closeContext() {
        if (context != null) {
            context.close();
        }
    }

    @AfterClass
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }

    protected void takeScreenshot(String filename) {
        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get("test-results/screenshots/" + filename + ".png"))
                .setFullPage(true));
    }
}

package com.playwright.framework.utils;

import com.microsoft.playwright.*;

public class BrowserFactory {
    
    public static Browser createBrowser(Playwright playwright, String browserType) {
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(50);
        
        return switch (browserType.toLowerCase()) {
            case "chrome", "chromium" -> playwright.chromium().launch(options);
            case "firefox" -> playwright.firefox().launch(options);
            case "webkit", "safari" -> playwright.webkit().launch(options);
            default -> playwright.chromium().launch(options);
        };
    }
    
    public static BrowserContext createContext(Browser browser) {
        return browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080)
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36"));
    }
}
package com.coveros.testsuite;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class exercise {

    private static final Logger LOGGER = LoggerFactory.getLogger(exercise.class);
    private static WebDriver driver;

    @BeforeClass
    public static void initialize() throws Exception {
        System.setProperty("webdriver.chrome.driver", Paths.get("src", "test", "resources", "chromedriver.exe").toString());
        driver = new ChromeDriver();
    }

    @Test
    public void findTheBrokenLink() throws Exception {

        // Load Page
        driver.get("http://te.dev.secureci.com/Exercise1.html");

        // Find Anchor Elements
        List<WebElement> elements = driver.findElements(By.tagName("area"));

        LOGGER.info("Found: {}", elements.size());

        // Check Each Anchor
        for (WebElement element : elements) {

            String href = element.getAttribute("href");

            if (href != null && !href.isEmpty()) {
                // Check Href
                HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
                connection.connect();

                // Valid?
                if (connection.getResponseCode() > 400) {
                    // No; Print warning
                    LOGGER.warn("Broken Link: {}", href);
                }
            }

        }

    }

    @Test
    public void flipAllTheSwitches() throws Exception {
        // Load Page
        driver.get("http://te.dev.secureci.com/Exercise2.html");

        // Find Switches
        List<WebElement> elements = driver.findElements(By.className("slider"));

        LOGGER.info("Found: {}", elements.size());

        // Check Each Anchor
        for (WebElement element : elements) {
            element.click();
        }
    }

    @Test
    public void findThoseBugs() throws Exception {

    }

    @Test
    public void crackThatLogin() throws Exception {

    }

    @AfterClass
    public static void cleanup() {
        driver.quit();
    }

}

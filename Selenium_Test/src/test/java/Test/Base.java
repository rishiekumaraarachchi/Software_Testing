package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {
    public static ChromeOptions options;
    public static WebDriver webDriver;

    @BeforeTest
    void setup() {
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\rishi\\Downloads\\Selenium_Test\\src\\test\\resources\\chromedriver.exe");
        webDriver = new ChromeDriver(options);
        webDriver.get("https://academybugs.com/find-bugs/#");
    }

    @AfterTest
//    void tearDown() {
//        if (webDriver != null) {
//            webDriver.quit();
//        }
//    }

    public void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

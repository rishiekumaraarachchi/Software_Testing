package Test;

import org.testng.Assert;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Cart extends  Base{
    @Test
    void checkCartCount() {
        webDriver.findElement(By.id("ec_add_to_cart_5")).click();

        webDriver.get("https://academybugs.com/my-cart/");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement increaseButton = webDriver.findElement(By.cssSelector("input.ec_plus"));
        increaseButton.click();
        increaseButton.click();
        WebElement updateButton = webDriver.findElement(By.cssSelector("div.ec_cartitem_update_button"));
        updateButton.click();

        pause(5000);
        String expectedValue = "3";

        try {
            WebElement element = webDriver.findElement(By.className("ec_quantity"));
            String text = element.getAttribute("value");
            System.out.println("Element text: " + text);
            Assert.assertEquals(expectedValue, text);
        } catch (NoSuchElementException e) {
            System.err.println("Element not found: " + e.getMessage());
        } catch (TimeoutException e) {
            System.err.println("Timed out : " + e.getMessage());
        }
    }
}

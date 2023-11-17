package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class Items extends Base {
    String initialXPath = "//*[@id='ec_product_page']/div[1]/span[1]/a[2]";
    String triggeringXPath = "//*[@id='ec_product_page']/div[1]/span[1]/a[3]";
    String imageXPath = "//*[@id=\"ec_product_image_effect_4281370\"]/a";

    //Test for check view count change
    @Test(priority = 0)
    void testforChangeViewCount() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement elementBefore = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(initialXPath)));

        WebElement triggerElement = webDriver.findElement(By.xpath(triggeringXPath));
        triggerElement.click();

        WebElement elementAfter = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(triggeringXPath)));
        Assert.assertNotEquals(elementAfter, elementBefore, "XPath did not change as expected.");
    }

    //Test for check image width equals height for first images.
    @Test(priority = 1)
    void testCheckImageWidthEqualsHeight() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement image = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(imageXPath)));

        int imageWidth = image.getSize().getWidth();
        int imageHeight = image.getSize().getHeight();

        System.out.println("Image Dimensions: Width=" + imageWidth + ", Height=" + imageHeight);

        // Check if the width is equal to the height
        Assert.assertEquals(imageWidth, imageHeight, "Width of the image is not equal to height.");
    }
}

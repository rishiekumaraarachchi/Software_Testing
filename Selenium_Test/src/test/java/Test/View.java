package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class View extends Base {
    private final String firstElementXPath = "//*[@id=\"ec_product_image_4481370\"]/div[3]/h3/a";
    private final String commentXPath = "//*[@id=\"comment\"]";
    private final String emailXPath = "//*[@id=\"email\"]";
    private final String urlXPath = "//*[@id=\"url\"]";
    private final String submitButtonXPath = "//*[@id=\"academy-comment-submit\"]";
    private final String searchInputXPath = "//*[@id=\"ec_searchwidget-3\"]/div/form/input[1]";
    private final String searchButtonXPath = "//*[@id=\"ec_searchwidget-3\"]/div/form/input[2]";
    private final String resultElementXPath = "//*[@id=\"ec_product_image_4281370\"]/div[3]/h3/a";
    private final String currencyConversionXPath = "//*[@id=\"ec_currency_conversion\"]";
    private final String priceElementXPath = "//span[@class='ec_product_price ec_product_price_5']";
    private final String dnkLinkText = "DNK";

    // Test for change currency
    @Test(priority = 2)
    void testForChangeCurrency() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement firstElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstElementXPath)));
        firstElement.click();

        WebElement currencyConversion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(currencyConversionXPath)));

        // Change the currency to EUR
        Select currencySelect = new Select(currencyConversion);
        currencySelect.selectByValue("EUR");

        WebElement priceElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(priceElementXPath)));
        String priceText = priceElement.getText();

        Assert.assertTrue(priceText.contains("$"), "Price not changing to the currency EUR.");
    }

    // Test for check manufacturer
    @Test(priority = 3)
    void testForCheckManufacturer() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement firstElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstElementXPath)));
        firstElement.click();

        WebElement dnkLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(dnkLinkText)));
        dnkLink.click();

        // Check if the page contains the "404 Error" message.
        boolean is404ErrorPresent = webDriver.findElements(By.xpath("//h3[contains(text(), '404 Error')]")).size() > 0;

        // Perform the assertion based on the presence of the "404 Error" message.
        Assert.assertFalse(is404ErrorPresent, "Navigation to the page resulted in a 404 Error.");
    }

    // Test for post a comment
    @Test(priority = 4)
    void testStepsPostComment() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement firstElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstElementXPath)));
        firstElement.click();

        WebElement commentElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(commentXPath)));
        commentElement.sendKeys("Test Comment");

        WebElement emailElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailXPath)));
        emailElement.sendKeys("user@gmail.com");

        WebElement urlElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(urlXPath)));
        urlElement.sendKeys("www.test.com");

        // Click the submit button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(submitButtonXPath)));
        submitButton.click();
    }

    // Test for search for an item
    @Test(priority = 5)
    void testForSearchItem() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement firstElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstElementXPath)));
        firstElement.click();

        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(searchInputXPath)));
        searchInput.sendKeys("Dark Grey Jeans");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchButtonXPath)));
        searchButton.click();

        WebElement resultElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(resultElementXPath)));
        String resultText = resultElement.getText();

        String expectedText = "DARK GREY JEANS";
        Assert.assertTrue(resultText.equalsIgnoreCase(expectedText), "Search result does not match the expected value.");
    }
}

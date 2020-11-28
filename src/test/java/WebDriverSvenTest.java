import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class WebDriverSvenTest {

    private WebDriver driver;

    @BeforeMethod (alwaysRun = true)
    public void browserSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test (description = "searchResultIsNotEmpty")
    public void searchResultIsNotEmpty() {

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("http://www.sven.fi/ru/");
        WebElement searchInput = driver.findElement(By.xpath(".//input[@name='q']"));
        searchInput.sendKeys("sven ps-650");

        WebElement searchButton = driver.findElement(By.xpath(".//input[@name='s']"));
        searchButton.click();

        List<WebElement> searchResults =  driver.findElements(By.xpath(".//div[contains(@class,'views_search')]/*[(contains(@class,'row'))]"));
        Assert.assertTrue(searchResults.size() > 0, "Search result are empty!");
    }

    @AfterMethod (alwaysRun = true)
    public void browserClose() {
        driver.quit();
        driver = null;
    }

}

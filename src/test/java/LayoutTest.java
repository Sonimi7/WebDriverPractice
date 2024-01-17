import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LayoutTest {
    WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofDays(10));
        driver.manage().window().fullscreen();
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void twoTest() {
        driver.get("https://p.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        By img = By.cssSelector("[data-id=\"id-1\"]");
        getElement(img);

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("argument[0].scrollIntoView()", getElement(img));
        js.executeScript("argument[0].click()", getElement(img));

        WebElement imgBtn = driver.findElement(By.cssSelector("[data-id=\"id-1\"]"));
        new WebDriverWait(driver, Duration.ofDays(5))
                .until(ExpectedConditions.elementToBeClickable((By) img));
        imgBtn.click();
        //driver.findElement(By.xpath("//li[@data-id=\"id-1\"]")).click();
        WebElement element = driver.findElement(By.xpath("//div[@class='pp_content_container']"));

        boolean factResult = driver.findElement(By.cssSelector("div.pp_pic_holder.light_rounded")).isDisplayed();
        Assertions.assertTrue(factResult);

    }

    private WebElement getElement(By locator) {
        return new WebDriverWait(driver, Duration.ofDays(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

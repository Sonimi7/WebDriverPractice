import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AutorizationViewCookie {
    WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void startDriver() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofDays(5));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void viewCookie() {
        driver.get("https://otus.ru");
        autorization();

    }

    private void autorization() {
        driver.findElement(By.xpath("//*[@id=\"__next\"]//section/div[1]/button")).click();
        By emailInput = By.xpath("//input[@name]/..");

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("argument[0].click().sendKeys(\"sofa99999@yandex.ru\")", getElement(emailInput));

        //driver.findElement(By.xpath("//input[@name]/..")).sendKeys("sofa99999@yandex.ru");
        driver.findElement(By.xpath("//input[@type=\"password\"]")).sendKeys("Moskva76metropol_");
    }

    private WebElement getElement(By locator) {
        return new WebDriverWait(driver, Duration.ofDays(5))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}

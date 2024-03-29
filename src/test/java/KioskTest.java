import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.WaitTools;

import java.util.List;

public class KioskTest {
    private Logger logger = (Logger) LogManager.getLogger(KioskTest.class);
    private String layoutUrl = System.getProperty("layout.url");
    private WebDriver driver;
    private WaitTools waitTools;

    @BeforeEach
    public void init() {
        driver = new DriverFactory("--kiosk").create();
        waitTools = new WaitTools(driver);
        driver.get(layoutUrl);
        logger.info("Start driver");
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Stop driver");
    }

    @Test
    public void modalWindowOpen() {

        List<WebElement> imgList = driver.findElements(By.cssSelector("div.content-overlay"));

        logger.info("Image is visibility. Continue the test");
        waitTools.waitForCondition(ExpectedConditions.stalenessOf(imgList.get(0)));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView()", imgList.get(0));

        imgList.get(0).click();

        WebElement modalWindowEl = driver.findElement(By.cssSelector("div.pp_hoverContainer"));

        logger.info("Popup is visibility. Continue the test");
        waitTools.waitForCondition(ExpectedConditions.stalenessOf(modalWindowEl));

        boolean factResult = driver.findElement(By.cssSelector("div.pp_pic_holder.light_rounded")).isDisplayed();
        Assertions.assertTrue(factResult);
        logger.info("Test passed");

    }
}

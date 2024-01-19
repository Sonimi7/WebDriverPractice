import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.WaitTools;

public class HeadlessModeTest {
    Logger logger = (Logger) LogManager.getLogger(HeadlessModeTest.class);

    private String duckUrl = System.getProperty("duck.url");
    private WebDriver driver;
    private WaitTools waitTools;

    @BeforeEach
    public void init() {
        driver = new DriverFactory("--headless").create();
        waitTools = new WaitTools(driver);
        driver.get(duckUrl);
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
    public void searchResult() {
        logger.info("Enter data to search");
        driver.findElement(By.cssSelector("input[aria-autocomplete=\"both\"]"))
                .sendKeys("отус" + Keys.ENTER);

        WebElement firstLinkEl = driver
                .findElement(By.xpath("//span[text()='Онлайн‑курсы для профессионалов, дистанционное обучение современным ...']"));

        logger.info("Wait results search");
        waitTools.waitForCondition(ExpectedConditions.stalenessOf(firstLinkEl));

        logger.info("Get text first link");
        String factResult = firstLinkEl.getText();
        logger.info("Check text");
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", factResult);
    }

}

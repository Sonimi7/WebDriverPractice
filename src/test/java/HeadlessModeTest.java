import factory.DriverFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.WaitTools;

public class HeadlessModeTest {

    private String duckUrl = System.getProperty("duck.url");
    private WebDriver driver;
    private WaitTools waitTools;

    @BeforeEach
    public void init() {
        driver = new DriverFactory("--headless").create();
        waitTools = new WaitTools(driver);
        driver.get(duckUrl);
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void searchResult() {
        driver.findElement(By.cssSelector("input[aria-autocomplete=\"both\"]"))
                .sendKeys("отус" + Keys.ENTER);

        WebElement firstLinkEl = driver
                .findElement(By.xpath("//span[text()='Онлайн‑курсы для профессионалов, дистанционное обучение современным ...']"));

        waitTools.waitForCondition(ExpectedConditions.stalenessOf(firstLinkEl));

        String factResult = firstLinkEl.getText();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", factResult);
    }

}

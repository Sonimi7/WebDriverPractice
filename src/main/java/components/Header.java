package components;

import common.AbsCommon;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Header extends AbsCommon {

    private String telHeaderMarkerSelector = "section > a[href*='tel']";
    private String signInBtnLocator = "//button[text()='Войти']";

    private String iconUserSelector = "img[src*='blue-owl']";

//    private String personalAreaDropSelector = "div.sc-qjead8-0.ePtcIY.sc-f3yn2n-0.DnSJp";

    private String personalAreaElementDropSelector = "//*[contains(text(), \"Личный кабинет\")]";

    private String learningButtonLocator = "//span[text()='Обучение']";

    public Header(WebDriver driver) {
        super(driver);
    }

    public void waitMarkerTelNumber() {
        waitTools.waitElementPresent(By.cssSelector(telHeaderMarkerSelector));
    }

    public void waitSignInBtnIsPresent() {
        waitTools.waitElementPresent(By.xpath(signInBtnLocator));
    }

    public void waitSignInBtnToBeClicable() {
        waitTools.waitElementToBeClicable(By.xpath(signInBtnLocator));
    }
    public void clickSignInButton() {
        WebElement signInBtn = driver.findElement(By.xpath(signInBtnLocator));

        String authPopupSelector = "#__PORTAL__ > div";
        waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions
                        .visibilityOfElementLocated(By.cssSelector(authPopupSelector))));

        signInBtn.click();
    }

    public void checkLogoUser() {
        waitTools.waitForCondition(ExpectedConditions
                .presenceOfElementLocated(By.cssSelector(iconUserSelector)));
    }

    public void clickToUserIcon() {
        driver.findElement(By.cssSelector(iconUserSelector)).click();
    }

//    public void waitPersonalAreaDrop() {
//        waitTools.waitNotElementPresent(By.cssSelector(personalAreaDropSelector));
//    }

    public void clickPersonalArea() {
        driver.findElement(By
                .xpath("//div[@class='sc-r03h0s-5 sc-1youhxc-2 bYKNcH imWQF sc-1og4wiw-0-Component fgPsmr']"))
                .click();
        driver.findElement(By.xpath("//div/a[@href='https://otus.ru/lk/biography/personal']")).click();
    }

    public void moveLearningItemHeader() {
        Actions actions = new Actions(driver);
        WebElement itemLearningHeader = driver.findElement(By.cssSelector("span[title='Обучение']"));
        actions.moveToElement(itemLearningHeader).build().perform();
    }

    public void waitDropHeader() {
        waitTools.waitElementPresent(By.cssSelector("div.sc-piuiz2-0.fzHlJa"));
    }

    public void clickItemTeastingHeader() {
        WebElement el = driver.findElement(By.cssSelector("span[title='Обучение']"));

                JavascriptExecutor js = (JavascriptExecutor)driver;
                js.executeScript("arguments[0].click();", el);

        waitTools.waitElementPresent(By.cssSelector("a[href='https://otus.ru/categories/testing']"));
        driver.findElement(By.cssSelector("a[href='https://otus.ru/categories/testing']")).click();

    }
}

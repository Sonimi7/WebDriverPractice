package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DetailedCardCoursePage extends AbsBasePage {

    public DetailedCardCoursePage(WebDriver driver, String coursesPath) {
        super(driver, String.format("/lessons/%s", coursesPath));
    }

    @FindBy(xpath = "//h1")
    private WebElement titleCardCourse;

    @FindBy(xpath = "//div/following-sibling::p[contains(text(), 'месяц')]")
    private WebElement durationCourse;

    @FindBy(css = "div.sc-1og4wiw-0.sc-s2pydo-3.gaEufI.dZDxRw")
    private String descriptionCardCourseSelector;


    public void checkTitleCourse(String expectedResult) {
        String result = titleCardCourse.getText();
        Assertions.assertEquals(expectedResult, result);
    }

    private DetailedCardCoursePage checkDescriptionCourseIsNotEmpty() {
       Assertions.assertFalse($(descriptionCardCourseSelector).getText().isEmpty());
        return this;
    }

    private DetailedCardCoursePage checkDurationCourseIsNotEmpty() {
        Assertions.assertFalse($((By) durationCourse).getText().isEmpty());
        return this;
    }

    private DetailedCardCoursePage checkFormatCourse(String format) {
        String formatCourse = driver.findElement(By.xpath(String.format("//p[contains(text(), '%s')]", format))).getText();
        Assertions.assertEquals(formatCourse.isEmpty(), "Description courses empty");
        return this;
    }

    public DetailedCardCoursePage checkDetailsCardCourse(String format) {
        this.checkDescriptionCourseIsNotEmpty()
                .checkDurationCourseIsNotEmpty()
                .checkFormatCourse(format);

        return this;
    }

}

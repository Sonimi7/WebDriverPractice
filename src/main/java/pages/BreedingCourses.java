package pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class BreedingCourses extends AbsBasePage {

    public BreedingCourses(WebDriver driver) {
        super(driver, "/catalog/courses");
    }

    @FindBy(xpath = "//section//div[not(@style)]/a[contains(@href, '/lessons/')][.//h6]")
    private List<WebElement> cardsCourses;
    @FindBy(xpath = "//section//div[not(@style)]/a[contains(@href, '/lessons/')]/h6/following-sibling::div")
    private List<WebElement> courseDuration;

    public void cardsCoursesCountShouldBeSameAs(int count) {
        Assertions.assertEquals(
                count,
                cardsCourses.size(),
                String.format("Count cards courses should be $d", count)
        );
    }

    public void clickRandomCardCourses() {
        faker.options().nextElement(cardsCourses).click();
    }

    public int getCardsCount() {
        return cardsCourses.size();
    }

    public String getTitleCourseByIndex(int index) {
        return cardsCourses.get(--index).findElement(By.xpath(".//h6")).getText();
    }

    public String getCourseDuration(int index) {
        return courseDuration.get(--index).getText();
    }

    private Document getDomPage(int index) throws IOException {
        String url = cardsCourses.get(--index).getAttribute("href");
        return Jsoup.connect(url).get();
    }

    public void checkTitleCourseByIndex(int index, String expectedTitle) throws IOException {
        Document dom = getDomPage(index);
        Element titleCourserPageElement = dom.selectFirst("h1");

        Assertions.assertEquals(expectedTitle, titleCourserPageElement.text(), "Title courses does not match");
    }

    public void checkDescriptionCourseByIndex(int index) throws  IOException {
        Elements elements = getDomPage(index).selectXpath("//h1/following-sibling::div[text()]");
        if (elements.isEmpty()) {
            elements = getDomPage(index).selectXpath("//h1/following-sibling::div/p[text()]");
        }

        Element titleCourserPageElement = elements.get(0);

        Assertions.assertFalse(titleCourserPageElement.text().isEmpty(), "Description courses empty");
    }

    public void checkCourseDuration(int index, String expectedDuration) throws IOException {
        Element titleCourserPageElement = getDomPage(index)
                .selectXpath("//div/following-sibling::p[contains(text(), 'месяц')]")
                .get(0);

        Assertions.assertEquals(expectedDuration
                .replaceAll("^.*·\\s", ""),
                titleCourserPageElement.text(), "Duration courses does not match");
    }

    public void checkCourseFormat(int index, String format) throws  IOException {
        Element formatCourseElement = getDomPage(index)
                .selectXpath(String.format("//p[contains(text(), '%s')]", format))
                .get(0);

        Assertions.assertFalse(formatCourseElement.text().isEmpty(), "Description courses empty");
    }
}

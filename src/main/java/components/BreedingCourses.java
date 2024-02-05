package components;

import common.AbsCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BreedingCourses extends AbsCommon {

    public BreedingCourses(WebDriver driver) {
        super(driver);
    }

    private String listCardsCoursesLocator = "//div[@class='sc-18q05a6-1 bwGwUO']/descendant::a";

    public void waitListCardsCourses() {
        waitTools.waitElementPresent(By.xpath(listCardsCoursesLocator));
    }

    public int getListCardsCourses() {
        List<WebElement> elements = driver.findElements(By.xpath(listCardsCoursesLocator));
        int countCardCourses = elements.size();
        return countCardCourses;
    }

}

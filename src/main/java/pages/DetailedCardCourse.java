package pages;

import common.AbsCommon;
import org.openqa.selenium.WebDriver;

public class DetailedCardCourse extends AbsBasePage {

    public DetailedCardCourse(WebDriver driver, String coursesPath) {
        super(driver, String.format("/lessons/%s", coursesPath));
    }

    private String titleCardCourseSelector = "h1.sc-1og4wiw-0.sc-s2pydo-1.ifZfhS.diGrSa";

    private String descriptionCardCourseSelector = "div.sc-1og4wiw-0.sc-s2pydo-3.gaEufI.dZDxRw";

    private String timeStudyingSelector = "div.sc-i28ik1-0.bmVffP.sc-3cb1l3-5.fKJcHa";

    private String time = "//section//div[not(@style)]/a[contains(@href, '/lessons/')]/h6/following-sibling::div";

    public void checkTitleCourse() {

    }
}

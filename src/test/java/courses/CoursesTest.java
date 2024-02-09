package courses;

import pages.BreedingCourses;
import components.Header;
import data.cardsCourses.CoursesCategoryData;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.DetailedCardCourse;
import pages.MainPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CoursesTest {

    private BreedingCourses breedingCourses = null;

    private Logger logger = (Logger) LogManager.getLogger(CoursesTest.class);

    private WebDriver driver;

    @BeforeEach
    public void init() {
        this.driver = new DriverFactory().create();
        logger.info("Start driver");

        List<String> queryParams = new ArrayList<>();
        queryParams.add(String.format("categories=%s", CoursesCategoryData.TESTING.name().toLowerCase()));

        this.breedingCourses = new BreedingCourses(driver);
        breedingCourses.open(queryParams);
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Stop driver");
    }

    @Test
    public void checkCountCourses() {
        breedingCourses.cardsCoursesCountShouldBeSameAs(10);
    }

    @Test
    public void checkDataCardCourse() throws IOException {
        for(int i = 1; i< breedingCourses.getCardsCount(); i++) {
            String expectedTitle = breedingCourses.getTitleCourseByIndex(i);
            String expectedCourseDuration = breedingCourses.getCourseDuration(i);

            breedingCourses.checkTitleCourseByIndex(i, expectedTitle);
            breedingCourses.checkDescriptionCourseByIndex(i);
            breedingCourses.checkCourseDuration(i, expectedCourseDuration);
            breedingCourses.checkCourseFormat(i, "Онлайн");
        }
        /**вне цикла проверить клик
         *
        */
        breedingCourses.clickRandomCardCourses();
        DetailedCardCourse detailedCardCourse = new DetailedCardCourse(driver, "");
    }
}

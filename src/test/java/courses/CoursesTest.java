package courses;

import components.BreedingCourses;
import components.Header;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;

import java.util.List;

public class CoursesTest {

    private Logger logger = (Logger) LogManager.getLogger(CoursesTest.class);

    private WebDriver driver;

    @BeforeEach
    public void init() {
        this.driver = new DriverFactory().create();
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
    public void checkCountCourses() {
        new MainPage(driver).open("/");

        Header header = new Header(driver);
       // header.moveLearningItemHeader();
       // header.waitDropHeader();
        header.clickItemTeastingHeader();

        BreedingCourses courses = new BreedingCourses(driver);

        logger.info("Waiting presence cards courses");
        courses.waitListCardsCourses();

        logger.info("Check count cards courses and assert count");
        Assertions.assertEquals(courses.getListCardsCourses(), 10);
    }

    @Test
    public void checkInfDetailCardOfCourse() {
        new MainPage(driver).open("/lessons/qa-engineer/");

        Header header = new Header(driver);

    }
}

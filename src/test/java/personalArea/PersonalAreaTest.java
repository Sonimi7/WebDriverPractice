package personalArea;

import com.github.javafaker.Faker;
import components.Header;
import components.PersonalDataPage;
import components.popups.AuthPopups;
import data.cities.ICityData;
import data.cities.RussiaCityData;
import data.english.EnglishLevel;
import data.english.IEnglishLevel;
import data.fieldData.InputFieldData;
import data.workGraf.WorkGraf;
import factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonalAreaTest {

    private Logger logger = (Logger) LogManager.getLogger(PersonalAreaTest.class);

    private WebDriver driver;
    protected Faker faker = new Faker();

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
    public void savePersonalData() {
        new MainPage(driver).open("/");

        logger.info("Waiting marker tel");
        Header header = new Header(driver);
        header.waitMarkerTelNumber();
        header.waitSignInBtnIsPresent();
        header.waitSignInBtnToBeClicable();

        logger.info("Check status auth popup");
        AuthPopups authPopups = new AuthPopups(driver);
        authPopups.popupShouldNotBeVisible();

        logger.info("Start of test logic. Login in LK");
        header.clickSignInButton();
        authPopups.popupShouldBeVisible();

        authPopups.enterDataEmail();
        authPopups.enterDataPassword();
        authPopups.clickSignInBtnPopups();

        header.checkLogoUser();
        logger.info("Login in LK successful. Switch personal data page");
//        header.clickToUserIcon();
//        header.waitPersonalAreaDrop();
//        header.clickPersonalArea();
        header.clickPersonalArea();

        PersonalDataPage personalData = new PersonalDataPage(driver);

        personalData.clearFieldsData(InputFieldData.FNAME);
        personalData.addNewDataFields(InputFieldData.FNAME, faker.name().firstName());
        personalData.addNewDataFields(InputFieldData.FNAMELATIN, faker.name().lastName());
        personalData.addNewDataFields(InputFieldData.LNAME, faker.name().name());
        personalData.addNewDataFields(InputFieldData.LNAMELATIN, faker.name().name());
        personalData.addNewDataFields(InputFieldData.BLOGNAME, faker.name().name());
        personalData.addNewDataFields(InputFieldData.DATEOFBRTH,
                faker.date().birthday().toInstant().atZone(ZoneId.
                        systemDefault()).toLocalDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));

//        personalData.enterDataName();
//        personalData.enterDataNameLatin();
//        personalData.enterDataLastName();
//        personalData.enterDataLastNameLatin();
//        personalData.enterDataNameBlog();
//        personalData.enterDataDateOfBirth();

        ICityData[] cityData = RussiaCityData.values();
        ICityData city = faker.options().nextElement(cityData);

        personalData.selectCountry(city);
        personalData.selectCity(city);

//        IEnglishLevel[] englishLevel = EnglishLevel.values();
//        IEnglishLevel level = faker.options().nextElement(englishLevel);
//
        personalData.selectEnglishLevel(EnglishLevel.BEGINNER);

        personalData.switchRelocate(true);
        personalData.switchWorkFormat(true, WorkGraf.REMOTELY);

        personalData.clickAddCommunicationMethod();
        personalData.addContact();

        personalData.clickSavePersonalData();


   }
}

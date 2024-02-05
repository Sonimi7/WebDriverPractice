package components;

import common.AbsCommon;
import data.cities.ICityData;
import data.english.EnglishLevel;
import data.english.IEnglishLevel;
import data.fieldData.InputFieldData;
import data.workGraf.WorkGraf;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalDataPage extends AbsCommon {

    public PersonalDataPage(WebDriver driver) {
        super(driver);
    }

    private String inputCountrySelector = "input[name='country']";
    private String inputCitySelector = "input[name='city']";
    private String inputEnglishLevelSelector = "input[name='english_level']";
//    private String radioInputRelocateFalseSelector = "";
//    private String radioInputRelocateTrueSelector = "";
    private String checkBoxFullDaySelector = "input[title='Полный день']";
    private String checkBoxFlexibleSelector = "input[title='Гибкий график']";
    private String checkBoxRemoteSelector = "input[title='Удаленно']";
    private String inputEmailSelector = "input[id='id_email']";
    private String inputTelSelector = "input[id='id_phone']";

    public void clearFieldsData(InputFieldData... inputFieldData) {
        for (InputFieldData fieldData : inputFieldData) {
            driver.findElement(By.cssSelector(String.format("input[name='%s']", fieldData.getName()))).clear();
        }
    }

    public void addNewDataFields(InputFieldData inputFieldData, String data) {
        driver.findElement(By.cssSelector(String.format("input[name='%s']", inputFieldData.getName())))
                    .sendKeys(data);
    }

    public void enterDataName() {
        driver.findElement(By.cssSelector("input[name='fname']")).sendKeys("test");
    }

    public void enterDataLastName() {
        driver.findElement(By.cssSelector("input[name='lname']")).sendKeys("test");
    }

    public void enterDataNameLatin() {
        driver.findElement(By.cssSelector("input[name='fname_latin']")).sendKeys("test");
    }

    public void enterDataLastNameLatin() {
        driver.findElement(By.cssSelector("input[name='lname_latin']")).sendKeys("test");
    }

    public void enterDataNameBlog() {
        driver.findElement(By.cssSelector("input[name='blog_name']")).sendKeys("test");
    }

    public void enterDataDateOfBirth() {
        driver.findElement(By.cssSelector("input[name='date_of_birth']")).sendKeys("07.05.1992");
    }

    public void selectCountry(ICityData cityData) {
        WebElement russiaSelectElement = driver.findElement(By.cssSelector("[data-slave-selector='.js-lk-cv-dependent-slave-city']"));
        russiaSelectElement.click();

        WebElement countryListContainer = russiaSelectElement
                .findElement(By.xpath(".//*[contains(@class, 'js-custom-select-options-container')]"));
        waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions
                .attributeContains(countryListContainer, "class", "hide")));

        driver.findElement(By.cssSelector(String.format("[title='%s']",
                cityData.getCountriesData().getNameCountry()))).click();

        waitTools.waitForCondition(ExpectedConditions
                .attributeContains(countryListContainer, "class", "hide"));
    }

    public void selectCity(ICityData cityData) {
        WebElement moscowSelectElement = driver.findElement(By.xpath("//*[contains(@class, 'js-lk-cv-dependent-slave-city')]"));
        moscowSelectElement.click();

        WebElement cityListContainer = moscowSelectElement
                .findElement(By.xpath(".//*[contains(@class, 'js-custom-select-options-container')]"));
        waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions
                .attributeContains(cityListContainer, "class", "hide")));

        driver.findElement(By.cssSelector(String.format("[title='%s']", cityData.getName()))).click();

        waitTools.waitForCondition(ExpectedConditions.attributeContains(cityListContainer, "class", "hide"));
    }

    public void selectEnglishLevel(EnglishLevel englishLevel) {
        WebElement englishLevelSelectElement = driver.findElement(By
                .xpath("//input[@name='english_level']/ancestor:: div[contains(@class, 'js-lk-cv-custom-select')]"));
        englishLevelSelectElement.click();

        WebElement levelListContainer = englishLevelSelectElement
                .findElement(By.xpath(".//*[contains(@class, 'js-custom-select-options-container')]"));
        waitTools.waitForCondition(ExpectedConditions.not(ExpectedConditions
                .attributeContains(levelListContainer, "class", "hide")));

        driver.findElement(By.cssSelector(String.format("[title*='%s']", englishLevel.getEnglishLevel()))).click();
    }

    public void switchRelocate(boolean isSelected) {
        String relocate = isSelected ? "Да" : "Нет";
        driver.findElement(By.xpath(String.format("//span[@class=\"radio__label\" and text()=\"%s\"]", relocate))).click();
    }

    public void switchWorkFormat(boolean isSelected, WorkGraf... workGrafs) {
        for(WorkGraf workGraf : workGrafs) {
            WebElement inputSelect = driver.findElement(By.cssSelector(String.format("input[title='%s']", workGraf.getName())));
            if (inputSelect.isSelected() != isSelected) {
                inputSelect.click();
            }
        }
    }

    public void clickAddCommunicationMethod() {
        driver.findElement(By.cssSelector("button.js-lk-cv-custom-select-add")).click();
    }

    public void addContact() {
        driver.findElement(By.cssSelector("input[name=contact-2-value]")).sendKeys("test");
    }

    public void clickSavePersonalData() {
        driver.findElement(By.cssSelector("button[name='continue']")).click();
    }

}

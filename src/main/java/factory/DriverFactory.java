package factory;

import exceptions.BrowserNotSupportedException;
import factory.impl.ChromeDriverSettings;
import factory.impl.IDriverSettings;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

import java.util.ArrayList;

public class DriverFactory {

    private String browserName = System.getProperty("browser.name");
    private String[] arguments;
    public DriverFactory(String... arguments) {
        this.arguments = arguments;
    }

    public WebDriver create() {
        browserName = browserName.toLowerCase();

        switch (browserName) {
            case "chrome": {
                return new ChromeDriver((ChromeOptions)new ChromeDriverSettings().settings());
            }
        }
        throw new BrowserNotSupportedException(browserName);
    }
}

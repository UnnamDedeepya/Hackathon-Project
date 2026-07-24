package steps;

import factory.DriverFactory;
import io.cucumber.java.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.ConfigReader;

public class Hooks {

    @After
    public void afterScenario(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) return;

        // Screenshot
        byte[] png = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(png, "image/png", scenario.getName());

        // Close modal via ESC (twice for safety)
        try {
            Actions esc = new Actions(driver);
            esc.sendKeys(Keys.ESCAPE).perform();
            Thread.sleep(300);
            esc.sendKeys(Keys.ESCAPE).perform();
            Thread.sleep(300);
        } catch (Exception ignored) {}

        // Force clean reload
        try {
            driver.get(ConfigReader.get("url"));
            Thread.sleep(2000);   // let page settle
        } catch (Exception ignored) {}
    }
}
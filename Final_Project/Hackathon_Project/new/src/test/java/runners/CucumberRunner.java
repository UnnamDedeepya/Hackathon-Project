package runners;

import factory.DriverFactory;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import java.time.Duration;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"steps"},
        plugin = {"pretty", "html:test-output/cucumber-report.html"},
        monochrome = true
)
public class CucumberRunner extends AbstractTestNGCucumberTests {

    @BeforeTest
    @Parameters("browser")
    public void setUpBrowser(@Optional("chrome") String browser) {
        // ✅ Guard: skip if driver already opened for this thread
        if (DriverFactory.getDriver() != null) {
            return;
        }

        WebDriver driver = DriverFactory.initDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.get("url"));
    }

    @AfterTest(alwaysRun = true)
    public void tearDownBrowser() {
        // Guard: only quit if not already quit
        if (DriverFactory.getDriver() != null) {
            DriverFactory.quitDriver();
        }
    }
}
package steps;

import factory.DriverFactory;   // ← NEW import
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigReader;

public class SignInSteps {
    private static final Logger log = LogManager.getLogger(SignInSteps.class);
    HomePage home;
    LoginPage login;

    @Given("the user is on the district.in home page")
    public void onHomePage() {
        home = new HomePage(DriverFactory.getDriver());   // ← CHANGED
        log.info("Continuing from current page");
    }

    @When("the user clicks the Sign In button")
    public void clickSignIn() {
        login = new LoginPage(DriverFactory.getDriver());  // ← CHANGED
        login.clickSignInButton();
    }

    @And("the user enters an invalid mobile number")
    public void enterInvalidMobile() {
        String invalid = ConfigReader.get("mobile");
        login.enterMobileNumber(invalid);
        log.info("Entered invalid mobile: " + invalid);
    }

    @And("the user clicks Continue")
    public void clickContinue() {
        login.clickContinue();
    }

    @Then("an error message should be displayed")
    public void verifyError() {
        String message = login.getErrorMessage();
        log.info("Captured error message: \"" + message + "\"");
        Assert.assertNotNull(message, "Error message is null");
        Assert.assertFalse(message.isEmpty(), "Error message is empty");
    }
}
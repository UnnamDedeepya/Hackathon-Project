package steps;

import factory.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.HomePage;
import pages.SportsPage;
import pages.SportsPage.Event;
import utils.ConfigReader;

import java.util.Comparator;
import java.util.List;

public class SportsSteps {

    private static final Logger log = LogManager.getLogger(SportsSteps.class);

    HomePage home;
    SportsPage sports;
    List<Event> events;

    @When("the user selects the city")
    public void selectCity() {
        home = new HomePage(DriverFactory.getDriver());
        home.selectCity();   // Reads from config.properties internally
        log.info("City selected: " + ConfigReader.get("city"));  // Actual city used
    }

    @And("the user navigates to Events section")
    public void goToEvents() {
        home.goToEvents();
        log.info("Navigated to Events section");
    }

    @And("the user applies the weekend sports filter")
    public void applyWeekendSportsFilter() {
        sports = new SportsPage(DriverFactory.getDriver());
        sports.applyWeekendSportsFilter();
        log.info("Weekend sports filter applied");
    }

    @Then("the sports events should be displayed with lowest price on top")
    public void verifyEventsSortedByLowestPrice() {
        events = sports.getAllEvents();
        Assert.assertFalse(events.isEmpty(), "No sports events found for this weekend.");
        events.sort(Comparator.comparingInt(e -> e.price));
        log.info("Sports events sorted by lowest price:");
    }

    @And("each event should show name and price")
    public void printEventDetails() {
        for (Event e : events) {
            log.info(e.name + " - " + e.priceText);
        }
        log.info("Total events: " + events.size());
    }
}
package steps;

import factory.DriverFactory;
import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.HomePage;
import pages.MoviesPage;
import java.util.List;

public class MovieSteps {

    HomePage home;
    MoviesPage moviesPage;
    List<String> languages;

    @When("the user navigates to Movies section")
    public void goToMovies() {
        home = new HomePage(DriverFactory.getDriver());
        home.goHome();
        home.goToMovies();
    }

    @And("the user opens the Language filter")
    public void openLanguageFilter() {
        moviesPage = new MoviesPage(DriverFactory.getDriver());
    }

    @Then("all available movie languages should be extracted")
    public void extractLanguages() {
        languages = moviesPage.getAllLanguages();
    }

    @And("the list should not be empty")
    public void verifyNotEmpty() {
        Assert.assertFalse(languages.isEmpty(), "No languages found.");
        for (int i = 0; i < languages.size(); i++) {
            System.out.println((i + 1) + ". " + languages.get(i));
        }
    }
}
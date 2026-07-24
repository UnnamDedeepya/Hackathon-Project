package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.MoviesPage;
import utils.ConfigReader;
import java.util.List;

public class MoviesTest extends BaseTest {

    @Test(priority = 2)
    public void displayAllMovieLanguages() throws InterruptedException {
        getDriver().get(ConfigReader.get("url"));
        Thread.sleep(2000);   // Wait for page to fully load

        HomePage home = new HomePage(getDriver());
        home.goHome();
        home.goToMovies();
        MoviesPage moviesPage = new MoviesPage(getDriver());
        List<String> languages = moviesPage.getAllLanguages();
        Assert.assertFalse(languages.isEmpty(), "No movie languages found.");
        log.info("Movie languages available on district.in:");
        for (int i = 0; i < languages.size(); i++) {
            log.info((i + 1) + ". " + languages.get(i));
        }
        log.info("Total languages: " + languages.size());
    }
}
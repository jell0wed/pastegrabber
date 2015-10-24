package fetchers.selenium;

import fetchers.BaseFetcher;
import fetchers.FetcherAction;
import fetchers.FetcherActionException;
import fetchers.FetcherException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Created by jeremiep on 15-10-21.
 */
public class SeleniumFetcher extends BaseFetcher {
    private static Logger LOG = Logger.getLogger(SeleniumFetcher.class);
    protected WebDriver driver;

    public SeleniumFetcher() {

    }

    @Override
    protected void executeAction(FetcherAction action) throws FetcherException {
        action.executeAction(this);
    }

    @Override
    protected void initialize() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("webdriver.load.strategy", "unstable");

        this.driver = new FirefoxDriver(profile);
    }

    public WebDriver getDriver() { return this.driver; }
    void overrideDriver(WebDriver newDriver) { this.driver = newDriver; }

    @Override
    protected void shutdown() {
        this.driver.close();
    }
}

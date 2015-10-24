package fetchers.selenium;

import fetchers.FetcherAction;
import fetchers.utils.TORCheckup;
import fetchers.utils.TORConfiguration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Created by jeremiep on 15-10-22.
 */
public class TORedSeleniumFetcher<T> extends SeleniumFetcher<T> {
    private TORConfiguration tor_config;
    private TORCheckup lastCheckup = null;

    public TORedSeleniumFetcher(TORConfiguration config) {
        this.tor_config = config;
    }

    public TORCheckup newCheckup() {
        // execute checkup

        // build chec
        if(lastCheckup == null) {

        }
    }

    @Override
    protected void initialize() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.socks", this.tor_config.getHostname());
        profile.setPreference("network.proxy.socks_port", this.tor_config.getPort());

        profile.setPreference("webdriver.load.strategy", "unstable");

        this.driver = new FirefoxDriver(profile);
    }
}

package fetchers.selenium;

import fetchers.FetcherAction;
import fetchers.FetcherException;
import fetchers.actions.http.TORCheckupAction;
import fetchers.actions.results.TORCheckup;
import fetchers.utils.TORConfiguration;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jeremiep on 15-10-22.
 */
public class TORedSeleniumFetcher extends SeleniumFetcher {
    private TORConfiguration tor_config;
    private TORCheckup lastCheckup = null;
    private Date nextCheckupDate = Calendar.getInstance().getTime();

    public TORedSeleniumFetcher(TORConfiguration config) {
        this.tor_config = config;
    }

    public void newCheckupIfNeeded() throws FetcherException {
        // build check
        if(this.nextCheckupDate.before(Calendar.getInstance().getTime())) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 1);
            this.nextCheckupDate = cal.getTime();

            // execute checkup
            TORCheckupAction checkupAction = new TORCheckupAction();
            this.execute(checkupAction);

            this.lastCheckup = checkupAction.getExecutedValue();
        }
    }

    @Override
    protected void executeAction(FetcherAction action) throws FetcherException {
        this.newCheckupIfNeeded();

        super.executeAction(action);
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

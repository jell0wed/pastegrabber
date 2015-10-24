package fetchers.actions.http;

import fetchers.FetcherAction;
import fetchers.selenium.SeleniumFetcher;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jeremiep on 15-10-22.
 */
public class GetWebpageSourceAction extends FetcherAction<String> {
    private String uri = "";
    private boolean waitUntilFullLoad = true;
    private By domWaitElement = null;

    public GetWebpageSourceAction(String uri, boolean waitUntilFullLoad, By domWaitElement) {
        this.uri = uri;
        this.waitUntilFullLoad = waitUntilFullLoad;
        this.domWaitElement = domWaitElement;
    }

    public GetWebpageSourceAction(String uri) {
        this(uri, false, null);
    }


    @Override
    public String executeAction(SeleniumFetcher<String> selenium) {
        selenium.getDriver().get(this.uri);
        if(this.waitUntilFullLoad) {
            WebDriverWait wait = new WebDriverWait(selenium.getDriver(), 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(this.domWaitElement));
        }

        return selenium.getDriver().getPageSource();
    }
}

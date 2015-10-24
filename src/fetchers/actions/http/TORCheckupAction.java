package fetchers.actions.http;

import fetchers.FetcherAction;
import fetchers.FetcherActionException;
import fetchers.FetcherException;
import fetchers.selenium.SeleniumFetcher;

/**
 * Created by jeremiep on 15-10-22.
 */
public class TORCheckupAction extends FetcherAction<fetchers.actions.results.TORCheckup> {

    @Override
    public void executeAction(SeleniumFetcher fetcher) throws FetcherActionException {
        try
        {
            GetWebpageSourceAction getSrcAction = new GetWebpageSourceAction("http://ericthibodeau.net/rd/ip.php");
            fetcher.execute(getSrcAction);

            this.setCompleted(new fetchers.actions.results.TORCheckup(getSrcAction.getExecutedValue()));

        } catch (FetcherException e) {
            throw new FetcherActionException("Unable to get tor checkup webpage", e);
        }
    }
}

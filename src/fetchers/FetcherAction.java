package fetchers;

import fetchers.selenium.SeleniumFetcher;
import org.apache.commons.lang3.NotImplementedException;

/**
 * Created by jeremiep on 15-10-22.
 */
public abstract class FetcherAction<T> {
    public abstract T executeAction(SeleniumFetcher<?> fetcher);
}

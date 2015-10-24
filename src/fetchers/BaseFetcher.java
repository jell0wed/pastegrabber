package fetchers;

/**
 * Created by jeremiep on 15-10-21.
 */
public abstract class BaseFetcher {

    public void execute(FetcherAction action) throws FetcherException {
        this.initialize();
        this.executeAction(action);
        this.shutdown();
    }

    protected abstract void executeAction(FetcherAction action) throws FetcherException;
    protected abstract void initialize() throws FetcherException;
    protected abstract void shutdown() throws FetcherException;
}

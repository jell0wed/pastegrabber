package fetchers;

/**
 * Created by jeremiep on 15-10-21.
 */
public abstract class BaseFetcher<T> {

    public T execute(FetcherAction<T> action) throws FetcherException {
        T result = null;

        this.initialize();
        result = this.executeAction(action);
        this.shutdown();

        return result;
    }

    protected abstract T executeAction(FetcherAction<T> action);
    protected abstract void initialize() throws FetcherException;
    protected abstract void shutdown() throws FetcherException;
}

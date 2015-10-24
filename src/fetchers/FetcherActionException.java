package fetchers;

/**
 * Created by jeremiep on 15-10-21.
 */
public class FetcherActionException extends FetcherException {
    public FetcherActionException(String msg, Exception src) {
        super(msg, src);
    }

    public FetcherActionException(Exception src) {
        super(src);
    }
}

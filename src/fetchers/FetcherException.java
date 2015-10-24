package fetchers;

/**
 * Created by jeremiep on 15-10-21.
 */
public class FetcherException extends Exception {
    public FetcherException(String msg, Exception src) {
        super(msg, src);
    }

    public FetcherException(Exception src) {
        super(src);
    }
}

package fetchers.http;

import fetchers.BaseFetcher;
import fetchers.FetcherException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;

/**
 * Created by jeremiep on 15-10-21.
 */
public abstract class BaseHTTPFetcher<T> extends BaseFetcher<T> {
    private static Logger LOG = Logger.getLogger(BaseHTTPFetcher.class);

    // TODO : socket factory
    private HttpClient http;

    protected BaseHTTPFetcher() {
    }

    protected HttpPost createPostRequest(URI uri) {
        return new HttpPost(uri);
    }

    protected HttpGet createGetRequest(URI uri) {
        return new HttpGet(uri);
    }

    protected HttpResponse execute(HttpUriRequest request) throws FetcherException {
        try {
            return this.http.execute(request);
        } catch (IOException e) {
            LOG.error(e);
            throw new FetcherException(e);
        }
    }

    @Override
    protected void initialize() {
        this.http = HttpClients.createDefault();
    }

    @Override
    protected  void shutdown() {

    }
}

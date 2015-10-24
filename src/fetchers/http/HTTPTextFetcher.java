package fetchers.http;

import fetchers.FetcherAction;
import fetchers.FetcherException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by jeremiep on 15-10-21.
 */
public class HTTPTextFetcher extends BaseHTTPFetcher<String> {
    private String uri = "";

    public HTTPTextFetcher(String uri) {
        this.uri = uri;
    }

    @Override
    protected String executeAction(FetcherAction<String> action) {
        return null;
    }
}

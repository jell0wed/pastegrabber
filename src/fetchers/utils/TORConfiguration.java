package fetchers.utils;

/**
 * Created by jeremiep on 15-10-22.
 */
public class TORConfiguration {
    private String hostname;
    private int port;

    public TORConfiguration(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return this.hostname;
    }

    public int getPort() {
        return this.port;
    }
}

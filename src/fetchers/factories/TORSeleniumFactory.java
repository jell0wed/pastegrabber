package fetchers.factories;

import fetchers.selenium.TORedSeleniumFetcher;
import fetchers.utils.TORConfiguration;

import java.util.LinkedList;

/**
 * Created by jeremiep on 15-10-22.
 */
public class TORSeleniumFactory {
    private static TORSeleniumFactory instance = null;
    private LinkedList<TORedSeleniumFetcher<String>> loadedTextFetchers = new LinkedList<>();
    private TORConfiguration[] availableConfigurations;

    public static TORSeleniumFactory getInstance() {
        if(instance == null) {
            instance = new TORSeleniumFactory();
        }

        return instance;
    }

    private TORSeleniumFactory() {
        this.availableConfigurations = this.getTORConfigurations();
        this.loadTextFetchers();
    }

    private void loadTextFetchers() {
        this.loadedTextFetchers.clear();
        for(TORConfiguration config : this.availableConfigurations) {
            this.loadedTextFetchers.push(new TORedSeleniumFetcher<>(config));
        }
    }

    public TORedSeleniumFetcher<String> getNewTextFetcher() {
        if(this.loadedTextFetchers.isEmpty()) {
            this.loadTextFetchers();
        }

        TORedSeleniumFetcher<String> nextFetcher = this.loadedTextFetchers.pop();
        return nextFetcher;
    }

    private TORConfiguration[] getTORConfigurations() {
        return new TORConfiguration[] {
                new TORConfiguration("localhost", 9050)
        };
    }

}

package pastebin;

import entities.pastebin.PastebinPost;
import entities.pastebin.PastebinPostEntry;
import fetchers.actions.http.GetWebpageSourceAction;
import fetchers.selenium.SeleniumFetcher;
import org.openqa.selenium.By;
import parsers.pastebin.PastebinPostParser;

import java.util.concurrent.Callable;

/**
 * Created by jeremiep on 15-10-21.
 */
public class PastebinProcessJob implements Callable<PastebinPost> {

    private PastebinPostEntry entry;

    public PastebinProcessJob(PastebinPostEntry entry) {
        this.entry = entry;
    }

    @Override
    public PastebinPost call() throws Exception {
        SeleniumFetcher<String> fetcher = new SeleniumFetcher<>();
        GetWebpageSourceAction sourceAction = new GetWebpageSourceAction(this.entry.getURL(), false, By.id("footer"));
        PastebinPostParser parser = new PastebinPostParser(fetcher.execute(sourceAction));

        return new PastebinPost(parser.tryParse());
    }
}

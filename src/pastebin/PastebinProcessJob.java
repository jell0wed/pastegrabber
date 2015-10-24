package pastebin;

import entities.pastebin.PastebinPost;
import entities.pastebin.PastebinPostEntry;
import fetchers.actions.http.GetWebpageSourceAction;
import fetchers.factories.TORSeleniumFactory;
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
        SeleniumFetcher fetcher = TORSeleniumFactory.getInstance().getNewTextFetcher();
        GetWebpageSourceAction sourceAction = new GetWebpageSourceAction(this.entry.getURL(), false, By.id("footer"));
        fetcher.execute(sourceAction);
        PastebinPostParser parser = new PastebinPostParser(sourceAction.getExecutedValue());

        return new PastebinPost(parser.tryParse());
    }
}

package pastebin;

import entities.pastebin.PastebinPostEntry;
import fetchers.actions.http.GetWebpageSourceAction;
import fetchers.factories.TORSeleniumFactory;
import fetchers.selenium.SeleniumFetcher;
import org.openqa.selenium.By;
import parsers.ParsedDataset;
import parsers.pastebin.PastebinParser;
import parsers.pastebin.entities.PastebinPostEntryParseData;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;

/**
 * Created by jeremiep on 15-10-21.
 */
public class PastebinGrabJob implements Callable<Set<PastebinPostEntry>> {

    @Override
    public Set<PastebinPostEntry> call() throws Exception {
        TreeSet<PastebinPostEntry> resultIds = new TreeSet<>();

        SeleniumFetcher fetcher = TORSeleniumFactory.getInstance().getNewTextFetcher();
        GetWebpageSourceAction pastebinSource = new GetWebpageSourceAction("http://pastebin.com", false, By.className("right_menu"));
        fetcher.execute(pastebinSource);
        ParsedDataset<PastebinPostEntryParseData, String> parsedDataset = new PastebinParser(pastebinSource.getExecutedValue()).tryParse();

        for(String id : parsedDataset.getMultipleValues(PastebinPostEntryParseData.IDENFIER)) {
            resultIds.add(new PastebinPostEntry(id));
        }

        return resultIds;
    }
}

import fetchers.FetcherException;
import fetchers.http.HTTPTextFetcher;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import parsers.ParsingException;
import parsers.pastebin.PastebinPostParser;
import parsers.pastebin.entities.PastebinPostParseData;
import pastebin.PastebinService;

/**
 * Created by jeremiep on 15-10-21.
 */
public class MainTest {
    public static void main(String[] args) throws FetcherException, ParsingException, InterruptedException {
        PropertyConfigurator.configure("res/log4j.properties");
        new PastebinService().fetch();
    }
}

package parsers.pastebin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parsers.ParsedDataset;
import parsers.ParsingException;
import parsers.pastebin.entities.PastebinPostEntryParseData;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jeremiep on 15-10-21.
 */
public class PastebinParser extends TextBasedParser<PastebinPostEntryParseData> {
    private Document htmlDoc;

    public PastebinParser(String rawData) {
        super(rawData);
    }

    @Override
    protected void parseRawData(String rawData) {
        this.htmlDoc = Jsoup.parse(rawData);
    }

    private List<String> getRecentPublicIdentifiers() throws ParsingException {
        List<String> ids = new LinkedList<String>();
        Elements publicPostList = this.htmlDoc.select("ul.right_menu li");
        if(publicPostList.size() == 0) {
            throw new ParsingException("Unable to find recent public posts");
        }

        for(Element listI : publicPostList) {
            ids.add(listI.getElementsByTag("a").get(0).attr("href").replace("/", ""));
        }

        return ids;
    }

    @Override
    protected ParsedDataset<PastebinPostEntryParseData, String> assembleParsedData() throws ParsingException {
        ParsedDataset<PastebinPostEntryParseData, String> dataset = new ParsedDataset<PastebinPostEntryParseData, String>();
        for(String id : this.getRecentPublicIdentifiers()) {
            dataset.addData(PastebinPostEntryParseData.IDENFIER, id);
        }

        return dataset;
    }
}

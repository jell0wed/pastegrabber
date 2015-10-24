package entities.pastebin;

import entities.FetchObject;
import parsers.ParsedDataset;
import parsers.pastebin.entities.PastebinPostParseData;

/**
 * Created by jeremiep on 15-10-21.
 */
public class PastebinPost extends FetchObject {
    private String title;
    private String identifier;
    private String source_url;
    private String author_user;
    private int views;
    private byte[] raw_data;

    public PastebinPost(ParsedDataset<PastebinPostParseData, String> parseData) {
        this.title = parseData.getValue(PastebinPostParseData.TITLE);
        this.identifier = parseData.getValue(PastebinPostParseData.IDENTIFIER);
        this.source_url = parseData.getValue(PastebinPostParseData.URL);
        this.author_user = parseData.getValue(PastebinPostParseData.AUTHOR_USER);
        //this.views = parseData.getValue(PastebinPostParseData.VIEWS);
    }

    public String getSourceURL() {
        return this.source_url;
    }

    public String getTitle() {
        return this.title;
    }
}

package parsers.pastebin;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parsers.ParsedDataset;
import parsers.ParsingException;
import parsers.pastebin.entities.PastebinPostParseData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jeremiep on 15-10-21.
 */
public class PastebinPostParser extends TextBasedParser<PastebinPostParseData> {

    private Document htmlDoc;

    public PastebinPostParser(String rawData) {
        super(rawData);
    }

    private String getPostTitle() throws ParsingException {
        Elements elem = this.htmlDoc.getElementsByClass("paste_box_frame");
        if(elem.size() == 0) {
            throw new ParsingException("Unable to parse post title (no .poste_box_frame)");
        }

        String attrTitle = elem.first().getElementsByTag("h1").first().text();
        if(attrTitle == null) {
            throw new ParsingException("Unable to get parse title attribute.");
        }

        return attrTitle;
    }

    private String getURL() throws ParsingException {
        Elements elems = this.htmlDoc.getElementsByAttributeValue("rel", "canonical");
        if(elems.size() == 0) {
            throw new ParsingException("Unable to get identifier via rel=canonical");
        }

        return elems.first().attr("href");
    }

    public String getIdentifier() throws ParsingException {
        Pattern pattIdentifier = Pattern.compile("^.*\\/(.+)$", Pattern.CASE_INSENSITIVE);
        Matcher matchIdentifier = pattIdentifier.matcher(this.getURL());
        if(!matchIdentifier.matches()) {
            throw new ParsingException("Unable to deduce identifier based on url");
        }

        return matchIdentifier.group(1);
    }

    public String getRawURL() throws ParsingException {
        return "http://pastebin.com/raw.php?i=".concat(this.getIdentifier());
    }

    @Override
    protected void parseRawData(String rawData) {
        this.htmlDoc = Jsoup.parse(rawData);
    }

    @Override
    protected ParsedDataset<PastebinPostParseData, String> assembleParsedData() throws ParsingException{
        return new ParsedDataset<PastebinPostParseData, String>()
                .addData(PastebinPostParseData.TITLE, this.getPostTitle())
                .addData(PastebinPostParseData.IDENTIFIER, this.getIdentifier())
                .addData(PastebinPostParseData.URL, this.getURL());
    }
}

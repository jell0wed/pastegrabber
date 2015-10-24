package entities.pastebin;

import com.sun.corba.se.spi.orb.ParserData;
import parsers.ParsedDataset;
import parsers.pastebin.entities.PastebinPostEntryParseData;

/**
 * Created by jeremiep on 15-10-21.
 */
public class PastebinPostEntry implements Comparable<PastebinPostEntry> {

    private String title;
    private String time_eslaped;
    private String url;
    private String identifier;

    public PastebinPostEntry(String id) {
        this.identifier = id;
    }

    public String getIdentifier() { return this.identifier; }
    public String getURL() { return "http://pastebin.com/" + getIdentifier(); }

    @Override
    public int compareTo(PastebinPostEntry o) {
        return this.identifier.compareTo(o.getIdentifier());
    }
}

package parsers.pastebin;

import parsers.BaseParser;
import parsers.ParsedDataset;

/**
 * Created by jeremiep on 15-10-21.
 */
public abstract class TextBasedParser<T> extends BaseParser<T, String> {
    public String rawData;

    @Override
    protected void parseData() {
        this.parseRawData(this.rawData);
    }

    protected abstract void parseRawData(String rawData);

    protected TextBasedParser(String rawData) {
        this.rawData = rawData;
    }
}

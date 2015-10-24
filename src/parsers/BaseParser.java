package parsers;

public abstract class BaseParser<K, V> {
    protected BaseParser() {}

    protected abstract void parseData() throws ParsingException;
    protected abstract ParsedDataset<K, V> assembleParsedData() throws ParsingException;

    public ParsedDataset<K, V> tryParse() throws ParsingException
    {
        this.parseData();
        return this.assembleParsedData();
    }
}

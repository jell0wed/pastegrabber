package parsers;

/**
 * Created by jeremiep on 15-10-21.
 */
public class ParsingException extends Exception {
    public ParsingException(String message, Throwable src) {
        super(message, src);
    }

    public ParsingException(String message) {
        super(message);
    }
}

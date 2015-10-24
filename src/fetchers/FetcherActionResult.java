package fetchers;

/**
 * Created by jpoisson on 10/24/15.
 */
public abstract class FetcherActionResult<T> {
    private T value;

    public FetcherActionResult(T obj) {
        this.value = obj;
    }

    public T getValue() {
        return this.value;
    }
}

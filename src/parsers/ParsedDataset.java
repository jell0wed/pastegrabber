package parsers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;

import java.util.List;

/**
 * Created by jeremiep on 15-10-21.
 */
/*
OMG OSTI QUE C'EST LAID CHANGE MOI SA AU PLUS CRISS GROS OSTI DE NIGAUD
 */
public class ParsedDataset<K, V> {
    private ListMultimap<K, V> parsedValues = ArrayListMultimap.create();

    public ParsedDataset<K, V> addData(K key, V val) {
        this.parsedValues.put(key, val);
        return this;
    }

    public V getValue(K key) {
        List<V> elems = this.parsedValues.get(key);
        if(elems.size() > 0) {
            return elems.get(0);
        }

        return null;
    }

    public List<V> getMultipleValues(K key) {
        return this.parsedValues.get(key);
    }

}

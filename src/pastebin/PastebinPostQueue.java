package pastebin;

import entities.pastebin.PastebinPostEntry;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by jeremiep on 15-10-21.
 */
public class PastebinPostQueue {
    private static final Logger LOG = Logger.getLogger(PastebinPostQueue.class);
    private LinkedList<PastebinPostEntry> entries = new LinkedList<>();

    public PastebinPostQueue() {

    }

    public synchronized void addEntry(PastebinPostEntry entry) {
        if(!entries.stream().filter(e -> e.getIdentifier().equalsIgnoreCase(entry.getIdentifier())).findAny().isPresent())
        {
            LOG.info("Added new post entry = " + entry.getIdentifier());
            this.entries.add(entry);
        }
    }

    public boolean isEmpty() { return this.entries.isEmpty(); }

    public synchronized PastebinPostEntry pop() {
        return entries.pop();
    }
}

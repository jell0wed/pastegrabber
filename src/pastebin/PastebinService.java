package pastebin;

import com.google.common.util.concurrent.*;
import org.apache.log4j.Logger;

import java.util.concurrent.Executors;

/**
 * Created by jeremiep on 15-10-21.
 */
public class PastebinService {
    private static final Logger LOG = Logger.getLogger(PastebinService.class);
    private PastebinPostQueue postQueue = new PastebinPostQueue();
    private ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));


}

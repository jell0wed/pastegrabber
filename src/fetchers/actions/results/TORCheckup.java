package fetchers.actions.results;

import fetchers.FetcherAction;
import fetchers.FetcherActionResult;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jpoisson on 10/24/15.
 */
public class TORCheckup {
    private String remote_ip;
    private Date last_checked;

    public TORCheckup(String ip) {
        this.remote_ip = ip;
        this.last_checked = Calendar.getInstance().getTime();
    }

    public Date getLastCheckedDate() {
        return this.last_checked;
    }

    public String getRemoteIp() {
        return this.remote_ip;
    }
}

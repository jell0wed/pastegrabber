package fetchers.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by jeremiep on 15-10-22.
 */
public class TORCheckup {
    private Date lastCheckDate;
    private String remoteIp;

    public TORCheckup(String ip) {
        this.lastCheckDate = Calendar.getInstance().getTime();
    }

    public Date getLastCheckDate() { return this.lastCheckDate; }
    public String getRemoteIp() { return this.remoteIp; }
}

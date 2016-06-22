package ph.gardenia.com.model;

import java.io.Serializable;

/**
 * Created by otomatik on 6/20/16.
 */
public class DownlineDsc implements Serializable {

    public int cid;
    public int branch;
    public String dscNo;
    public int routeId;
    public int guardId;
    public String guardTime;
    public int trayIn;
    public int trayOut;
    public int boTrayIn;
    public boolean isPreloaded;
    public int truckerId;
    public boolean boConfirmed;
    public boolean cashierReceived;
    public String cashierReceivedDate;
    public String datePosted;

}

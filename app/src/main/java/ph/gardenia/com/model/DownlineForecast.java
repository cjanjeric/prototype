package ph.gardenia.com.model;

import java.io.Serializable;

/**
 * Created by otomatik on 7/12/16.
 */
public class DownlineForecast implements Serializable{
    public int cid;
    public int routeCid;
    public int customerCid;
    public boolean isDelivered;
    public boolean storeClose;
    public String dscNo;
    public boolean exchangeConfirmed;
    public boolean isPaid;
    public boolean isRsLocked;
    public boolean isAudited;
}

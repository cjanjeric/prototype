package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import java.util.List;

import ph.gardenia.com.model.DownlineDsc;

/**
 * Created by otomatik on 6/20/16.
 */
public class DownlineDscHelper extends SugarRecord {

    private int cid;
    private int branch;
    private String dscNo;
    private int routeId;
    private int guardId;
    private String guardTime;
    private int trayIn;
    private int trayOut;
    private int boTrayIn;
    private boolean isPreloaded;
    private int truckerId;
    private boolean boConfirmed;
    private boolean cashierReceived;
    private String cashierReceivedDate;
    private String datePosted;

    public DownlineDscHelper() {
    }

    public DownlineDscHelper(DownlineDsc dsc) {

        this.cid = dsc.cid;
        this.branch = dsc.branch;
        this.dscNo = dsc.dscNo;
        this.routeId = dsc.routeId;
        this.guardId = dsc.guardId;
        this.guardTime = dsc.guardTime;
        this.trayIn = dsc.trayIn;
        this.trayOut = dsc.trayOut;
        this.boTrayIn = dsc.boTrayIn;
        this.isPreloaded = dsc.isPreloaded;
        this.truckerId = dsc.truckerId;
        this.boConfirmed = dsc.boConfirmed;
        this.cashierReceived = dsc.cashierReceived;
        this.cashierReceivedDate = dsc.cashierReceivedDate;
        this.datePosted = dsc.datePosted;

    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public String getDscNo() {
        return dscNo;
    }

    public void setDscNo(String dscNo) {
        this.dscNo = dscNo;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getGuardId() {
        return guardId;
    }

    public void setGuardId(int guardId) {
        this.guardId = guardId;
    }

    public String getGuardTime() {
        return guardTime;
    }

    public void setGuardTime(String guardTime) {
        this.guardTime = guardTime;
    }

    public int getTrayIn() {
        return trayIn;
    }

    public void setTrayIn(int trayIn) {
        this.trayIn = trayIn;
    }

    public int getTrayOut() {
        return trayOut;
    }

    public void setTrayOut(int trayOut) {
        this.trayOut = trayOut;
    }

    public int getBoTrayIn() {
        return boTrayIn;
    }

    public void setBoTrayIn(int boTrayIn) {
        this.boTrayIn = boTrayIn;
    }

    public boolean isPreloaded() {
        return isPreloaded;
    }

    public void setPreloaded(boolean preloaded) {
        isPreloaded = preloaded;
    }

    public int getTruckerId() {
        return truckerId;
    }

    public void setTruckerId(int truckerId) {
        this.truckerId = truckerId;
    }

    public boolean isBoConfirmed() {
        return boConfirmed;
    }

    public void setBoConfirmed(boolean boConfirmed) {
        this.boConfirmed = boConfirmed;
    }

    public boolean isCashierReceived() {
        return cashierReceived;
    }

    public void setCashierReceived(boolean cashierReceived) {
        this.cashierReceived = cashierReceived;
    }

    public String getCashierReceivedDate() {
        return cashierReceivedDate;
    }

    public void setCashierReceivedDate(String cashierReceivedDate) {
        this.cashierReceivedDate = cashierReceivedDate;
    }

    public String getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(String datePosted) {
        this.datePosted = datePosted;
    }

    public List<DownlineDscItemHelper> getDscItems() {
        return DownlineDscItemHelper.find(DownlineDscItemHelper.class, "dsc_helper = ?", String.valueOf(this.getId()));
    }

    @Override
    public String toString() {
        return super.toString();
    }

}

package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import ph.gardenia.com.model.DownlineDscItem;
import ph.gardenia.com.model.DownlineForecast;

/**
 * Created by otomatik on 7/12/16.
 */
public class DownlineForecastHelper extends SugarRecord {

    private int cid;
    private int routeCid;
    private int customerCid;
    private boolean isDelivered;
    private boolean storeClose;
    private String dscNo;
    private boolean exchangeConfirmed;
    private boolean isPaid;
    private boolean isRsLocked;
    private boolean isAudited;

    public DownlineForecastHelper(){}

    public DownlineForecastHelper(DownlineForecast forecast){
        this.cid = forecast.cid;
        this.routeCid = forecast.routeCid;
        this.customerCid = forecast.customerCid;
        this.isDelivered = forecast.isDelivered;
        this.storeClose = forecast.storeClose;
        this.dscNo = forecast.dscNo;
        this.exchangeConfirmed = forecast.exchangeConfirmed;
        this.isPaid = forecast.isPaid;
        this.isRsLocked  = forecast.isRsLocked;
        this.isAudited = forecast.isAudited;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getRouteCid() {
        return routeCid;
    }

    public void setRouteCid(int routeCid) {
        this.routeCid = routeCid;
    }

    public int getCustomerCid() {
        return customerCid;
    }

    public void setCustomerCid(int customerCid) {
        this.customerCid = customerCid;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public boolean isStoreClose() {
        return storeClose;
    }

    public void setStoreClose(boolean storeClose) {
        this.storeClose = storeClose;
    }

    public String getDscNo() {
        return dscNo;
    }

    public void setDscNo(String dscNo) {
        this.dscNo = dscNo;
    }

    public boolean isExchangeConfirmed() {
        return exchangeConfirmed;
    }

    public void setExchangeConfirmed(boolean exchangeConfirmed) {
        this.exchangeConfirmed = exchangeConfirmed;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isRsLocked() {
        return isRsLocked;
    }

    public void setRsLocked(boolean rsLocked) {
        isRsLocked = rsLocked;
    }

    public boolean isAudited() {
        return isAudited;
    }

    public void setAudited(boolean audited) {
        isAudited = audited;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

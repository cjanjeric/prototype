package ph.gardenia.com.model;

import java.io.Serializable;

/**
 * Created by otomatik on 7/5/16.
 */
public class DownlineCustomer implements Serializable{
    public int cid;
    public int branch;
    public String ancestor;
    public String customerCode;
    public String customerName;
    public String outletName;
    public String businessAddress;
    public String tinNo;
    public String telephoneNum;
    public String faxNo;
    public int storeType;
    public int payType;
    public int routeId;
    public String bankCode;
    public double tradeDiscount;
    public int decimalPlaces;

    public boolean needDr;
    public boolean needSi;
    public boolean showExchangeDr;
    public boolean showExchangeSi;

    public boolean zeroRated;

    public String storeDescription;
    public int discountType;
    public int maxPrintCopies;
    public int prodCodeId;

    public boolean isManualRs;
    public boolean hasEwt;

    public boolean needDpv;
    public boolean needGrv;
    public boolean needPo;
}

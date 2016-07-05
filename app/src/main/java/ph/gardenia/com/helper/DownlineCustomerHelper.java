package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import ph.gardenia.com.model.DownlineCustomer;

/**
 * Created by otomatik on 7/5/16.
 */
public class DownlineCustomerHelper extends SugarRecord {

    private int cid;
    private int branch;
    private String ancestor;
    private String customerCode;
    private String customerName;
    private String outletName;
    private String businessAddress;
    private String tinNo;
    private String telephoneNum;
    private String faxNo;
    private int storeType;
    private int payType;
    private int routeId;
    private String bankCode;
    private double tradeDiscount;
    private int decimalPlaces;

    private boolean needDr;
    private boolean needSi;
    private boolean showExchangeDr;
    private boolean showExchangeSi;

    private boolean zeroRated;

    private String storeDescription;
    private int discountType;
    private int maxPrintCopies;
    private int prodCodeId;

    private boolean isManualRs;
    private boolean hasEwt;

    private boolean needDpv;
    private boolean needGrv;
    private boolean needPo;

    public DownlineCustomerHelper(){}

    public DownlineCustomerHelper(DownlineCustomer downlineCustomer){
        this.cid = downlineCustomer.cid;
        this.branch = downlineCustomer.branch;
        this.ancestor = downlineCustomer.ancestor;
        this.customerCode = downlineCustomer.customerCode;
        this.customerName = downlineCustomer.customerName;
        this.outletName = downlineCustomer.outletName;
        this.businessAddress = downlineCustomer.businessAddress;
        this.tinNo = downlineCustomer.tinNo;
        this.telephoneNum = downlineCustomer.telephoneNum;
        this.faxNo = downlineCustomer.faxNo;
        this.storeType = downlineCustomer.storeType;
        this.payType = downlineCustomer.payType;
        this.routeId = downlineCustomer.routeId;
        this.bankCode = downlineCustomer.bankCode;
        this.tradeDiscount = downlineCustomer.tradeDiscount;
        this.decimalPlaces = downlineCustomer.decimalPlaces;
        this.needDr = downlineCustomer.needDr;
        this.needSi = downlineCustomer.needSi;
        this.showExchangeDr = downlineCustomer.showExchangeDr;
        this.showExchangeSi = downlineCustomer.showExchangeSi;
        this.zeroRated = downlineCustomer.zeroRated;
        this.storeDescription = downlineCustomer.storeDescription;
        this.discountType = downlineCustomer.discountType;
        this.maxPrintCopies = downlineCustomer.maxPrintCopies;
        this.prodCodeId = downlineCustomer.prodCodeId;
        this.isManualRs = downlineCustomer.isManualRs;
        this.hasEwt = downlineCustomer.hasEwt;
        this.needGrv = downlineCustomer.needGrv;
        this.needDpv = downlineCustomer.needDpv;
        this.needPo = downlineCustomer.needPo;
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

    public String getAncestor() {
        return ancestor;
    }

    public void setAncestor(String ancestor) {
        this.ancestor = ancestor;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public String getTelephoneNum() {
        return telephoneNum;
    }

    public void setTelephoneNum(String telephoneNum) {
        this.telephoneNum = telephoneNum;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public int getStoreType() {
        return storeType;
    }

    public void setStoreType(int storeType) {
        this.storeType = storeType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public double getTradeDiscount() {
        return tradeDiscount;
    }

    public void setTradeDiscount(double tradeDiscount) {
        this.tradeDiscount = tradeDiscount;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public boolean isNeedDr() {
        return needDr;
    }

    public void setNeedDr(boolean needDr) {
        this.needDr = needDr;
    }

    public boolean isNeedSi() {
        return needSi;
    }

    public void setNeedSi(boolean needSi) {
        this.needSi = needSi;
    }

    public boolean isShowExchangeDr() {
        return showExchangeDr;
    }

    public void setShowExchangeDr(boolean showExchangeDr) {
        this.showExchangeDr = showExchangeDr;
    }

    public boolean isShowExchangeSi() {
        return showExchangeSi;
    }

    public void setShowExchangeSi(boolean showExchangeSi) {
        this.showExchangeSi = showExchangeSi;
    }

    public boolean isZeroRated() {
        return zeroRated;
    }

    public void setZeroRated(boolean zeroRated) {
        this.zeroRated = zeroRated;
    }

    public String getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(String storeDescription) {
        this.storeDescription = storeDescription;
    }

    public int getDiscountType() {
        return discountType;
    }

    public void setDiscountType(int discountType) {
        this.discountType = discountType;
    }

    public int getMaxPrintCopies() {
        return maxPrintCopies;
    }

    public void setMaxPrintCopies(int maxPrintCopies) {
        this.maxPrintCopies = maxPrintCopies;
    }

    public int getProdCodeId() {
        return prodCodeId;
    }

    public void setProdCodeId(int prodCodeId) {
        this.prodCodeId = prodCodeId;
    }

    public boolean isManualRs() {
        return isManualRs;
    }

    public void setManualRs(boolean manualRs) {
        isManualRs = manualRs;
    }

    public boolean isHasEwt() {
        return hasEwt;
    }

    public void setHasEwt(boolean hasEwt) {
        this.hasEwt = hasEwt;
    }

    public boolean isNeedDpv() {
        return needDpv;
    }

    public void setNeedDpv(boolean needDpv) {
        this.needDpv = needDpv;
    }

    public boolean isNeedGrv() {
        return needGrv;
    }

    public void setNeedGrv(boolean needGrv) {
        this.needGrv = needGrv;
    }

    public boolean isNeedPo() {
        return needPo;
    }

    public void setNeedPo(boolean needPo) {
        this.needPo = needPo;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}

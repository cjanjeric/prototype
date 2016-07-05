package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import ph.gardenia.com.model.DownlineProduct;

/**
 * Created by otomatik on 6/21/16.
 */
public class DownlineProductHelper extends SugarRecord {

    private int cid;
    private String prodCode;
    private String alias;
    private String description;
    private double srp;
    private int packsPerTray;
    private String barcode;
    private boolean isActive;
    private boolean discountExcluded;

    public DownlineProductHelper(){}

    public DownlineProductHelper(DownlineProduct downlineProduct){
        this.cid = downlineProduct.cid;
        this.prodCode = downlineProduct.prodCode;
        this.alias = downlineProduct.alias;
        this.description = downlineProduct.description;
        this.srp = downlineProduct.srp;
        this.packsPerTray = downlineProduct.packsPerTray;
        this.barcode = downlineProduct.barcode;
        this.isActive = downlineProduct.isActive;
        this.discountExcluded = downlineProduct.discountExcluded;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSrp() {
        return srp;
    }

    public void setSrp(double srp) {
        this.srp = srp;
    }

    public int getPacksPerTray() {
        return packsPerTray;
    }

    public void setPacksPerTray(int packsPerTray) {
        this.packsPerTray = packsPerTray;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDiscountExcluded() {
        return discountExcluded;
    }

    public void setDiscountExcluded(boolean discountExcluded) {
        this.discountExcluded = discountExcluded;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

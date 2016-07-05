package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import ph.gardenia.com.model.DownlineDscItem;

/**
 * Created by otomatik on 6/21/16.
 */
public class DownlineDscItemHelper extends SugarRecord {

    private int dscId;
    private int issued;
    private int productCode;
    private int trayCount;
    private int excessPack;
    public DownlineProductHelper productHelper;
    public DownlineDscHelper dscHelper;

    public DownlineDscItemHelper(){}

    public DownlineDscItemHelper(DownlineDscItem dscItem){

        this.dscId = dscItem.dscId;
        this.issued = dscItem.issued;
        this.productCode = dscItem.productCode;
        this.trayCount = dscItem.trayCount;
        this.excessPack = dscItem.excessPack;

    }

    public int getDscId() {
        return dscId;
    }

    public void setDscId(int dscId) {
        this.dscId = dscId;
    }

    public int getIssued() {
        return issued;
    }

    public void setIssued(int issued) {
        this.issued = issued;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public int getTrayCount() {
        return trayCount;
    }

    public void setTrayCount(int trayCount) {
        this.trayCount = trayCount;
    }

    public int getExcessPack() {
        return excessPack;
    }

    public void setExcessPack(int excessPack) {
        this.excessPack = excessPack;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

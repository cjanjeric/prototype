package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import ph.gardenia.com.model.DownlineProductPrice;

/**
 * Created by otomatik on 7/5/16.
 */
public class DownlineProductPriceHelper extends SugarRecord {

    private int discountTypeId;
    private int productCode;
    private double amount;

    public DownlineProductPriceHelper(){}

    public DownlineProductPriceHelper(DownlineProductPrice downlineProductPrice){
        this.discountTypeId = downlineProductPrice.discountTypeId;
        this.productCode = downlineProductPrice.productCode;
        this.amount = downlineProductPrice.amount;
    }

    public int getDiscountTypeId() {
        return discountTypeId;
    }

    public void setDiscountTypeId(int discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

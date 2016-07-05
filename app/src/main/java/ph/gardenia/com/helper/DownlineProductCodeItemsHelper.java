package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import ph.gardenia.com.model.DownlineProductCodeItems;

/**
 * Created by otomatik on 7/5/16.
 */
public class DownlineProductCodeItemsHelper extends SugarRecord{

    private int prodCodeId;
    private int productCode;
    private String sku;

    public DownlineProductCodeItemsHelper(){}

    public DownlineProductCodeItemsHelper(DownlineProductCodeItems productCodeItems){
        this.prodCodeId = productCodeItems.prodCodeId;
        this.productCode = productCodeItems.productCode;
        this.sku = productCodeItems.sku;
    }

    public int getProdCodeId() {
        return prodCodeId;
    }

    public void setProdCodeId(int prodCodeId) {
        this.prodCodeId = prodCodeId;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

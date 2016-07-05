package ph.gardenia.com.model;

import java.io.Serializable;

/**
 * Created by otomatik on 6/21/16.
 */
public class DownlineProduct implements Serializable {
    public int cid;
    public String prodCode;
    public String alias;
    public String description;
    public double srp;
    public int packsPerTray;
    public String barcode;
    public boolean isActive;
    public boolean discountExcluded;
}

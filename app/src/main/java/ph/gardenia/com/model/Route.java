package ph.gardenia.com.model;

import java.io.Serializable;

/**
 * Created by otomatik on 8/17/15.
 */
public class Route implements Serializable {

    public int cid;
    public String codeNo;
    public String descript;
    public int seniorPersonnel;
    public int dispatcherId;
    public int truckNo;
    public int loadingAssignmentId;

    @Override
    public String toString() {
        return super.toString();
    }
}

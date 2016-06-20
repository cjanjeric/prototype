package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import ph.gardenia.com.model.Route;

/**
 * Created by otomatik on 6/10/16.
 */
public class RouteHelper extends SugarRecord {

    private int cid;
    private String codeNo;
    private String descript;
    private int seniorPersonnel;
    private int dispatcherId;
    private int truckNo;
    private int loadingAssignmentId;

    public RouteHelper() {
    }

    public RouteHelper(Route route) {

        this.cid = route.cid;
        this.codeNo = route.codeNo;
        this.descript = route.descript;
        this.seniorPersonnel = route.seniorPersonnel;
        this.dispatcherId = route.dispatcherId;
        this.truckNo = route.truckNo;
        this.loadingAssignmentId = route.loadingAssignmentId;

    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getSeniorPersonnel() {
        return seniorPersonnel;
    }

    public void setSeniorPersonnel(int seniorPersonnel) {
        this.seniorPersonnel = seniorPersonnel;
    }

    public int getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(int dispatcherId) {
        this.dispatcherId = dispatcherId;
    }

    public int getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(int truckNo) {
        this.truckNo = truckNo;
    }

    public int getLoadingAssignmentId() {
        return loadingAssignmentId;
    }

    public void setLoadingAssignmentId(int loadingAssignmentId) {
        this.loadingAssignmentId = loadingAssignmentId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

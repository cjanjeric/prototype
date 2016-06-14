package ph.gardenia.com.helper;

import com.orm.SugarRecord;

import ph.gardenia.com.model.Route;

/**
 * Created by otomatik on 6/10/16.
 */
public class RouteHelper extends SugarRecord {

    private String cid;
    private String codeNo;
    private String descript;
    private String seniorPersonnel;
    private String dispatcherId;
    private String truckNo;
    private String loadingAssignmentId;

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

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
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

    public String getSeniorPersonnel() {
        return seniorPersonnel;
    }

    public void setSeniorPersonnel(String seniorPersonnel) {
        this.seniorPersonnel = seniorPersonnel;
    }

    public String getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(String dispatcherId) {
        this.dispatcherId = dispatcherId;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
    }

    public String getLoadingAssignmentId() {
        return loadingAssignmentId;
    }

    public void setLoadingAssignmentId(String loadingAssignmentId) {
        this.loadingAssignmentId = loadingAssignmentId;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

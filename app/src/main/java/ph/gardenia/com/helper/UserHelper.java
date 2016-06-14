package ph.gardenia.com.helper;

import android.util.Log;

import com.orm.SugarRecord;

import ph.gardenia.com.model.User;

/**
 * Created by otomatik on 8/12/15.
 */
public class UserHelper extends SugarRecord {

    private String empId;
    private int cid;
    private String empCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private String userName;
    private String password;
    private String userGroup;
    private String active;

    public UserHelper() {
    }

    public UserHelper(User user) {

        this.empId = user.empId;
        this.cid = user.cid;
        this.empCode = user.empCode;
        this.firstName = user.firstName;
        this.middleName = user.middleName;
        this.lastName = user.lastName;
        this.userName = user.userName;
        this.password = user.password;
        this.userGroup = user.userGroup;
        this.active = user.active;

        Log.d("TAG", empId);

    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return cid + " " + empId + " " + empCode;
    }
}

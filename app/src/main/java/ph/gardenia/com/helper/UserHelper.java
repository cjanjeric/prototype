package ph.gardenia.com.helper;

import android.util.Log;

import com.orm.SugarRecord;

import ph.gardenia.com.model.User;

/**
 * Created by otomatik on 8/12/15.
 */
public class UserHelper extends SugarRecord {

    private int empId;
    private int cid;
    private String empCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private String completeName;
    private String userName;
    private String password;
    private int userGroup;
    private int active;
    private int canReallocate;

    public UserHelper() {
    }

    public UserHelper(User user) {

        this.empId = user.empId;
        this.cid = user.cid;
        this.empCode = user.empCode;
        this.firstName = user.firstName;
        this.middleName = user.middleName;
        this.lastName = user.lastName;
        this.completeName = user.completeName;
        this.userName = user.userName;
        this.password = user.password;
        this.userGroup = user.userGroup;
        this.active = user.active;
        this.canReallocate = user.canReallocate;

    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
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

    public String getCompleteName() {
        return completeName;
    }

    public void setCompleteName(String completeName) {
        this.completeName = completeName;
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

    public int getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public int getCanReallocate() {
        return canReallocate;
    }

    public void setCanReallocate(int canReallocate) {
        this.canReallocate = canReallocate;
    }

    @Override
    public String toString() {
        return cid + " " + empId + " " + empCode;
    }
}

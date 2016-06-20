package ph.gardenia.com.model;

import java.io.Serializable;

/**
 * Created by otomatik on 8/17/15.
 */
public class User implements Serializable {

    public int empId;
    public int cid;
    public String empCode;
    public String firstName;
    public String middleName;
    public String lastName;
    public String completeName;
    public String userName;
    public String password;
    public int userGroup;
    public int active;
    public int canReallocate;

    @Override
    public String toString() {
        return super.toString();
    }
}

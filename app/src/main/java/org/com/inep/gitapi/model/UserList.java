package org.com.inep.gitapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Shiki on 27/12/2017.
 */
public class UserList {

    @SerializedName("total_count")
    private Integer count;

    @SerializedName("incomplete_results")
    private boolean result;

    @SerializedName("items")
    private List<User> users;


    /** ***************************************************** */
    /** ********************** GETTERs ********************** */
    /** ***************************************************** */
    public Integer getCount() {
        return count;
    }

    public boolean isResult() {
        return result;
    }

    public List<User> getUsers() {
        return users;
    }

    /** ***************************************************** */
    /** ********************** SETTERs ********************** */
    /** ***************************************************** */
    public void setCount(Integer count) {
        this.count = count;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    @Override
    public String toString() {
        String toStr = "";
        toStr += "USER LIST { ";
        toStr += "\t COUNT = " + getCount();
        toStr += ",\r";
        toStr += "\t RESULT = " + isResult();
        toStr += ", \r";
        toStr += "\t USER { ";
        for (User user : getUsers()) {
            toStr += user.getLogin();
            toStr += ", \r";
        }
        toStr += "\r\t}";
        toStr += "\r}";
        return super.toString();
    }
}

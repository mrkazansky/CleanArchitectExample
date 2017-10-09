package fpo.childhubmanagement.storage.model;

/**
 * Created by TranThaoUyen on 7/12/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OauthModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("exitcode")
    @Expose
    private Integer exitcode;
    @SerializedName("token")
    @Expose
    private String token;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getExitcode() {
        return exitcode;
    }

    public void setExitcode(Integer exitcode) {
        this.exitcode = exitcode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
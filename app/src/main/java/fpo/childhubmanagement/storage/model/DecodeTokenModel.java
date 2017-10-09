package fpo.childhubmanagement.storage.model;

/**
 * Created by TranThaoUyen on 7/12/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

public class DecodeTokenModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("exitcode")
    @Expose
    private Integer exitcode;
    @SerializedName("data")
    @Expose
    private DecodeTokenDataModel data;
    @SerializedName("error")
    @Expose
    private Object error;

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

    public DecodeTokenDataModel getData() {
        return data;
    }

    public void setData(DecodeTokenDataModel data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
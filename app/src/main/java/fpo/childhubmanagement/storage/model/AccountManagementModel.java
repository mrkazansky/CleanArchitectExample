package fpo.childhubmanagement.storage.model;

/**
 * Created by mrkaz on 7/26/2017.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

public class AccountManagementModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("exitcode")
    @Expose
    private Integer exitcode;
    @SerializedName("data")
    @Expose
    private List<DecodeTokenDataModel> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public AccountManagementModel() {
    }

    /**
     *
     * @param status
     * @param data
     * @param exitcode
     */
    public AccountManagementModel(String status, Integer exitcode, List<DecodeTokenDataModel> data) {
        super();
        this.status = status;
        this.exitcode = exitcode;
        this.data = data;
    }

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

    public List<DecodeTokenDataModel> getData() {
        return data;
    }

    public void setData(List<DecodeTokenDataModel> data) {
        this.data = data;
    }

}


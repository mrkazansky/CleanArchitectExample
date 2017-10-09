package fpo.childhubmanagement.domain.repository;

import java.util.List;

import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;


/**
 * Created by mrkaz on 7/26/2017.
 */

public interface AccountManagementRepository {
    List<DecodeTokenDataModel> getList(String token);
    void block(String token, String id);
}

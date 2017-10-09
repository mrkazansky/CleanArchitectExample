package fpo.childhubmanagement.domain.repository;

import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;

/**
 * Created by mrkaz on 7/18/2017.
 */

public interface AccountRepository {
    DecodeTokenDataModel decode(String token);
    String getTokenFromLogin(String username, String password);
    String checkToken();
    void saveToken(String token);
}

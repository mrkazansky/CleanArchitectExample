package fpo.childhubmanagement.storage;

import android.content.Context;

import java.io.IOException;

import fpo.childhubmanagement.contanst.CacheKey;
import fpo.childhubmanagement.domain.repository.AccountRepository;
import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;
import fpo.childhubmanagement.storage.network.RestClient;
import fpo.childhubmanagement.storage.model.DecodeTokenModel;
import fpo.childhubmanagement.storage.model.OauthModel;
import fpo.childhubmanagement.storage.savePreference.CacheClient;
import fpo.childhubmanagement.storage.services.AccountService;
import retrofit2.Response;

/**
 * Created by mrkaz on 7/18/2017.
 */
//Get data and give it to domain layer - this run in 1 thread ? or another thread ?
public class AccountRepositoryImpl implements AccountRepository  {
    private Context mContext;

    public AccountRepositoryImpl(Context context)
    {
        mContext=context;
    }

    @Override
    public DecodeTokenDataModel decode(String token) {
        AccountService service = RestClient.createService(AccountService.class);

        try{
            Response<DecodeTokenModel> response = service.decode(token).execute();
            if (response.isSuccessful())
            {
                return response.body().getData();
            }
            else
            {
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getTokenFromLogin(String username, String password) {

        AccountService service = RestClient.createService(AccountService.class);

        try {
            Response<OauthModel> response = service.login(username,password).execute();
            if (response.isSuccessful())
            {
                if (response.body().getExitcode()==1)
                {

                    return response.body().getToken();
                }
                else
                {

                    return null;
                }
            }
            else
            {
                return null;
            }
        } catch (IOException e) {

            return null;
        }


    }

    @Override
    public String checkToken() {
        return CacheClient.getCache(mContext, CacheKey.TOKEN);
    }

    @Override
    public void saveToken(String token) {
        CacheClient.setCache(mContext,CacheKey.TOKEN,token);
    }
}

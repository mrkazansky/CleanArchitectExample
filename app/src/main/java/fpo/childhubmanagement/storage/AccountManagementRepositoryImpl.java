package fpo.childhubmanagement.storage;

import android.content.Context;

import java.io.IOException;
import java.util.List;

import fpo.childhubmanagement.shareModel.DecodeTokenDataModel;
import fpo.childhubmanagement.storage.network.RestClient;
import fpo.childhubmanagement.storage.services.AccountManagementService;
import retrofit2.Response;

/**
 * Created by mrkaz on 7/26/2017.
 */

public class AccountManagementRepositoryImpl implements fpo.childhubmanagement.domain.repository.AccountManagementRepository {
    private Context context;
    public AccountManagementRepositoryImpl(Context context)
    {
        this.context=context;
    }

    @Override
    public List<DecodeTokenDataModel> getList(String token) {
        AccountManagementService service = RestClient.createService(AccountManagementService.class);

        try{
            Response<fpo.childhubmanagement.storage.model.AccountManagementModel> response = service.getList(token).execute();
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
    public void block(String token, String id) {

    }
}

package fpo.childhubmanagement.storage.services;


import fpo.childhubmanagement.storage.model.DecodeTokenModel;
import fpo.childhubmanagement.storage.model.OauthModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * A REST interface describing how data will be synced with the backend.
 * <p/>
 */
public interface AccountService {

    /**
     * This endpoint will be used to send new costs created on this device.
     */
    @FormUrlEncoded
    @POST("/account/oauth2")
    Call<OauthModel> login(@Field("username") String username,
                           @Field("password") String password);

    @FormUrlEncoded
    @POST("/util/decode")
    Call<DecodeTokenModel> decode(@Field("token") String token);
}
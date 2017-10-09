package fpo.childhubmanagement.storage.services;

import fpo.childhubmanagement.storage.model.AccountManagementModel;
import fpo.childhubmanagement.storage.model.DecodeTokenModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by mrkaz on 7/26/2017.
 */

public interface AccountManagementService {
    @FormUrlEncoded
    @POST("/account/get")
    Call<AccountManagementModel> getList(@Field("token") String token);


    //token, accountId, oldPassword, password, fullname, gender, birthday (2016-05-02), phone, email, address, permission
    @FormUrlEncoded
    @POST("/account/update")
    Call<AccountManagementModel> update(@Field("token") String token,
                                        @Field("accountId") String accountId,
                                        @Field("oldPassword") String oldPassword,
                                        @Field("password") String password,
                                        @Field("fullname") String fullname,
                                        @Field("gender") String gender,
                                        @Field("birthday") String birthday,
                                        @Field("phone") String phone,
                                        @Field("email") String email,
                                        @Field("address") String address,
                                        @Field("permission") String permission );
}

package cloud.mosque.com.mosques.service;

import cloud.mosque.com.mosques.home.model.DataResponse;
import cloud.mosque.com.mosques.login.model.Login;
import cloud.mosque.com.mosques.register.model.Register;
import cloud.mosque.com.mosques.testmodel.Example;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

public interface MosqueService {

    @POST("api/v1/login")
    @FormUrlEncoded
    Observable<Login> sendLoginMosque(@Field("username") String username,
                                @Field("password") String password);

    @POST("api/v1/register")
    @FormUrlEncoded
    Observable<Register> sendRegisterMosque(@Field("username")String username,
                                            @Field("password")String password,
                                            @Field("email")String email,
                                            @Field("full_name")String full_name,
                                            @Field("last_name")String last_name);

    @GET("api/v1/prayer_places")
    Observable<DataResponse> getDataMosque(@Header("Access-Token") String token);

    @GET("list_all_outlet")
    Observable<Example> getDataDetail();


}

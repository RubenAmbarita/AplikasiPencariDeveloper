package metrodata.mii.aplikasideveloper.Api;

import metrodata.mii.aplikasideveloper.Model.m.register.Register.ResponseUser;
import metrodata.mii.aplikasideveloper.Model.m.register.editProfil.ResponseEdit;
import metrodata.mii.aplikasideveloper.Model.m.register.getKontak.ResponseKontak;
import metrodata.mii.aplikasideveloper.Model.m.register.getPesan.ResponsePesan;
import metrodata.mii.aplikasideveloper.Model.m.register.getdata.ResponseGetData;
import metrodata.mii.aplikasideveloper.Model.m.register.insertChat.ResponseInsert;
import metrodata.mii.aplikasideveloper.Model.m.register.m.login.ResponseLogin;
import metrodata.mii.aplikasideveloper.Model.m.register.statusLaptop.ResponseStatus;
import metrodata.mii.aplikasideveloper.Model.m.register.user.ResponseUserProfile;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    //servis untuk register
    @FormUrlEncoded
    @POST("ApiRegister.php")
    Call<ResponseUser> requestRegister(@Field("fullname") String fullname,
                                       @Field("email") String email,
                                       @Field("no_hp") String no_hp,
                                       @Field("tgl_lahir") String tgl_lahir,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("ApiLogin.php")
    Call<ResponseLogin> requestLogin(@Field("email") String email,
                                     @Field("password") String password);

    @FormUrlEncoded
    @POST("ApiProfil.php")
    Call<ResponseUserProfile> requestProfil(@Field("email") String email);

    @FormUrlEncoded
    @POST("ApiEditProfil.php")
    Call<ResponseEdit> requestEdit(@Field("id_user") String id_user,
                                   @Field("fullname") String fullname,
                                   //@Field("email") String email,
                                   @Field("no_hp") String no_hp,
                                   @Field("tgl_lahir") String tgl_lahir);

    @GET("ApiGet.php")
    Call<ResponseGetData> requestData();

//    @FormUrlEncoded
//    @POST("ApiRegister.php")
//    Call<ResponseUser> requestProfil(@Field("fullname")String fullname,
//                                     @Field("email") String email,
//                                     @Field("no_hp") String no_hp,
//                                     @Field("tgl_lahir") String tgl_lahir);

    @FormUrlEncoded
    @POST("tampil_pesan.php")
    Call<ResponsePesan> tampilPesan(@Field("message_sender") String sender,
                                    @Field("message_to") String to,
                                    @Field("message_sender1") String sender1,
                                    @Field("message_to1") String to1);

    @FormUrlEncoded
    @POST("ApiStatus.php")
    Call<ResponseStatus> tampilStatus(@Field("nama_hr_factor") String nama,
                                      @Field("value_hr_factor") String value);

    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseInsert> insertMessage(@Field("message_body") String body,
                                       @Field("message_sender")String sender,
                                       @Field("message_to") String to,
                                       @Field("message_unread") String unread);

    @FormUrlEncoded
    @POST("ApiGetKontak.php")
    Call<ResponseKontak> getKontak(@Field("id") String id);
}

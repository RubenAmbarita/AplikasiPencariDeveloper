package metrodata.mii.aplikasideveloper.Api;

import metrodata.mii.aplikasideveloper.Helper.HelpConstanta;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InitRetrofit {

    //method conversi hasil api ke json
    public static Retrofit setInit() {
        return new Retrofit.Builder().baseUrl(HelpConstanta.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static ApiServices getInstance() {
        return setInit().create(ApiServices.class);
    }
}

package org.sang.gank.util;

import org.sang.gank.net.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 王松 on 2016/9/16.
 */
public class NetUtils {
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).build();
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static ApiService getApiService() {
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }
}

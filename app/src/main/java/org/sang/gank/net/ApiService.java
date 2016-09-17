package org.sang.gank.net;

import org.sang.gank.bean.ItemBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by 王松 on 2016/9/16.
 */
public interface ApiService {
    @GET("category/{category}/count/{count}/page/{page}")
    Call<ItemBean> getItemBeen(@Path("category") String category, @Path("count") String count, @Path("page") String page);
}

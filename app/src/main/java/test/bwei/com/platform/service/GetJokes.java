package test.bwei.com.platform.service;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import test.bwei.com.platform.bean.BaseBean;
import test.bwei.com.platform.jsonbean.JokeBean;
import test.bwei.com.platform.common.Api;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/28
 * Description:
 */

public interface GetJokes {
    @Headers("cache:20")
    @GET(Api.GETJOKES)
    Observable<JokeBean> getJokes(@Query("page") String page);

    @POST(Api.PRAISEJOKE)
    @FormUrlEncoded
    Observable<BaseBean> Praise(@FieldMap Map<String,String> map);
}

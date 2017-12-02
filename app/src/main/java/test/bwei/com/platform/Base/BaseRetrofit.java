package test.bwei.com.platform.Base;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.bwei.com.platform.common.Api;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/1
 * Description:
 */

public class BaseRetrofit {
        private static BaseRetrofit instence=new BaseRetrofit();
        private BaseRetrofit(){}
        public static BaseRetrofit getInstence(){
            return instence;
        }

    public Retrofit getRetrofit() {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new MyIntercepter())
                .build();
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Api.BASEURL).build();
        return retrofit;
    }
}


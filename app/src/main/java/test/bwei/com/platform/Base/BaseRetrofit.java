package test.bwei.com.platform.Base;

import android.app.Application;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.cache.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.bwei.com.platform.application;
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
    public Retrofit getRetrofitCache() {
        String cacheFile = application.getContext().getCacheDir()+"/http";
        Cache cache = new Cache(new File(cacheFile), 10 * 1024 * 1024);
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new MyIntercepter())
                .addNetworkInterceptor(CacheInterceptor.REWRITE_RESPONSE_INTERCEPTOR)
                .addInterceptor(CacheInterceptor.REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                .cache(cache)
                .build();
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Api.BASEURL).build();
        return retrofit;
    }
}


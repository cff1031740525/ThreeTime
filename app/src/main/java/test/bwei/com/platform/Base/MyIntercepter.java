package test.bwei.com.platform.Base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import test.bwei.com.platform.application;

/**
 * current package:zhanghegang.com.bawei.onetime.utils
 * Created by Mr.zhang
 * date: 2017/11/28
 * decription:开发
 */

public class MyIntercepter implements Interceptor {
    private final String TAG = "respond";
    private int versionCode;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        SharedPreferences sp = application.getContext().getSharedPreferences("userinfo", application.getContext().MODE_PRIVATE);
        String token = sp.getString("token", null);
        System.out.println(request.method() + "开始添加公共参数222222222");

        try {
            Context context = application.getContext();
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        if ("POST".equals(request.method())) {

            System.out.println(request.method() + "开始添加公共参数3333333333" + request.body().toString());
            if (request.body() instanceof FormBody) {
                System.out.println("FormBody开始添加公共参数");
                FormBody.Builder builder = new FormBody.Builder();
                FormBody body = (FormBody) request.body();

                for (int i = 0; i < body.size(); i++) {
                    builder.add(body.encodedName(i), body.encodedValue(i));
                }

                body = builder.add("source", "android").build();
                body = builder.add("appVersion", String.valueOf(versionCode)).build();
                body = builder.add("token", token + "").build();
                System.out.println("开始添加公共参数55555");
                request = request.newBuilder().post(body).build();

            } else if (request.body() instanceof MultipartBody) {
                System.out.println("MultipartBody开始添加公共参数");
                MultipartBody body = (MultipartBody) request.body();
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("source", "android")
                        .addFormDataPart("appVersion", versionCode + "")
                        .addFormDataPart("token", token + "");
                List<MultipartBody.Part> parts = body.parts();
                for (MultipartBody.Part part : parts) {
                    builder.addPart(part);
                }
                request = request.newBuilder().post(builder.build()).build();

            }
        } else if ("GET".equals(request.method())) {
            HttpUrl httpUrl = request.url()
                    .newBuilder()
                    .addQueryParameter("token",token)
                    .addQueryParameter("source","android")
                    .addQueryParameter("appVersion", "101")
                    .build();
            request = request.newBuilder().url(httpUrl).build();
        }

        //System.out.println("开始添加公共参数44444444444" + chain.proceed(request).body().string());

        return chain.proceed(request);

    }


}

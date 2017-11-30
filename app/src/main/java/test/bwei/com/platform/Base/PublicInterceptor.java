package test.bwei.com.platform.Base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.http.Url;
import test.bwei.com.platform.application;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/18
 * Description:
 */

public class PublicInterceptor implements Interceptor {
    public String TAG = "LogInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        SharedPreferences sp = application.getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String token = sp.getString("token", null);
        Log.d(TAG, "\n");
        Log.d(TAG, "----------Start----------------");
        String method = request.method();
        if ("POST".equals(method)) {
            FormBody.Builder sb = new FormBody.Builder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.add(body.encodedName(i), body.encodedValue(i));
                }
                body = sb.add("source", "android").add("appVersion", "101")
                        .add("token", token)
                        .build();
                request = request.newBuilder().post(body).build();
                Log.d(TAG, "| " + request.toString());
            } else {
                MultipartBody body = (MultipartBody) request.body();
                MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
                build.addFormDataPart("source", "android");
                build.addFormDataPart("appVersion", "101");
                build.addFormDataPart("token", token);
                List<MultipartBody.Part> parts = body.parts();
                for (MultipartBody.Part part : parts) {
                    build.addPart(part);
                }
                request = request.newBuilder().post(build.build()).build();

            }
        }

      /*   String content = proceed.body().string();
             long duration=endTime-startTime;
            Log.d(TAG, "| Response:" + content);
            Log.d(TAG,"----------End:"+duration+"毫秒----------");*/
        return chain.proceed(request);
    }
}
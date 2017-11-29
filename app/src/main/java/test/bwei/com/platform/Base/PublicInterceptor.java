package test.bwei.com.platform.Base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.IOException;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
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

public class PublicInterceptor implements Interceptor{
    public Context context;

    public PublicInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //判断当前的请求
        if (request.method().equals("POST")){
            //判断当前的请求Boby
            if (request.body() instanceof FormBody){
                //创建一个新的FromBoby
                FormBody.Builder bobyBuilder = new FormBody.Builder();
                //获取原先的boby
                FormBody body = (FormBody) request.body();
                //遍历boby
                for (int i = 0; i < body.size(); i++) {
                    //取出原先boby的数据  存入新的boby里
                    bobyBuilder.add(body.encodedName(i),body.encodedValue(i));
                }
                //添加制定的公共参数到新的boby里  把原先的boby给替换掉
                try {
                    PackageManager pm = context.getPackageManager();//得到PackageManager对象
                    PackageInfo pi = null;//得到PackageInfo对象，封装了一些软件包的信息在里面
                    pi = pm.getPackageInfo(context.getPackageName(), 0);
                    int appVersion = pi.versionCode;//获取清单文件中versionCode节点的值
                    SharedPreferences userinfo = application.getContext().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                    String token = userinfo.getString("token", null);
                    if(token!=null){
                        body=bobyBuilder.add("token",token).build();
                    }
                    body=bobyBuilder.add("source","android").build();
                     body=bobyBuilder.add("appVersion",appVersion+"").build();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                request=request.newBuilder().post(body).build();
            }
        }
        return chain.proceed(request);
    }
}

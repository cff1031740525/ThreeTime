package test.bwei.com.platform.model;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.bwei.com.platform.Base.MyIntercepter;
import test.bwei.com.platform.Base.PublicInterceptor;
import test.bwei.com.platform.jsonbean.loginbean;
import test.bwei.com.platform.common.Api;
import test.bwei.com.platform.service.loginService;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/15
 * Description:
 */

public class LoginModel {
    private Context context;

    public LoginModel(Context context) {
        this.context = context;
    }

    public void LoginM(String user, String password){
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(new MyIntercepter())
                .build();
        Retrofit retrofit=new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Api.BASEURL).build();
        loginService loginService = retrofit.create(loginService.class);
        Map<String,String> map=new HashMap<>();
        map.put("mobile",user);
        map.put("password",password);
        loginService.getstatus(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<loginbean>() {
                    @Override
                    public void accept(loginbean loginbean) throws Exception {
                        if(loginbean!=null&&loginbean.code.equals("0")){
                            loginListener.LoginSuccess(loginbean);
                        }else{
                            loginListener.LoginFail(loginbean.msg);
                        }

                    }
                });
    }
    private LoginListener loginListener;
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
    public interface LoginListener{
        void LoginSuccess(loginbean bean);
        void LoginFail(String msg);
    }
}

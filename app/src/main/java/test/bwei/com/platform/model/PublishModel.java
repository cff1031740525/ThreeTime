package test.bwei.com.platform.model;

import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Multipart;
import test.bwei.com.platform.Base.MyIntercepter;
import test.bwei.com.platform.Base.PublicInterceptor;
import test.bwei.com.platform.activity.ThreeActionActivity;
import test.bwei.com.platform.common.Api;
import test.bwei.com.platform.jsonbean.PublishBean;
import test.bwei.com.platform.service.PublishService;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/29
 * Description:
 */

public class PublishModel {
    private Context context;

    public PublishModel(Context context) {
        this.context = context;
    }

    public void publishJokes(String uid, final String content, List<String> path) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new MyIntercepter())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Api.BASEURL).build();

        PublishService publishService = retrofit.create(PublishService.class);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid", uid + "")
                .addFormDataPart("content", content);
        if (path != null && path.size() > 0) {
            for (String s : path) {
                File file = new File(s);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("jokeFiles", s, requestBody);

            }
        }
        MultipartBody sss = builder.build();
        List<MultipartBody.Part> partss = sss.parts();
        publishService.publishImgJoke(partss).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PublishBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PublishBean publishBean) {
                        if (publishBean.code.equals("0")) {
                            publishInterface.Success(publishBean.msg);
                        } else if (publishBean.code.equals("2")) {
                            publishInterface.Fail("2");
                        } else {
                            publishInterface.Fail(publishBean.msg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private PublishInterface publishInterface;

    public void setPublishInterface(PublishInterface publishInterface) {
        this.publishInterface = publishInterface;
    }

    public interface PublishInterface {
        void Success(String msg);

        void Fail(String msg);
    }
}

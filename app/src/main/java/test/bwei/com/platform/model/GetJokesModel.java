package test.bwei.com.platform.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import test.bwei.com.platform.Base.MyIntercepter;
import test.bwei.com.platform.Base.PublicInterceptor;
import test.bwei.com.platform.bean.UseJokeBean;
import test.bwei.com.platform.common.Api;
import test.bwei.com.platform.jsonbean.JokeBean;
import test.bwei.com.platform.service.GetJokes;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/28
 * Description:
 */

public class GetJokesModel {
    private Context context;
    private List<UseJokeBean> list ;
    public GetJokesModel(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void loadMore(String page){
        getJokes(page);

    }
    public void getJokes(String page) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new MyIntercepter())
                .build();
        Retrofit retrofit = new Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(Api.BASEURL).build();
        final GetJokes getJokes = retrofit.create(GetJokes.class);
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        getJokes.getJokes(map)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JokeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JokeBean jokeBean) {
                        if (jokeBean.code.equals("0")) {
                            UseJokeBean bean = null;
                            for (JokeBean.DataBean datum : jokeBean.data) {
                                bean = new UseJokeBean();
                                bean.commentNum = (String) datum.commentNum;
                                bean.praiseNum = (String) datum.praiseNum;
                                bean.shareNum = (String) datum.shareNum;
                                bean.content = datum.content;
                                bean.createtime = datum.createTime;
                                bean.image = datum.user.icon;
                                bean.nickname = datum.user.nickname;
                                bean.imgurls=datum.imgUrls;
                                bean.jid = datum.jid;
                                list.add(bean);
                                bean = null;
                            }
                            getUseJokes.Success(list);
                        } else {
                            getUseJokes.Fail("请求失败,请刷新重试");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            throw (e);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

  private GetUseJokes getUseJokes;

    public void setGetUseJokes(GetUseJokes getUseJokes) {
        this.getUseJokes = getUseJokes;
    }

    public interface GetUseJokes {
        void Success(List<UseJokeBean> list);
        void Fail(String msg);
    }
}

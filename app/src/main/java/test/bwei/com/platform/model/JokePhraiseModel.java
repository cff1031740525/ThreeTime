package test.bwei.com.platform.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import test.bwei.com.platform.Base.BaseRetrofit;
import test.bwei.com.platform.bean.BaseBean;
import test.bwei.com.platform.service.GetJokes;
import test.bwei.com.platform.service.PublishService;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/1
 * Description:
 */

public class JokePhraiseModel {

    public void PhraiseJoke(String uid, String jid) {
        Retrofit retrofit = BaseRetrofit.getInstence().getRetrofit();
        GetJokes getJokes = retrofit.create(GetJokes.class);
        Map<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("jid", jid);

        getJokes.Praise(map)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean>() {
                    @Override
                    public void accept(BaseBean baseBean) throws Exception {
                            if(baseBean.code.equals("0")){
                                    phraiseJoke.Success("点赞成功");
                            }else if(baseBean.code.equals("2")){
                                phraiseJoke.Fail("2");
                            }else{
                                phraiseJoke.Fail("点赞失败");
                            }
                    }
                });
    }
    private PhraiseJoke phraiseJoke;

    public void setPhraiseJoke(PhraiseJoke phraiseJoke) {
        this.phraiseJoke = phraiseJoke;
    }

    public interface PhraiseJoke{
        void Success(String msg);
        void Fail(String msg);
    }
}

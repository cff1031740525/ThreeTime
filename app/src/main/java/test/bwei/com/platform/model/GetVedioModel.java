package test.bwei.com.platform.model;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import test.bwei.com.platform.Base.BaseRetrofit;
import test.bwei.com.platform.jsonbean.VedioBean;
import test.bwei.com.platform.service.GetVedioService;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/2
 * Description:
 */

public class GetVedioModel {

    public void getVedioMore(String uid, String type, String page){
        getVedioInfo(uid,type,page);
    }
    public void getVedioInfo(String uid, String type, String page) {
        Retrofit retrofit = BaseRetrofit.getInstence().getRetrofit();
        GetVedioService getVedioService = retrofit.create(GetVedioService.class);
        Map<String,String> map=new HashMap<>();
        map.put("uid",uid);
        map.put("type",type);
        map.put("page",page);
        getVedioService.getVedio(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<VedioBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(VedioBean vedioBean) {
                        if(vedioBean.code.equals("0")){

                            getVedioInterface.VSuccess(vedioBean);
                        }else if(vedioBean.code.equals("2")){
                            getVedioInterface.VFail("2");
                        }else{
                            getVedioInterface.VFail(vedioBean.msg);
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
    private GetVedioInterface getVedioInterface;

    public void setGetVedioInterface(GetVedioInterface getVedioInterface) {
        this.getVedioInterface = getVedioInterface;
    }

    public interface GetVedioInterface{
        void VSuccess(VedioBean vedioBean);
        void VFail(String msg);
    }
}

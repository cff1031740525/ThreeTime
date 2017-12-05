package test.bwei.com.platform.model;


import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;
import test.bwei.com.platform.Base.BaseRetrofit;
import test.bwei.com.platform.jsonbean.PublishBean;
import test.bwei.com.platform.service.PVeiosService;
import test.bwei.com.platform.view.MainInterface;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/5
 * Description:
 */

public class PVedioModel {
    public void PublishVedio(String uid, File vediofile, File coverImgPath, String vedioDesc, String latitude, String longitude) {
        Retrofit retrofit = BaseRetrofit.getInstence().getRetrofit();
        PVeiosService pVeiosService = retrofit.create(PVeiosService.class);
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid", uid)
                .addFormDataPart("vedioDesc", vedioDesc)
                .addFormDataPart("latitude", latitude)
                .addFormDataPart("longitude", longitude);
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), vediofile);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("multipart/form-data"), coverImgPath);
        builder.addFormDataPart("videoFile", vediofile.getPath(), requestBody1);
        builder.addFormDataPart("coverFile", coverImgPath.getPath(), requestBody2);
        MultipartBody build = builder.build();
        List<MultipartBody.Part> parts = build.parts();
        pVeiosService.publishVedio(parts)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PublishBean>() {
                    @Override
                    public void accept(PublishBean publishBean) throws Exception {
                            if(publishBean.code.equals("0")){
                                mIntervice.MSuccess(publishBean.msg);
                            }else{
                                mIntervice.MFail(publishBean.code);
                            }
                    }
                });

    }
        private MIntervice mIntervice;

    public void setmIntervice(MIntervice mIntervice) {
        this.mIntervice = mIntervice;
    }

    public interface MIntervice {
        void MSuccess(String msg);
        void MFail(String msg);
    }
}

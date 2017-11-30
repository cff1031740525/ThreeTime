package test.bwei.com.platform.service;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;
import test.bwei.com.platform.common.Api;
import test.bwei.com.platform.jsonbean.PublishBean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/29
 * Description:
 */

public interface PublishService {
    @POST(Api.PUBLISHJOKE)
    @FormUrlEncoded
    Observable<PublishBean> publishJoke(@FieldMap Map<String, String> map);


    @POST(Api.PUBLISHJOKE)
    @Multipart
    Observable<PublishBean>  publishImgJoke(@Part List<MultipartBody.Part> list);




}

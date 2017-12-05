package test.bwei.com.platform.service;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import test.bwei.com.platform.common.Api;
import test.bwei.com.platform.jsonbean.PublishBean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/5
 * Description:
 */

public interface PVeiosService {
    @POST(Api.PVEDIO)
    @Multipart
    Observable<PublishBean> publishVedio(@Part List<MultipartBody.Part> list);
}

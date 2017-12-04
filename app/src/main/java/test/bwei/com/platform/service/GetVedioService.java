package test.bwei.com.platform.service;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import test.bwei.com.platform.common.Api;
import test.bwei.com.platform.jsonbean.VedioBean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/2
 * Description:
 */

public interface GetVedioService {
    @POST(Api.GETVIDEOS)
    @FormUrlEncoded
    Observable<VedioBean> getVedio(@FieldMap Map<String, String> map);
}

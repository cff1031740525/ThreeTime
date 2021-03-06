package test.bwei.com.platform.service;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import test.bwei.com.platform.jsonbean.loginbean;
import test.bwei.com.platform.common.Api;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/20
 * Description:
 */

public interface loginService {
    @POST(Api.LOGIN)
    @FormUrlEncoded
    Observable<loginbean> getstatus(@FieldMap Map<String,String> map);
}

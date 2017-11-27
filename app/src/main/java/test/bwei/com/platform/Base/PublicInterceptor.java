package test.bwei.com.platform.Base;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/18
 * Description:
 */

public class PublicInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String method = request.method();

        return null;
    }
}

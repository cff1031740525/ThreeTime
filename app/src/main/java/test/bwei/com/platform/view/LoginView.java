package test.bwei.com.platform.view;

import test.bwei.com.platform.jsonbean.loginbean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/15
 * Description:
 */

public interface LoginView {
    void Success(loginbean loginBean);
    void Fail(String msg);
}

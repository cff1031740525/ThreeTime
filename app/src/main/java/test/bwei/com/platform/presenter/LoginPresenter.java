package test.bwei.com.platform.presenter;

import test.bwei.com.platform.Base.BasePresenter;
import test.bwei.com.platform.model.LoginModel;
import test.bwei.com.platform.view.LoginView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/15
 * Description:
 */

public class LoginPresenter extends BasePresenter<LoginView>{
    private LoginModel loginModel;
    public LoginPresenter(LoginView mView) {
        super(mView);
    }
}

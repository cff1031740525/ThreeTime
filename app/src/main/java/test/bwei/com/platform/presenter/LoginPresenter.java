package test.bwei.com.platform.presenter;

import android.content.Context;

import test.bwei.com.platform.Base.BasePresenter;
import test.bwei.com.platform.jsonbean.loginbean;
import test.bwei.com.platform.model.LoginModel;
import test.bwei.com.platform.view.LoginView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/15
 * Description:
 */

public class LoginPresenter extends BasePresenter<LoginView> implements LoginModel.LoginListener{
    private LoginModel loginModel;
    private LoginView loginView;
    private Context context;
    public LoginPresenter(LoginView mView,Context context) {
        super(mView);
        this.context=context;
        loginModel=new LoginModel(context);
        loginModel.setLoginListener(this);
        loginView=mView;
    }

    public void PresenterLogin(String user,String password){

        loginModel.LoginM(user,password);
    }

    @Override
    public void LoginSuccess(loginbean bean) {
            loginView.Success(bean);
    }

    @Override
    public void LoginFail(String msg) {
        loginView.Fail(msg);
    }
}

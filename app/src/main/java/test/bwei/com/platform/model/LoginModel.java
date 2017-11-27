package test.bwei.com.platform.model;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/15
 * Description:
 */

public class LoginModel {

    public void LoginM(String user,String password){

    }
    private LoginListener loginListener;
    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }
    public interface LoginListener{
        void LoginSuccess();
        void LoginFail();
    }
}

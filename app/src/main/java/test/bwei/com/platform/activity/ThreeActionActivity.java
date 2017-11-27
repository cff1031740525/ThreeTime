package test.bwei.com.platform.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import test.bwei.com.platform.Base.BaseActivity;
import test.bwei.com.platform.R;
import test.bwei.com.platform.model.LoginModel;
import test.bwei.com.platform.presenter.LoginPresenter;

public class ThreeActionActivity extends BaseActivity<LoginPresenter>{

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public LoginPresenter initPresenter() {
        return null;
    }
}

package test.bwei.com.platform.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/12
 * Description:
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(getLayoutId());

        initView();
        mPresenter = initPresenter();
        initData();

    }

    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();

    //初始化presenter的抽象方法
    public abstract T initPresenter();

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.Deatch();
        }

    }
}

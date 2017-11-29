package test.bwei.com.platform.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import test.bwei.com.platform.Base.BaseActivity;
import test.bwei.com.platform.R;
import test.bwei.com.platform.jsonbean.loginbean;
import test.bwei.com.platform.presenter.LoginPresenter;
import test.bwei.com.platform.view.LoginView;

public class ThreeActionActivity extends BaseActivity<LoginPresenter> implements LoginView{

    private EditText user;
    private EditText password;
    private Button btn;
    private LoginPresenter loginPresenter;
    private SharedPreferences sp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_three_action;

    }

    @Override
    public void initView() {
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        btn = findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = user.getText().toString();
                String psd = password.getText().toString();
                loginPresenter.PresenterLogin(id,psd);
            }
        });
    }

    @Override
    public void initData() {
        loginPresenter=new LoginPresenter(this,this);
        sp = getSharedPreferences("userinfo", MODE_PRIVATE);
        int uid = sp.getInt("uid", -100);
        if(uid>=0){
            Intent intent=new Intent(ThreeActionActivity.this,OneTimeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public LoginPresenter initPresenter() {
        return loginPresenter;
    }


    @Override
    public void Success(loginbean loginBean) {
        Toast.makeText(this,loginBean.msg+"登录成功",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(ThreeActionActivity.this,OneTimeActivity.class);
        sp.edit().remove("token").commit();
        sp.edit().remove("uid").commit();
        sp.edit().putInt("uid",loginBean.data.uid).commit();
        sp.edit().putString("token",loginBean.data.token).commit();
        startActivity(intent);
        finish();
    }

    @Override
    public void Fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

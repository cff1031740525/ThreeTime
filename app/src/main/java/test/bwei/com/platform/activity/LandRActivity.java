package test.bwei.com.platform.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hxe.platform.R;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import test.bwei.com.platform.Base.BaseActivity;

import test.bwei.com.platform.presenter.LoginPresenter;

import static android.Manifest.permission_group.SMS;

public class LandRActivity extends BaseActivity<LoginPresenter> {


    private EditText userid;
    private EditText password;
    private Button yzm;
    private Button login;
    private ImageView qqlogin;
    private ImageView wxlogin;
    private EventHandler eventHandler;
    private int second = 30;
    private Timer timer;
    private TimerTask task;
    private TextView another;
    private boolean flag = true;
    private boolean flag1 = true;
    private RelativeLayout r3;
    private RelativeLayout r6;
    private EditText psd6;
    private ImageView yan;

    @Override
    public int getLayoutId() {
        return R.layout.activity_land_r;
    }

    @Override
    public void initView() {
        userid = findViewById(R.id.userid);
        password = findViewById(R.id.psd);
        yzm = findViewById(R.id.yzmbtn);
        login = findViewById(R.id.login);
        qqlogin = findViewById(R.id.qqlogin);
        wxlogin = findViewById(R.id.wxlogin);
        another = findViewById(R.id.another);
        r3 = findViewById(R.id.relativeLayout3);
        r6 = findViewById(R.id.relativeLayout6);
        psd6 = findViewById(R.id.psd6);
        yan = findViewById(R.id.yzmbtn6);
        yan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag1) {
                    yan.setImageResource(R.mipmap.eyeopen);
                    psd6.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag1 = false;
                } else {
                    yan.setImageResource(R.mipmap.eyeclose);
                    psd6.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag1 = true;
                }
            }
        });
        another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    r3.setVisibility(View.GONE);
                    r6.setVisibility(View.VISIBLE);
                    another.setText("短信验证码登录方式");
                    flag = false;
                } else {
                    r3.setVisibility(View.VISIBLE);
                    r6.setVisibility(View.GONE);
                    another.setText("使用常规登录方式");
                    flag = true;
                }
            }
        });
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                int s = (int) msg.obj;
                if (s == 0) {
                    second = 30;
                    yzm.setEnabled(true);
                    task.cancel();
                    yzm.setText("重新获取");
                    yzm.setBackgroundResource(R.drawable.dlbackgroud);
                    return;
                }
                yzm.setText(s + "s后");
            }
        };
        yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = userid.getText().toString();
                if (s.length() != 11) {
                    Toast.makeText(LandRActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (s == null) {
                    Toast.makeText(LandRActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.getVerificationCode("86", s);
                yzm.setEnabled(false);

                yzm.setBackgroundResource(R.drawable.redlbackgroud);
                timer = new Timer();
                task = new TimerTask() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        msg.obj = second;
                        handler.sendMessage(msg);
                        second--;
                    }
                };
                timer.schedule(task, 0, 1000);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LandRActivity.this,OrderActivity.class);
                        startActivity(intent);
                        finish();
                String s1 = userid.getText().toString();
                String s = password.getText().toString();
                if (s == null) {
                    Toast.makeText(LandRActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.submitVerificationCode("86", s1, s);
            }
        });
    }

    @Override
    public void initData() {
        eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                if (data instanceof Throwable) {
                    Throwable throwable = (Throwable) data;
                    final String msg = throwable.getMessage();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LandRActivity.this, msg, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Toast.makeText(LandRActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //TODO   登录操作
                            Toast.makeText(LandRActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        }
                    });

                }

            }
        };
        SMSSDK.registerEventHandler(eventHandler);

    }

    @Override
    public LoginPresenter initPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}

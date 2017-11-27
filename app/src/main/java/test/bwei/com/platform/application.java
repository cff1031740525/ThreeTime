package test.bwei.com.platform;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.igexin.sdk.PushManager;
import com.mob.MobSDK;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import static com.igexin.sdk.GTServiceManager.context;


/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/13
 * Description:
 */

public class application extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "54b9f7c7d0", false);
        LeakCanary.install(this);
       PushManager.getInstance().initialize(this.getApplicationContext(), GtService.class);
       PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), IntentService.class);
        Fresco.initialize(this);
// 通过代码注册你的AppKey和AppSecret
        MobSDK.init(getApplicationContext() , "227a7f3745748", "dfdce85ac0f749b107a5aac1400ec8fe");
    }
}

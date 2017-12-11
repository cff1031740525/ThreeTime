package test.bwei.com.platform.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hxe.platform.R;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.bwei.com.platform.Base.DataClearManger;

public class SetActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.update_version)
    RelativeLayout updateversion;
    @BindView(R.id.tv_versioncode)
    TextView tvVersioncode;
    @BindView(R.id.cache_size)
    TextView cacheSize;
    @BindView(R.id.clear_cache)
    RelativeLayout clearCache;
    @BindView(R.id.unlogin)
    Button unlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        try {
            String cz = DataClearManger.getCacheSize(new File("/data/data/com.hxe.platform"));
            cacheSize.setText(cz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initView();
        PackageManager manager = this.getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(this.getPackageName(), 0);
            String versionName = info.versionName;
            int versionCode = info.versionCode;
            tvVersioncode.setText(versionName);
            System.out.println("versionName"+versionName+"versionCode"+versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        clearCache.setOnClickListener(this);
        updateversion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.clear_cache:
                DataClearManger.cleanApplicationData(SetActivity.this,"/data/data/com.hxe.platform");
                Toast.makeText(this,"清除完成",Toast.LENGTH_SHORT).show();
                cacheSize.setText("0.00M");
                break;
        }
    }
}

package test.bwei.com.platform.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.hxe.platform.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import test.bwei.com.platform.Base.BaseActivity;
import test.bwei.com.platform.presenter.PVedioPresenter;
import test.bwei.com.platform.view.PVedioView;

public class PublishVediosActivity extends BaseActivity<PVedioPresenter> implements PVedioView, View.OnClickListener {
    @BindView(R.id.coverschange)
    ImageView coverschange;
    @BindView(R.id.et_pvedio)
    EditText etPvedio;
    @BindView(R.id.btn_pvedio)
    Button btnPvedio;
    @BindView(R.id.pbrale)
    RelativeLayout pbrale;
    private PVedioPresenter pVedioPresenter;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private List<String> list;
    private String la;
    private String lo;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            list = new ArrayList<>();
            String location = (String) msg.obj;
            String[] ls = location.split("l");
            la = ls[0];
            lo = ls[1];

        }
    };
    private String vediopath;
    private int uid;
    private File vediofile;
    private String coverpath;


    @Override
    public int getLayoutId() {
        return R.layout.activity_publish_vedios;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        btnPvedio.setOnClickListener(this);
        coverschange.setOnClickListener(this);
    }

    @Override
    public void initData() {
        InitMap();
        Intent intent = getIntent();
        vediopath = intent.getStringExtra("vediopath");
        SharedPreferences userinfo = getSharedPreferences("userinfo", MODE_PRIVATE);
        uid = userinfo.getInt("uid", -100);
        vediofile = new File(vediopath);


    }

    @Override
    public PVedioPresenter initPresenter() {
        pVedioPresenter=new PVedioPresenter(this);
        return pVedioPresenter;
    }

    public void InitMap() {
        locationClient = new AMapLocationClient(this);
        locationOption = getDefaultOption();
//设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        locationClient.startLocation();
    }

    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(true);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTPS);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(true);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        mOption.setMockEnable(true);
        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            double latitude = aMapLocation.getLatitude();
            double longitude = aMapLocation.getLongitude();
            if (aMapLocation.getErrorCode() == 0) {
                Message message = Message.obtain();
                String m = latitude + "l" + longitude;
                message.obj = m;
                handler.sendMessage(message);
                locationClient.stopLocation();
            } else {
                System.out.println("定位失败++++++++" + aMapLocation.getErrorCode());
            }

        }
    };


    @Override
    public void PvSuccess(String msg) {
            pbrale.setVisibility(View.GONE);
        Toast.makeText(PublishVediosActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this,OneTimeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void PvFail(String msg) {
        Toast.makeText(PublishVediosActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
        pbrale.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coverschange:
                Toast.makeText(PublishVediosActivity.this, "dianji", Toast.LENGTH_SHORT).show();
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.btn_pvedio:
                pbrale.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(coverpath)) {
                    Toast.makeText(PublishVediosActivity.this, "请选择视屏封面", Toast.LENGTH_SHORT).show();
                    return;
                }
                File coverfile = new File(coverpath);
                String s = etPvedio.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(PublishVediosActivity.this, "视屏描述不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                pVedioPresenter.PVedio(uid + "", vediofile, coverfile, s, la, lo);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    LocalMedia localMedia = selectList.get(0);
                    coverpath = localMedia.getPath();

                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

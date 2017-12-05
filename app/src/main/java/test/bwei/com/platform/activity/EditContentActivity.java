package test.bwei.com.platform.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hxe.platform.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import test.bwei.com.platform.Base.BaseActivity;
import test.bwei.com.platform.Base.BasePresenter;

import test.bwei.com.platform.presenter.PublishPresenter;
import test.bwei.com.platform.view.PublishView;

public class EditContentActivity extends BaseActivity<PublishPresenter> implements PublishView, View.OnClickListener {
    private PublishPresenter publishPresenter;
    private TextView cancle;
    private RelativeLayout r1;
    private RelativeLayout r2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_content;
    }

    @Override
    public void initView() {
        cancle = findViewById(R.id.publish_cancle);
        r1 = findViewById(R.id.pbr1);
        r2 = findViewById(R.id.pbr2);
        cancle.setOnClickListener(this);
        r1.setOnClickListener(this);
        r2.setOnClickListener(this);
    }

    @Override
    public void initData() {


    }

    @Override
    public PublishPresenter initPresenter() {

        return null;
    }

    @Override
    public void Success(String msg) {

    }

    @Override
    public void Fail(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.publish_cancle:
                finish();
                break;
            case R.id.pbr1:
                //TODO 上传视屏的点击事件
                PictureSelector.create(this)
                        .openCamera(PictureMimeType.ofVideo())
                        .forResult(PictureConfig.CHOOSE_REQUEST)
                ;
                break;
            case R.id.pbr2:
                Intent intent = new Intent(EditContentActivity.this, DuanZiActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    LocalMedia localMedia = selectList.get(0);
                    String path = localMedia.getPath();
                    System.out.println(path+"patthi++++++");
                    Intent intent = new Intent(this, PublishVediosActivity.class);
                    intent.putExtra("vediopath",path);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }
}

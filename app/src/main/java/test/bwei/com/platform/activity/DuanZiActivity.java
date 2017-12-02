package test.bwei.com.platform.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageSelector;
import com.yancy.imageselector.ImageSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import test.bwei.com.platform.Base.BaseActivity;
import test.bwei.com.platform.GlideLoader;
import test.bwei.com.platform.R;
import test.bwei.com.platform.presenter.PublishPresenter;
import test.bwei.com.platform.view.PublishView;

public class DuanZiActivity extends BaseActivity<PublishPresenter> implements PublishView, View.OnClickListener {
    private PublishPresenter publishPresenter;
    private TextView cancle;
    private TextView publish;
    private EditText content;
    private SharedPreferences sp;
    private ImageView publish_image;
    private ArrayList<String> path = new ArrayList<>();
    private List<String> pathList;
    private RelativeLayout rela;
    private ProgressBar pb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_duan_zi;
    }

    @Override
    public void initView() {
        cancle = findViewById(R.id.duanzi_cancle);
        publish = findViewById(R.id.duanzi_publish);
        publish_image = findViewById(R.id.publish_image);
        rela = findViewById(R.id.pj_r1);
        pb = findViewById(R.id.pb);
        content = findViewById(R.id.publish_content);
        cancle.setOnClickListener(this);
        publish.setOnClickListener(this);
        publish_image.setOnClickListener(this);
    }

    @Override
    public void initData() {
        publishPresenter = new PublishPresenter(this, DuanZiActivity.this);
        sp = getSharedPreferences("userinfo", MODE_PRIVATE);
    }

    @Override
    public PublishPresenter initPresenter() {
        return publishPresenter;
    }

    @Override
    public void Success(String msg) {
        rela.setVisibility(View.GONE);
        Toast.makeText(DuanZiActivity.this, "发布成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void Fail(String msg) {
        rela.setVisibility(View.GONE);
        if (msg.equals("2")) {
            sp.edit().clear().commit();
            Intent intent = new Intent(DuanZiActivity.this, ThreeActionActivity.class);

            startActivity(intent);
            Toast.makeText(DuanZiActivity.this, "token超时", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(DuanZiActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.duanzi_cancle:
                finish();
                break;
            case R.id.duanzi_publish:
                rela.setVisibility(View.VISIBLE);
                int uid = sp.getInt("uid", -100);
                if (uid > 0) {
                    String s = content.getText().toString();
                    if (!TextUtils.isEmpty(s)) {
                        publishPresenter.PublishJokes(uid + "", s, path);
                    } else {
                        Toast.makeText(DuanZiActivity.this, "请输入内容再发布", Toast.LENGTH_SHORT).show();
                        return;
                    }

                }
                break;
            case R.id.publish_image:
                ImageConfig imageConfig
                        = new ImageConfig.Builder(
                        // GlideLoader 可用自己用的缓存库
                        new GlideLoader())
                        // 如果在 4.4 以上，则修改状态栏颜色 （默认黑色）
                        .steepToolBarColor(getResources().getColor(R.color.blue))
                        // 标题的背景颜色 （默认黑色）
                        .titleBgColor(getResources().getColor(R.color.blue))
                        // 提交按钮字体的颜色  （默认白色）
                        .titleSubmitTextColor(getResources().getColor(R.color.white))
                        // 标题颜色 （默认白色）
                        .titleTextColor(getResources().getColor(R.color.white))
                        // 开启多选   （默认为多选）  (单选 为 singleSelect)

//                        .crop()
                        // 多选时的最大数量   （默认 9 张）
                        .mutiSelectMaxSize(9)
                        // 已选择的图片路径
                        .pathList(path)
                        // 拍照后存放的图片路径（默认 /temp/picture）
                        .filePath("/ImageSelector/Pictures")
                        // 开启拍照功能 （默认开启）
                        .showCamera()
                        .requestCode(200)
                        .build();
                ImageSelector.open(DuanZiActivity.this, imageConfig);   // 开启图片选择器
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == RESULT_OK && data != null) {
            pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            for (String s : pathList) {
                System.out.println(s + "path+++++++++++++++");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}

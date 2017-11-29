package test.bwei.com.platform.activity;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.kson.slidingmenu.SlidingMenu;

import test.bwei.com.platform.Base.BaseActivity;
import test.bwei.com.platform.MainActivity;
import test.bwei.com.platform.R;

import test.bwei.com.platform.fragment.DZFragment;
import test.bwei.com.platform.fragment.MenuFragment;
import test.bwei.com.platform.fragment.SPFragment;
import test.bwei.com.platform.fragment.TJFragment;
import test.bwei.com.platform.presenter.MainPresenter;
import test.bwei.com.platform.view.MainInterface;

public class OneTimeActivity extends BaseActivity<MainPresenter> implements MainInterface, View.OnClickListener {
    private MainPresenter mainPresenter;
    private ImageView imgbj;
    private TextView title;
    private ImageView tj;
    private ImageView dz;
    private ImageView sp;
    private FragmentTransaction fragmentTransaction;
    private TJFragment f1;
    private DZFragment f2;
    private SPFragment f3;
    private TextView tvtj;
    private TextView tvdz;
    private TextView tvsp;
    private SimpleDraweeView icon;
    private SlidingMenu menu;
    private FragmentTransaction fragmentTransactions;

    @Override
    public int getLayoutId() {
        return R.layout.activity_one_time;
    }

    @Override
    public void initView() {
        icon = findViewById(R.id.fiv);
        imgbj = findViewById(R.id.bj);
        title = findViewById(R.id.maintitle);
        tj = findViewById(R.id.tjimg);
        dz = findViewById(R.id.dzimg);
        sp = findViewById(R.id.spimg);
        tvtj = findViewById(R.id.tvtj);
        tvdz = findViewById(R.id.tvdz);
        tvsp = findViewById(R.id.tvsp);
        imgbj.setOnClickListener(this);
        icon.setOnClickListener(this);
        tj.setOnClickListener(this);
        dz.setOnClickListener(this);
        sp.setOnClickListener(this);
        setSlidingMenu();
    }

    private void setSlidingMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setBehindOffsetRes(R.dimen.menuwidth);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.left_menu_frame);
        getSupportFragmentManager().beginTransaction().replace(R.id.fr, new MenuFragment()).commit();
    }

    @Override
    public void initData() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        f1 = new TJFragment();
        fragmentTransaction.add(R.id.frame, f1).commit();

    }

    @Override
    public MainPresenter initPresenter() {
        mainPresenter = new MainPresenter(this);
        return mainPresenter;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        fragmentTransactions = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.fiv:
                menu.showMenu();

                break;
            case R.id.bj:



                break;
            case R.id.tjimg:
                title.setText("推荐");
                tvtj.setTextColor(Color.parseColor("#03A9F4"));
                tvdz.setTextColor(Color.parseColor("#999999"));
                tvsp.setTextColor(Color.parseColor("#999999"));
                tj.setImageResource(R.mipmap.otjselected);
                dz.setImageResource(R.mipmap.odznormal);
                sp.setImageResource(R.mipmap.ospnormal);
                if (f1 == null) {
                    f1 = new TJFragment();
                    fragmentTransactions.add(R.id.frame, f1);
                    if (f2 != null) {
                        fragmentTransactions.hide(f2);
                    }
                    if (f3 != null) {
                        fragmentTransactions.hide(f3);
                    }
                } else {
                    if (f2 != null) {
                        fragmentTransactions.hide(f2);
                    }
                    if (f3 != null) {
                        fragmentTransactions.hide(f3);
                    }
                    fragmentTransactions.show(f1);
                }
                break;
            case R.id.dzimg:
                title.setText("段子");
                tvtj.setTextColor(Color.parseColor("#999999"));
                tvdz.setTextColor(Color.parseColor("#03A9F4"));
                tvsp.setTextColor(Color.parseColor("#999999"));
                tj.setImageResource(R.mipmap.otjnormal);
                dz.setImageResource(R.mipmap.odzselected);
                sp.setImageResource(R.mipmap.ospnormal);
                if (f2 == null) {
                    f2 = new DZFragment();
                    fragmentTransactions.add(R.id.frame, f2);
                    if (f1 != null) {
                        fragmentTransactions.hide(f1);
                    }
                    if (f3 != null) {
                        fragmentTransactions.hide(f3);
                    }
                } else {
                    if (f1 != null) {
                        fragmentTransactions.hide(f1);
                    }
                    if (f3 != null) {
                        fragmentTransactions.hide(f3);
                    }
                    fragmentTransactions.show(f2);
                }
                break;
            case R.id.spimg:
                title.setText("视频");
                tvtj.setTextColor(Color.parseColor("#999999"));
                tvdz.setTextColor(Color.parseColor("#999999"));
                tvsp.setTextColor(Color.parseColor("#03A9F4"));
                tj.setImageResource(R.mipmap.otjnormal);
                dz.setImageResource(R.mipmap.odznormal);
                sp.setImageResource(R.mipmap.ospselected);
                if (f3 == null) {
                    f3 = new SPFragment();
                    fragmentTransactions.add(R.id.frame, f3);
                    if (f1 != null) {
                        fragmentTransactions.hide(f1);
                    }
                    if (f2 != null) {
                        fragmentTransactions.hide(f2);
                    }
                } else {
                    if (f1 != null) {
                        fragmentTransactions.hide(f1);
                    }
                    if (f2 != null) {
                        fragmentTransactions.hide(f2);
                    }
                    fragmentTransactions.show(f3);
                }
                break;
        }
        fragmentTransactions.commit();
    }


}

package test.bwei.com.platform.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.hxe.platform.R;
import test.bwei.com.platform.fragment.MyFragment;
import test.bwei.com.platform.fragment.OrderFragment;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView yy;
    private ImageView my;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        initData();
    }

    private void initData() {
        FvAdapter fvAdapter = new FvAdapter(getSupportFragmentManager());
        vp.setAdapter(fvAdapter);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        yy.setImageResource(R.mipmap.yyselected);
                        my.setImageResource(R.mipmap.mynormal);
                        break;
                    case 1:
                        yy.setImageResource(R.mipmap.yynormal);
                        my.setImageResource(R.mipmap.myselected);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initView() {
        yy = findViewById(R.id.yy);
        my = findViewById(R.id.my);
        yy.setOnClickListener(this);
        my.setOnClickListener(this);
        vp = findViewById(R.id.ordervp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yy:

                break;
            case R.id.my:
                break;
        }
    }

    class FvAdapter extends FragmentPagerAdapter {
        public FvAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new OrderFragment();
                    break;
                case 1:
                    fragment = new MyFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}

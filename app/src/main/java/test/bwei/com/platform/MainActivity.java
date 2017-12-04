package test.bwei.com.platform;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.hxe.platform.R;

import test.bwei.com.platform.Base.BaseActivity;
import test.bwei.com.platform.Base.BasePresenter;
import test.bwei.com.platform.activity.LandRActivity;
import test.bwei.com.platform.activity.StartActivity;

public class MainActivity extends BaseActivity {


    private ViewPager vp;
    private int[] vpimg = {R.mipmap.vp1, R.mipmap.vp2, R.mipmap.vp3};
    private SharedPreferences sp;
    private ImageView gif;
    private ImageView img;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        sp = getSharedPreferences("ss", MODE_PRIVATE);
        final boolean ss = this.sp.getBoolean("ss", false);
        if (ss) {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
            finish();
        }
        vp = findViewById(R.id.Fvp);
        vp.setAdapter(new VpAdapter());
        gif = findViewById(R.id.gif);
        img = findViewById(R.id.btns);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LandRActivity.class);
                startActivity(intent);
            //    sp.edit().putBoolean("ss",true).commit();
                finish();
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    img.setVisibility(View.VISIBLE);
                    gif.setVisibility(View.VISIBLE);
                    Glide.with(MainActivity.this).load(R.mipmap.jiesuo).into(img);
                    Glide.with(MainActivity.this).load(R.mipmap .spgif).into(new GlideDrawableImageViewTarget(gif, 1));
                } else {
                    gif.setVisibility(View.GONE);
                    img.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    class VpAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View img = View.inflate(MainActivity.this, R.layout.vpimage, null);
            ImageView imgs = img.findViewById(R.id.itemimg);
            if (position == 3) {
                return null;
            }
            imgs.setImageResource(vpimg[position]);
            container.addView(img);
            return img;


        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}

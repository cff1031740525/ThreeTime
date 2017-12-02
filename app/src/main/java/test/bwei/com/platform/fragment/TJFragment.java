package test.bwei.com.platform.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import test.bwei.com.platform.R;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/23
 * Description:
 */

public class TJFragment extends Fragment {

    private View v;
    private XBanner banner;
    private TabLayout tab;
    private ViewPager vp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(getActivity()).inflate(R.layout.tjfragment, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        vp = v.findViewById(R.id.vp);
        tab = v.findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("热门"));
        tab.addTab(tab.newTab().setText("关注"));
        vp.setAdapter(new vpfragement(getActivity().getSupportFragmentManager()));
        tab.setupWithViewPager(vp);
    }

    class vpfragement extends FragmentPagerAdapter {
        public vpfragement(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new RMFragment();
                    break;
                case 1:
                    fragment = new GZFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "热门";
            } else {
                return "关注";
            }

        }
    }
}

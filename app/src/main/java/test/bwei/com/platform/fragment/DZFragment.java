package test.bwei.com.platform.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import test.bwei.com.platform.R;
import test.bwei.com.platform.activity.DuanZiActivity;
import test.bwei.com.platform.activity.ThreeActionActivity;
import test.bwei.com.platform.adapter.JokesAdapter;
import test.bwei.com.platform.bean.UseJokeBean;
import test.bwei.com.platform.model.JokePhraiseModel;
import test.bwei.com.platform.presenter.GetJokesPresenter;
import test.bwei.com.platform.presenter.PhraisePresenter;
import test.bwei.com.platform.view.DZView;
import test.bwei.com.platform.view.JokePhraiseView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/23
 * Description:
 */

public class DZFragment extends Fragment implements DZView, JokePhraiseView {

    private View v;
    private XRecyclerView rlv;
    private GetJokesPresenter getJokesPresenter;
    private PhraisePresenter phraisePresenter;
    private int page = 1;
    private JokesAdapter jokesAdapter;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dz_fragment, null);
        return v;
    }

    //
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rlv = v.findViewById(R.id.dzrlv);
        rlv.setPullRefreshEnabled(true);
        rlv.setLoadingMoreEnabled(true);
        initData();
        InitMap();
    }

    private void initData() {


        getJokesPresenter = new GetJokesPresenter(this, getActivity());
        phraisePresenter = new PhraisePresenter(this);
        getJokesPresenter.getJokesContent(page + "");
        sp = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    }

    @Override
    public void Success(final List<UseJokeBean> list) {
        if (jokesAdapter == null) {
            jokesAdapter = new JokesAdapter(list, getActivity());
            rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
            rlv.setAdapter(jokesAdapter);
        } else {
            jokesAdapter.notifyDataSetChanged();
        }
        jokesAdapter.setUserOperation(new JokesAdapter.UserOperation() {
            @Override
            public void CommentOperation(int postion) {

            }

            @Override
            public void LoveOperation(int postion) {
                int  uid = sp.getInt("uid", -100);
                if (uid<0) {
                    Toast.makeText(getActivity(), "请先登录再点赞", Toast.LENGTH_SHORT).show();
                    return;
                }
                phraisePresenter.PhrasisJoke(uid+"", list.get(postion).jid + "");
            }

            @Override
            public void ShareOperation(int postion) {

            }

            @Override
            public void ShowOperation(int postion) {

            }
        });
        rlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                getJokesPresenter.getJokesContent("1");
                page = 1;
                rlv.refreshComplete();
            }

            @Override
            public void onLoadMore() {

                page++;

                System.out.println(page + "page");
                getJokesPresenter.PresenterLoadMore(page + "");
                jokesAdapter.notifyDataSetChanged();
                rlv.loadMoreComplete();

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void Fail(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        page = 1;
        getJokesPresenter.getJokesContent(page + "");
    }

    public void InitMap() {
        locationClient = new AMapLocationClient(getActivity().getApplicationContext());
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
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true

        return mOption;
    }

    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(final AMapLocation aMapLocation) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(aMapLocation.getLatitude() + "+++++" + aMapLocation.getLongitude());
                    locationClient.stopLocation();
                }
            });

        }
    };

    @Override
    public void SuccessP(String msg) {
        Toast.makeText(getActivity(), "点赞成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void FailP(String msg) {
        if (msg.equals("2")) {
            sp.edit().clear().commit();
            Intent intent = new Intent(getActivity(), ThreeActionActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "token超时", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        } else {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}

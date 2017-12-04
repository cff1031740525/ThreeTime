package test.bwei.com.platform.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import com.hxe.platform.R;

import test.bwei.com.platform.activity.ThreeActionActivity;
import test.bwei.com.platform.adapter.RMGZAdapter;
import test.bwei.com.platform.jsonbean.VedioBean;
import test.bwei.com.platform.presenter.GetVideoPresenter;
import test.bwei.com.platform.view.GetVediosView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/24
 * Description:
 */

public class RMFragment extends Fragment implements GetVediosView {

    private View view;
    private XRecyclerView rlv;
    private XBanner banner;
    private List<String> list = new ArrayList<>();
    private GetVideoPresenter getVideoPresenter;
    private SharedPreferences sp;
    private int uid;

    private int page = 1;
    private RMGZAdapter rmgzAdapter;
    private List<VedioBean.DataBean> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.rmfragment, null);
        ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        rlv = view.findViewById(R.id.rmrlv);
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.tjheader, null);
        banner = v.findViewById(R.id.xbanner);
        banner.setData(list, null);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }
        });
        rlv.addHeaderView(v);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv.setLoadingMoreEnabled(true);
        rlv.setPullRefreshEnabled(true);
    }

    private void initData() {
        data = new ArrayList<>();
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2945489876,500929370&fm=27&gp=0.jpg");
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1850716398,489717713&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2786507612,2329204542&fm=27&gp=0.jpg");
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1620654765,1617017402&fm=27&gp=0.jpg");
        sp = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        uid = sp.getInt("uid", -100);
        getVideoPresenter = new GetVideoPresenter(this);
        getVideoPresenter.getVedioPresenter(uid + "", "1", "1");
    }

    @Override
    public void VSuccess(final VedioBean vedioBean) {
        data.addAll(vedioBean.data);
        if (rmgzAdapter == null) {
            rmgzAdapter = new RMGZAdapter(getActivity(), data);
            rlv.setAdapter(rmgzAdapter);
        } else {

            rmgzAdapter.notifyDataSetChanged();
            rlv.loadMoreComplete();
        }
        rlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                vedioBean.data.clear();
                rmgzAdapter = null;
                page=1;
                getVideoPresenter.getVedioPresenter(uid + "", "1", "1");
                rlv.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                page++;
                getVideoPresenter.getVedioPresenter(uid + "", "1", page + "");
            }
        });
    }
    @Override
    public void VFail(String msg) {
        if (msg.equals("2")) {
            SharedPreferences sp = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            sp.edit().clear().commit();
            Intent intent = new Intent(getActivity(), ThreeActionActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}

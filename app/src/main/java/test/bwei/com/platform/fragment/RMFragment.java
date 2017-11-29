package test.bwei.com.platform.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import test.bwei.com.platform.R;
import test.bwei.com.platform.adapter.RMGZAdapter;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/24
 * Description:
 */

public class RMFragment extends Fragment{

    private View view;
    private XRecyclerView rlv;
    private XBanner banner;
    private List<String> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.rmfragment,null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        rlv = view.findViewById(R.id.rmrlv);
        View v=LayoutInflater.from(getActivity()).inflate(R.layout.tjheader,null);
        banner = v.findViewById(R.id.xbanner);
        banner.setData(list,null);
        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
            }
        });
        rlv.addHeaderView(v);
        RMGZAdapter adapter=new RMGZAdapter(getActivity());
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv.setAdapter(adapter);
        //TODO 热门类数据获取
    }

    private void initData() {
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2945489876,500929370&fm=27&gp=0.jpg");
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1850716398,489717713&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2786507612,2329204542&fm=27&gp=0.jpg");
        list.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1620654765,1617017402&fm=27&gp=0.jpg");

    }
}

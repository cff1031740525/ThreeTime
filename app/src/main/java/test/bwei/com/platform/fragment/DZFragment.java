package test.bwei.com.platform.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import test.bwei.com.platform.R;
import test.bwei.com.platform.adapter.JokesAdapter;
import test.bwei.com.platform.bean.UseJokeBean;
import test.bwei.com.platform.presenter.GetJokesPresenter;
import test.bwei.com.platform.view.DZView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/23
 * Description:
 */

public class DZFragment extends Fragment implements DZView {

    private View v;
    private XRecyclerView rlv;
    private GetJokesPresenter getJokesPresenter;
    private int page = 1;
    private JokesAdapter jokesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = LayoutInflater.from(getActivity()).inflate(R.layout.dz_fragment, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rlv = v.findViewById(R.id.dzrlv);
        rlv.setPullRefreshEnabled(true);
        rlv.setLoadingMoreEnabled(true);
        initData();
    }

    private void initData() {
        getJokesPresenter = new GetJokesPresenter(this, getActivity());
        getJokesPresenter.getJokesContent(page + "");

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
    public void Fail(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        page=1;
        getJokesPresenter.getJokesContent(page + "");
    }
}

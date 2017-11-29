package test.bwei.com.platform.presenter;

import android.content.Context;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import test.bwei.com.platform.Base.BasePresenter;
import test.bwei.com.platform.bean.UseJokeBean;
import test.bwei.com.platform.model.GetJokesModel;
import test.bwei.com.platform.view.DZView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/28
 * Description:
 */

public class GetJokesPresenter extends BasePresenter<DZView> implements GetJokesModel.GetUseJokes {
    private DZView dzView;
    private GetJokesModel getJokesModel;
    private Context context;

    public GetJokesPresenter(DZView mView,Context context) {
        super(mView);
        dzView = mView;
        this.context=context;
        getJokesModel = new GetJokesModel(context);
        getJokesModel.setGetUseJokes(this);

    }

    public void PresenterLoadMore(String page){
        getJokesModel.loadMore(page);
    }

    public void getJokesContent(String page) {
        getJokesModel.getJokes(page);
    }

    @Override
    public void Success(List<UseJokeBean> list) {
        dzView.Success(list);
    }

    @Override
    public void Fail(String msg) {
        dzView.Fail(msg);
    }

}

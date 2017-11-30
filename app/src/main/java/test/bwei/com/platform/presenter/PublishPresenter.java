package test.bwei.com.platform.presenter;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import test.bwei.com.platform.Base.BasePresenter;
import test.bwei.com.platform.model.PublishModel;
import test.bwei.com.platform.view.PublishView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/29
 * Description:
 */

public class PublishPresenter extends BasePresenter<PublishView> implements PublishModel.PublishInterface {
    private PublishView publishView;
    private PublishModel publishModel;
private Context contexts;

    public PublishPresenter(PublishView mView, Context context) {
        super(mView);
        this.contexts=context;
        publishModel = new PublishModel(contexts);
        publishModel.setPublishInterface(this);
        publishView = mView;
    }

    public void PublishJokes(String uid, String content, List<String> path) {
        publishModel.publishJokes(uid, content, path);
    }

    @Override
    public void Success(String msg) {
        publishView.Success(msg);
    }

    @Override
    public void Fail(String msg) {
        publishView.Fail(msg);
    }
}

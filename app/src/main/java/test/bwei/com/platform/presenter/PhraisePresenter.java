package test.bwei.com.platform.presenter;

import test.bwei.com.platform.Base.BasePresenter;
import test.bwei.com.platform.model.JokePhraiseModel;
import test.bwei.com.platform.view.JokePhraiseView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/1
 * Description:
 */

public class PhraisePresenter extends BasePresenter<JokePhraiseView> implements JokePhraiseModel.PhraiseJoke {
    private JokePhraiseView jokePhraiseView;
    private JokePhraiseModel phraiseModel;

    public PhraisePresenter(JokePhraiseView mView) {
        super(mView);
        jokePhraiseView = mView;
        phraiseModel = new JokePhraiseModel();
        phraiseModel.setPhraiseJoke(this);
    }

    public void PhrasisJoke(String uid, String jid) {
        phraiseModel.PhraiseJoke(uid, jid);
    }

    @Override
    public void Success(String msg) {
        jokePhraiseView.SuccessP(msg);
    }

    @Override
    public void Fail(String msg) {
        jokePhraiseView.FailP(msg);
    }
}

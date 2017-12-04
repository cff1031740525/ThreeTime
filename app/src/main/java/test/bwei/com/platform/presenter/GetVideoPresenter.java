package test.bwei.com.platform.presenter;

import test.bwei.com.platform.Base.BasePresenter;
import test.bwei.com.platform.jsonbean.VedioBean;
import test.bwei.com.platform.model.GetVedioModel;
import test.bwei.com.platform.view.GetVediosView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/2
 * Description:
 */

public class GetVideoPresenter extends BasePresenter<GetVediosView> implements GetVedioModel.GetVedioInterface {
    private GetVediosView getVediosView;
    private GetVedioModel getVedioModel;

    public GetVideoPresenter(GetVediosView mView) {
        super(mView);
        getVediosView = mView;
        getVedioModel = new GetVedioModel();
        getVedioModel.setGetVedioInterface(this);
    }

    public void getVedioPresenter(String uid, String type, String page) {
        getVedioModel.getVedioInfo(uid, type, page);
    }

    public void getMoreVedio(String uid, String type, String page) {
        getVedioModel.getVedioMore(uid,type,page);
    }

    @Override
    public void VSuccess(VedioBean vedioBean) {
        getVediosView.VSuccess(vedioBean);
    }

    @Override
    public void VFail(String msg) {
        getVediosView.VFail(msg);
    }
}

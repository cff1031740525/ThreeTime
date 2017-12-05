package test.bwei.com.platform.presenter;

import java.io.File;

import test.bwei.com.platform.Base.BasePresenter;
import test.bwei.com.platform.activity.PublishVediosActivity;
import test.bwei.com.platform.model.PVedioModel;
import test.bwei.com.platform.model.PublishModel;
import test.bwei.com.platform.view.PVedioView;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/5
 * Description:
 */

public class PVedioPresenter extends BasePresenter<PVedioView> implements PVedioModel.MIntervice{
    private PVedioView pVedioView;
    private PVedioModel pVedioModel;
    public PVedioPresenter(PVedioView mView) {
        super(mView);
        pVedioView=mView;
        pVedioModel=new PVedioModel();
        pVedioModel.setmIntervice(this);
    }
    public void PVedio(String uid, File vediofile, File coverImgPath, String vedioDesc, String latitude, String longitude){
        pVedioModel.PublishVedio(uid, vediofile, coverImgPath, vedioDesc, latitude, longitude);
    }


    @Override
    public void MSuccess(String msg) {
        pVedioView.PvSuccess(msg);
    }

    @Override
    public void MFail(String msg) {
        pVedioView.PvFail(msg);
    }
}

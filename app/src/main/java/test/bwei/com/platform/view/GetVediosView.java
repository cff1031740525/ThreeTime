package test.bwei.com.platform.view;

import test.bwei.com.platform.jsonbean.VedioBean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/12/2
 * Description:
 */

public interface GetVediosView {
    void VSuccess(VedioBean vedioBean);
    void VFail(String  msg);
}

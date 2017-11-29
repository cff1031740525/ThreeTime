package test.bwei.com.platform.view;

import java.util.List;

import test.bwei.com.platform.bean.UseJokeBean;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/28
 * Description:
 */

public interface DZView {
    void Success(List<UseJokeBean> list);
    void Fail(String msg);
}

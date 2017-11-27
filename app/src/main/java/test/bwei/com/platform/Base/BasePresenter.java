package test.bwei.com.platform.Base;

/**
 * Author:Chen
 * E-mail:1031740525@qq.com
 * Time: 2017/11/12
 * Description:
 */
//basepresenter类   用于view和Model的交互
public class BasePresenter<V> {
    private V mView;

    public BasePresenter(V mView) {
        this.mView = mView;
    }
    //用于解绑view
    public void Deatch() {
        this.mView = null;
    }
}

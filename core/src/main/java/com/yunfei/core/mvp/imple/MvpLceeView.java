package com.yunfei.core.mvp.imple;

import com.yunfei.core.mvp.core.MvpView;

/**
 * Created by yunfei on 2016/12/19.
 * email mayunfei6@gmail.com
 * <p>
 * Loading-Content-Empty-Error (LCEE)
 */

public interface MvpLceeView<M> extends MvpView {
  /**
   * 显示一个加载中的视图
   *
   * @param pullToRefresh 如果是true,那么表示下拉刷新被触发了
   */
  public void showLoading(boolean pullToRefresh);

  /**
   * 显示 content view.
   */
  public void showContent();

  public void showEmpty();

  /**
   * 显示错误信息
   */
  public void showError(Throwable e, boolean pullToRefresh);

  /**
   * The data that should be displayed with {@link #showContent()}
   */
  public void setData(M data);

  public void showNetError();
}

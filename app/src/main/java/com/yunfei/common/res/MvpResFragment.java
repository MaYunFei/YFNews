package com.yunfei.common.res;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.yunfei.common.ui.CommonLceeLayout;
import com.yunfei.core.mvp.core.MvpFragment;
import com.yunfei.yfnews.R;

/**
 * Created by yunfei on 2017/1/1.
 * email mayunfei6@gmail.com
 * http 资源请求
 */

public abstract class MvpResFragment<M, P extends ResPresenter> extends MvpFragment<P>
    implements ResView<M> {
  protected CommonLceeLayout mLceeLayout;

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mLceeLayout = (CommonLceeLayout) view.findViewById(R.id.lcee_layout);
  }

  @Override public void showEmpty() {
    mLceeLayout.showEmpty();
  }

  @Override public void showError(String error) {
    mLceeLayout.showCommentError(error);
  }

  @Override public void showNetError() {
    if (mLceeLayout != null) {
      mLceeLayout.showNetError();
    }
  }

  @Override public void showContent() {
    mLceeLayout.showContent();
  }
}

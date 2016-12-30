package com.yunfei.core.mvp.core;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yunfei on 2016/12/19.
 * email mayunfei6@gmail.com
 */

public abstract class BaseFragment extends Fragment {
  private Unbinder mUnbinder;

  @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(getContentLayoutId(), container, false);
    mUnbinder = ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    if (mUnbinder != null) {
      mUnbinder.unbind();
    }
  }

  @LayoutRes protected abstract int getContentLayoutId();
}

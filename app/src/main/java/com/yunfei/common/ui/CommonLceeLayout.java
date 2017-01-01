package com.yunfei.common.ui;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.yunfei.lceelayout.LceeLayout;
import com.yunfei.yfnews.R;

/**
 * Created by yunfei on 2017/1/1.
 * email mayunfei6@gmail.com
 */

public class CommonLceeLayout extends LceeLayout {

  private View mCommentNetErrorView;
  private View mCommentErrorView;

  public CommonLceeLayout(@NonNull Context context) {
    this(context, null);
  }

  public CommonLceeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CommonLceeLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    loadingLayoutId = R.layout.common_loading;
  }

  public void showCommentError(@StringRes int strId) {
    showCommentError(getContext().getString(strId), -1, null);
  }

  public void showCommentError(String str) {
    showCommentError(str, -1, null);
  }

  public void showCommentError(String str, OnClickListener onClickListener) {
    showCommentError(str, -1, onClickListener);
  }

  public void showCommentError(String message, @DrawableRes int imgid, OnClickListener onClickListener) {
    if (mCommentErrorView == null) {
      mCommentErrorView = LayoutInflater.from(getContext()).inflate(R.layout.common_error, this, false);
      if (onClickListener != null) {
        mCommentErrorView.findViewById(R.id.comment_error_layout).setOnClickListener(onClickListener);
      }
      if (imgid > 0) {
        ((ImageView) mCommentErrorView.findViewById(R.id.comment_error_img)).setImageResource(imgid);
      }
      if (!TextUtils.isEmpty(message)) {
        ((TextView) mCommentErrorView.findViewById(R.id.comment_error_tv)).setText(message);
      }
      bindView(mCommentErrorView, STATE_ERROR);
    }
    showError();
  }

  private void showCommentNetError() {
    if (mCommentNetErrorView == null) {
      mCommentNetErrorView = LayoutInflater.from(getContext()).inflate(R.layout.common_net_error, this, false);
      bindView(mCommentErrorView, STATE_ERROR);
    }
    showError();
  }
}

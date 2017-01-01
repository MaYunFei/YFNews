package com.yunfei.lceelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.AttrRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.FrameLayout;

/**
 * Created by yunfei on 2016/12/20.
 * email mayunfei6@gmail.com
 *
 * Loading Content Empty Error View L-C-E-E View
 *
 * 我觉的最佳用法是，写一个类继承这个Layout 将一般情况重写
 */

public class LceeLayout extends FrameLayout implements LceeInterface {

  protected View loadingView;
  protected View errorView;
  protected View emptyView;
  protected View contentView;
  protected View net_errorView;

  protected int loadingLayoutId = RES_NONE;
  protected int errorLayoutId = RES_NONE;
  protected int emptyLayoutId = RES_NONE;
  protected int contentLayoutId = RES_NONE;
  protected int net_errorLayoutId = RES_NONE;

  public static final int STATE_LOADING = 0x1;
  public static final int STATE_ERROR = 0x2;
  public static final int STATE_EMPTY = 0x3;
  public static final int STATE_CONTENT = 0x4;
  public static final int STATE_NET_ERROR = 0x5;
  int displayState = STATE_LOADING;

  static final int RES_NONE = -1;

  public LceeLayout(@NonNull Context context) {
    this(context, null);
  }

  public LceeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public LceeLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initAttrs(context, attrs);
  }

  private void initAttrs(Context context, AttributeSet attr) {
    TypedArray typedArray = context.obtainStyledAttributes(attr, R.styleable.LceeLayout);
    loadingLayoutId = typedArray.getResourceId(R.styleable.LceeLayout_lcee_loadingLayoutId, RES_NONE);
    contentLayoutId = typedArray.getResourceId(R.styleable.LceeLayout_lcee_contentLayoutId, RES_NONE);
    errorLayoutId = typedArray.getResourceId(R.styleable.LceeLayout_lcee_errorLayoutId, RES_NONE);
    emptyLayoutId = typedArray.getResourceId(R.styleable.LceeLayout_lcee_emptyLayoutId, RES_NONE);
    net_errorLayoutId = typedArray.getResourceId(R.styleable.LceeLayout_lcee_net_error_LayoutId, RES_NONE);
    typedArray.recycle();
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    int childCount = getChildCount();
    if (childCount > 5) {
      throw new IllegalStateException("QTContentLayout can only host 5 elements");
    } else {
      if (loadingLayoutId != RES_NONE) {
        loadingView = LayoutInflater.from(getContext()).inflate(loadingLayoutId, this, false);
      }
      if (errorLayoutId != RES_NONE) {
        errorView = LayoutInflater.from(getContext()).inflate(errorLayoutId, this, false);
      }
      if (emptyLayoutId != RES_NONE) {
        emptyView = LayoutInflater.from(getContext()).inflate(emptyLayoutId, this, false);
      }
      if (contentLayoutId != RES_NONE) {
        contentView = LayoutInflater.from(getContext()).inflate(contentLayoutId, this, false);
      }
      if (net_errorLayoutId != RES_NONE) {
        net_errorView = LayoutInflater.from(getContext()).inflate(net_errorLayoutId, this, false);
      }

      if (contentView == null) {
        if (childCount == 1) {
          contentView = getChildAt(0);
        }
      }
      if (contentView == null) {
        throw new IllegalStateException("ContentView can not be null");
      }

      bindView(loadingView, STATE_LOADING);
      bindView(errorView, STATE_ERROR);
      bindView(emptyView, STATE_EMPTY);
      bindView(contentView, STATE_CONTENT);
      bindView(net_errorView, STATE_NET_ERROR);

      if (loadingView != null) {
        setDisplayState(STATE_LOADING);
      } else {
        setDisplayState(STATE_CONTENT);
      }
    }
  }

  public void setDisplayState(int displayState) {
    this.displayState = displayState;

    if (loadingView != null) {
      if (displayState == STATE_LOADING) {
        loadingView.setVisibility(VISIBLE);
        loadingView.bringToFront();
      } else {
        loadingView.setVisibility(GONE);
      }
    }

    if (errorView != null) {
      if (displayState == STATE_ERROR) {
        errorView.setVisibility(VISIBLE);
        errorView.bringToFront();
      } else {
        errorView.setVisibility(GONE);
      }
    }

    if (emptyView != null) {
      if (displayState == STATE_EMPTY) {
        emptyView.setVisibility(VISIBLE);
        emptyView.bringToFront();
      } else {
        emptyView.setVisibility(GONE);
      }
    }

    if (contentView != null) {
      if (displayState == STATE_CONTENT) {
        contentView.setVisibility(VISIBLE);
        contentView.bringToFront();
      } else {
        contentView.setVisibility(GONE);
      }
    }

    if (net_errorView != null) {
      if (displayState == STATE_NET_ERROR) {
        net_errorView.setVisibility(VISIBLE);
        net_errorView.bringToFront();
      } else {
        net_errorView.setVisibility(GONE);
      }
    }
  }

  public void bindView(@LayoutRes int layoutid, int state) {
    bindView(LayoutInflater.from(getContext()).inflate(loadingLayoutId, this, false), state);
  }

  public void bindView(View child, int state) {
    if (child != null) {
      if (state == STATE_LOADING) {
        if (loadingView != null) {
          removeView(loadingView);
          loadingView = null;
        }
        loadingView = child;
      }
      if (state == STATE_EMPTY) {
        if (emptyView != null) {
          removeView(emptyView);
          emptyView = null;
        }
        emptyView = child;
      }
      if (state == STATE_ERROR) {
        if (errorView != null) {
          removeView(errorView);
          errorView = null;
        }
        errorView = child;
      }
      if (state == STATE_CONTENT) {
        if (contentView != null) {
          removeView(contentView);
          contentView = null;
        }
        contentView = child;
      }
      if (state == STATE_NET_ERROR) {
        if (net_errorView != null) {
          removeView(net_errorView);
          net_errorView = null;
        }
        net_errorView = child;
      }

      ViewParent parent = child.getParent();
      if (parent == null || parent != this) {
        addView(child);
      }
    }
  }

  static class SavedState extends BaseSavedState {
    int state;

    SavedState(Parcelable superState) {
      super(superState);
    }

    public SavedState(Parcel source) {
      super(source);
      try {
        state = source.readInt();
      } catch (IllegalArgumentException e) {
        state = STATE_LOADING;
      }
    }

    @Override public void writeToParcel(Parcel out, int flags) {
      super.writeToParcel(out, flags);
      out.writeInt(state);
    }

    public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
      @Override public SavedState createFromParcel(Parcel in) {
        return new SavedState(in);
      }

      @Override public SavedState[] newArray(int size) {
        return new SavedState[size];
      }
    };
  }

  @Override protected Parcelable onSaveInstanceState() {
    Parcelable parcelable = super.onSaveInstanceState();
    SavedState savedState = new SavedState(parcelable);
    savedState.state = this.displayState;
    return savedState;
  }

  @Override protected void onRestoreInstanceState(Parcelable state) {
    SavedState savedState = (SavedState) state;
    super.onRestoreInstanceState(savedState.getSuperState());
    this.displayState = savedState.state;
    setDisplayState(this.displayState);
  }

  @Override public void showLoading() {
    setDisplayState(STATE_LOADING);
  }

  @Override public void showContent() {
    setDisplayState(STATE_CONTENT);
  }

  @Override public void showEmpty() {
    setDisplayState(STATE_EMPTY);
  }

  @Override public void showError() {
    setDisplayState(STATE_ERROR);
  }

  @Override public void showNetError() {
    setDisplayState(STATE_NET_ERROR);
  }
}

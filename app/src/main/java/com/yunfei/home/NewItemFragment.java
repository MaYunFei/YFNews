package com.yunfei.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.squareup.picasso.Picasso;
import com.yunfei.common.res.MvpResFragment;
import com.yunfei.core.ui.BaseRecyclerAdapter;
import com.yunfei.core.utils.NetUtil;
import com.yunfei.entity.NewItem;
import com.yunfei.net.ApiClient;
import com.yunfei.net.NewsService;
import com.yunfei.utils.CustomTabsUtils;
import com.yunfei.utils.L;
import com.yunfei.yfnews.R;
import java.util.List;

/**
 * Created by yunfei on 2016/12/12.
 * email mayunfei6@gmail.com
 */

public class NewItemFragment extends MvpResFragment<List<NewItem>, NewItemPresenter>
    implements NiewItemView, SwipeRefreshLayout.OnRefreshListener {

  @BindView(R.id.recyclerview) RecyclerView mRecyclerview;
  @BindView(R.id.refresh_layout) SwipeRefreshLayout mSwipeRefreshLayout;

  private NewItmeAdapter mNewItmeAdapter;
  private static final String BUNDLE_TYPE = "bundle_type";
  private boolean isVisible;
  private boolean isPrepared;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    L.i(getArguments().getString(BUNDLE_TYPE) + "   " + toString());
  }

  //实现懒加载
  @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (getUserVisibleHint()) {
      //当前可见
      isVisible = true;
      lazyLoad();
    }
  }

  private void lazyLoad() {

    L.i(getArguments().getString(BUNDLE_TYPE));
    //if (!getArguments().getString(BUNDLE_TYPE).equals("top")){
    if (!isPrepared || !isVisible) {
      return;
      }
    //}

    mSwipeRefreshLayout.setColorSchemeColors(
        ContextCompat.getColor(getActivity(), R.color.colorPrimary),
        ContextCompat.getColor(getActivity(), R.color.colorAccent),
        ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));
    mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
    mNewItmeAdapter = new NewItmeAdapter(getContext());
    mRecyclerview.setAdapter(mNewItmeAdapter);
    mSwipeRefreshLayout.setOnRefreshListener(this);
    mRecyclerview.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener() {
      @Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        super.onTouchEvent(rv, e);
      }
    });

    //没有数据的时候更新 如果有需要操作view 的方法应该判断view是否生成，因为setUserVisibleHint 方法在onCreateView之前
    if (NetUtil.isNetWorkAvilable()) {
      if ((mPresenter != null && (mNewItmeAdapter == null
          || mNewItmeAdapter.getItemCount() == 0))) {
        mPresenter.getNews();
      }
    } else {
      showNetError();
    }
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    isPrepared = true;
    L.i("onViewCreated()  " + toString());
    lazyLoad();
  }

  public static NewItemFragment getNewInstance(String type) {
    NewItemFragment instance = new NewItemFragment();
    Bundle bundle = new Bundle();
    bundle.putString(BUNDLE_TYPE, type);
    instance.setArguments(bundle);
    return instance;
  }

  @Override protected int getContentLayoutId() {
    return R.layout.home_new_item_fragment;
  }

  @Override public void onRefresh() {
    mPresenter.getNews();
  }

  @Override protected NewItemPresenter createPresenter() {
    return new NewItemPresenter(ApiClient.retrofit().create(NewsService.class),
        getArguments().getString(BUNDLE_TYPE));
  }

  @Override public void setData(List<NewItem> data) {
    mNewItmeAdapter.clearItems();
    mNewItmeAdapter.addItems(data);
    mNewItmeAdapter.notifyDataSetChanged();
  }

  @Override public void showContent() {
    super.showContent();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showEmpty() {
    super.showEmpty();
    mSwipeRefreshLayout.setRefreshing(false);
  }

  private static class NewItmeAdapter extends BaseRecyclerAdapter<NewItem, NewItemView> {

    public NewItmeAdapter(Context context) {
      super(context);
    }

    @Override public NewItemView onCreateViewHolder(ViewGroup parent, int viewType) {
      return new NewItemView(mLayoutInflater.inflate(R.layout.home_new_item, parent, false));
    }

    @Override public void onBindViewHolder(NewItemView holder, int position) {
      NewItem newItem = getDataList().get(position);
      //            holder.tv_title.setText(newItem.getTitle());
      //            holder.tv_des.setText(newItem.getRealtype());
      holder.bindData(newItem);
    }
  }

  private static class NewItemView extends RecyclerView.ViewHolder {
    TextView tv_title;
    TextView tv_des;
    ImageView iv_img;

    public NewItemView(View itemView) {
      super(itemView);
      tv_title = (TextView) itemView.findViewById(R.id.tv_title);
      tv_des = (TextView) itemView.findViewById(R.id.tv_des);
      iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
    }

    public void bindData(final NewItem newItem) {
      tv_title.setText(newItem.getTitle());
      Picasso.with(iv_img.getContext()).load(newItem.getThumbnail_pic_s()).into(iv_img);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          CustomTabsUtils.startCustomTabs(itemView.getContext(), newItem.getUrl());
        }
      });
    }
  }
}

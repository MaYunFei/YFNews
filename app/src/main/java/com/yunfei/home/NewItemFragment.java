package com.yunfei.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yunfei.entity.NewItem;
import com.yunfei.net.ApiClient;
import com.yunfei.net.NewsService;
import com.yunfei.utils.CustomTabsUtils;
import com.yunfei.utils.L;
import com.yunfei.yfnews.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by yunfei on 2016/12/12.
 * email mayunfei6@gmail.com
 */

public class NewItemFragment extends Fragment implements HomeContract.View, SwipeRefreshLayout.OnRefreshListener {

    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerview;
    private NewItmeAdapter mNewItmeAdapter;
    private static final String BUNDLE_TYPE = "bundle_type";

    public static NewItemFragment getNewInstance(String type) {
        NewItemFragment instance = new NewItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TYPE, type);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注入
        new NewItemPresenter(this, ApiClient.retrofit().create(NewsService.class), getArguments().getString(BUNDLE_TYPE));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_new_item_fragment, container, false);
        mRecyclerview = (RecyclerView) root.findViewById(R.id.recyclerview);
        final SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewItmeAdapter = new NewItmeAdapter(getContext());
        mRecyclerview.setAdapter(mNewItmeAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
        L.i("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
        L.i("onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.i("onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.i("onDetach");
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }

        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public void showNews(List<NewItem> newItems) {
        mNewItmeAdapter.setData(newItems);
        mNewItmeAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onRefresh() {
        mPresenter.getNews();
    }

    private static class NewItmeAdapter extends RecyclerView.Adapter<NewItemView> {

        private final LayoutInflater mLayoutInflater;
        List<NewItem> mData;

        public NewItmeAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
            mData = Collections.emptyList();
        }

        public void setData(List<NewItem> data) {
            if (mData.isEmpty()) {
                mData = data;
            } else {
                mData.clear();
                mData.addAll(data);
            }
        }

        @Override
        public NewItemView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new NewItemView(mLayoutInflater.inflate(R.layout.home_new_item, parent, false));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public void onBindViewHolder(NewItemView holder, int position) {
            NewItem newItem = mData.get(position);
//            holder.tv_title.setText(newItem.getTitle());
//            holder.tv_des.setText(newItem.getRealtype());
            holder.bindData(newItem);
        }

    }

    private static class NewItemView extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_des;
        ImageView iv_img;
        View v_current;

        public NewItemView(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_des = (TextView) itemView.findViewById(R.id.tv_des);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            v_current = itemView;
        }

        public void bindData(final NewItem newItem) {
            tv_title.setText(newItem.getTitle());
            Picasso.with(iv_img.getContext())
                    .load(newItem.getThumbnail_pic_s())
                    .into(iv_img);
            v_current.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomTabsUtils.startCustomTabs(view.getContext(), newItem.getUrl());
                }
            });

        }
    }


}

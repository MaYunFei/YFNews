package com.yunfei.home;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.yunfei.yfnews.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yunfei on 2016/12/12.
 * email mayunfei6@gmail.com
 */

public class HomeActivity extends AppCompatActivity {

    private TabLayout mTablayout;
    private ViewPager mViewPager;
    private List<Type> typeList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();
        initView();
    }

    private void initView() {
        mTablayout = (TabLayout) findViewById(R.id.table_layout);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new NewsPageAdapter(getApplicationContext(), getSupportFragmentManager(), typeList));
        mTablayout.setupWithViewPager(mViewPager);
    }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_main, menu);
    MenuItem searchItem = menu.findItem(R.id.action_search);

    SearchManager searchManager = (SearchManager) this.getSystemService(Context.SEARCH_SERVICE);

    SearchView searchView = null;
    if (searchItem != null) {
      searchView = (SearchView) searchItem.getActionView();
    }
    if (searchView != null) {
      searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));
    }
    return super.onCreateOptionsMenu(menu);
  }

    private void initData() {
        typeList = new ArrayList<Type>();
        typeList.add(new Type("top", R.string.top));
        typeList.add(new Type("shehui", R.string.shehui));
        typeList.add(new Type("guonei", R.string.guonei));
        typeList.add(new Type("guoji", R.string.guoji));
        typeList.add(new Type("yule", R.string.yule));
        typeList.add(new Type("tiyu", R.string.tiyu));
        typeList.add(new Type("junshi", R.string.junshi));
        typeList.add(new Type("keji", R.string.keji));
        typeList.add(new Type("caijing", R.string.caijing));
        typeList.add(new Type("shishang", R.string.shishang));
    }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
        return true;
      default:
        // If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }
    }

    private static class NewsPageAdapter extends FragmentPagerAdapter {

        private final List<Type> typeList;
        private final Context mContext;

        public NewsPageAdapter(Context context, FragmentManager fm, List<Type> typeList) {
            super(fm);
            this.typeList = typeList;
            this.mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            return NewItemFragment.getNewInstance(typeList.get(position).type);
        }

        @Override
        public int getCount() {
            return typeList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mContext.getString(typeList.get(position).stringResourceId);
        }
    }

    private static class Type {
        String type;
        int stringResourceId;

        public Type(String type, int stringResourceId) {
            this.type = type;
            this.stringResourceId = stringResourceId;
        }
    }
}

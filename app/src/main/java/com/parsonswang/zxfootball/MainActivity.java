package com.parsonswang.zxfootball;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;

import com.parsonswang.common.base.BaseActivity;
import com.parsonswang.zxfootball.common.utils.BottomNavagationHelper;
import com.parsonswang.zxfootball.data.DataFragment;
import com.parsonswang.zxfootball.matches.MatchesFragment;
import com.parsonswang.zxfootball.news.NewsFragment;
import com.parsonswang.zxfootball.price.PriceFragment;

import java.util.ArrayList;
import java.util.List;

import io.flutter.embedding.android.FlutterFragment;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String TAG_NEWS = "news";
    private static final String TAG_MATCHFRAGMENT = "match";
    private static final String TAG_DATAFRAGMENT = "data";
    private static final String TAG_PRICEFRAGMENT = "price";

    private int mCurrMenuItemId;
    private Fragment mCurrFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            final int itemId = item.getItemId();
            switch (itemId) {
                case R.id.navigation_news:
                    Timber.i("navigation_news click");
                    switchFragment(itemId, TAG_NEWS);
                    return true;
                case R.id.navigation_match:
                    Timber.i("navigation_match click");
                    switchFragment(itemId, TAG_MATCHFRAGMENT);
                    return true;
                case R.id.navigation_data:
                    Timber.i("navigation_data click");
                    switchFragment(itemId, TAG_DATAFRAGMENT);
                    return true;
                case R.id.navigation_price:
                    Timber.i("navigation_price click");
                    switchFragment(itemId, TAG_PRICEFRAGMENT);
                    return true;
            }
            return false;
        }
    };

    private void switchFragment(int menuItemId, String tag) {
        //如果已经是当前Fragment了也就不用添加了
        if (mCurrMenuItemId == menuItemId) {
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager == null) {
            return;
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transaction == null) {
            return;
        }

        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        //刚开始就没有该fragment
        if (fragment == null) {
            fragment = createFragment(menuItemId);
            if (fragment == null && !isFinishing()) {
                transaction.commitAllowingStateLoss();
                return;
            }

            transaction.add(R.id.mFlContent, fragment, tag);
        } else {
            transaction.replace(R.id.mFlContent, fragment, tag);
        }

        //隐藏当前fragment
        if (mCurrFragment != null) {
            transaction.hide(mCurrFragment);
        }

        transaction.show(fragment);
        if (!isFinishing()) {
            transaction.commitAllowingStateLoss();
        }

        mCurrFragment = fragment;
        mCurrMenuItemId = menuItemId;

    }

    private Fragment createFragment(int menuItemId) {
        switch (menuItemId) {
            case R.id.navigation_news:
                Timber.i(TAG, "navigation_news createFragment");
                FlutterFragment NewsFragment = FlutterFragment.createDefault();
                return new NewsFragment();
            case R.id.navigation_match:
                Timber.i(TAG, "navigation_match createFragment");
                return new MatchesFragment();
            case R.id.navigation_data:
                Timber.i(TAG, "navigation_data createFragment");
                return new DataFragment();
            case R.id.navigation_price:
                Timber.i(TAG, "navigation_price createFragment");
                return new PriceFragment();
        }
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeLayoutHelper.getSwipeBackLayout().setEnable(false);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        BottomNavagationHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        switchFragment(R.id.navigation_match, TAG_MATCHFRAGMENT);

        new MethodChannel(((ZxApplication)getApplicationContext()).flutterEngine.getDartExecutor(), "zxfootball.news.getnewstab")
                .setMethodCallHandler(new MethodChannel.MethodCallHandler() {
                    @Override
                    public void onMethodCall(@NonNull MethodCall call, @NonNull MethodChannel.Result result) {
                        Timber.i("---method---" + call.method);
                        if (call.method.equals("getNewsTabs")) {
                            List<String> tabs = new ArrayList<>();
                            tabs.add("全部");
                            tabs.add("转会");
                            tabs.add("转回留言");
                            tabs.add("统计");
                            tabs.add("球员动态");
                            tabs.add("球队动态");
                            tabs.add("球队身价");
                            result.success(tabs);
                        }
                    }
                });
    }
}

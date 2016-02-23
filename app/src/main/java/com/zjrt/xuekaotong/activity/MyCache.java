package com.zjrt.xuekaotong.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.TeacherFragmentPagerAdapter;
import com.zjrt.xuekaotong.fragment.CacheUndone;
import com.zjrt.xuekaotong.fragment.Cachedone;

import java.util.ArrayList;
import java.util.List;

public class MyCache extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentManager manager;
    private List<Fragment> fragments;
    private List<String> mTitleList;
    private Cachedone cachedone;
    private CacheUndone cacheUndone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cache);
        manager = getSupportFragmentManager();
        mTitleList = new ArrayList<>();//页面选项卡集合
        mTitleList.add("已完成");
        mTitleList.add("未完成");
        cachedone = new Cachedone();
        cacheUndone = new CacheUndone();
        fragments = new ArrayList<>();
        fragments.add(cachedone);
        fragments.add(cacheUndone);
        mTabLayout = ((TabLayout) findViewById(R.id.sliding_tabs));
        mViewPager = ((ViewPager) findViewById(R.id.viewpager));
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
        TeacherFragmentPagerAdapter adapter = new TeacherFragmentPagerAdapter(manager, fragments, mTitleList);
        mTabLayout.setTabsFromPagerAdapter(adapter);
        mViewPager.setAdapter(adapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
    }

}

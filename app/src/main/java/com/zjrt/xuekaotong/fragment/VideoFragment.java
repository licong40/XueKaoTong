package com.zjrt.xuekaotong.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.activity.Search;
import com.zjrt.xuekaotong.adapter.TeacherFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class VideoFragment extends Fragment implements View.OnClickListener{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private VideoRenqiFragment videoRenqiFragment;
    private VideoTuijianFragment videoTuijianFragment;
    private VideoPriceFragment videoPriceFragment;
    private VideoFreeFragment videoFreeFragment;
    private FragmentManager manager;
    private List<Fragment> fragments;
    private LayoutInflater mInflater;
    private List<String> mTitleList;
    private ImageView search;
    private ConnectivityManager mg;

    public VideoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, null);
        mTitleList = new ArrayList<>();//页卡标题集合
        manager = getFragmentManager();
        videoRenqiFragment= new VideoRenqiFragment();
        videoTuijianFragment = new VideoTuijianFragment();
        videoPriceFragment = new VideoPriceFragment();
        videoFreeFragment = new VideoFreeFragment();
        fragments = new ArrayList<>();
        fragments.add(videoRenqiFragment);
        fragments.add(videoTuijianFragment);
        fragments.add(videoPriceFragment);
        fragments.add(videoFreeFragment);
        mTitleList.add("人气");
        mTitleList.add("推荐");
        mTitleList.add("价格");
        mTitleList.add("免费");
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search = ((ImageView) view.findViewById(R.id.search));
        mTabLayout = ((TabLayout) view.findViewById(R.id.sliding_tabs));
        mViewPager = ((ViewPager) view.findViewById(R.id.viewpager));
        mg = ((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE));
        if (mg.getActiveNetworkInfo()!=null){
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(3)));
            TeacherFragmentPagerAdapter adapter = new TeacherFragmentPagerAdapter(getChildFragmentManager(), fragments, mTitleList);
            mTabLayout.setTabsFromPagerAdapter(adapter);
            mViewPager.setAdapter(adapter);//给ViewPager设置适配器
            mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        }else {
            mViewPager.setBackgroundResource(R.mipmap.image_break1);
        }

        search.setOnClickListener(this);

    }


    public void onSaveInstanceState(Bundle outState) {
        // 防止重叠
        // TODO Auto-generated method stub
        //super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                Intent intent = new Intent();
                intent.setClass(getActivity(), Search.class);
                startActivity(intent);
                break;
        }
    }
}

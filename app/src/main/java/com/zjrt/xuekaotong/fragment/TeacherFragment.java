package com.zjrt.xuekaotong.fragment;



import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.activity.Search;
import com.zjrt.xuekaotong.adapter.TeacherFragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class TeacherFragment extends Fragment {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TeacherRecFragment teacherRecFragment;
    private TeacherFenleiFragment teacherFenleiFragment;
    private TeacherRenqiFragment teacherRenqiFragment1,teacherRenqiFragment2;
    private FragmentManager manager;
    private List<Fragment> fragments;
    private List<String> mTitleList = new ArrayList<>() ;//页卡标题集合
    private ConnectivityManager mg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_teacher, container, false);
        manager = getFragmentManager();
        teacherRecFragment = new TeacherRecFragment();
        teacherRenqiFragment1 = new TeacherRenqiFragment();
        teacherRenqiFragment2 = new TeacherRenqiFragment();
        fragments = new ArrayList<>();
        fragments.add(teacherRecFragment);
        fragments.add(teacherRenqiFragment1);
        fragments.add(teacherRenqiFragment2);
        mTitleList.add("推荐");
        mTitleList.add("人气");
        mTitleList.add("经验");
        return inflate;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = ((TabLayout) view.findViewById(R.id.sliding_tabs));
        mViewPager = ((ViewPager) view.findViewById(R.id.viewpager));
        mg = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (mg.getActiveNetworkInfo()!=null){
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
            TeacherFragmentPagerAdapter adapter= new TeacherFragmentPagerAdapter(getChildFragmentManager(),fragments,mTitleList);
            mTabLayout.setTabsFromPagerAdapter(adapter);
            mViewPager.setAdapter(adapter);//给ViewPager设置适配器
            mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        }else {
            mViewPager.setBackgroundResource(R.mipmap.image_break1);
        }

         view.findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity(new Intent(getActivity(), Search.class));
             }
         });
    }

}


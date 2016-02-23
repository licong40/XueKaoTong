package com.zjrt.xuekaotong;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.zjrt.xuekaotong.fragment.HomeFragment;
import com.zjrt.xuekaotong.fragment.QuestionFragment;
import com.zjrt.xuekaotong.fragment.ShouyeFragment;
import com.zjrt.xuekaotong.fragment.TeacherFragment;
import com.zjrt.xuekaotong.fragment.VideoFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioButton bt1, bt2, bt3, bt4, bt5;
    private RadioGroup choosePager;
    private HomeFragment home;
    private QuestionFragment question;
    private ShouyeFragment shouye;
    private TeacherFragment teacher;
    private VideoFragment video;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        if(savedInstanceState ==null){
            transaction = manager.beginTransaction();
            transaction.add(R.id.fragment,shouye).commit();
        }
        choosePager.setOnCheckedChangeListener(this);
    }

    private void init() {
        bt1 = ((RadioButton) findViewById(R.id.bt1));
        bt2 = ((RadioButton) findViewById(R.id.bt2));
        bt3 = ((RadioButton) findViewById(R.id.bt3));
        bt4 = ((RadioButton) findViewById(R.id.bt4));
        bt5 = ((RadioButton) findViewById(R.id.bt5));
        choosePager = ((RadioGroup) findViewById(R.id.radioGroup));
        shouye = new ShouyeFragment();
        manager = getSupportFragmentManager();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction.remove(shouye);
        Log.d("onCheck", "onCheck");
        switch (checkedId) {
            case R.id.bt1:
                showFragment(1);
                break;
            case R.id.bt2:
                showFragment(2);
                break;
            case R.id.bt3:
                showFragment(3);
                break;
            case R.id.bt4:
                showFragment(4);
                break;
            case R.id.bt5:
                showFragment(5);
                break;

        }
    }

    public void showFragment(int index) {
        FragmentTransaction ft = manager.beginTransaction();
        //显示一个fragment前先隐藏所有fragment，防止重叠
        hideFragments(ft);
        switch (index) {
            case 1:
                if (shouye != null)
                    ft.show(shouye);
                else {
                    shouye = new ShouyeFragment();
                    ft.add(R.id.fragment, shouye);

                }
                break;
            case 2:
                if (question != null){
                    Log.d("question", "question2");
                    ft.show(question);
                }

                else {
                    Log.d("question","question");
                    question = new QuestionFragment();
                    ft.add(R.id.fragment, question);
                }
                break;
            case 3:
                if (teacher != null)
                    ft.show(teacher);
                else {
                    teacher = new TeacherFragment();
                    ft.add(R.id.fragment, teacher);

                }
                break;
            case 4:
                if (video != null)
                    ft.show(video);
                else {
                    video = new VideoFragment();
                    ft.add(R.id.fragment, video);

                }
                break;
            case 5:
                if (home != null)
                    ft.show(home);
                else {
                    home = new HomeFragment();
                    ft.add(R.id.fragment, home);

                }
                break;
        }
        ft.commit();
    }

    //当fragment已被实例化，就隐藏起来
    private void hideFragments(FragmentTransaction ft) {
        if (shouye != null)
            ft.hide(shouye);
        if (question != null)
            ft.hide(question);
        if (teacher != null)
            ft.hide(teacher);
        if (video != null)
            ft.hide(video);
        if (home != null)
            ft.hide(home);
    }
    //防止fragment重叠
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        // super.onSaveInstanceState(outState, outPersistentState);
    }
}

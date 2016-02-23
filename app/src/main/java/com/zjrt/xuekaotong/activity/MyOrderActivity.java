package com.zjrt.xuekaotong.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.VideoAdapter;
import com.zjrt.xuekaotong.adapter.VideoAdapter2;
import com.zjrt.xuekaotong.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private ListView listview;
    private RelativeLayout paid;
    private RelativeLayout non_paid;
    private TextView num1;
    private View bar1;
    private TextView num2;
    private View bar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        init();
        Video video1 = new Video("", "一级建造师市政精讲考前冲刺课", "", "甘森");
        List<Video> list = new ArrayList<>();
        list.add(video1);
        list.add(video1);
        list.add(video1);
        list.add(video1);
        list.add(video1);
        list.add(video1);
        list.add(video1);
        VideoAdapter2 adapter = new VideoAdapter2(this, list);
        listview.setAdapter(adapter);
        back.setOnClickListener(this);
        non_paid.setOnClickListener(this);
        paid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.paid:
                num1.setTextColor(Color.argb(255, 58, 159, 255));
                bar1.setVisibility(View.VISIBLE);
                num2.setTextColor(Color.argb(255, 51, 51, 51));
                bar2.setVisibility(View.GONE);
                break;
            case R.id.non_paid:
                num2.setTextColor(Color.argb(255, 58, 159, 255));
                bar2.setVisibility(View.VISIBLE);
                num1.setTextColor(Color.argb(255, 51, 51, 51));
                bar1.setVisibility(View.GONE);
                break;

        }
    }

    private void init() {
        back = ((ImageView) findViewById(R.id.back));
        listview = ((ListView) findViewById(R.id.listview));
        paid = ((RelativeLayout) findViewById(R.id.paid));
        non_paid = ((RelativeLayout) findViewById(R.id.non_paid));
        num1 = ((TextView) paid.findViewById(R.id.num1));
        bar1 = paid.findViewById(R.id.bar1);
        num2 = ((TextView) non_paid.findViewById(R.id.num2));
        bar2 = non_paid.findViewById(R.id.bar2);
    }
}

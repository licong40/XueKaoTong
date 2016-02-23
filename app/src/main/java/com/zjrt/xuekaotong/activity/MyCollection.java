package com.zjrt.xuekaotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.VideoAdapter;
import com.zjrt.xuekaotong.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MyCollection extends AppCompatActivity implements View.OnClickListener {

    private ListView listview;
    private List<Video> list;
    private VideoAdapter adapter;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        back = ((ImageView) findViewById(R.id.back));
        listview = ((ListView) findViewById(R.id.listview));
        Video video1 = new Video("1234","一级建造师","");
        Video video2 = new Video("1234","一级建造师","");
        Video video3 = new Video("1234","一级建造师","");
        Video video4 = new Video("1234","一级建造师","");
        Video video5 = new Video("1234","一级建造师","");

        list = new ArrayList<>();
        list.add(video1);
        list.add(video2);
        list.add(video3);
        list.add(video4);
        list.add(video5);

        adapter = new VideoAdapter(this,list);
        listview.setAdapter(adapter);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}

package com.zjrt.xuekaotong.activity;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.VideoAdapter;
import com.zjrt.xuekaotong.model.Video;
import java.util.ArrayList;

public class ShouyeMore extends AppCompatActivity implements Response.ErrorListener, View.OnClickListener {
    private ArrayList<Video> videos;
    private VideoAdapter adapter;
    private ListView listview;
    private View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye_more);
        listview = ((ListView) findViewById(R.id.listview));
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        Intent intent = getIntent();
        videos = ((ArrayList<Video>) intent.getSerializableExtra("list"));
        adapter = new VideoAdapter(this,videos);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = (Video) parent.getAdapter().getItem(position);
                Intent intent = new Intent();
                intent.putExtra("id", video.getId());
                intent.putExtra("coursePhoto", video.getCoursePhoto());
                intent.setClass(ShouyeMore.this, VideoDetail.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}

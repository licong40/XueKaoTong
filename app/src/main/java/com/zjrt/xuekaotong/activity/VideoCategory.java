package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.VideoAdapter;
import com.zjrt.xuekaotong.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/7.
 */
public class VideoCategory extends AppCompatActivity implements Response.ErrorListener ,View.OnClickListener{
    private TextView textView;
    private List<Video> videos;
    private RequestQueue queue;
    private VideoAdapter adapter;
    private ListView listview;
    private View back;
    private String _id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye_more);
        textView = ((TextView) findViewById(R.id.textView));
        listview = ((ListView) findViewById(R.id.listview));
        back = findViewById(R.id.back);
        back.setOnClickListener(this);
        Intent intent = getIntent();
        _id = intent.getStringExtra("_id");
        String str = intent.getStringExtra("video");
        textView.setText(str);
        queue = Volley.newRequestQueue(this);
        videos = new ArrayList<>();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://www.xuekaotong.cn/api/school/categories/"+_id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("result");
                    for(int i=0;i<result.length();i++){
                        JSONObject object = result.getJSONObject(i);
                        String id = object.getString("id");
                        String title = object.getString("title");
                        String coursePhoto = object.getString("coursePhoto");
                        Video video = new Video(id,title,coursePhoto);
                        videos.add(video);
                    }
                    adapter = new VideoAdapter(VideoCategory.this,videos);
                    listview.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, this);
        request.setTag(this);
        queue.add(request);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = (Video) parent.getAdapter().getItem(position);
                String _id = video.getId();
                Intent intent1 = new Intent();
                intent1.putExtra("id",_id);
                intent1.setClass(VideoCategory.this,VideoDetail.class);
                startActivity(intent1);

            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {

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

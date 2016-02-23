package com.zjrt.xuekaotong.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zjrt.xuekaotong.CreateDialog;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.activity.VideoDetail;
import com.zjrt.xuekaotong.adapter.VideoAdapter;
import com.zjrt.xuekaotong.model.Video;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoTuijianFragment extends Fragment implements Response.ErrorListener{
    private VideoAdapter adapter;
    private RequestQueue queue;
    private List<Video> videos;
    private ListView listview;

    public VideoTuijianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_video_renqi, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queue = Volley.newRequestQueue(getActivity());
        videos = new ArrayList<>();
        listview = ((ListView) view.findViewById(R.id.listview));
        final Dialog dialog = CreateDialog.createLoadingDialog(getActivity(), "正在加载，请稍后...");
        dialog.show();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://www.xuekaotong.cn/api/school/categories/210791", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("result");
                    for(int i=0;i<array.length();i++){
                        JSONObject object = array.getJSONObject(i);
                        String id = object.getString("id");
                        String title = object.getString("title");
                        String coursePhoto = object.getString("coursePhoto");
                        String teacher = object.getString("teacher");
                        Video video = new Video(id,title,coursePhoto,teacher);
                        videos.add(video);
                    }
                    adapter = new VideoAdapter(getActivity(),videos);
                    listview.setAdapter(adapter);
                    dialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, this);
        request.setTag(this);
        queue.add(request);
        listview.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = (Video) parent.getAdapter().getItem(position);
                Intent intent = new Intent();
                intent.putExtra("id", video.getId());
                intent.putExtra("coursePhoto",video.getCoursePhoto());
                intent.setClass(getActivity(), VideoDetail.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}

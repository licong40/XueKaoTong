package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.TeacherListAdapter;
import com.zjrt.xuekaotong.model.Teacher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ShouyeTeacherMore extends AppCompatActivity implements Response.ErrorListener,View.OnClickListener,AdapterView.OnItemClickListener {
    private RequestQueue queue;
    private Teacher teacher;
    private List<Teacher> teachers;
    private TeacherListAdapter adapter;
    private ListView listview;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shouye_teacher_more);
        queue = Volley.newRequestQueue(this);
        listview = ((ListView) findViewById(R.id.listview));
        back = ((ImageView) findViewById(R.id.back));
        back.setOnClickListener(this);
        teachers = new ArrayList<>();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://www.xuekaotong.cn/api/mobile/home/teachers", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    JSONArray list = result.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        String _id = object.getString("id");
                        String name = object.getString("name");
                        String photoUrl = object.getString("photo");
                        String major = object.getString("major");
                        teacher = new Teacher(_id, name, photoUrl,major);
                        teachers.add(teacher);
                    }
                    adapter = new TeacherListAdapter(ShouyeTeacherMore.this,teachers);
                    listview.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, this);
        request.setTag(this);
        queue.add(request);
        listview.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        teacher = (Teacher)parent.getAdapter().getItem(position);
        intent.putExtra("id",teacher.getId());
        intent.setClass(this, TeacherDetail.class);
        startActivity(intent);
    }
}

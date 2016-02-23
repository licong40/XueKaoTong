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
import com.zjrt.xuekaotong.activity.TeacherDetail;
import com.zjrt.xuekaotong.adapter.TeacherListAdapter;
import com.zjrt.xuekaotong.model.Teacher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class TeacherRecFragment extends Fragment implements Response.ErrorListener {
    private ListView listview;
    private Teacher teacher;
    private TeacherListAdapter adapter2;
    private RequestQueue queue;
    private List<Teacher> teachers;

    public TeacherRecFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_rec, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Dialog dialog = CreateDialog.createLoadingDialog(getActivity(), "正在加载，请稍后...");
        dialog.show();
        initView(view);
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
                        String major = object.getString("major");
                        String photoUrl = object.getString("photo");
                        teacher = new Teacher(_id, name, photoUrl,major);
                        teachers.add(teacher);
                    }
                    adapter2 = new TeacherListAdapter(getActivity(), teachers);
                    listview.setAdapter(adapter2);
                    dialog.cancel();
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
                Intent intent = new Intent();
                teacher = (Teacher)parent.getAdapter().getItem(position);
                intent.putExtra("id",teacher.getId());
                intent.setClass(getActivity(), TeacherDetail.class);
                startActivity(intent);
            }
        });

    }
    private void initView(View view) {
        listview = ((ListView) view.findViewById(R.id.listview));
        teacher = new Teacher();
        teachers = new ArrayList<Teacher>();
        queue = Volley.newRequestQueue(getActivity());
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
    public void onSaveInstanceState(Bundle outState) {
        // 防止重叠
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onStop() {
        super.onStop();
    }
}

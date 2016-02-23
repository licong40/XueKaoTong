package com.zjrt.xuekaotong.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.activity.Search;
import com.zjrt.xuekaotong.activity.ShouyeMore;
import com.zjrt.xuekaotong.activity.ShouyeTeacherMore;
import com.zjrt.xuekaotong.activity.TeacherDetail;
import com.zjrt.xuekaotong.activity.VideoCategory;
import com.zjrt.xuekaotong.activity.VideoDetail;
import com.zjrt.xuekaotong.adapter.Grid2Adapter;
import com.zjrt.xuekaotong.adapter.GridAdapter;
import com.zjrt.xuekaotong.citylist.LocationMainActivity;
import com.zjrt.xuekaotong.model.Teacher;
import com.zjrt.xuekaotong.model.Video;
import com.zjrt.xuekaotong.view.MyGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ShouyeFragment extends Fragment implements View.OnClickListener, Response.ErrorListener {
    private MyGridView gridView, gridView2, gridView3, gridView4;
    private ImageView message_bt;
    private RequestQueue queue,queue2,queue3;
    private ArrayList<Video> videos,videos1,videos2,videos3;
    private GridAdapter adapter1,adapter2,adapter5;
    private Grid2Adapter adapter3;
    private Teacher teacher;
    private List<Teacher> teachers1;
    private TextView jingpin_more;
    private TextView teacher_more;
    private TextView newcourse_more;
    private TextView vipcourse_more;
    private RadioButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8;
    private EditText search;
    private View city;


    public ShouyeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shouye, container, false);

    }

    @Override
    public void onViewCreated(View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        queue = Volley.newRequestQueue(getActivity());
        queue2 = Volley.newRequestQueue(getActivity());
        queue3 = Volley.newRequestQueue(getActivity());
        Teacher teacher1 = new Teacher("","甘森","");
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher1);
        teachers.add(teacher1);
        teachers.add(teacher1);
        final Grid2Adapter adapter = new Grid2Adapter(getActivity(),teachers);
        gridView2.setAdapter(adapter);
        Video video1 = new Video("","一级建造师精讲课","");
        List<Video> list = new ArrayList<>();
        list.add(video1);
        list.add(video1);
        list.add(video1);
        list.add(video1);
        GridAdapter adapter4 = new GridAdapter(getActivity(),list);
        gridView.setAdapter(adapter4);
        gridView3.setAdapter(adapter4);
        gridView4.setAdapter(adapter4);
        JsonObjectRequest request2 = new JsonObjectRequest(Request.Method.GET, "http://www.xuekaotong.cn/api/mobile/home/teachers", null, new Response.Listener<JSONObject>() {
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
                                    teachers1.add(teacher);
                                }
                    adapter3 = new Grid2Adapter(getActivity(),teachers1);
                    gridView2.setAdapter(adapter3);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, this);
        final JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, "http://www.xuekaotong.cn/api/mobile/home/courses", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("result");
                    JSONArray list = jsonObject.getJSONArray("list");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        String id = object.getString("id");
                        String title = object.getString("title");
                        String coursePhoto = object.getString("photo");
                        String teacher = object.getString("teacher");
                        String type = object.getString("type");
                        Video video = new Video(id,title,coursePhoto,teacher);
                        if(type.equals("0")){
                            videos1.add(video);
                        }else if(type.equals("1")){
                            videos2.add(video);
                        }else{
                            videos3.add(video);
                        }
                       // videos.add(video);
                    }
                    adapter1 = new GridAdapter(getActivity(), videos1);
                    adapter2 = new GridAdapter(getActivity(), videos2);
                    adapter5 = new GridAdapter(getActivity(), videos3);
                    gridView.setAdapter(adapter1);
                    gridView3.setAdapter(adapter2);
                    gridView4.setAdapter(adapter5);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, this);
        request1.setTag(this);
        queue.add(request1);
        request2.setTag(this);
        queue2.add(request2);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = (Video) parent.getAdapter().getItem(position);
                String id1 = video.getId();
                Intent intent = new Intent();
                intent.putExtra("id", id1);
                intent.putExtra("coursePhoto", video.getCoursePhoto());
                intent.setClass(getActivity(), VideoDetail.class);
                startActivity(intent);
            }
        });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Teacher teacher = (Teacher) parent.getAdapter().getItem(position);
                String _id = teacher.getId();
                Intent intent = new Intent();
                intent.putExtra("id",_id);
                intent.setClass(getActivity(), TeacherDetail.class);
                startActivity(intent);

            }
        });
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = (Video) parent.getAdapter().getItem(position);
                String id1 = video.getId();
                Intent intent = new Intent();
                intent.putExtra("id", id1);
                intent.putExtra("coursePhoto",video.getCoursePhoto());
                intent.setClass(getActivity(), VideoDetail.class);
                startActivity(intent);
            }
        });
        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = (Video) parent.getAdapter().getItem(position);
                String id1 = video.getId();
                Intent intent = new Intent();
                intent.putExtra("id", id1);
                intent.putExtra("coursePhoto",video.getCoursePhoto());
                intent.setClass(getActivity(), VideoDetail.class);
                startActivity(intent);
            }
        });

        message_bt.setOnClickListener(this);
        jingpin_more.setOnClickListener(this);
        newcourse_more.setOnClickListener(this);
        vipcourse_more.setOnClickListener(this);
        teacher_more.setOnClickListener(this);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        bt5.setOnClickListener(this);
        bt6.setOnClickListener(this);
        bt7.setOnClickListener(this);
        bt8.setOnClickListener(this);
        search.setOnClickListener(this);
        city.setOnClickListener(this);

    }
    private void init(View view) {
        gridView = ((MyGridView) view.findViewById(R.id.gridView));
        gridView2 = ((MyGridView) view.findViewById(R.id.teacher));
        gridView3 = ((MyGridView) view.findViewById(R.id.newCourse));
        gridView4 = ((MyGridView) view.findViewById(R.id.vipCourse));
        message_bt = ((ImageView) view.findViewById(R.id.message_bt));
        jingpin_more = ((TextView) view.findViewById(R.id.jingpin_more));
        teacher_more = ((TextView) view.findViewById(R.id.teacher_more));
        newcourse_more = ((TextView) view.findViewById(R.id.newcourse_more));
        vipcourse_more = ((TextView) view.findViewById(R.id.vipcourse_more));
        search = ((EditText) view.findViewById(R.id.search));
        bt1 = ((RadioButton) view.findViewById(R.id.bt1));
        bt2 = ((RadioButton) view.findViewById(R.id.bt2));
        bt3 = ((RadioButton) view.findViewById(R.id.bt3));
        bt4 = ((RadioButton) view.findViewById(R.id.bt4));
        bt5 = ((RadioButton) view.findViewById(R.id.bt5));
        bt6 = ((RadioButton) view.findViewById(R.id.bt6));
        bt7 = ((RadioButton) view.findViewById(R.id.bt7));
        bt8 = ((RadioButton) view.findViewById(R.id.bt8));
        city = view.findViewById(R.id.city);
        teachers1 = new ArrayList<>();
        videos = new ArrayList<>();
        videos1 = new ArrayList<>();
        videos2 = new ArrayList<>();
        videos3 = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_bt:
//                Intent intent1 = new Intent();
//                intent1.setClass(getActivity(), MyMessageActivity.class);
//                startActivity(intent1);
                break;
            case R.id.jingpin_more:
                Intent intent2 = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",videos1);
                intent2.putExtras(bundle);
                intent2.setClass(getActivity(), ShouyeMore.class);
                startActivity(intent2);
                break;
            case R.id.teacher_more:
                Intent intent3 = new Intent();
                intent3.setClass(getActivity(), ShouyeTeacherMore.class);
                startActivity(intent3);
                break;
            case R.id.newcourse_more:
                Intent intent4 = new Intent();
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("list", videos2);
                intent4.putExtras(bundle1);
                intent4.setClass(getActivity(), ShouyeMore.class);
                startActivity(intent4);
                break;
            case R.id.vipcourse_more:
                Intent intent5 = new Intent();
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("list", videos3);
                intent5.putExtras(bundle2);
                intent5.setClass(getActivity(), ShouyeMore.class);
                startActivity(intent5);
                break;
            case R.id.bt1:
                Intent intent6 = new Intent();
                intent6.putExtra("video","注册建造师课程");
                intent6.putExtra("_id","207470");
                intent6.setClass(getActivity(), VideoCategory.class);
                startActivity(intent6);
                break;
            case R.id.bt2:
                Intent intent7 = new Intent();
                intent7.putExtra("_id","209731");
                intent7.putExtra("video","结构工程师课程");
                intent7.setClass(getActivity(), VideoCategory.class);
                startActivity(intent7);
                break;
            case R.id.bt3:
                Intent intent8 = new Intent();
                intent8.putExtra("_id","207512");
                intent8.putExtra("video","造价工程师课程");
                intent8.setClass(getActivity(), VideoCategory.class);
                startActivity(intent8);
                break;
            case R.id.bt4:
                Intent intent9 = new Intent();
                intent9.putExtra("_id","209734");
                intent9.putExtra("video","监理工程师课程");
                intent9.setClass(getActivity(), VideoCategory.class);
                startActivity(intent9);
                break;
            case R.id.bt5:
                Intent intent10 = new Intent();
                intent10.putExtra("_id","209747");
                intent10.putExtra("video","消防工程师课程");
                intent10.setClass(getActivity(), VideoCategory.class);
                startActivity(intent10);
                break;
            case R.id.bt6:
                Intent intent11 = new Intent();
                intent11.putExtra("_id","209749");
                intent11.putExtra("video","电气工程师课程");
                intent11.setClass(getActivity(), VideoCategory.class);
                startActivity(intent11);
                break;
            case R.id.bt7:
                Intent intent12 = new Intent();
                intent12.putExtra("_id","209904");
                intent12.putExtra("video","物业管理师课程");
                intent12.setClass(getActivity(), VideoCategory.class);
                startActivity(intent12);
                break;
            case R.id.bt8:
                Intent intent13 = new Intent();
                intent13.setClass(getActivity(), Search.class);
                startActivity(intent13);
                break;
            case R.id.search:
                Intent intent = new Intent();
                intent.setClass(getActivity(), Search.class);
                startActivity(intent);
                break;
            case R.id.city:
                Intent i = new Intent();
                i.setClass(getActivity(), LocationMainActivity.class);
                startActivityForResult(i, getActivity().RESULT_FIRST_USER);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}

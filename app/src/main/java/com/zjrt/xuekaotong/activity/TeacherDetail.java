package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.VideoAdapter;
import com.zjrt.xuekaotong.model.Teacher;
import com.zjrt.xuekaotong.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import it.sephiroth.android.library.picasso.Picasso;
public class TeacherDetail extends AppCompatActivity implements View.OnClickListener, Response.ErrorListener {
    private Teacher teacher;
    private ImageView back;
    private RequestQueue queue;
    private TextView name;
    private TextView work_exp;
    private TextView major;
    private TextView work_exp2;
    private TextView major2;
    //private TextView desc;
    private ImageView portrait;
    private List<Video> videos;
    private VideoAdapter adapter;
    private ListView listview;
    private LinearLayout layoutView;
    private TextView descriptionView;
    private View expandView;
    int maxDescripLine = 3;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);
        init();
        queue = Volley.newRequestQueue(this);
        back.setOnClickListener(this);
        Intent intent = getIntent();
        String id = String.valueOf(intent.getStringExtra("id"));
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://www.xuekaotong.cn/api/mobile/home/teachers" + "/" + id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("result");
                    name.setText(object.getString("name"));
                    major.setText("主讲科目："+object.getString("major"));
                    work_exp.setText("教学经验："+object.getString("work_exp")+"年");
                    work_exp2.setText("教学经验：" + object.getString("work_exp")+"年");
                    major2.setText("主讲科目："+object.getString("major"));
                    descriptionView.setText(object.getString("description"));
                    descriptionView.setHeight(descriptionView.getLineHeight() * maxDescripLine);
                    //根据高度来判断是否需要再点击展开
                    descriptionView.post(new Runnable() {

                        @Override
                        public void run() {
                            text.setText("点击查看更多");
                            expandView.setVisibility(descriptionView.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
                        }
                    });
                    String photo = object.getString("photo");
                    Picasso.with(TeacherDetail.this).load(photo).into(portrait);
                    JSONObject categories = object.getJSONObject("categories");
                    JSONArray list = categories.getJSONArray("list");
                    for(int i=0;i<list.length();i++){
                        JSONObject object1 = list.getJSONObject(i);
                        String _id = object1.getString("id");
                        String title = object1.getString("title");
                        String _photo = object1.getString("photo");
                        Video video = new Video(_id,title,_photo,object.getString("name"));
                        videos.add(video);
                    }
                    adapter = new VideoAdapter(TeacherDetail.this,videos);
                    listview.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, this);
        request.setTag(this);
        queue.add(request);
        layoutView.setOnClickListener(new View.OnClickListener() {
            boolean isExpand;//是否已展开的状态

            @Override
            public void onClick(View v) {
                isExpand = !isExpand;
                descriptionView.clearAnimation();//清楚动画效果
                final int deltaValue;//默认高度，即前边由maxLine确定的高度
                final int startValue = descriptionView.getHeight();//起始高度
                int durationMillis = 350;//动画持续时间
                if (isExpand) {
                    /**
                     * 折叠动画
                     * 从实际高度缩回起始高度
                     */
                    deltaValue = descriptionView.getLineHeight() * descriptionView.getLineCount() - startValue;
                    RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                    text.setText("点击收起");
                } else {
                    /**
                     * 展开动画
                     * 从起始高度增长至实际高度
                     */
                    deltaValue = descriptionView.getLineHeight() * maxDescripLine - startValue;
                    RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    animation.setDuration(durationMillis);
                    animation.setFillAfter(true);
                    expandView.startAnimation(animation);
                    text.setText("点击查看更多");
                }
                Animation animation = new Animation() {
                    protected void applyTransformation(float interpolatedTime, Transformation t) { //根据ImageView旋转动画的百分比来显示textview高度，达到动画效果
                        descriptionView.setHeight((int) (startValue + deltaValue * interpolatedTime));
                    }
                };
                animation.setDuration(durationMillis);
                descriptionView.startAnimation(animation);
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video video = (Video) parent.getAdapter().getItem(position);
                String _id = video.getId();
                Intent intent1 = new Intent();
                intent1.putExtra("id", _id);
                intent1.putExtra("coursePhoto",video.getCoursePhoto());
                intent1.setClass(TeacherDetail.this, VideoDetail.class);
                startActivity(intent1);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    private void init() {
        name = ((TextView) findViewById(R.id.name));
        work_exp = ((TextView) findViewById(R.id.work_exp));
        major = ((TextView) findViewById(R.id.major));
        work_exp2 = ((TextView) findViewById(R.id.work_exp2));
        major2 = ((TextView) findViewById(R.id.major2));
        back = ((ImageView) findViewById(R.id.back));
        portrait = ((ImageView) findViewById(R.id.portrait));
        listview = ((ListView) findViewById(R.id.listview));
        layoutView = ((LinearLayout) findViewById(R.id.description_layout));
        descriptionView = ((TextView) findViewById(R.id.description_view));
        expandView = findViewById(R.id.expand_view);
        text = ((TextView) findViewById(R.id.text));
        videos = new ArrayList<>();
    }
}

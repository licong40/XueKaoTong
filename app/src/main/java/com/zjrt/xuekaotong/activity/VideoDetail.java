package com.zjrt.xuekaotong.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tencent.qcload.playersdk.ui.PlayerActionInterface;
import com.tencent.qcload.playersdk.ui.TitleMenu;
import com.tencent.qcload.playersdk.ui.UiChangeInterface;
import com.tencent.qcload.playersdk.ui.VideoRootFrame;
import com.tencent.qcload.playersdk.util.PlayerListener;
import com.tencent.qcload.playersdk.util.VideoInfo;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.ablesky.TypeDef;
import com.zjrt.xuekaotong.adapter.VideoDetailListAdapter;
import com.zjrt.xuekaotong.adapter.VideoDownListAdapter;
import com.zjrt.xuekaotong.model.Course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

public class VideoDetail extends AppCompatActivity implements View.OnClickListener {
    private String url = "http://www.xuekaotong.cn/api/school/courses/";
    private RequestQueue queue;
    private ListView listview;
    private List<Course> list;
    private ArrayList<String> downList;
    VideoDetailListAdapter adapter;
    VideoDownListAdapter adapter2;
    String id1, _id;
    private ImageView down;
    private int checkNum;
    private String TAG = "VideoDetail";
    private VideoRootFrame player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        player = (VideoRootFrame) findViewById(R.id.player);
        List<TitleMenu> videoTitleMenus = new ArrayList<TitleMenu>();
        TitleMenu icon1 = new TitleMenu();
        icon1.iconId = R.drawable.ic_share;
        icon1.action = new PlayerActionInterface() {
            @Override
            public void action() {
                Toast.makeText(VideoDetail.this, "share icon taped", Toast.LENGTH_SHORT).show();
            }
        };
        videoTitleMenus.add(icon1);
        TitleMenu icon2 = new TitleMenu();
        icon2.iconId = R.drawable.ic_favorite;
        videoTitleMenus.add(icon2);
        TitleMenu icon3 = new TitleMenu();
        icon3.iconId = R.drawable.ic_perm_identity;
        videoTitleMenus.add(icon3);
        player.setMenu(videoTitleMenus);
        List<VideoInfo> videos = new ArrayList<VideoInfo>();
        VideoInfo v1 = new VideoInfo();
        v1.description = "标清";
        v1.type = VideoInfo.VideoType.MP4;
        v1.url = "http://200005887.vod.myqcloud.com/200005887_12a1c04ad55211e5a91f5d250d829fc2.f0.mp4";
        videos.add(v1);
        VideoInfo v2 = new VideoInfo();
        v2.description = "高清";
        v2.type = VideoInfo.VideoType.MP4;
        v2.url = "http://200005887.vod.myqcloud.com/200005887_12a1c04ad55211e5a91f5d250d829fc2.f0.mp4";
        videos.add(v2);
        player.setListener(new PlayerListener() {

            @Override
            public void onError(Exception arg0) {
                arg0.printStackTrace();

            }

            @Override
            public void onStateChanged(int arg0) {
                Log.d(TAG, "player states:" + arg0);

            }

        });
        player.play(videos);
        player.setToggleFullScreenHandler(new UiChangeInterface() {
            @Override
            public void OnChange() {
                if (player.isFullScreen()) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
        listview = ((ListView) findViewById(R.id.listview));
        down = ((ImageView) findViewById(R.id.back));
        list = new ArrayList<Course>();
        downList = new ArrayList<String>();
        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        id1 = intent.getStringExtra("id");
        JsonObjectRequest request = new JsonObjectRequest(url + id1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("result");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        _id = object.optString("id");
                        String title = object.optString("title");
                        Log.d("title", title);
                        Course course = new Course(_id, title);
                        list.add(course);
                    }
                    adapter = new VideoDetailListAdapter(VideoDetail.this, list);
                    listview.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setTag(this);
        queue.add(request);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent1 = new Intent();
                intent1.setClassName(getApplication(),
                        "com.ablesky.activity.VerificationActivity");
                intent1.putExtra("username", "18600895117");
                intent1.putExtra("password", "a7777777");
                intent1.putExtra("course_id", id1);
                String id2 = ((Course) parent.getAdapter().getItem(position)).getId();
                intent1.putExtra("course_item_id", _id);
                intent1.putExtra("video_path_courseid", "");
                if (id2.equals("null")) {
                    Toast.makeText(VideoDetail.this, "不支持播放pdf格式文件", Toast.LENGTH_LONG).show();
                } else {
                    startActivityForResult(intent1, 0);
                }
            }
        });
        findViewById(R.id.down).setOnClickListener(this);
    }

    public void setDialogSize(Dialog dg) {
        Window dialogWindow = dg.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();// 获取对话框当前的参数值
        dialogWindow.setGravity(Gravity.BOTTOM);//设置对话框屏幕居中
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay();// 获取屏幕宽、高用
        lp.height = (int) (d.getHeight() * 0.6);// 高度设置为屏幕的0.25
        lp.width = d.getWidth();// 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(lp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.down:
                Log.d("aa", "aa");
                final LinearLayout layout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.video_list_down, null);
                final Dialog dialog = new AlertDialog.Builder(this, R.style.loading_dialog2).create();
                ListView listView = ((ListView) layout.findViewById(R.id.listview));
                adapter2 = new VideoDownListAdapter(VideoDetail.this, list);
                listView.setAdapter(adapter2);
                dialog.show();
                setDialogSize(dialog);
                dialog.getWindow().setContentView(layout);
                layout.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        downList.clear();
                        dialog.dismiss();
                    }
                });
                //listview多选
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                        VideoDownListAdapter.ViewHolder holder = (VideoDownListAdapter.ViewHolder) view.getTag();
                        // 改变CheckBox的状态
                        holder.cb.toggle();
                        // 将CheckBox的选中状况记录下来
                        VideoDownListAdapter.getIsSelected().put(position, holder.cb.isChecked());
                        // 调整选定条目
                        if (holder.cb.isChecked() == true) {
                            checkNum++;
                            downList.add(((Course) parent.getAdapter().getItem(position)).getId());
                            Log.d("downList", downList.toString());
                        } else {
                            checkNum--;
                        }
                    }
                });
                layout.findViewById(R.id.download_sure).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClassName(getApplication(),
                                "com.ablesky.activity.VerificationDownloaderActivity");
                        intent.putExtra("username", "18600895117");
                        intent.putExtra("password", "a7777777");
                        intent.putExtra("course_id", id1);
                        intent.putExtra("downloadlist", downList);
                        Log.d("downList", downList.toString());
                        intent.putExtra("sd_path", "/mnt/sdcard/jsxl/video/");
                        startActivityForResult(intent, 0);


                    }
                });
                //缓存全部
                layout.findViewById(R.id.download).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setClassName(getApplication(),
                                "com.ablesky.activity.VerificationDownloaderActivity");
                        intent.putExtra("username", "18600895117");
                        intent.putExtra("password", "a7777777");
                        intent.putExtra("course_id", id1);
                        ArrayList<String> downloadList = new ArrayList<String>();
                        for (int i = 0; i < list.size(); i++) {
                            downloadList.add(list.get(i).getId());
                        }
                        intent.putExtra("downloadlist", downloadList);
                        intent.putExtra("sd_path", "/mnt/sdcard/jsxl/video/");
                        startActivityForResult(intent, 0);
                    }
                });
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            Log.d("ss","ss");
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            return false;
        }
        return false;
    }
}


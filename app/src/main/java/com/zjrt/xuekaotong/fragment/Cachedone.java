package com.zjrt.xuekaotong.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ablesky.activity.ControlPauseContinue;
import com.ablesky.download.DownloadFinished;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.VideoDoneListAdapter;
import com.zjrt.xuekaotong.model.Course;

import java.util.ArrayList;
import java.util.List;

public class Cachedone extends Fragment implements AdapterView.OnItemClickListener{
    private  List<DownloadFinished> list;
    private ListView listview;
    private int checkNum;
    private String username = "18600895117";// 用户名
    private String password = "a7777777";// 密码
    private ControlPauseContinue control;
    public Cachedone() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cache_done, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listview = ((ListView) view.findViewById(R.id.listview));
        control = new ControlPauseContinue(getContext(), username,
                password, "/mnt/sdcard/jsxl/video/");
         list = control.queryDownloadFinishList(username);
        VideoDoneListAdapter adapter = new VideoDoneListAdapter(getActivity(), list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得ViewHolder对象，这样就省去了通过层层的findViewById去实例化我们需要的cb实例的步骤
                VideoDoneListAdapter.ViewHolder holder = (VideoDoneListAdapter.ViewHolder) view.getTag();
                // 改变CheckBox的状态
                holder.cb.toggle();
                // 将CheckBox的选中状况记录下来
                VideoDoneListAdapter.getIsSelected().put(position, holder.cb.isChecked());
                // 调整选定条目
                if (holder.cb.isChecked() == true) {
                    checkNum++;
                } else {
                    checkNum--;
                }
                DownloadFinished down = list.get(position);
                String coursid = down.getCourseid();
                String type = down.getType();
                String snapid = down.getSnapid();

                Intent intent = new Intent();
                intent.setClassName(getContext(),
                        "com.ablesky.activity.VerificationActivity");
                intent.putExtra("username", username);
                intent.putExtra("password", password);

                intent.putExtra("course_item_id", coursid);// 课件id
                intent.putExtra("course_id", snapid);// 课程id
                intent.putExtra("snapshot_id", "");// 快照id

                // 本地路径 ======********>>>课件id 714721
                intent.putExtra("video_path_courseid", "/mnt/sdcard/jsxl/video/"
                        + coursid);

                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

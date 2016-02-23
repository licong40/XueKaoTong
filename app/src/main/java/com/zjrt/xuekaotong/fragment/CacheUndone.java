package com.zjrt.xuekaotong.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ablesky.activity.ControlPauseContinue;
import com.ablesky.download.DownloadService;
import com.ablesky.download.DownloaderUnFinished;
import com.ablesky.download.MyData;
import com.ablesky.util.ThreadPoolUtil;
import com.zjrt.xuekaotong.R;

import java.text.DecimalFormat;
import java.util.List;

public class CacheUndone extends Fragment {
    private String username = "18600895117";// 用户名
    private String password = "a7777777";// 密码
    private List<DownloaderUnFinished> downloadingList;
    private ControlPauseContinue control;
    private int progress;
    private DownloadAdapter downLoadAdapter;
    private String downloading_courseid;
    private int max;
    private DownReceiver downReceiver;
    private Handler mHandler = new Handler() {
        public void handleMessage(final Message msg) {
            switch (msg.what) {
                case 1:
                    if (downLoadAdapter != null) {
                        downLoadAdapter.notifyDataSetChanged();
                    }
                    break;
                case 12:
                    downloadingList = control.queryDownloadList(username);
                    downLoadAdapter.notifyDataSetChanged();
                    break;

                case 70:
                    downloadingList = control.queryDownloadList(username);
                    if (downLoadAdapter != null) {
                        downLoadAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };
    private ListView lv_download;

    public CacheUndone() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cache_undone, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv_download = ((ListView) view.findViewById(R.id.listview));
        control = new ControlPauseContinue(getContext(), username, password, "/mnt/sdcard/jsxl/video/");
        downloadingList = control.queryDownloadList(username);
        registerDownloadReceiver();
        downLoadAdapter = new DownloadAdapter();
        lv_download.setAdapter(downLoadAdapter);

    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 接受广播
     */
    class DownReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, final Intent intent) {
            ThreadPoolUtil.getInstance().execute(new Runnable() {

                @Override
                public void run() {
                    MyData data = (MyData) intent
                            .getSerializableExtra(DownloadService.DATA);
                    try {

                        if (data.flag) {
                            progress = data.progress;
                            max = data.max;
                            downloading_courseid = data.courseid;

                            Message msg = Message.obtain();
                            msg.what = 1;
                            mHandler.sendMessage(msg);
                        } else {
                            Message msg = Message.obtain();
                            msg.what = 70;
                            mHandler.sendMessage(msg);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private class DownloadAdapter extends BaseAdapter {
        private DownloaderUnFinished download;
        boolean flag;

        @Override
        public int getCount() {
            return (downloadingList == null) ? 0 : downloadingList.size();
        }

        @Override
        public Object getItem(int arg0) {
            return null;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            download = downloadingList.get(position);
            View view = View.inflate(getContext(), R.layout.video_undone_list_item, null);
            TextView title = ((TextView) view.findViewById(R.id.title));
            TextView yixiazai = (TextView) view.findViewById(R.id.yixiazai);
            TextView quanbu = (TextView) view.findViewById(R.id.quanbu);
            ProgressBar bar = (ProgressBar) view.findViewById(R.id.custom_choose_sb);
            title.setText(download.getTitle());
            quanbu.setText(FormetFileSize(download.getEndPos()));

            bar.setMax(download.getEndPos());

            if (download.getDownstatus() == 1
                    && download.getCourseid().equals(downloading_courseid)) {
                // TODO 设置进度条刷新
                yixiazai.setText(FormetFileSize(progress) + "/");
                bar.setProgress(progress);
            }
            if (download.getDownstatus() == 2 || download.getDownstatus() == 0) {
                yixiazai.setText(FormetFileSize(download.getCompeleteSize())
                        + "/");
                bar.setProgress(download.getCompeleteSize());
            }
            int downstatus = download.getDownstatus();
            String isexist = download.getIsexist();
            return view;
        }
    }
    private void registerDownloadReceiver() {
        downReceiver = new DownReceiver();
        IntentFilter filter = new IntentFilter(DownloadService.ACTION_UPDATE_DATA);
        getActivity().registerReceiver(downReceiver, filter);
    }
}

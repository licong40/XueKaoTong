package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.Video;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2015/11/16.
 */
public class VideoAdapter extends BaseAdapter {
    private Context context;
    private List<Video> list;

    public VideoAdapter(Context context, List<Video> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.video_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText(list.get(position).getTitle());
        holder.teacherName.setText("主讲老师:" + list.get(position).getTeacher() + " 课时:2小时");
        int number = ((int) (Math.random() * 1000));
        holder.num.setText(String.valueOf(number)+"人在学");
        if (list.get(position).getCoursePhoto().equals("")){
            holder.coursePhoto.setImageResource(R.mipmap.image6);
        }else {
            Picasso.with(context).load(list.get(position).getCoursePhoto()).into(holder.coursePhoto);
        }

        return convertView;
    }

    private class ViewHolder {
        private final TextView title;
        private final ImageView coursePhoto;
        private final TextView teacherName,num;

        ViewHolder(View view) {
            title = ((TextView) view.findViewById(R.id.title));
            coursePhoto = ((ImageView) view.findViewById(R.id.coursePhoto));
            teacherName = ((TextView) view.findViewById(R.id.teacherName));
            num = ((TextView) view.findViewById(R.id.num));
        }
    }
}

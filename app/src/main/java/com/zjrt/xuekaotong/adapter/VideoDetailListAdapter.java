package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.Course;

import java.util.List;

/**
 * Created by Administrator on 2015/11/24.
 */
public class VideoDetailListAdapter extends BaseAdapter {
    private Context context;
    private List<Course> list;

    public VideoDetailListAdapter(Context context, List<Course> list) {
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
        if (convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.video_course_list_item,null);
            convertView.setTag(new ViewHolder(convertView));
        }else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText(list.get(position).getTitle());
        return convertView;
    }
    public class ViewHolder{
        private TextView title;
        ViewHolder(View view){
            title = ((TextView) view.findViewById(R.id.courseName));
        }
    }
}

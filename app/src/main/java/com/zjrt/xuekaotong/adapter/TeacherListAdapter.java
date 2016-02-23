package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.Teacher;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2015/11/16.
 */
public class TeacherListAdapter extends BaseAdapter {
    List<Teacher> teacherList;
    private Context context;

    public TeacherListAdapter(Context context, List<Teacher> teacherList) {
        this.context = context;
        this.teacherList = teacherList;
    }

    @Override
    public int getCount() {
        return teacherList.size();
    }

    @Override
    public Object getItem(int position) {
        return teacherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.teacher_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.teacherName.setText(teacherList.get(position).getName());
        holder.major.setText("主讲科目："+teacherList.get(position).getMajor());
        Picasso.with(context).load(teacherList.get(position).getPototoUrl()).into(holder.imageView);
        return convertView;
    }
    public class ViewHolder {
        private TextView teacherName,major;
        private ImageView imageView;
        ViewHolder(View view) {
            imageView = ((ImageView) view.findViewById(R.id.image));
            teacherName = ((TextView) view.findViewById(R.id.name));
            major = ((TextView) view.findViewById(R.id.major));
        }
    }
}

package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.Teacher;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class TeacherFenleiAdapter extends BaseAdapter {
    List<Teacher> teacherList;
    private Context context;

    public TeacherFenleiAdapter(Context context, List<Teacher> teacherList) {
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
        holder.teacherName.setText(teacherList.get(position).getName()+"|"+"教学经验"+teacherList.get(position).getYear()+"年");
        holder.major.setText("主讲科目"+teacherList.get(position).getMajor());
        return convertView;
    }
    public class ViewHolder {
        private TextView teacherName,major;
        private ImageView imageView;
        ViewHolder(View view) {
            imageView = ((ImageView) view.findViewById(R.id.image));
            teacherName = ((TextView) view.findViewById(R.id.teacherName));
            major = ((TextView) view.findViewById(R.id.major));
        }
    }
}

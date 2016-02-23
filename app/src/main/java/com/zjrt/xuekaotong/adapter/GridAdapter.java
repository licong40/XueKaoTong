package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.OneFenlei;
import com.zjrt.xuekaotong.model.Video;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2015/11/16.
 */
public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<Video> list;

    public GridAdapter(Context context, List<Video> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 4;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.shouye_grid_1_item, null);
            convertView.setTag(new ViewHolder(convertView));
        } else {

        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText(list.get(position).getTitle());
        holder.teacher.setText("主讲："+list.get(position).getTeacher());
        int number = ((int) (Math.random() * 1000));
        holder.num.setText(String.valueOf(number)+"人在学");
        if(list.get(position).getCoursePhoto().equals("")){
        }else {
            Picasso.with(context).load(list.get(position).getCoursePhoto()).into(holder.photo);
        }

        return convertView;
    }
    private class ViewHolder{
        private final ImageView photo;
        private final TextView teacher,num;
        private TextView title;
        ViewHolder(View view) {
            title = ((TextView) view.findViewById(R.id.title));
            photo = ((ImageView) view.findViewById(R.id.image));
            teacher = ((TextView) view.findViewById(R.id.teacher));
            num = (TextView)view.findViewById(R.id.num);
        }
    }

}

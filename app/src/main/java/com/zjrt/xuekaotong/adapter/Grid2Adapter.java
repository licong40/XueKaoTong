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
import com.zjrt.xuekaotong.model.Video;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2015/11/16.
 */
public class Grid2Adapter extends BaseAdapter {
    private Context context;
    private List<Teacher> list;

    public Grid2Adapter(Context context, List<Teacher> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 3;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.shouye_grid_2_item, null);
            convertView.setTag(new ViewHolder(convertView));
        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
       holder.major.setText("类别："+list.get(position).getMajor());
        holder.name.setText(list.get(position).getName());
        if(list.get(position).getPototoUrl().equals("")){

        }else {
            Picasso.with(context).load(list.get(position).getPototoUrl()).into(holder.photo);
        }
        return convertView;
    }
    private class ViewHolder{

        private final TextView name;
        private final ImageView photo;
        private final TextView major;

        ViewHolder(View view) {
            name = ((TextView) view.findViewById(R.id.name));
            photo = ((ImageView) view.findViewById(R.id.image));
            major = ((TextView) view.findViewById(R.id.major));
        }
    }

}

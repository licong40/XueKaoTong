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

import java.util.Collection;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2015/11/11.
 */
public class ShouyeTeacherAdapter extends BaseAdapter {
    private Context context;
    private List<Teacher> list;

    public ShouyeTeacherAdapter(Context context, List<Teacher> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.shouye_grid_2_item, null);
            convertView.setTag(new ViewHolder(convertView));
        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.name.setText(list.get(position).getName());
        Picasso.with(context).load(list.get(position).getPototoUrl()).into(holder.photo);
        return convertView;
    }

    public void addAll(Collection<? extends Teacher> teachers, boolean flag) {
        if (!flag) {
            list.clear();
        }
        list.addAll(teachers);
        notifyDataSetChanged();
    }

    public class ViewHolder {
        private final ImageView photo;
        private TextView name;

        ViewHolder(View view) {
            name = ((TextView) view.findViewById(R.id.name));
            photo = ((ImageView) view.findViewById(R.id.image));
        }
    }
}

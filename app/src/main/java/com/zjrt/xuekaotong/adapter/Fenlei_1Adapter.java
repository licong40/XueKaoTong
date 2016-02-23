package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.OneFenlei;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class Fenlei_1Adapter extends BaseAdapter {
    private Context context;
    private List<OneFenlei> list;

    public Fenlei_1Adapter(Context context, List<OneFenlei> list) {
        this.context = context;
        this.list = list;
    }
    public void addAll(List<OneFenlei> twoFenleis,boolean isrefersh) {
        if (!isrefersh){
            list.clear();
        }
        list.addAll(twoFenleis);
        notifyDataSetChanged();
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fenlei_list_item, null);
            convertView.setTag(new ViewHolder(convertView));

        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.categoryName.setText(list.get(position).getCategoryName());
        if (position==0){
            convertView.setBackgroundColor(Color.WHITE);
            convertView.findViewById(R.id.bar).setVisibility(View.VISIBLE);
            ((TextView) convertView.findViewById(R.id.big_type)).setTextColor(Color.argb(255,49,151,255));
        }
        return convertView;
    }
    private class ViewHolder{
        private final View bar;
        private  TextView categoryName;
        ViewHolder(View view) {
            categoryName = ((TextView) view.findViewById(R.id.big_type));
            bar = view.findViewById(R.id.bar);
        }
    }
}

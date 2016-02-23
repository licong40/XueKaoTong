package com.zjrt.xuekaotong.citylist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;

/**
 * Created by Administrator on 2016/1/29.
 */
public class HotCityAdapter extends BaseAdapter {
    //上下文对象
    private Context context;
    private String[] hot_city_name;

    HotCityAdapter(Context context, String[] hot_city_name) {
        this.context = context;
        this.hot_city_name=hot_city_name;
    }

    public int getCount() {
        return hot_city_name.length;
    }

    public Object getItem(int item) {
        return item;
    }

    public long getItemId(int id) {
        return id;
    }

    //创建View方法
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView ==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_hot_city,null);
            convertView.setTag(new ViewHolder(convertView));
        }else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.hot_city_name.setText(hot_city_name[position]);

        return convertView;
    }
    public class ViewHolder{
        private TextView hot_city_name;
        ViewHolder(View view){
            hot_city_name = ((TextView) view.findViewById(R.id.hot_city_name));
        }
    }
}

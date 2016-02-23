package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.Video;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2015/11/16.
 */
public class CityAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public CityAdapter(Context context, List<String> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.city_item, null);
            convertView.setTag(new ViewHolder(convertView));
        } else {

        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.city.setText(list.get(position));

        return convertView;
    }
    private class ViewHolder{

        private final TextView city;

        ViewHolder(View view) {
            city = ((TextView) view.findViewById(R.id.city));
        }
    }

}

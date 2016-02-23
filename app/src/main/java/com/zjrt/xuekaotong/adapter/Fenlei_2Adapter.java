package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.TwoFenlei;
import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class Fenlei_2Adapter extends BaseAdapter {
    private Context context;
    private List<TwoFenlei> list;

    public Fenlei_2Adapter(Context context) {
        this.context = context;
    }

    public Fenlei_2Adapter(Context context, List<TwoFenlei> list) {
        this.context = context;
        this.list = list;
    }
    public void addAll(List<TwoFenlei> twoFenleis,boolean isrefersh) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.fenlei_list_item2, null);
            convertView.setTag(new ViewHolder(convertView));
        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.smallType.setText(list.get(position).getCategoryName());
        return convertView;
    }
    private class ViewHolder{
        private  TextView smallType;

        ViewHolder(View view) {
           smallType = ((TextView) view.findViewById(R.id.smallType));

        }
    }
}

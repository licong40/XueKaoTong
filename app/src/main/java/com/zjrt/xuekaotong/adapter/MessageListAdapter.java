package com.zjrt.xuekaotong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.Message;

import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class MessageListAdapter extends BaseAdapter {
    private Context context;
    private List<Message> list;

    public MessageListAdapter(Context context, List<Message> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.message_list_item,null);
            convertView.setTag(new ViewHolder(convertView));
        }else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.recTime.setText(list.get(position).getRecTime());
        holder.content.setText(list.get(position).getContent());
        return convertView;
    }
    public class ViewHolder{
        private TextView recTime,content;
        ViewHolder(View view){
            recTime = ((TextView) view.findViewById(R.id.recTime));
            content = ((TextView) view.findViewById(R.id.content));
        }
    }
}

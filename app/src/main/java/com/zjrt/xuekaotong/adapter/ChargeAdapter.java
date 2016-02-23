package com.zjrt.xuekaotong.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.Charge;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public class ChargeAdapter extends BaseAdapter {
    private Context context;
    private List<Charge> list;

    public ChargeAdapter(Context context, List<Charge> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.charge_list_item, null);
            convertView.setTag(new ViewHolder(convertView));

        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
       switch (list.get(position).getType()){
           case 0:
               holder.type.setText("提现");
               break;
           case 1:
               holder.type.setText("退款至余额");
               break;
           case 2:
               holder.type.setText("消费");
               break;
           case 3:
               holder.type.setText("充值");
               break;
       }

        return convertView;
    }

    private class ViewHolder {
        private TextView type;

        ViewHolder(View view) {
            type = ((TextView) view.findViewById(R.id.type));
        }
    }
}

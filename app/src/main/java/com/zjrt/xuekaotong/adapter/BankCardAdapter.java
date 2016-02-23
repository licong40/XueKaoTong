package com.zjrt.xuekaotong.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.BankCard;
import com.zjrt.xuekaotong.model.Charge;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public class BankCardAdapter extends BaseAdapter {
    private Context context;
    private List<BankCard> list;

    public BankCardAdapter(Context context, List<BankCard> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.card_list_item, null);
            convertView.setTag(new ViewHolder(convertView));

        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.bank_name.setText(list.get(position).getBank());
        holder.num.setText("尾号："+list.get(position).getNum());
        return convertView;
    }

    private class ViewHolder {
        private final TextView bank_name;
        private final TextView num;

        ViewHolder(View view) {
            bank_name = ((TextView) view.findViewById(R.id.bank_name));
            num = ((TextView) view.findViewById(R.id.num));
        }
    }
}

package com.zjrt.xuekaotong.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.BankCard;
import com.zjrt.xuekaotong.model.Coupon;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public class CouponAdapter extends BaseAdapter {
    private Context context;
    private List<Coupon> list;

    public CouponAdapter(Context context, List<Coupon> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.coupon_list_item, null);
            convertView.setTag(new ViewHolder(convertView));

        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
       holder.value.setText(list.get(position).getValue());
        holder.max_num.setText("订单满"+list.get(position).getMax_num()+"元可用");
        holder.over_due.setText("有效期至："+list.get(position).getTime());
        holder.type.setText(list.get(position).getType());
        return convertView;
    }

    private class ViewHolder {


        private final TextView value;
        private final TextView over_due;
        private final TextView max_num;
        private final TextView type;

        ViewHolder(View view) {
            value = ((TextView) view.findViewById(R.id.value));
            over_due = ((TextView) view.findViewById(R.id.over_due));
            max_num = ((TextView) view.findViewById(R.id.max_num));
            type = ((TextView) view.findViewById(R.id.type));
        }
    }
}

package com.zjrt.xuekaotong.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.model.BankCard;
import com.zjrt.xuekaotong.model.Icon;

import java.util.List;

/**
 * Created by Administrator on 2015/12/28.
 */
public class IconListAdapter extends BaseAdapter {
    private Context context;
    private List<Icon> list;

    public IconListAdapter(Context context, List<Icon> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.icon_list_item, null);
            convertView.setTag(new ViewHolder(convertView));

        } else {
        }
        final ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.title.setText(list.get(position).getTitle());
        holder.icon_num.setText("数量："+list.get(position).getIcon_num());
        holder.exchanged_num.setText("已兑："+list.get(position).getExchanged_num());
        holder.price.setText(list.get(position).getPrice()+" 学币");
        return convertView;
    }

    private class ViewHolder {
        private final TextView title;
        private final TextView icon_num;
        private final TextView exchanged_num;
        private final TextView price;

        ViewHolder(View view) {
            title = ((TextView) view.findViewById(R.id.title));
            icon_num = ((TextView) view.findViewById(R.id.icon_num));
            exchanged_num = ((TextView) view.findViewById(R.id.exchanged_num));
            price = ((TextView) view.findViewById(R.id.price));
        }
    }
}

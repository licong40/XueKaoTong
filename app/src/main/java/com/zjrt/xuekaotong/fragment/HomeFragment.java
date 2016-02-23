package com.zjrt.xuekaotong.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.activity.Exchange;
import com.zjrt.xuekaotong.activity.Feedback;
import com.zjrt.xuekaotong.activity.MyCache;
import com.zjrt.xuekaotong.activity.MyCollection;
import com.zjrt.xuekaotong.activity.MyCoupon;
import com.zjrt.xuekaotong.activity.MyData;
import com.zjrt.xuekaotong.activity.MyMessageActivity;
import com.zjrt.xuekaotong.activity.MyOrderActivity;
import com.zjrt.xuekaotong.activity.MyWallet;
import com.zjrt.xuekaotong.activity.SetActivity;
import com.zjrt.xuekaotong.view.Dialog1;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout order,wallet,coupon,collect,question,share,message,feedback;
    private ImageView set_bt;
    private RelativeLayout mine;
    private TextView exchange;
    private TextView sign_in;
    private RelativeLayout cache;

    public HomeFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,null);
        initView(view);
        return  view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        order.setOnClickListener(this);
        mine.setOnClickListener(this);
        collect.setOnClickListener(this);
        message.setOnClickListener(this);
        set_bt.setOnClickListener(this);
        wallet.setOnClickListener(this);
        feedback.setOnClickListener(this);
        coupon.setOnClickListener(this);
        exchange.setOnClickListener(this);
        sign_in.setOnClickListener(this);
        cache.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_order:
                Log.d("order","order");
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(), MyOrderActivity.class);
                startActivity(intent2);
                break;
            case R.id.my_wallet:
                Intent intent6 = new Intent();
                intent6.setClass(getActivity(), MyWallet.class);
                startActivity(intent6);
                break;
            case R.id.my_coupon:
                Intent intent8 = new Intent();
                intent8.setClass(getActivity(), MyCoupon.class);
                startActivity(intent8);
                break;
            case R.id.my_collect:
                Intent intent3 = new Intent();
                intent3.setClass(getActivity(), MyCollection.class);
                startActivity(intent3);
                break;
            case R.id.question:
                break;
            case R.id.share:
                break;
            case R.id.my_message:
                Intent intent4 = new Intent();
                intent4.setClass(getActivity(), MyMessageActivity.class);
                startActivity(intent4);
                break;
            case R.id.my_feedback:
                Intent intent7 = new Intent();
                intent7.setClass(getActivity(), Feedback.class);
                startActivity(intent7);
                break;
            case R.id.mine:
                Intent intent = new Intent();
                intent.setClass(getActivity(), MyData.class);
                startActivity(intent);
                break;
            case R.id.set_bt:
                Intent intent5 = new Intent();
                intent5.setClass(getActivity(), SetActivity.class);
                startActivity(intent5);
                break;
            case R.id.exchang:
                Intent intent9 = new Intent(getActivity(), Exchange.class);
                startActivity(intent9);
                break;
            case R.id.sign_in:
                showLoginDialog();
                break;
            case R.id.my_cache:
                Log.d("aa","aa");
                startActivity(new Intent(getActivity(), MyCache.class));
                break;
        }
    }
    private void showLoginDialog(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup, null);
        final Dialog1 dialog = new Dialog1(getActivity(),0,0,view,R.style.dialog);
        dialog.show();
        Button button = ((Button) view.findViewById(R.id.button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                ((TextView) getActivity().findViewById(R.id.sign_in)).setText("今日已签到");
            }
        });



    }
    private void initView(View view){
        order = ((RelativeLayout) view.findViewById(R.id.my_order));
        wallet = ((RelativeLayout) view.findViewById(R.id.my_wallet));
        coupon = ((RelativeLayout) view.findViewById(R.id.my_coupon));
        collect = ((RelativeLayout) view.findViewById(R.id.my_collect));
        question = ((RelativeLayout) view.findViewById(R.id.my_question));
        share = ((RelativeLayout) view.findViewById(R.id.my_share));
        message = ((RelativeLayout) view.findViewById(R.id.my_message));
        feedback = ((RelativeLayout) view.findViewById(R.id.my_feedback));
        mine = ((RelativeLayout) view.findViewById(R.id.mine));
        set_bt = ((ImageView) view.findViewById(R.id.set_bt));
        exchange = ((TextView) view.findViewById(R.id.exchang));
        sign_in = ((TextView) view.findViewById(R.id.sign_in));
        cache = ((RelativeLayout) view.findViewById(R.id.my_cache));
    }
    public void onSaveInstanceState(Bundle outState) {
        // 防止重叠
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }
}

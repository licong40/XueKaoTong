package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.CouponAdapter;
import com.zjrt.xuekaotong.model.Coupon;

import java.util.ArrayList;
import java.util.List;

public class MyCoupon extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private ListView listview;
    private View help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupon);
        back = ((ImageView) findViewById(R.id.back));
        help = findViewById(R.id.help);
        listview = ((ListView) findViewById(R.id.listview));
        Coupon coupon1 = new Coupon("系统赠送红包","10","50","2016-03.01");
        Coupon coupon2 = new Coupon("系统赠送红包","20","80","2016-08.01");
        Coupon coupon3 = new Coupon("积分兑换红包","10","30","2016-02.01");
        Coupon coupon4 = new Coupon("积分兑换红包","50","300","2016-04.01");
        List<Coupon> list = new ArrayList<>();
        list.add(coupon1);
        list.add(coupon2);
        list.add(coupon3);
        list.add(coupon4);
        CouponAdapter adapter = new CouponAdapter(this,list);
        listview.setAdapter(adapter);
        back.setOnClickListener(this);
        help.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.help:
                Intent intent = new Intent(this,SkipUrl.class);
                intent.putExtra("title","帮助");
                intent.putExtra("url","file:///android_asset/Untitled-1.html");
                startActivity(intent);
                break;
        }
    }
}

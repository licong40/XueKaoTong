package com.zjrt.xuekaotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.IconListAdapter;
import com.zjrt.xuekaotong.model.Icon;

import java.util.ArrayList;
import java.util.List;

public class Exchange extends AppCompatActivity {

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange);
        listview = ((ListView) findViewById(R.id.listview));
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Icon icon1 = new Icon("彩虹5号电池(十粒装) 彩色","3000","286","2000");
        Icon icon2 = new Icon("彩虹7号电池(十粒装) 彩色","2000","358","1000");
        Icon icon3 = new Icon("苹果Lighting to USB数据线","500","856","2080");
        Icon icon4 = new Icon("小米移动电源10000mAh 银色","800","280","700");
        Icon icon5 = new Icon("苹果Lighting to USB数据线","3000","286","2000");
        Icon icon6 = new Icon("彩虹5号电池(十粒装) 彩色","288","458","2579");
        Icon icon7 = new Icon("苹果Lighting to USB数据线","3000","7890","4567");
        Icon icon8 = new Icon("彩虹5号电池(十粒装) 彩色","567","286","2500");
        Icon icon9 = new Icon("小米移动电源10000mAh 银色","1480","5478","3000");
        List<Icon> list = new ArrayList<>();
        list.add(icon1);
        list.add(icon2);
        list.add(icon3);
        list.add(icon4);
        list.add(icon5);
        list.add(icon6);
        list.add(icon7);
        list.add(icon8);
        list.add(icon9);
        IconListAdapter adapter = new IconListAdapter(this,list);
        listview.setAdapter(adapter);
    }
}

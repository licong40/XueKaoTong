package com.zjrt.xuekaotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.ChargeAdapter;
import com.zjrt.xuekaotong.model.Charge;

import java.util.ArrayList;
import java.util.List;

public class RecentCharge extends AppCompatActivity {

    private ListView listview;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_charge);
        listview = ((ListView) findViewById(R.id.listview));
        back = ((ImageView) findViewById(R.id.back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Charge charge1 = new Charge(0,"87","2015-12-21","20");
        Charge charge2 = new Charge(1,"100","2015-12-21","20");
        Charge charge3 = new Charge(2,"121","2015-12-21","20");
        Charge charge4 = new Charge(3,"141","2015-12-21","20");
        List<Charge> list = new ArrayList<>();
        list.add(charge1);
        list.add(charge2);
        list.add(charge3);
        list.add(charge4);
        ChargeAdapter adapter = new ChargeAdapter(this,list);
        listview.setAdapter(adapter);
    }
}

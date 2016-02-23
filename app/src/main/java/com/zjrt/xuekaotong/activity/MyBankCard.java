package com.zjrt.xuekaotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.BankCardAdapter;
import com.zjrt.xuekaotong.model.BankCard;

import java.util.ArrayList;
import java.util.List;

public class MyBankCard extends AppCompatActivity {

    private ListView listview;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bank_card);
        back = ((ImageView) findViewById(R.id.back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listview = ((ListView) findViewById(R.id.listview));
        BankCard card1 = new BankCard("招商银行","2025","");
        BankCard card2 = new BankCard("工商银行","2049","");
        BankCard card3 = new BankCard("建设银行","1023","");
        BankCard card4 = new BankCard("中国银行","5562","");
        List<BankCard> list = new ArrayList<>();
        list.add(card1);
        list.add(card2);
        list.add(card3);
        list.add(card4);
        BankCardAdapter adapter = new BankCardAdapter(this,list);
        View view =LayoutInflater.from(this).inflate(R.layout.include_divider2,null);
        listview.addFooterView(view);
        listview.setAdapter(adapter);

    }
}

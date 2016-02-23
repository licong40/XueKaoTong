package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zjrt.xuekaotong.R;

public class MyWallet extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private RelativeLayout input;
    private RelativeLayout output;
    private Intent intent1,intent2,intent3,intent4,intent5;
    private RelativeLayout recent_charge;
    private RelativeLayout my_bank_card;
    private RelativeLayout pay_manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        init();
        back.setOnClickListener(this);
        input.setOnClickListener(this);
        output.setOnClickListener(this);
        recent_charge.setOnClickListener(this);
        my_bank_card.setOnClickListener(this);
        pay_manager.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.input:
                startActivity(intent1);
                break;
            case R.id.output:
                startActivity(intent2);
                break;
            case R.id.recent_charge:
                startActivity(intent3);
                break;
            case R.id.my_bank_card:
                startActivity(intent4);
                break;
            case R.id.pay_manager:
                startActivity(intent5);
                break;
        }
    }
    private void init(){
        back = ((ImageView) findViewById(R.id.back));
        input = ((RelativeLayout) findViewById(R.id.input));
        output = ((RelativeLayout) findViewById(R.id.output));
        recent_charge = ((RelativeLayout) findViewById(R.id.recent_charge));
        my_bank_card = ((RelativeLayout) findViewById(R.id.my_bank_card));
        pay_manager = ((RelativeLayout) findViewById(R.id.pay_manager));
        intent1 = new Intent(this,Recharge.class);
        intent2 = new Intent(this,Output.class);
        intent3 = new Intent(this,RecentCharge.class);
        intent4 = new Intent(this,MyBankCard.class);
        intent5 = new Intent(this,PayManage.class);
    }
}

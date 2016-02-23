package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private TextView register_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        back = ((ImageView) findViewById(R.id.back));
        register_bt = ((TextView) findViewById(R.id.register_bt));
        back.setOnClickListener(this);
        register_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.register_bt:
                Intent intent1 = new Intent();
                intent1.setClass(this,Register.class);
                startActivity(intent1);
                break;
        }
    }
}

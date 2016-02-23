package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;

public class Register2 extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private ImageView back;
    private TextView vw;
    private MyCount mc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        button = ((Button) findViewById(R.id.button));
        back = ((ImageView) findViewById(R.id.back));
        vw = ((TextView) findViewById(R.id.timer));
        mc = new MyCount(60*1000,1000);
        mc.start();
        back.setOnClickListener(this);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent();
                intent.setClass(Register2.this, Register3.class);
                startActivity(intent);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    /*继承CountDownTimer类*/
    class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish()  /*完成时候调用*/ {
            vw.setText("");
    /*vw.cancel();  //关闭*/
        }

        @Override
        public void onTick(long millisUntilFinished)/*开始时候调用*/ {
            vw.setText(String.valueOf(millisUntilFinished / 1000)+"秒后");
        }
    }
}

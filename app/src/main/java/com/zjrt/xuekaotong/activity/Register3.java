package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.zjrt.xuekaotong.R;

public class Register3 extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        back = ((ImageView) findViewById(R.id.back));
        button = ((Button) findViewById(R.id.button));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.button:
                break;
        }
    }
}

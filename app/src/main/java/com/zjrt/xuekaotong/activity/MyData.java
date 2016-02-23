package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;

public class MyData extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private Button exit;
    private RelativeLayout account;
    private Intent intent1,intent2,intent3;
    private RelativeLayout rename;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_data);
        init();
        back.setOnClickListener(this);
        exit.setOnClickListener(this);
        account.setOnClickListener(this);
        rename.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.exit:
                startActivity(intent1);
                break;
            case R.id.account:
                startActivity(intent2);
                break;
            case R.id.rename:
                intent3.putExtra("name",name.getText());
                startActivity(intent3);
                break;
        }
    }
    private void init(){
        back = ((ImageView) findViewById(R.id.back));
        exit = ((Button) findViewById(R.id.exit));
        account = ((RelativeLayout) findViewById(R.id.account));
        rename = ((RelativeLayout) findViewById(R.id.rename));
        name = ((TextView) findViewById(R.id.name));
        intent1 = new Intent(this,Login.class);
        intent2 = new Intent(this,MyAccount.class);
        intent3 = new Intent(this,Rename.class);
    }
}

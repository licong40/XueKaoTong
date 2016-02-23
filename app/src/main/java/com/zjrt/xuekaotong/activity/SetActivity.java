package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.utils.DataCleanManager;

public class SetActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView back;
    private  boolean flag;
    private RelativeLayout about_us;
    private RelativeLayout law;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        back = ((ImageView) findViewById(R.id.rollback));
        about_us = ((RelativeLayout) findViewById(R.id.about_us));
        law = ((RelativeLayout) findViewById(R.id.law));
        about_us.setOnClickListener(this);
        back.setOnClickListener(this);
        law.setOnClickListener(this);
        try {
            ((TextView) findViewById(R.id.cache)).setText(DataCleanManager.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        findViewById(R.id.clean).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rollback:
                finish();
                break;
            case R.id.about_us:
                Intent intent = new Intent(this,AboutUs.class);
                startActivity(intent);
                break;
            case R.id.law:
                Intent intent1 = new Intent(this,SkipUrl.class);
                intent1.putExtra("title","关于我们");
                intent1.putExtra("url","file:///android_asset/statement.html");
                startActivity(intent1);
                break;
            case R.id.clean:
                DataCleanManager.clearAllCache(getApplicationContext());
                try {
                    ((TextView) findViewById(R.id.cache)).setText(DataCleanManager.getTotalCacheSize(getApplicationContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}

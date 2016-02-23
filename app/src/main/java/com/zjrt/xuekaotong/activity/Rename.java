package com.zjrt.xuekaotong.activity;

import android.content.Intent;
import android.sax.EndElementListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.zjrt.xuekaotong.R;

public class Rename extends AppCompatActivity implements View.OnClickListener {
    private Intent intent;
    private String name;
    private EditText text;
    private ImageView delete;
    private ImageView back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rename);
        init();
        text.setText(name);
        text.setSelection(name.length());
        delete.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    private void init() {
        intent = getIntent();
        name = intent.getStringExtra("name");
        text = ((EditText) findViewById(R.id.edit_name));
        delete = ((ImageView) findViewById(R.id.delete));
        back = ((ImageView) findViewById(R.id.back));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete:
                text.setText("");
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}

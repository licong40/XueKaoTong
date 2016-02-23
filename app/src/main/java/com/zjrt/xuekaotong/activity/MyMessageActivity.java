package com.zjrt.xuekaotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.adapter.MessageListAdapter;
import com.zjrt.xuekaotong.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MyMessageActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView listView;
    private MessageListAdapter adapter;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        back = ((ImageView) findViewById(R.id.back));
        listView = ((ListView) findViewById(R.id.listview));
        List<Message> list = new ArrayList<Message>();
        adapter = new MessageListAdapter(this, list);
        Message message1 = new Message("2015-06.17", "恭喜您获得一个8.0元红包，红包金额8.0元,(点击查看)");
        Message message2 = new Message("2015-06.17", "恭喜您获得一个8.0元红包，红包金额8.0元,(点击查看)");
        Message message3 = new Message("2015-06.17", "恭喜您获得一个8.0元红包，红包金额8.0元,(点击查看)");
        Message message4 = new Message("2015-06.17", "恭喜您获得一个8.0元红包，红包金额8.0元,(点击查看)");
        Message message5 = new Message("2015-06.17", "恭喜您获得一个8.0元红包，红包金额8.0元,(点击查看)");
        Message message6 = new Message("2015-06.17", "恭喜您获得一个8.0元红包，红包金额8.0元,(点击查看)");
        list.add(message1);
        list.add(message2);
        list.add(message3);
        list.add(message4);
        list.add(message5);
        list.add(message6);
        listView.setAdapter(adapter);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }
}

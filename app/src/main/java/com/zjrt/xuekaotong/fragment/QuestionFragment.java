package com.zjrt.xuekaotong.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.zjrt.xuekaotong.R;
import com.zjrt.xuekaotong.activity.VideoCategory;
import com.zjrt.xuekaotong.adapter.Fenlei_1Adapter;
import com.zjrt.xuekaotong.adapter.Fenlei_2Adapter;
import com.zjrt.xuekaotong.model.OneFenlei;
import com.zjrt.xuekaotong.model.TwoFenlei;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QuestionFragment extends Fragment implements Response.ErrorListener {
    private ListView listView1, listView2;
    private RequestQueue queue;
    private Fenlei_1Adapter adapter1;
    private Fenlei_2Adapter adapter2;
    private List<TwoFenlei> twoFenleis;
    private List<OneFenlei> oneFenleis;
    private OneFenlei oneFenlei;
    private TwoFenlei twoFenlei;

    public QuestionFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_question, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isNetworkConnected(getActivity());
        listView1 = ((ListView) view.findViewById(R.id.listview1));
        listView1.setSelection(0);
        listView2 = ((ListView) view.findViewById(R.id.listview2));
        queue = Volley.newRequestQueue(getActivity());
        oneFenleis = new ArrayList<>();
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, "http://www.xuekaotong.cn/api/school/categories", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray result = response.getJSONArray("result");
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject object = (JSONObject) result.get(i);
                        String id = object.getString("id");
                        String categoryName = object.getString("categoryName");
                        JSONArray array = object.getJSONArray("children");
                        twoFenleis = new ArrayList<>();
                        for (int j = 0; j < array.length(); j++) {
                            JSONObject object1 = array.getJSONObject(j);
                            String _id = object1.getString("id");
                            String _categoryName = object1.getString("categoryName");
                            twoFenlei = new TwoFenlei(_id, _categoryName);
                            twoFenleis.add(twoFenlei);
                        }
                        oneFenlei = new OneFenlei(id, categoryName, twoFenleis);
                        Log.d("oneFenlei", oneFenlei.toString());
                        oneFenleis.add(oneFenlei);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                adapter1 = new Fenlei_1Adapter(getActivity(), oneFenleis);
                listView1.setAdapter(adapter1);
                adapter2 = new Fenlei_2Adapter(getActivity(),oneFenleis.get(0).getList());
                listView2.setAdapter(adapter2);

            }
        }, this);
        request.setTag(this);
        queue.add(request);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for(int i=0;i<parent.getChildCount();i++){
                    parent.getChildAt(i).findViewById(R.id.bar).setVisibility(View.GONE);
                    parent.getChildAt(i).findViewById(R.id.big_type).setBackgroundColor(Color.argb(255, 237, 237, 237));
                    TextView v = (TextView)parent.getChildAt(i).findViewById(R.id.big_type);
                    v.setTextColor(Color.argb(255,100,100,100));

                }
                parent.getChildAt(position).findViewById(R.id.bar).setVisibility(View.VISIBLE);
                parent.getChildAt(position).findViewById(R.id.big_type).setBackgroundColor(Color.WHITE);
                ((TextView) parent.getChildAt(position).findViewById(R.id.big_type)).setTextColor(Color.argb(255, 49, 151, 255));
                OneFenlei oneFenlei = (OneFenlei) parent.getAdapter().getItem(position);
                twoFenleis = oneFenlei.getList();
                adapter2 = new Fenlei_2Adapter(getActivity(), twoFenleis);
                listView2.setAdapter(adapter2);
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                twoFenlei = (TwoFenlei) parent.getAdapter().getItem(position);
                String id2 = twoFenlei.getId();
                String title = twoFenlei.getCategoryName();
                Intent intent = new Intent();
                intent.putExtra("_id", id2);
                intent.putExtra("video", title);
                intent.setClass(getActivity(), VideoCategory.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    public void onSaveInstanceState(Bundle outState) {
        // 防止重叠
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo == null) {
                //return mNetworkInfo.isAvailable();
                Toast.makeText(getActivity(), "没有网络连接", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }
}



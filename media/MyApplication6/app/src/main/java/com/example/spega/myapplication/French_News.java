package com.example.spega.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class French_News extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private String TAG = French_News.class.getSimpleName();
    private String URL_TOP_250 = "http://speganews.com/api/french_sepcat.php?id=1&off=0";
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView_french;
    List<Dataclass_News> data;
    private Recycler_News adapter;
    ImageView icon;
    ImageView chooseicon;
    ProgressBar bar;
    private int offSet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_french__news);
        getSupportActionBar().hide();
        chooseicon = (ImageView) findViewById(R.id.new_right_menu);
         icon = (ImageView) findViewById(R.id.imageView_icon);
         recyclerView_french = (RecyclerView) findViewById(R.id.recycler_news);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
         data = new ArrayList<>();
        adapter = new Recycler_News(this, data);
        recyclerView_french.setAdapter(adapter);
         swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
             }
        });
    }


    @Override
    public void onRefresh() {
        fetchNews();
    }


    private void fetchNews() {

        swipeRefreshLayout.setRefreshing(true);


        String url = URL_TOP_250 + offSet;

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCancelable(false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {

                            JSONObject Jobject = (JSONObject) new JSONTokener(response).nextValue();
                            JSONArray feedArray = Jobject.getJSONArray("result");

                            Log.e("feedlength", String.valueOf(feedArray.length()));

                            for (int i = 0; i < feedArray.length(); i++) {
                                JSONObject Obj = (JSONObject) feedArray.get(i);
                                String title = Obj.getString("title");
                                String desc = Obj.getString("description");
                                String image = Obj.getString("image");
                                 Dataclass_News m = new Dataclass_News(title, desc, image);
                                 data.add(m);

                            }
                            adapter.notifyDataSetChanged();

                            pDialog.cancel();;
                        } catch (Exception ez) {
                            pDialog.cancel();;
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.hide();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                return null;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}



package com.example.rishimadhok.cdf.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.example.rishimadhok.cdf.Base.BaseActivity;
import com.example.rishimadhok.cdf.Adapters.NotificationAdapter;
import com.example.rishimadhok.cdf.Beans.NotificationBean;
import com.example.rishimadhok.cdf.Adapters.NotificationsDatabaseAdapter;
import com.example.rishimadhok.cdf.R;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {


    public static final String TAG = NotificationActivity.class.getName();

    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    NotificationsDatabaseAdapter database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        LocalBroadcastManager.getInstance(NotificationActivity.this).registerReceiver(mMessageReceiver,
                new IntentFilter("notif-received"));


        ActionBar actionBar = ((AppCompatActivity) NotificationActivity.this).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back);
            actionBar.setTitle("Notifications");
        }

        recyclerView = (RecyclerView) NotificationActivity.this.findViewById(R.id.notification_list);
        adapter = new NotificationAdapter(NotificationActivity.this,getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
        adapter.notifyDataSetChanged();
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            Log.d("receiver", "Got message: " + message);
            adapter.setData();
            adapter.notifyDataSetChanged();

//            Intent newIntent = new Intent("notif-count");
//            // You can also include some extra data.
//            intent.putExtra("message", "This is my message!");
//            LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(newIntent);
        }
    };

    public ArrayList<NotificationBean> getData()
    {
        database = new NotificationsDatabaseAdapter(NotificationActivity.this);
        ArrayList<NotificationBean> data = database.viewDetails();
        return data;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(NotificationActivity.this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                startActivity(new Intent(NotificationActivity.this,MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

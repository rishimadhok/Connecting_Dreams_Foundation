package com.example.rishimadhok.cdf.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.rishimadhok.cdf.Beans.NotificationBean;
import com.example.rishimadhok.cdf.R;

import java.util.ArrayList;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    ArrayList<NotificationBean> data;
    NotificationsDatabaseAdapter database;

    public NotificationAdapter(Context context, ArrayList<NotificationBean> data)
    {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        database = new NotificationsDatabaseAdapter(context);
    }

//    public ArrayList<NotificationBean> getData()
//    {
//        //database = new NotificationsDatabaseAdapter(context);
//        ArrayList<NotificationBean> data =
//        return data;
//    }

    public void setData()
    {
        this.data = database.viewDetails();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_notification,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final NotificationBean current = data.get(position);
//        Log.d("Notif","title of " + position + " notification is " + data.get(position).title);
//        Log.d("Notif","body of " + position + " notification is " + data.get(position).body);
        holder.title.setText(current.title);
        holder.body.setText(current.body);
        holder.date.setText(current.date);

        holder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = database.delete(current.id);
            }
        });

    }

    @Override
    public int getItemCount() {
//        Log.d("data_size",  String.valueOf(data.size()));
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView body;
        TextView date;
        ImageView cross;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.tv_notification_Title);
            body = (TextView) itemView.findViewById(R.id.tv_notification_Body);
            date = (TextView) itemView.findViewById(R.id.tv_notification_Date);
            cross = (ImageView) itemView.findViewById(R.id.iv_removeNotif);
        }
    }

}

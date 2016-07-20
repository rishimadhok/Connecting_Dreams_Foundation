package com.example.rishimadhok.cdf.Services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.rishimadhok.cdf.Activities.MainActivity;
import com.example.rishimadhok.cdf.Adapters.NotificationsDatabaseAdapter;
import com.example.rishimadhok.cdf.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by shopclues on 12/7/16.
 */
public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String title = remoteMessage.getNotification().getTitle();
        String body = remoteMessage.getNotification().getBody();

        SimpleDateFormat DateFormater = new SimpleDateFormat("MMMM dd, yyyy");;
        Calendar c = Calendar.getInstance();
        String formattedDate = DateFormater.format(c.getTime());

        NotificationsDatabaseAdapter notificationsDatabaseAdapter = new NotificationsDatabaseAdapter(this);
        long id = notificationsDatabaseAdapter.insert(title,body,formattedDate);

        if(id < 0)
        {
            Log.d("Database","Unsuccessful");
//            Toast.makeText(getApplicationContext(),"Unsuccessful",Toast.LENGTH_SHORT).show();
        }

        else
        {
            Log.d("Database","Successfully inserted a row");
//            Toast.makeText(getApplicationContext(),"Successfully inserted a row",Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent("notif-received");
        // You can also include some extra data.
        intent.putExtra("message", "This is my message!");
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        Intent newIntent = new Intent("notif-count");
        // You can also include some extra data.
        intent.putExtra("message", notificationsDatabaseAdapter.viewDetails().size());
        LocalBroadcastManager.getInstance(this).sendBroadcast(newIntent);

        showNotification(title,body);
    }

    private void showNotification(String title,String body) {

        Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.connecting_dream_logo)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,builder.build());
    }

}

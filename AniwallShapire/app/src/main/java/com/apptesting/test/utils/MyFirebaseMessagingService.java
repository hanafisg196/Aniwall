package com.apptesting.test.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.apptesting.test.R;
import com.apptesting.test.activity.SplashActivity;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    public static int NOTIFICATION_ID = 0;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        // TODO(developer): Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        Map<String, String> data = remoteMessage.getData();


        Log.i("TAG", "onMessageReceived:noti " + notification);
        Log.i("TAG", "onMessageReceived:data " + data);
        Log.e("FROM", remoteMessage.getFrom());
//        sendNotification(notification, data);

//        if (notification != null) {
//            sendNotification(notification);
//        }
    }

    private void sendNotification(RemoteMessage.Notification notification) {
//        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        int requestID = (int) System.currentTimeMillis();


        Intent intent = new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);


        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), requestID, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                | PendingIntent.FLAG_ONE_SHOT);
        String CHANNEL_ID = "01";
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(notification.getTitle())
                .setContentText(notification.getBody())
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setColor(getResources().getColor(R.color.color_theme_blue))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.drawable.logo)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //CharSequence name = getString(R.string.channel_name);

            String name = "Channel_001";
            String description = "Channel Description";
            //String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            //NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        //NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NOTIFICATION_ID = NOTIFICATION_ID + 1;
        Log.i("TAG", "sendNotification:  shown from service");
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
    }
}
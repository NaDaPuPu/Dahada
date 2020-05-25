package com.d2w.dahada.datapushsend;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.d2w.dahada.R;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService{

    protected static final String FOMTAG = "[FC Sserivce]";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(FOMTAG, "onMessageReceived is Called");

        String msg, title;

        msg = remoteMessage.getNotification().getBody();
        title = remoteMessage.getNotification().getTitle();

        Notification.Builder noti = new Notification.Builder(this)
                .setContentTitle("New push from " + title)
                .setContentText(msg)
                .setSmallIcon(R.mipmap.ic_launcher);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0,noti.build());



    }
}

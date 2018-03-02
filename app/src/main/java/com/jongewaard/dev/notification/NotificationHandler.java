package com.jongewaard.dev.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

/**
 * Created by german on 1-3-18.
 */

public class NotificationHandler extends ContextWrapper {

    private NotificationManager manager;

    public static final String CHANNEL_HIGH_ID = "1";
    public final String CHANNEL_HIGH_NAME = "HIGH CHANNEL";
    public static final String CHANNEL_LOW_ID = "2";
    public final String CHANNEL_LOW_NAME = "LOW CHANNEL";

    public NotificationHandler(Context context) {
        super(context);

        CreateChanels();
    }

    public NotificationManager getManager(){

        if(manager == null){
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        }

        return manager;
    }

    private void CreateChanels(){

        //Si es Mayor o igual es compatible con las versiones superiores a la 26
        if(Build.VERSION.SDK_INT >=  26){
            //Creating High Channel
            NotificationChannel highChannel = new NotificationChannel(
                    CHANNEL_HIGH_ID, CHANNEL_HIGH_NAME, NotificationManager.IMPORTANCE_HIGH);

            // ... Extra Config ...

            NotificationChannel lowChannel = new NotificationChannel(
                    CHANNEL_LOW_ID, CHANNEL_LOW_NAME, NotificationManager.IMPORTANCE_LOW);

            getManager().createNotificationChannel(highChannel);
            getManager().createNotificationChannel(lowChannel);

        }

    }

    public  Notification.Builder createNotification(String title,
                         String message, boolean isHighImportance){

        if(Build.VERSION.SDK_INT >= 26){

            if(isHighImportance == true){

                return this.createNotificationWithChannel(title, message, CHANNEL_HIGH_ID);
            }else{
                return this.createNotificationWithChannel(title, message, CHANNEL_LOW_ID);
            }
        }
        return createNotificationWithoutChannel(title, message);

    }

    private Notification.Builder createNotificationWithChannel(String title,
                         String message, String channelId){

        if(Build.VERSION.SDK_INT >=  Build.VERSION_CODES.O){

            return new Notification.Builder(getApplicationContext(), channelId)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setAutoCancel(true);
        }

        return null;
    }

    private Notification.Builder createNotificationWithoutChannel(String title,
                         String message){


            return new Notification.Builder(getApplicationContext())
                    .setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setAutoCancel(true);
    }




}

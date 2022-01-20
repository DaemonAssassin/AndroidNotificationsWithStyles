package com.gmail.mateendev3.androidnotificationswithstyles.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationChannelGroupCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    //declaring members
    public static final String ID_1 = "CID_1";
    public static final String ID_2 = "CID_2";
    public static final String ID_3 = "CID_3";
    public static final String GROUP_ID_1 = "GID_1";
    public static final String GROUP_ID_2 = "GID_2";
    NotificationManagerCompat mManager;

    @Override
    public void onCreate() {
        super.onCreate();

        //initializing members
        mManager = NotificationManagerCompat.from(this);

        //creating notifications channels
        createNotificationChannels();
    }

    private void createNotificationChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //Group 1
            NotificationChannelGroup group1 = new NotificationChannelGroup(
                    GROUP_ID_1, "Video Games"
            );

            //Group 2
            NotificationChannelGroup group2 = new NotificationChannelGroup(
                    GROUP_ID_2, "Sports"
            );

            //channel 1
            NotificationChannel channel1 = createChannel(
                    ID_1, "Audio",
                    "This category is for Audio notifications", true
            );
            channel1.setGroup(GROUP_ID_1);

            //channel 2
            NotificationChannel channel2 = createChannel(
                    ID_2, "Video",
                    "This category is for Video notifications", true
            );
            channel2.setGroup(GROUP_ID_2);

            //channel 3
            NotificationChannel channel3 = createChannel(
                    ID_3, "Games",
                    "This category is for Games notifications", true
            );

            //Setting channels to manager to manage it
            List<NotificationChannel> channels = new ArrayList<>();
            channels.add(channel1);
            channels.add(channel2);
            channels.add(channel3);

            mManager.createNotificationChannelGroup(group1);
            mManager.createNotificationChannelGroup(group2);
            mManager.createNotificationChannels(channels);

        }
    }

    /**
     * Method to create a notification channel
     * @param cID channel id
     * @param cName channel name
     * @param cDis channel description
     * @param hasBadge channel badge
     * @return notification channel
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel createChannel(String cID, CharSequence cName,
                                              String cDis, boolean hasBadge) {
        NotificationChannel channel = new NotificationChannel(
                cID, cName, NotificationManager.IMPORTANCE_HIGH
        );
        channel.setDescription(cDis);
        channel.setShowBadge(hasBadge);

        return channel;
    }
}

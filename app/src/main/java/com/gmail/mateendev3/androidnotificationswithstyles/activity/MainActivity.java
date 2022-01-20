package com.gmail.mateendev3.androidnotificationswithstyles.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;

import com.gmail.mateendev3.androidnotificationswithstyles.R;
import com.gmail.mateendev3.androidnotificationswithstyles.application.App;
import com.gmail.mateendev3.androidnotificationswithstyles.databinding.ActivityMainBinding;

import java.nio.channels.Channel;

public class MainActivity extends AppCompatActivity {

    //Declaring members
    private NotificationManagerCompat mManager;
    private ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());

        //initializing members
        mManager = NotificationManagerCompat.from(this);

        //setting notifications to buttons
        setNotificationsToButtons();
    }

    private void setNotificationsToButtons() {
        //Big Text Style
        setListenerToButton1();
        //Inbox Style
        setListenerToButton2();
        //Big Picture Style
        setListenerToButton3();
        //ProgressBar Style
        setListenerToButton4();
        //Indeterminate ProgressBar Style
        setListenerToButton5();


    }

    private void setListenerToButton1() {
        mMainBinding.btnBigTextStyle.setOnClickListener(v -> {

            if (!mManager.areNotificationsEnabled()) {
                openNotificationSettings();
                return;
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
                    isChannelBlocked(App.ID_1)) {
                openNotificationChannelSetting(App.ID_1);
                return;
            }

            Notification notification = new NotificationCompat.Builder(this, App.ID_1)
                    .setSmallIcon(R.drawable.ic_one)
                    .setContentTitle("Big Text Style")
                    .setContentText("In this video, we'll learn about android notification actions and notification styles. Big picture style and Big text style.\n" +
                            "Subscribe and share if u like my videos.")
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.mateenv1))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .setBigContentTitle("Big Content Title")
                            .setSummaryText("mateen.mehmood@gmail.com")
                            .bigText("In the next parts of the notification tutorial, we will learn how to apply different pre-defined styles to our notifications with the NotificationCompat.Builder's setStyle method.\n" +
                                    "We will start by adding the BigTextStyle, with which we can display a larger amount of text in the expanded state of the notification. For this we have to call bigText on this BigTextStyle object and provide a CharSequence.\n" +
                                    "Furthermore we can call setBigContentTitle and setSummaryText to provide an alternative title for the expanded state, and a small summary text.\n" +
                                    "Next we will take a look at the InboxStyle, which is pretty similar to BigTextStyle, but instead of defining one big text, we can display up to 7 separate lines by calling addLine and supplying a string for each one.\n" +
                                    "Also we will learn how to add a large icon (for example a user image) to our notificaiton with the setLargeIcon method, where we have to pass a Bitmap."))
                    .setColor(Color.YELLOW)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .build();

            //setting notification to notification manager
            mManager.notify(1, notification);
        });
    }


    private void setListenerToButton2() {
        mMainBinding.btnInboxStyle.setOnClickListener(v -> {
            Notification notification = new NotificationCompat.Builder(this, App.ID_1)
                    .setSmallIcon(R.drawable.ic_one)
                    .setContentTitle("Inbox Style")
                    .setContentText("In this video, we'll learn about android notification actions and notification styles. Big picture style and Big text style.\n" +
                            "Subscribe and share if u like my videos.")
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.mateenv1))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setStyle(new NotificationCompat.InboxStyle()
                            .setBigContentTitle("Big Content Title")
                            .setSummaryText("mateen.mehmood@gmail.com")
                            .addLine("This is line 1")
                            .addLine("This is line 2")
                            .addLine("This is line 3")
                            .addLine("This is line 4")
                            .addLine("This is line 5")
                            .addLine("This is line 6")
                            .addLine("This is line 7")
                            .addLine("This is line 8"))
                    .setColor(Color.YELLOW)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .build();

            //setting notification to notification manager
            mManager.notify(2, notification);
        });
    }

    private void setListenerToButton3() {
        mMainBinding.btnBigPictureStyle.setOnClickListener(v -> {
            Notification notification = new NotificationCompat.Builder(this, App.ID_3)
                    .setSmallIcon(R.drawable.ic_three)
                    .setContentTitle("Big Picture Style")
                    .setContentText("In this video, we'll learn about android notification actions and notification styles. Big picture style and Big text style.\n" +
                            "Subscribe and share if u like my videos.")
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.mateenv1))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .setBigContentTitle("Big Content Title")
                            .setSummaryText("mateen.mehmood@gmail.com")
                            .bigLargeIcon(null)
                            .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.mateenv1)))
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .build();

            //setting notification to notification manager
            mManager.notify(3, notification);
        });
    }

    private void setListenerToButton4() {
        mMainBinding.btnProgressBarStyle.setOnClickListener(v -> {
            int maxProgress = 100;

            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    this, App.ID_2
            )
                    .setSmallIcon(R.drawable.ic_four)
                    .setContentTitle("GTA")
                    .setContentText("Downloading in progress")
                    .setOnlyAlertOnce(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setOngoing(true)
                    .setProgress(maxProgress, 0, false);

            mManager.notify(4, builder.build());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //SystemClock.sleep(2000);

                    for (int progress = 0; progress <= maxProgress; progress += 10) {
                        builder.setProgress(maxProgress, progress, false);
                        mManager.notify(4, builder.build());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    builder.setProgress(0, 0, false)
                            .setOngoing(false);
                    mManager.notify(4, builder.build());
                }
            }).start();
        });
    }

    private void setListenerToButton5() {
        mMainBinding.btnIndeterminatePb.setOnClickListener(v -> {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    this, App.ID_3
            )
                    .setSmallIcon(R.drawable.ic_four)
                    .setContentTitle("GTA")
                    .setContentText("Downloading in progress")
                    .setOnlyAlertOnce(true)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setOngoing(true)
                    .setProgress(0, 0, true);

            mManager.notify(5, builder.build());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    SystemClock.sleep(2000);

                    for (int progress = 0; progress <= 100; progress += 10) {
                        SystemClock.sleep(1000);
                    }
                    builder.setProgress(0, 0, false)
                            .setOngoing(false);
                    mManager.notify(5, builder.build());
                }
            }).start();
        });
    }

    /**
     * Method invoked to open Notifications setting w.r.t API level
     * if they are disabled
     */
    private void openNotificationSettings() {
        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        } else {
            intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + getPackageName()));
        }
        startActivity(intent);
    }


    /**
     * Method to check if the user blocked the channel notifications
     * @param channelID ID of the channel which we are gonna check
     * @return true if channel blocked and false if channel not blocked
     */
    @RequiresApi(26)
    private boolean isChannelBlocked(String channelID) {
        NotificationChannel channel = mManager.getNotificationChannel(channelID);

        return channel != null &&
                channel.getImportance() == NotificationManager.IMPORTANCE_NONE;
    }

    /**
     * Method to open channel notification channel setting directly
     * @param channelID ID of the channel which we are gonna check
     */
    @RequiresApi(26)
    private void openNotificationChannelSetting(String channelID) {
        Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        intent.putExtra(Settings.EXTRA_CHANNEL_ID, channelID);
        startActivity(intent);
    }
}
package com.example.mob_dev_portfolio;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.mob_dev_portfolio.fragments.HomeFragment;

public class DetectPhoneCall extends BroadcastReceiver {
    public static final String EXAMPLE_CHANNEL_ID = "type1";
    @Override
    public void onReceive(Context context, Intent intent) {
        try{
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            if(state.equalsIgnoreCase(TelephonyManager.EXTRA_STATE_IDLE)) {
                Toast.makeText(context, "Phone call", Toast.LENGTH_SHORT).show();
                phoneCallNotification();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //phoneCallNotification();
    }

    public void phoneCallNotification() {
        HomeFragment homeFragment = new HomeFragment();

        Context context = homeFragment.getContext();
        if(context == null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (notificationManager != null && notificationManager.getNotificationChannel(EXAMPLE_CHANNEL_ID) == null) {
                    NotificationChannel notificationChannel = new NotificationChannel(EXAMPLE_CHANNEL_ID, "Phone call notification", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationChannel.setDescription("You just received a call");
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, EXAMPLE_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                    .setContentTitle("Phone call received")
                    .setContentText("Do you want to open the app?")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .addAction(0, "Open App", pendingIntent)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            Notification notification = builder.build();
            notificationManager.notify(1, notification);
        }
    }
}

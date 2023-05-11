package com.example.mob_dev_portfolio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.databinding.ActivityMainBinding;
import com.example.mob_dev_portfolio.fragments.BlockFragment;
import com.example.mob_dev_portfolio.fragments.HomeFragment;
import com.example.mob_dev_portfolio.fragments.ReportListFragment;
import com.example.mob_dev_portfolio.fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {
    public static final String EXAMPLE_CHANNEL_ID = "type1";

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        replaceFragment(new HomeFragment());
        checkPermission();

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.block:
                    replaceFragment(new BlockFragment());
                    break;
                case R.id.search:
                    replaceFragment(new SearchFragment());
                    break;
                case R.id.report:
                    replaceFragment(new ReportListFragment());
                    break;
            }
            return true;

        });
        phoneCallNotification();
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void checkPermission() {
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_PHONE_STATE)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.READ_PHONE_STATE}, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No permission granted", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    private void phoneCallNotification () {
        Intent intent = new Intent(this, MainActivity.class);
        DetectPhoneCall detectPhoneCall = new DetectPhoneCall();
        //detectPhoneCall.onReceive(binding.getRoot().getContext(), intent);
        Context context = getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if(notificationManager != null && notificationManager.getNotificationChannel(EXAMPLE_CHANNEL_ID) == null) {
                NotificationChannel notificationChannel = new NotificationChannel(EXAMPLE_CHANNEL_ID, "Phone call notification", NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.setDescription("You just received a call");
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        //Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_IMMUTABLE);

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


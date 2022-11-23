package com.moutamid.car_gps_app.Service;

import static android.os.Build.VERSION_CODES.R;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.moutamid.car_gps_app.MainActivity;
import com.moutamid.car_gps_app.R;
import com.moutamid.car_gps_app.model.CarDetails;

import java.util.ArrayList;
import java.util.Date;
public class ListenNotification extends Service {
    DatabaseReference notificationDB;
    String channelId = "Channel_id";

    public ListenNotification() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationDB = FirebaseDatabase.getInstance().getReference("Car");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Query query = notificationDB.orderByChild("status").equalTo("moving");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds : snapshot.getChildren()){
                        CarDetails model = ds.getValue(CarDetails.class);
                        showNotification(model);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //notificationArrayList.clear();
        return super.onStartCommand(intent, flags, startId);
    }


    PendingIntent notifyPendingIntent;
    Intent intent;
    private void showNotification(CarDetails info) {
        intent = new Intent(getBaseContext(), MainActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notifyPendingIntent = PendingIntent.getActivity(getBaseContext(),
                    0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        }else {
            notifyPendingIntent = PendingIntent.getActivity(getBaseContext(),
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        }
        createNotificationChannel(this);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
                .setContentTitle("Vehicle is MOVING")
                .setContentText("Vehicle " + info.getCar() + " is turned on.")
                /*.setLargeIcon(largeIcon)*/
                .setSmallIcon(com.moutamid.car_gps_app.R.mipmap.ic_launcher_round) //needs white icon with transparent BG (For all platforms)
                .setContentIntent(notifyPendingIntent)
                .setPriority(Notification.PRIORITY_HIGH)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1 , notificationBuilder.build());

    }

    public void createNotificationChannel(Context context) {

        // NotificationChannels are required for Notifications on O (API 26) and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // The id of the channel.
            // The user-visible name of the channel.
            CharSequence channelName = "Grube";
            // The user-visible description of the channel.
            String channelDescription = "Grubu Alert";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;
            boolean channelEnableVibrate = true;
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();
            // Initializes NotificationChannel.
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            notificationChannel.setDescription(channelDescription);
            notificationChannel.enableVibration(channelEnableVibrate);

//            notificationChannel.setSound(soundUri, audioAttributes);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}

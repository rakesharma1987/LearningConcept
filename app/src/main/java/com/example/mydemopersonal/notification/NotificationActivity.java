package com.example.mydemopersonal.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.RemoteInput;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mydemopersonal.R;

public class NotificationActivity extends AppCompatActivity {
    private String channelId = "com.example.mydemopersonal.notification";
    private NotificationManager notificationManager;
    private Button button;
    private String keyReply = "keyReply";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        button = findViewById(R.id.notification);
        createNotificationChennel(channelId, "Demo", "This is Demo");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNotification();
            }
        });

    }

    private void displayNotification(){
        Intent intent = new Intent(NotificationActivity.this, DetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notification_data", "Here is the discription");
        PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // set Action 1
        Intent intent1 = new Intent(NotificationActivity.this, ActionTapActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notification_data", "Here is the discription");
        PendingIntent pendingIntent1 = PendingIntent.getActivity(NotificationActivity.this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);

        // set action 2
        Intent intent2 = new Intent(NotificationActivity.this, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notification_data", "Here is the discription");
        PendingIntent pendingIntent2 = PendingIntent.getActivity(NotificationActivity.this, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent intentReply = new Intent(NotificationActivity.this, ReceiveReplyActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra("notification_data", "Here is the discription");
        PendingIntent replyPendeingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intentReply, PendingIntent.FLAG_UPDATE_CURRENT);

        //Reply action
        RemoteInput remoteInput = new RemoteInput.Builder(keyReply)
                .setLabel("Enter your name")
                .build();
        NotificationCompat.Action replyAction =
                new NotificationCompat.Action.Builder(0,
                        "Reply", replyPendeingIntent)
                        .addRemoteInput(remoteInput)
                        .build();

        int notificationId = 45;
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelId);
        notification.setContentTitle("MyDemo");
        notification.setContentText("This is demo content, This is demo content,This is demo content, This is demo content, This is demo content");
        notification.setSmallIcon(android.R.drawable.ic_btn_speak_now);
        notification.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        notification.setAutoCancel(true);
        notification.setContentIntent(pendingIntent);
        notification.addAction(R.drawable.ic_alarm, "Details", pendingIntent1);
        notification.addAction(0, "Settings", pendingIntent2);
        notification.addAction(replyAction);
        notificationManager.notify(notificationId, notification.build());

    }

    private void createNotificationChennel(String id, String name, String chennelDiscription){
        int importance = NotificationManager.IMPORTANCE_HIGH;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(id, name, importance);
            channel.setDescription(chennelDiscription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }
}
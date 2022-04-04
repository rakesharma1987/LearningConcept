package com.example.mydemopersonal.workManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DemoWorker extends Worker {
    public DemoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
//        for (int i=0; i<=100000; i++){
//            Log.d("MYTAG:", "Count is "+i);
//        }
        Data data = getInputData();
        int data1 = data.getInt("key", 0);
        for (int i=0; i<=data1; i++){
            Log.d("MYTAG:", "Count is "+i);
            displayNotification("Notification", i);
        }
        return Result.success();
    }

    private void displayNotification(String title, int count){
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channelId", "channelName", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "channelId")
                .setContentTitle(title)
                .setContentText(String.valueOf(count))
                .setSmallIcon(android.R.drawable.star_on);

        notificationManager.notify(101, builder.build());

    }
}

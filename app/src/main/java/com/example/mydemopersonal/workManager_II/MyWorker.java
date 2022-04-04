package com.example.mydemopersonal.workManager_II;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.mydemopersonal.R;

public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.d("WORKER:", "Start great work!");

        String data1 = getInputData().getString("_data"); //10. Receivinh data from activiity
        Log.d("WORKER", data1);

        Data data = new Data.Builder().putString("_data", "Hello from do work!").build(); // 5. added Data to Result
//        return Result.success();
        //16. adding notification
        displayNotification(data1, data.getString("_data"));
        return Result.success(data); //6. set data to Result
    }

    private void displayNotification(String title, String message){
        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channelId", "channelName", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "channelId")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(android.R.drawable.star_on);

        notificationManager.notify(101, builder.build());

    }
}

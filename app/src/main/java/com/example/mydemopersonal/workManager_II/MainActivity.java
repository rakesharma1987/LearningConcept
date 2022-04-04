package com.example.mydemopersonal.workManager_II;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.os.Bundle;
import android.view.View;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityMain5Binding;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private ActivityMain5Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain5Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnStartWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 14. PeriodicWorkRequest
                WorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 10, TimeUnit.SECONDS).build();

                //3. Adding Constraint to the workManager
                Constraints constraints = new Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build();

                //8. Passing Data from activity
                Data data = new Data.Builder()
                        .putString("_data", "Msg from activity").build();

                // 2. Creating the object of WorkRequest
                WorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setConstraints(constraints) // 4. add constraints to the workRequest
                        .setInputData(data) //9. Passing data
//                        .addTag("MyWork") // 12. adding tag to cancel work
                        .build();
                // 1. Adding work request to the que
                WorkManager.getInstance(getApplicationContext()).enqueue(oneTimeWorkRequest);

                //15 Enque work request

//                WorkManager.getInstance(getApplicationContext()).enqueue(periodicWorkRequest);

                // 7. getting output from doWork
                WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId())
                        .observe(MainActivity.this, new Observer<WorkInfo>() {
                            @Override
                            public void onChanged(WorkInfo workInfo) {
                                if (workInfo.getState() == WorkInfo.State.SUCCEEDED){
                                    binding.tvResult.setText(workInfo.getOutputData().getString("_data")); // Receiveing data from worker
                                }
                            }
                        });

                //13. calling cancelwork
//                cancelWork(workRequest);
            }
        });
    }

    private void cancelWork(WorkRequest workRequest){
//        WorkManager.getInstance(getApplicationContext()).cancelAllWork();
//        WorkManager.getInstance(getApplicationContext()).cancelAllWorkByTag("MyWork"); // 11. Cancel work by Tag
        WorkManager.getInstance(getApplicationContext()).cancelWorkById(workRequest.getId()); // 12 cancel work by Id
    }
}
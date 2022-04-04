package com.example.mydemopersonal.workManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityMain4Binding;

public class MainActivity extends AppCompatActivity {
    ActivityMain4Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        Constraints constraints = new Constraints.Builder().setRequiresCharging(true).build();

        Data data = new Data.Builder()
                .putInt("key", 10)
                .build();

//        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest
//                .Builder(DemoWorker.class)
//                .setConstraints(constraints)
//                .setInputData(data)
//                .build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest
                .Builder(DemoWorker.class)
                .setInputData(data)
                .build();

        WorkManager.getInstance(this).enqueue(oneTimeWorkRequest);

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                binding.tvResult.setText(workInfo.getState().name());
            }
        });
    }
}
package com.example.mydemopersonal.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityReceiveReplyBinding;

public class ReceiveReplyActivity extends AppCompatActivity {
    ActivityReceiveReplyBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_receive_reply);
        receiveInput();
    }

    private void receiveInput(){
        String keyReply = "keyReply";
        Intent intent = getIntent();
        Bundle bundle = RemoteInput.getResultsFromIntent(intent);
        String remoteInput = bundle.getCharSequence(keyReply).toString();
        binding.tvReceiveReply.setText(remoteInput);
    }
}
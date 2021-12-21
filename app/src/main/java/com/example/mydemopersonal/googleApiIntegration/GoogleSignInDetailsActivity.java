package com.example.mydemopersonal.googleApiIntegration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityGoogleSignInDetailsBinding;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class GoogleSignInDetailsActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    ActivityGoogleSignInDetailsBinding binding;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGoogleSignInDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        Intent intent = getIntent();
        binding.txtName.setText(intent.getStringExtra("name"));
        binding.txtEmail.setText(intent.getStringExtra("email"));
        Glide.with(getApplicationContext())
                .load(intent.getStringExtra("photoUrl"))
                .thumbnail(0.5f)
                .into(binding.imgProfilePic);
        binding.btnLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.getStatus().isSuccess()) {
                    finish();
                }else {
                    Toast.makeText(GoogleSignInDetailsActivity.this, "Not able to Loggedout", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
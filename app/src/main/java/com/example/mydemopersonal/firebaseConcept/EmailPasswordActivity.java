package com.example.mydemopersonal.firebaseConcept;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityEmailPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private ActivityEmailPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        binding.btnSignIn.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //  // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            Intent intent = new Intent(EmailPasswordActivity.this, WelcomeActivity.class);
            intent.putExtra("_email", user.getEmail());
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        firebaseAuth.signInWithEmailAndPassword(binding.etEmail.getText().toString(), binding.etPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(EmailPasswordActivity.this, user.getEmail(), Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(EmailPasswordActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}


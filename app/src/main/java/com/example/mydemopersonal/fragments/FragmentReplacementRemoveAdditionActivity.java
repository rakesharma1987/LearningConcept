package com.example.mydemopersonal.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.mydemopersonal.R;
import com.example.mydemopersonal.databinding.ActivityFragmentReplacementRemoveAdditionBinding;

public class FragmentReplacementRemoveAdditionActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityFragmentReplacementRemoveAdditionBinding binding;
    FragmentManager fragmentManager;
    private static String TAG = "_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFragmentReplacementRemoveAdditionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnAdd.setOnClickListener(this);
        binding.btnRemove.setOnClickListener(this);
        binding.btnReplace.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.fragment_layout, new Fragment1(), TAG);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case R.id.btn_remove:

                for (Fragment fragment : fragmentManager.getFragments()){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                break;

            case R.id.btn_replace:
                FragmentTransaction transaction1 = fragmentManager.beginTransaction();
                transaction1.setCustomAnimations(R.anim.slide_in, R.anim.slide_out, R.anim.fade_in, R.anim.fade_out);
                transaction1.replace(R.id.fragment_layout, new Fragment1(), TAG);
                transaction1.addToBackStack(null);
                transaction1.commit();
                break;
        }
    }
}
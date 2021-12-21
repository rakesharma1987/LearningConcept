package com.example.mydemopersonal.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mydemopersonal.R;

public class FragmentA extends Fragment {
    private TextView textView;
    private OnInputListener onInputListener;

    public FragmentA() {
        // Required empty public constructor
    }

    public interface OnInputListener{
        void input(String input);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        EditText editText = view.findViewById(R.id.etInput);
        Button button = view.findViewById(R.id.btnSend);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInputListener.input(editText.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        onInputListener = (OnInputListener) activity;

    }
}
package com.example.mydemopersonal.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mydemopersonal.R;

public class FragmentDisplay extends Fragment {

    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_display, container, false);
        textView = view.findViewById(R.id.tvDisplay);
        Bundle bundle = getArguments();
        String msg = bundle.getString("message");
        textView.setText(msg);
        return view;
    }
}

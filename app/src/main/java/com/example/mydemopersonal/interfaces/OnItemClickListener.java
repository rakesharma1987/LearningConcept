package com.example.mydemopersonal.interfaces;

import com.example.mydemopersonal.model.Persons;

import java.util.List;

public interface OnItemClickListener {
    void onItemClick(List<Persons> personsList, int position);
}
